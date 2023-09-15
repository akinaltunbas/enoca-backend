package com.example.enocabackend.controller.admin;

import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.dto.constants.ResponseMessages;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.services.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/employee")
public class AdminEmployeeController {
private EmployeeServiceImpl employeeService;
	
	public AdminEmployeeController(EmployeeServiceImpl employeeService) {
		this.employeeService = employeeService;
	}
	 
	@PostMapping("/createEmployee")
	public ResponseEntity<String> createOneEmployee(@RequestBody EmployeeCreateRequestDto newEmployeeRequest) {
		 employeeService.createOneEmployee(newEmployeeRequest);
		 return ResponseEntity.ok(ResponseMessages.EMPLOYEE_CREATED.getMessage());
	}
	
	@GetMapping("/listEmployee")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK) ;
	}
	
	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Long employeeId) {
		return new ResponseEntity<>(employeeService.getOneEmployeeById(employeeId),HttpStatus.OK);
	}
	
	@PutMapping("/updateEmployee/{employeeId}")
	public ResponseEntity<String> updateOneEmployee(@PathVariable Long employeeId, @RequestBody EmployeeUpdateRequestDto updateEmployeeRequest) {
		 employeeService.updateOneEmployeeById(employeeId, updateEmployeeRequest);
		return ResponseEntity.ok(ResponseMessages.EMPLOYEE_UPDATED.getMessage());
	}
	
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<String> deleteOneEmployee(@PathVariable Long employeeId) {
		employeeService.deleteOneEmployeeById(employeeId);
		return ResponseEntity.ok(ResponseMessages.EMPLOYEE_DELETED.getMessage());
	}

}