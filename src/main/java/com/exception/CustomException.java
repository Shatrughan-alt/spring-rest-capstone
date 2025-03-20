package com.exception;


/**
 * Custom exception class for handling application-specific runtime errors.
 */
public class CustomException extends RuntimeException {
	
	/**
     * Constructor to create a new CustomException with a specific error message.
     * 
     * @param message The detail message explaining the cause of the exception.
     */
	public CustomException(String message){
		super(message);
	}
	
}
