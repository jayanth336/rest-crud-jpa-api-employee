package com.luv2code.springboot.cruddemo.exceptionHandler;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
