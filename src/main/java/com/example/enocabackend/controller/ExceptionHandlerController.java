package com.example.enocabackend.controller;

import com.example.enocabackend.exception.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class ExceptionHandlerController extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(InternalException.class)
	public final ResponseEntity<String> handleInternalExceptions(InternalException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<String> handleAllExceptions(Exception e) {
	       return new ResponseEntity<>("Error Happened, Our teams have been warned about it", HttpStatus.INTERNAL_SERVER_ERROR);
	  }

}