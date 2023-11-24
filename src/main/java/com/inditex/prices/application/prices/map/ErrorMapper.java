package com.inditex.prices.application.prices.map;

import com.inditex.prices.application.prices.exceptions.ErrorMessage;
import com.inditex.prices.application.prices.exceptions.NoPricesFoundException;
import com.inditex.prices.application.prices.exceptions.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

public class ErrorMapper {
    public static ErrorResponse mapError(Exception ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return ErrorResponse.builder()
                .date(Timestamp.from(Instant.now()))
                .message(ErrorMessage.NO_PRICES_FOUND.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .url(request.getRequestURI())
                .build();
    }
}
