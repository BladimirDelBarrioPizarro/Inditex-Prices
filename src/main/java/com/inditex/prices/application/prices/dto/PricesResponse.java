package com.inditex.prices.application.prices.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class PricesResponse {
    private Long productId;
    private Long brandId;
    private Integer fee;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal finalPrice;
}
