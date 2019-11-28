package com.appointment.exceptions;


public class EntitySaveFailureException extends RuntimeException {
    public EntitySaveFailureException(String message) {
        super(message);
    }
}
