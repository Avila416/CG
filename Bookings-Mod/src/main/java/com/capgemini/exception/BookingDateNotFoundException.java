package com.capgemini.exception;

public class BookingDateNotFoundException extends RuntimeException {
	public BookingDateNotFoundException(String message) {
		super(message);
	}

}
