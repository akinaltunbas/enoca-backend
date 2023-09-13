package com.example.enocabackend.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enocabackend.entities.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
