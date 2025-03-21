package com.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Global exception handler to handle exceptions occurring in the application.
 * This class provides centralized exception handling for different types of errors.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Handles custom exceptions
	@ExceptionHandler(CustomException.class)
	public String handleCustomException(CustomException ex) {
		return ex.getMessage();
	}
	
	// Handles validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			details.add(errorMessage);
		});		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}
}
