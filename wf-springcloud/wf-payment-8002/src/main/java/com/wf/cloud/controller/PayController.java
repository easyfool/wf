package com.wf.cloud.controller;

import cn.hutool.core.lang.UUID;
import com.wf.cloud.common.domain.CommonResponse;
import com.wf.cloud.common.domain.PaymentDTO;
import com.wf.cloud.common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PayController {
    @PostMapping("/payment/pay")
    public CommonResponse<PaymentDTO> pay(@RequestBody PaymentDTO paymentDTO) {
        log.info("pay order start");
        log.info("pay order finish");
        return CommonResponse.fail(ErrorCode.BUSINESS_ERROR, paymentDTO);
    }
}
