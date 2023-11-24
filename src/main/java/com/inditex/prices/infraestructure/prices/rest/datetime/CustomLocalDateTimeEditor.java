package com.inditex.prices.infraestructure.prices.rest.datetime;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeEditor extends PropertyEditorSupport {
    private final DateTimeFormatter[] formatters;

    public CustomLocalDateTimeEditor(DateTimeFormatter... formatters) {
        this.formatters = formatters;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime date = LocalDateTime.parse(text, formatter);
                setValue(date);
                return;
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + text);
    }
}
