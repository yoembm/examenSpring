package com.example.examenspring.exception;

public class ExternalServiceException extends RuntimeException {

    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(String message, Exception e) {
        super(message, e);
    }

}
