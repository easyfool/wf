package com.wf.cloud.common.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private String orderNo;
    private BigDecimal amt;
}
