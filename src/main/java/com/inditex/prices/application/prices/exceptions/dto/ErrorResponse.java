package com.inditex.prices.application.prices.exceptions.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String url;
    private String message;
    private String method;
    private String cause;
    private Timestamp date;
}


