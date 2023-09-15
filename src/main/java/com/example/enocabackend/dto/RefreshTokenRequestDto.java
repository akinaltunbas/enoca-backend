package com.example.enocabackend.dto;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
	
	private Long userId;
	private String refreshToken;

}
