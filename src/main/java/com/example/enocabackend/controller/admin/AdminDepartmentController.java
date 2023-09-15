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

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.entities.Department;
import com.example.enocabackend.services.DepartmentServiceImpl;

@RestController
@RequestMapping("/admin/department")
public class AdminDepartmentController {
	
	private DepartmentServiceImpl departmentService;
	
	public AdminDepartmentController(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	@PostMapping("/createDepartment")
	public Department createOneDepartment(@RequestBody DepartmentCreateRequestDto newDepartmentRequest) {
		return departmentService.createOneDepartment(newDepartmentRequest);
	}
	
	@GetMapping("/listDepartment")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	@GetMapping("/getDepartment/{departmentId}")
	public Department getOneDepartment(@PathVariable Long departmentId) {
		return departmentService.getDepartmentById(departmentId);
	}
	
	@PutMapping("/updateDepartment/{departmentId}") 
	public Department updateOneDepartment(@PathVariable Long departmentId, @RequestBody DepartmentUpdateRequestDto updateDepartmentRequest) {
		return departmentService.updateOneDepartmentById(departmentId, updateDepartmentRequest);
	}
	
	@DeleteMapping("/deleteDepartment/{departmentId}")
	public void deleteOneDepartment(@PathVariable Long departmentId) {
		 departmentService.deleteOneDepartmentById(departmentId);
	}
	
	
}
