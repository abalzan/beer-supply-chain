package com.andrei.beer.service.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> errorHandlerConstraintValidation(ConstraintViolationException ex) {
        final var errorList = ex.getConstraintViolations().stream().map(ConstraintViolation::toString).collect(Collectors.toList());
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> errorHandlerBindException(BindException e) {
        return new ResponseEntity<>(e.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
