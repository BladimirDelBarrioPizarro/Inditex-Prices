package com.inditex.prices.application.prices;

import com.inditex.prices.application.prices.dto.PricesResponse;

import java.sql.Timestamp;


public interface PricesService {
    PricesResponse getPrices(String date, Long productId, Long brandId);
}
