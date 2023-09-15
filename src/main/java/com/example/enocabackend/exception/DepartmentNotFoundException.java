package com.example.enocabackend.exception;

public class DepartmentNotFoundException extends RuntimeException {
	
	public DepartmentNotFoundException(final Long id) {
        super(String.format("Department not found with id : %s", id.toString()));
    }

}
