package com.empik.recruitment.repositorysearch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(
            value=HttpStatus.NOT_FOUND,
            reason="Resource not found")
    public void handleResourceNotFound() {
    }
}
