package com.example.enocabackend.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enocabackend.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
