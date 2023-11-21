package com.inditex.prices.application.prices;

import com.inditex.prices.application.prices.dto.PricesResponse;

import java.time.LocalDateTime;


public interface PricesService {
    PricesResponse getPrices(LocalDateTime date, Long productId, Long brandId);
}
