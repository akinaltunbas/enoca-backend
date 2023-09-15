package com.example.enocabackend.controller.admin;

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.dto.constants.ResponseMessages;
import com.example.enocabackend.entities.Department;
import com.example.enocabackend.services.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/department")
public class AdminDepartmentController {
	
	private DepartmentServiceImpl departmentService;
	
	public AdminDepartmentController(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/createDepartment")
	public ResponseEntity<String> createOneDepartment(@RequestBody DepartmentCreateRequestDto newDepartmentRequest) {
		departmentService.createOneDepartment(newDepartmentRequest);
		return ResponseEntity.ok(ResponseMessages.DEPARTMENT_CREATED.getMessage());
	}
	
	@GetMapping("/listDepartment")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}
	
	@GetMapping("/getDepartment/{departmentId}")
	public ResponseEntity<Department> getOneDepartment(@PathVariable Long departmentId) {
		return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}
	
	@PutMapping("/updateDepartment/{departmentId}") 
	public ResponseEntity<String> updateOneDepartment(@PathVariable Long departmentId, @RequestBody DepartmentUpdateRequestDto updateDepartmentRequest) {
		departmentService.updateOneDepartmentById(departmentId, updateDepartmentRequest);
		return ResponseEntity.ok(ResponseMessages.DEPARTMENT_UPDATED.getMessage());
	}
	
	@DeleteMapping("/deleteDepartment/{departmentId}")
	public ResponseEntity<String> deleteOneDepartment(@PathVariable Long departmentId) {
		 departmentService.deleteOneDepartmentById(departmentId);
		return ResponseEntity.ok(ResponseMessages.DEPARTMENT_DELETED.getMessage());
	}
	
}