package com.wf.cloud.controller;

import com.wf.cloud.common.domain.PaymentDTO;
import com.wf.cloud.feign.PaymentFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private PaymentFeign paymentFeign;

    @PostMapping("/order/pay")
    public PaymentDTO payOrder( PaymentDTO paymentDTO) {
        log.info("create order start");
        PaymentDTO payResult = paymentFeign.pay(paymentDTO);
        log.info("create order finish");
        return payResult;

    }
}
