package com.example.practice.controllers;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(reason = "The person with the id not found",value = HttpStatus.NOT_FOUND)
public class ExceptionHandler extends RuntimeException{
    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionHandler() {
        super();
    }

    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(Throwable cause) {
        super(cause);
    }

    protected ExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
