package com.example.DRiverREntal.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//Global exception handler to manage application-wide exceptions
@ControllerAdvice
public class GlobalExceptionHandler {
	// Handles validation errors and returns them in a structured format
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
     // Extracting field errors and adding to the error map
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();// Get the invalid field name
            String errorMessage = error.getDefaultMessage();// Get the error message
            errors.put(fieldName, errorMessage);// Add field and message to the map
        });
     // Return the error map with a BAD_REQUEST (400) status
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
 // Handles cases where a driver is not found
    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDrivereNotFoundException(DriverNotFoundException ex) {
    	 // Return the exception message with a NOT_FOUND (404) status
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}