package io.umid.supportservice.controller;

import io.umid.supportservice.exception.NotAllowedException;
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
}
