package com.example.enocabackend.services;

import java.util.List;

import com.example.enocabackend.dto.UserCreateRequestDto;
import com.example.enocabackend.dto.UserUpdateRequestDto;
import com.example.enocabackend.entities.User;

public interface UserService {
	

	public List<User> getAllUsers();
	
	public User createOneUser(UserCreateRequestDto newUserRequest);
	
	public void deleteOneUserById(Long userId);
	
	public User getOneUserById(Long userId);
	
	public User updateOneUserById(Long userId, UserUpdateRequestDto updateUserRequest);
	
	public User getOneUserByUserName(String username);
	
	public User saveOneUser(User user);
	

}
