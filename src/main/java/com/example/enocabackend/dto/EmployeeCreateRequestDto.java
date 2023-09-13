package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Department;
import com.example.enocabackend.entities.Employee;


import lombok.Data;

@Data
public class EmployeeCreateRequestDto {
	
	private String name;
	private String surname;
	private int salary;
	private String email;
	private String password;
	private Department department;
	
	public void mapEmployeeCreateRequestDto(Employee employee) {
		employee.setName(this.getName());
		employee.setSurname(this.getSurname());
		employee.setSalary(this.getSalary());
		employee.setEmail(this.getEmail());
		employee.setPassword(this.getPassword());
		employee.setDepartment(this.department);
		
	}

}
