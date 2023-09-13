package com.example.enocabackend.dto;

import com.example.enocabackend.entities.Role;
import com.example.enocabackend.entities.User;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
	
	private String username;
	private String password;
	private Role role;
	
	public void mapUserUpdateRquestDto(User user) {
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setRole(this.getRole());
	}

}
