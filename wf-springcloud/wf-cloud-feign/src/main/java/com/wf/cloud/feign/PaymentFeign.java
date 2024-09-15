package com.wf.cloud.feign;

import com.wf.cloud.common.domain.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENT-SERVER")
public interface PaymentFeign {

    @PostMapping("/payment/pay")
    PaymentDTO pay(PaymentDTO paymentDTO);
}
