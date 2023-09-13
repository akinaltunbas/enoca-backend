package com.example.enocabackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enocabackend.dto.AuthResponseDto;
import com.example.enocabackend.dto.RefreshTokenRequestDto;
import com.example.enocabackend.dto.UserLoginRequestDto;
import com.example.enocabackend.dto.UserRegisterRequestDto;
import com.example.enocabackend.services.AuthServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthServiceImpl authService;
	
	public AuthController(AuthServiceImpl authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register") 
		public ResponseEntity<AuthResponseDto> register(@RequestBody UserRegisterRequestDto registerRequest) {
			return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
		}
	@PostMapping("/login")
	public AuthResponseDto login(@RequestBody UserLoginRequestDto loginRequest) {
		return authService.loginUser(loginRequest);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshRequest) {
			return new ResponseEntity<>(authService.refreshToken(refreshRequest), HttpStatus.OK);		
	} 	
}


