package com.example.enocabackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateProfileRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.entities.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createOneEmploye(EmployeeCreateRequestDto newEmployeeRequest) {
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
		return employeeRepository.findById(employeeId).orElse(null);
	}

	@Override
	public Employee updateOneEmployeeById(Long employeeId, EmployeeUpdateRequestDto updateEmployeeRequest) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(employee.isPresent()) {
			Employee employeeUpdate = employee.get();
			updateEmployeeRequest.mapEmployeeUpdateRequestDto(employeeUpdate);
			employeeRepository.save(employeeUpdate);
			return employeeUpdate;
		}
		return null;
	}

	@Override
	public void deleteOneEmployeeById(Long departmentId) {
		employeeRepository.deleteById(departmentId);
		
	}

	@Override
	public Employee updateEmployeeProfile(Long employeeId, EmployeeUpdateProfileRequestDto updateProfileEmployee) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if(employee.isPresent()) {
			Employee employeeProfile = employee.get();
			updateProfileEmployee.mapEmployeeUpdateProfileRequestDto(employeeProfile);
			employeeRepository.save(employeeProfile);
			return employeeProfile;
		}
		return null;
	}

}
