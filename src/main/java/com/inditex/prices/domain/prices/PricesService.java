package com.inditex.prices.domain.prices;

import java.time.LocalDateTime;

public interface PricesService {
    Prices getPrices(LocalDateTime date, Long productId, Long brandId);
}
