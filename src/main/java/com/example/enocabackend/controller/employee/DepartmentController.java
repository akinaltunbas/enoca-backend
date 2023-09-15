package com.example.enocabackend.controller.employee;

import com.example.enocabackend.entities.Department;
import com.example.enocabackend.services.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee/department")
public class DepartmentController {
	
	private DepartmentServiceImpl departmentService;
	
	public DepartmentController(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/listDepartment") 
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}
}