package com.jz.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {HttpClientErrorException.class})
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
		ApiError apiError = new ApiError(ex.getStatusText(), "Resource not found. Check URL.");
		return new ResponseEntity<>(apiError, ex.getStatusCode());
	}

}

