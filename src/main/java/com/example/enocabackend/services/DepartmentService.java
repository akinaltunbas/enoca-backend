package com.example.enocabackend.services;

import java.util.List;

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.entities.Department;

public interface DepartmentService {
	
	public Department createOneDepartment(DepartmentCreateRequestDto newDepartmentRequest);
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(Long departmentId);
	
	public Department updateOneDepartmentById(Long departmentId, DepartmentUpdateRequestDto updateDepartmentRequest);
	
	public void deleteOneDepartmentById(Long departmentId);

}
