package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Department;

import lombok.Data;

@Data
public class DepartmentCreateRequestDto {
	
	private String name;
	
	public void mapDepartmentRequestDto(Department department) {
		
		department.setName(this.getName());
	}

}
