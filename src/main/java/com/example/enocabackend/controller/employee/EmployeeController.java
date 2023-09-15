package com.example.enocabackend.controller.employee;

import com.example.enocabackend.dto.constants.ResponseMessages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocabackend.dto.EmployeeUpdateProfileRequestDto;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeServiceImpl employeeService;
	
	public EmployeeController(EmployeeServiceImpl employeeService) {
		this.employeeService = employeeService;
	}
	
	@PutMapping("/updateProfile/{employeeId}")
	public ResponseEntity<String> updateprofile(@PathVariable Long employeeId, @RequestBody EmployeeUpdateProfileRequestDto updateProfileRequest) {
		 employeeService.updateEmployeeProfile(employeeId, updateProfileRequest);
		 return ResponseEntity.ok(ResponseMessages.EMPLOYEE_UPDATED.getMessage());
	}
}