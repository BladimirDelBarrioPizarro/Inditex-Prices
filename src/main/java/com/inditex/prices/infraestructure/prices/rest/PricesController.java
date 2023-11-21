package com.inditex.prices.infraestructure.prices.rest;

import com.inditex.prices.application.prices.PricesService;
import com.inditex.prices.application.prices.dto.PricesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping("/prices")
    public PricesResponse getPrices(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        return pricesService.getPrices(date, productId, brandId);
    }
}
