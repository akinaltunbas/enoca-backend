package com.example.enocabackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

class ExceptionHandlerController extends ResponseEntityExceptionHandler  {
	
	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<String> handleAllExceptions(Exception e) {
	       return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }


}
