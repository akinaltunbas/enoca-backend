package com.example.enocabackend.dto;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {
	

	Long userId;
	String refreshToken;

}
