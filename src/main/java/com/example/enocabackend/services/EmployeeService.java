package com.example.enocabackend.services;

import java.util.List;

import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateProfileRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.entities.Employee;

public interface EmployeeService {
	
	public Employee createOneEmployee(EmployeeCreateRequestDto newEmployeeRequest);
	
	public List<Employee> getAllEmployees();
	
	public Employee getOneEmployeeById(Long employeeId);
	
	public Employee updateOneEmployeeById(Long employeeId, EmployeeUpdateRequestDto updateEmployeeRequest);
	
	public Employee updateEmployeeProfile(Long employeeId, EmployeeUpdateProfileRequestDto updateProfileEmployee);
	
	public void deleteOneEmployeeById(Long departmentId);
		
	

}
