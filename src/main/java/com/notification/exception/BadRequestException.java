package com.notification.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message, Exception exception) {
        super(message, exception);
    }
}
