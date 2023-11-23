package com.inditex.prices.application.prices.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    NO_PRICES_FOUND("No prices found for the provided date, product, and brand.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
