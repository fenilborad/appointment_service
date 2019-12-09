package com.appointment.exceptions;

public class HeaderMissingException extends RuntimeException {
    public HeaderMissingException(String message) {
        super(message);
    }
}
