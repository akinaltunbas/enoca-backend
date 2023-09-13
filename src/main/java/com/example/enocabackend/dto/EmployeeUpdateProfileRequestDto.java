package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Employee;

import lombok.Data;

@Data
public class EmployeeUpdateProfileRequestDto {
	
	private String name;
	private String surname;
	private String email;
	private String password;
	
	public void mapEmployeeUpdateProfileRequestDto(Employee employee) {
		employee.setName(this.getName());
		employee.setSurname(this.getSurname());
		employee.setEmail(this.getEmail());
		employee.setPassword(this.getPassword());
	}


}
