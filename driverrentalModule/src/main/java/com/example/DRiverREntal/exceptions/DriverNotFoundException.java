package com.example.DRiverREntal.exceptions;
//Custom exception for handling "Driver Not Found" scenarios.
public class DriverNotFoundException extends RuntimeException {
	 // Constructor to initialize the exception with a custom message.
    public DriverNotFoundException(String message) {
        super(message);// Call the superclass (RuntimeException) constructor with the provided message.
    }
}
