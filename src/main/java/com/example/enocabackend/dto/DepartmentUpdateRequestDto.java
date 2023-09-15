package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Department;


import lombok.Data;

@Data
public class DepartmentUpdateRequestDto {
	
	private String name;
	
	public void mapDepartmentUpdateRequestDto(Department department) {
		department.setName(this.getName());
	
	}

}
