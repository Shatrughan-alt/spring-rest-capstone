package com.exception;


// Custom exception class for handling runtime errors
public class CustomException extends RuntimeException {
	public CustomException(String message){
		super(message);
	}
	
}
