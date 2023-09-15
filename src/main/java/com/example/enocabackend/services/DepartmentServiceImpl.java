package com.example.enocabackend.services;

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.entities.Department;

import com.example.enocabackend.exception.DepartmentNotFoundException;
import com.example.enocabackend.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
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
		return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
	}

	@Override
	public Department updateOneDepartmentById(Long departmentId, DepartmentUpdateRequestDto updateDepartmentRequest) {
		Optional<Department> department = departmentRepository.findById(departmentId);
		if(department.isPresent()) {
			Department departmentUpdate = department.get();
			updateDepartmentRequest.mapDepartmentUpdateRequestDto(departmentUpdate);
			return departmentRepository.save(departmentUpdate);
		}
		else {
			throw  new DepartmentNotFoundException(departmentId);
		}
	}

	@Override
	public void deleteOneDepartmentById(Long departmentId) {
		departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
		departmentRepository.deleteById(departmentId);
	}

}