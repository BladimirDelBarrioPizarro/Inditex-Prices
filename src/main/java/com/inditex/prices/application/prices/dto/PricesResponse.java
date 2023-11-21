package com.inditex.prices.application.prices.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PricesResponse {
    private Long productId;
    private Long brandId;
    private Integer fee;
    private String startDate;
    private String endDate;
    private BigDecimal finalPrice;
}
