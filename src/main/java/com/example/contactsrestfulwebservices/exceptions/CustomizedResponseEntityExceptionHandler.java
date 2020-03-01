package com.example.contactsrestfulwebservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// this class can be used by different controller class ex. ContactsApiController
@ControllerAdvice // makes it available or shares across multiple controller classes
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity handleAllException(Exception ex, WebRequest request) throws java.lang.Exception {

		// whenever the exception occurs, we want to create our specific bean. i.e for all exceptions we return Internal Server Error
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	// To define our own user exception
	@ExceptionHandler(ContactNotFoundException.class)
	public final ResponseEntity<Object> handleContactNoFoundException(ContactNotFoundException ex, WebRequest request) throws java.lang.Exception {

		// Customize exception for specific Exception to return different status back
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	// Error message when validation fails for First Name, Last Name and email
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Field is invalid", ex.getBindingResult().toString());

		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
