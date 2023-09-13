package com.example.enocabackend.controller.admin;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/admin/employee")
public class AdminEmployeeController {
	
	private EmployeeServiceImpl employeeService;
	
	public AdminEmployeeController(EmployeeServiceImpl employeeService) {
		this.employeeService = employeeService;
	}
	 
	@PostMapping("/createEmployee")
	public Employee createOneEmployee(@RequestBody EmployeeCreateRequestDto newEmployeeRequest) {
		return employeeService.createOneEmploye(newEmployeeRequest);
	}
	
	@GetMapping("/listEmployee")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/getEmployee/{employeeId}")
	public Employee getOneEmployee(@PathVariable Long employeeId) {
		return employeeService.getOneEmployeeById(employeeId);
	}
	
	@PutMapping("/updateEmployee/{employeeId}")
	public Employee updateOneEmployee(@PathVariable Long employeeId, @RequestBody EmployeeUpdateRequestDto updateEmployeeRequest) {
		return employeeService.updateOneEmployeeById(employeeId, updateEmployeeRequest);
	}
	
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public void deleteOneEmployee(@PathVariable Long employeeId) {
		employeeService.deleteOneEmployeeById(employeeId);
	}
		
	
	
	

}
