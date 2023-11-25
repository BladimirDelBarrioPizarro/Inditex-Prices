package com.inditex.prices.application.prices.exceptions;

public class NoPricesFoundException extends RuntimeException {
    public NoPricesFoundException(String message) {
        super(message);
    }
}
