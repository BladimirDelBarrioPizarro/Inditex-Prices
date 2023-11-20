package com.inditex.prices.application.prices.map;

import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.domain.prices.Price;

public class PricesMapper {
    public static PricesResponse mapPricesToPricesResponse(Price price){
        return PricesResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .fee(price.getPriceList())
               // .finalPrice()
                .endDate(price.getEndDate())
                .startDate(price.getStartDate())
                .finalPrice(price.getPrice())
                .build();
    }
}
