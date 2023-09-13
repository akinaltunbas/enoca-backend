package com.example.enocabackend.controller.employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocabackend.entities.Department;
import com.example.enocabackend.services.DepartmentServiceImpl;

@RestController
@RequestMapping("/employee/department")
public class EmployeDepartmentController {
	
	private DepartmentServiceImpl departmentService;
	
	public EmployeDepartmentController(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/listDepartment") 
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
}
