package com.example.enocabackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.enocabackend.dto.UserCreateRequestDto;
import com.example.enocabackend.dto.UserUpdateRequestDto;
import com.example.enocabackend.entities.User;
import com.example.enocabackend.repository.UserRepository;
;


@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;


	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
	}

	@Override
	public User createOneUser(UserCreateRequestDto newUserRequest) {
		User user = new User();
		newUserRequest.mapUserCreateRquestDto(user);;
		return userRepository.save(user);
	}

	@Override
	public void deleteOneUserById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User updateOneUserById(Long userId, UserUpdateRequestDto updateUserRequest) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User user1 = user.get();
			updateUserRequest.mapUserUpdateRequestDto(user1);
			userRepository.save(user1);
			
			return user1;
		}
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getOneUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User saveOneUser(User user) {
		return userRepository.save(user);
	}

}