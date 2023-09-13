package com.example.enocabackend.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
	
	String message;
	Long userId;
	String accessToken;
	String refreshToken;

}
