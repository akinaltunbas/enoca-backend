package com.example.enocabackend.exception;

public class EmployeeNotFoundException  extends InternalException {
	
    public EmployeeNotFoundException(final Long id) {
        super(String.format("Employee not found with id : %s", id.toString()));
    }
}