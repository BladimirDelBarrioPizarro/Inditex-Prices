package com.inditex.prices.infraestructure.prices.rest;

import com.inditex.prices.application.prices.PricesService;
import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.infraestructure.prices.rest.dateutils.CustomLocalDateTimeEditor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Prices", description = "Endpoints for managing prices")
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
    @Operation(
            summary = "Get Prices",
            description = "Retrieve prices for a specific date, product, and brand."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success in obtaining prices"
    )
    public PricesResponse getPrices(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        return pricesService.getPrices(date, productId, brandId);
    }
}
