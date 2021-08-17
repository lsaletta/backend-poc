package com.backend.poc.controller.handler;


import com.backend.poc.exception.BackendException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BackendException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorMessage handlerException(BackendException ex) {
        ErrorMessage message = new ErrorMessage(ex.getErrorDescription());
        return message;
    }
}
