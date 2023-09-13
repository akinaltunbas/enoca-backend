package com.example.enocabackend.services;

import com.example.enocabackend.dto.AuthResponseDto;
import com.example.enocabackend.dto.RefreshTokenRequestDto;
import com.example.enocabackend.dto.UserLoginRequestDto;
import com.example.enocabackend.dto.UserRegisterRequestDto;


public interface AuthService {
	
	public AuthResponseDto loginUser(UserLoginRequestDto loginRequest);
	
	public AuthResponseDto registerUser(UserRegisterRequestDto registerRequest);
	
	public AuthResponseDto refreshToken(RefreshTokenRequestDto refreshRequest);

	
}
