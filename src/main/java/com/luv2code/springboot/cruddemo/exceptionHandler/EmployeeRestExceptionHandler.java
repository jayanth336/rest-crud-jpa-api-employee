package com.luv2code.springboot.cruddemo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class EmployeeRestExceptionHandler {
    //Exception handler for EmployeeNotFound exception
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exception) {
        EmployeeErrorResponse response = new EmployeeErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(LocalTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Exception handler for any other bad requests
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exception) {
        EmployeeErrorResponse response = new EmployeeErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(LocalTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}