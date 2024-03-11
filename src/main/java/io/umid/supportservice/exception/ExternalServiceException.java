package io.umid.supportservice.exception;

public class ExternalServiceException extends RuntimeException {

    public ExternalServiceException() {
    }

    public ExternalServiceException(String message) {
        super(message);
    }
}
