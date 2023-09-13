package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Role;

import lombok.Data;
@Data
public class UserRegisterRequestDto {

	private String username;
	private String password;
	private Role role;

}
