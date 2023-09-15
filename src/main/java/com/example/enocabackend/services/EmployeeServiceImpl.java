package com.example.enocabackend.services;

import java.util.List;
import java.util.Optional;

import com.example.enocabackend.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateProfileRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.entities.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;

	private final DepartmentService departmentService;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService) {
		this.employeeRepository = employeeRepository;
		this.departmentService = departmentService;
	}

	@Override
	public Employee createOneEmployee(EmployeeCreateRequestDto newEmployeeRequest) {
		Employee employee = new Employee();
		newEmployeeRequest.mapEmployeeCreateRequestDto(employee);
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getOneEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
	}

	@Override
	public Employee updateOneEmployeeById(Long employeeId, EmployeeUpdateRequestDto updateEmployeeRequest) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(employee.isPresent()) {
			Employee employeeUpdate = employee.get();
			updateEmployeeRequest.mapEmployeeUpdateRequestDto(employeeUpdate);
			employeeRepository.save(employeeUpdate);
			return employeeUpdate;
		}else {
			throw new EmployeeNotFoundException(employeeId);
		}
	}

	@Override
	public void deleteOneEmployeeById(Long employeeId) {
		employeeRepository.deleteById(employeeId);
		employeeRepository.findById(employeeId)
		.orElseThrow(() -> new EmployeeNotFoundException(employeeId));
		
	}

	@Override
	public Employee updateEmployeeProfile(Long employeeId, EmployeeUpdateProfileRequestDto updateProfileEmployee) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(employee.isPresent()) {
			Employee employeeProfile = employee.get();
			updateProfileEmployee.mapEmployeeUpdateProfileRequestDto(employeeProfile);
			employeeRepository.save(employeeProfile);
			return employeeProfile;
		}else {
			throw new EmployeeNotFoundException(employeeId);
		}
	}

}
