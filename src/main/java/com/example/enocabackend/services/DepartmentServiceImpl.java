package com.example.enocabackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.entities.Department;
import com.example.enocabackend.entities.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Department createOneDepartment(DepartmentCreateRequestDto newDepartmentRequest) {
		Department department = new Department();
		newDepartmentRequest.mapDepartmentRequestDto(department);
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long departmentId) {
		return departmentRepository.findById(departmentId).orElse(null);
	}

	@Override
	public Department updateOneDepartmentById(Long departmentId, DepartmentUpdateRequestDto updateDepartmentRequest) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		if(department.isPresent()) {
			Department departmentUpdate = department.get();
			updateDepartmentRequest.mapDepartmentUpdateRequestDto(departmentUpdate);
			departmentRepository.save(departmentUpdate);
			return departmentUpdate;
		}
		
		return null;
	}

	@Override
	public void deleteOneDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
		
	}

}
