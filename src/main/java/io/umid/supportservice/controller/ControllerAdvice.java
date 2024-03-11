package io.umid.supportservice.controller;

import io.umid.supportservice.exception.ExternalServiceException;
import io.umid.supportservice.exception.NotAllowedException;
import io.umid.supportservice.exception.PhoneNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(NotAllowedException.class)
    public ErrorResponse handleNotAllowed(NotAllowedException e) {
        log.warn("Handled NotAllowedException: {}", e.getMessage());
        return new ErrorResponseException(HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(PhoneNumberException.class)
    public ErrorResponse handlePhoneNumberInvalid(PhoneNumberException e) {
        log.warn("Handled PhoneNumberException: {}", e.getMessage());
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ErrorResponse handleExternalService(ExternalServiceException e) {
        log.warn("Handled ExternalServiceException: {}", e.getMessage());
        return new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
}
