package com.inditex.prices.exceptions;

import com.inditex.prices.application.prices.PricesServiceImpl;
import com.inditex.prices.application.prices.exceptions.NoPricesFoundException;
import com.inditex.prices.application.prices.exceptions.dto.ErrorResponse;
import com.inditex.prices.infraestructure.prices.repository.PricesRepository;
import com.inditex.prices.infraestructure.prices.rest.PriceExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PricesExceptionsTest {
    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    PricesServiceImpl pricesService;

    @InjectMocks
    private PriceExceptionHandler priceExceptionHandler;



    @Test
    public void testNoPricesFoundExceptionHandling() {
        when(pricesRepository.findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                LocalDateTime.now(), 1L, 1L)).thenReturn(Collections.emptyList());

        NoPricesFoundException e = assertThrows(NoPricesFoundException.class,
                () -> pricesService.getPrices(LocalDateTime.now(), 1L, 1L));


        ResponseEntity<ErrorResponse> responseEntity = priceExceptionHandler.handleNoPricesFoundException(e);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No prices found for the provided date, product, and brand.", responseEntity.getBody().getMessage());
    }
}
