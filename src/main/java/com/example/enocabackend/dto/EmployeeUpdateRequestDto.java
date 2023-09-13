package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Department;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.entities.Role;

import lombok.Data;

@Data
public class EmployeeUpdateRequestDto {
	
	private String name;
	private String surname;
	private int salary;
	private String email;
	private String password;
	private Department department;
	
	public void mapEmployeeUpdateRequestDto(Employee employee) {
		employee.setName(this.getName());
		employee.setSurname(this.getSurname());
		employee.setSalary(this.getSalary());
		employee.setEmail(this.getEmail());
		employee.setPassword(this.getPassword());
		employee.setDepartment(this.department);
		
		
	}

}
