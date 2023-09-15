package com.example.enocabackend.services;

import com.example.enocabackend.dto.AuthResponseDto;
import com.example.enocabackend.dto.RefreshTokenRequestDto;
import com.example.enocabackend.dto.UserLoginRequestDto;
import com.example.enocabackend.dto.UserRegisterRequestDto;
import com.example.enocabackend.dto.constants.ResponseMessages;
import com.example.enocabackend.entities.RefreshToken;
import com.example.enocabackend.entities.User;
import com.example.enocabackend.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
	
private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserServiceImpl userService;
	
	private PasswordEncoder passwordEncoder;
	
	private RefreshTokenServiceImpl refreshTokenService;
	
	

	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserServiceImpl userService, PasswordEncoder passwordEncoder, RefreshTokenServiceImpl refreshTokenService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.refreshTokenService = refreshTokenService;
	}
	

	@Override
	public AuthResponseDto loginUser(UserLoginRequestDto loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userService.getOneUserByUserName(loginRequest.getUsername());
		AuthResponseDto authResponse = new AuthResponseDto();
		authResponse.setMessage(ResponseMessages.LOGIN_SUCCESS.getMessage());
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return authResponse;
		
	}

	@Override
	public AuthResponseDto registerUser(UserRegisterRequestDto registerRequest) {
		AuthResponseDto authResponse = new AuthResponseDto();
		if(userService.getOneUserByUserName(registerRequest.getUsername()) != null) {
			authResponse.setMessage(ResponseMessages.USER_IN_USE.getMessage());
			return authResponse;
		}
		
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole(registerRequest.getRole());
		userService.saveOneUser(user);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		authResponse.setMessage(ResponseMessages.REGISTERED.getMessage());
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return authResponse;		
	}

	@Override
	public AuthResponseDto refreshToken(RefreshTokenRequestDto refreshRequest) {
		AuthResponseDto response = new AuthResponseDto();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
			response.setMessage(ResponseMessages.TOKEN_REFRESHED.getMessage());
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getId());
				
		} else {
			response.setMessage(ResponseMessages.TOKEN_NOT_VALID.getMessage());
		}
		return response;
		
	}

}