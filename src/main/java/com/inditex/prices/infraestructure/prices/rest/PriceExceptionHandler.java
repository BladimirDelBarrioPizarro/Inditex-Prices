package com.inditex.prices.infraestructure.prices.rest;

import com.inditex.prices.application.prices.exceptions.NoPricesFoundException;
import com.inditex.prices.application.prices.exceptions.PricesServiceException;
import com.inditex.prices.application.prices.exceptions.dto.ErrorResponse;
import com.inditex.prices.application.prices.map.ErrorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class PriceExceptionHandler {
    @ExceptionHandler(NoPricesFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoPricesFoundException(NoPricesFoundException ex) {
        ErrorResponse errorResponse = ErrorMapper.mapError(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PricesServiceException.class)
    public ResponseEntity<ErrorResponse> handleNoPricesServiceException(PricesServiceException ex) {
        ErrorResponse errorResponse = ErrorMapper.mapError(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
