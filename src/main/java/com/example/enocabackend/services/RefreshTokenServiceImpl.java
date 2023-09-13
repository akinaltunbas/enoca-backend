package com.example.enocabackend.services;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.enocabackend.entities.RefreshToken;
import com.example.enocabackend.entities.User;
import com.example.enocabackend.entities.repository.RefreshTokenRepository;
import com.example.enocabackend.entities.repository.UserRepository;


@Service
public class RefreshTokenServiceImpl {
	
	@Value("${refresh.token.expires.in}")
	Long expireSeconds = 604800L;
	
	private RefreshTokenRepository refreshTokenRepository;
	
	private UserRepository userRepository;

	public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}
	
	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
		if(token == null) {
			token =	new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}

	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public RefreshToken getByUser(Long userId) {	
		return refreshTokenRepository.findByUserId(userId);
	}

	

}
