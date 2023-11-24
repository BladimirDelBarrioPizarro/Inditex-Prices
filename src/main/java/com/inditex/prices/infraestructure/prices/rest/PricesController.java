package com.inditex.prices.infraestructure.prices.rest;

import com.inditex.prices.application.prices.PricesService;
import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.infraestructure.prices.rest.datetime.CustomLocalDateTimeEditor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/api")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeEditor(
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
        ));
    }

    @GetMapping("/prices")
    public PricesResponse getPrices(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        return pricesService.getPrices(date, productId, brandId);
    }
}
