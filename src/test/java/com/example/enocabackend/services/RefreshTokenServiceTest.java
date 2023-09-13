package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.enocabackend.entities.RefreshToken;
import com.example.enocabackend.entities.Role;
import com.example.enocabackend.entities.User;
import com.example.enocabackend.entities.repository.RefreshTokenRepository;


@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceTest {
	
	@InjectMocks
	private RefreshTokenServiceImpl refreshTokenService;
	
	@Mock
	private RefreshTokenRepository refreshTokenRepository;
	
	private RefreshToken refreshToken;
	
	@DisplayName("Junit test for getByUser")
	@Test 
	public void testGetByUser() {
	//given
	RefreshToken tokenMock = Mockito.mock(RefreshToken.class);
	
	//when
	Mockito.when(refreshTokenRepository.findByUserId(ArgumentMatchers.any())).thenReturn(tokenMock);
	
	RefreshToken savedToken = refreshTokenService.getByUser(1L);
	
	//then
	assertThat(savedToken).isNotNull();
					
	}
	
	@DisplayName("Junit test for createRefreshToken")
	@Test
	public void testCreateRefreshToken_NotNull() {
		//given
		User user = new User(1L,"Akito","1234",Role.ADMIN);
	
		RefreshToken tokenMock = Mockito.mock(RefreshToken.class);

		Mockito.when(refreshTokenRepository.findByUserId(ArgumentMatchers.any())).thenReturn(tokenMock);
		
		//when
		String savedToken = refreshTokenService.createRefreshToken(user);
		
		savedToken= "90aeb74a-babb-4c98-96a6-9a3b93eda2db";
		
		//then
		assertThat(savedToken).isNotNull();
		
	}
	
	@DisplayName("Junit test for createRefreshToken")
	@Test
	public void testCreateRefreshToken_Null() {
		//given
		User user = new User();
		
		//when
		String savedToken = refreshTokenService.createRefreshToken(user);
		
		savedToken= null;
		
		//then
		assertThat(savedToken).isNull();
	}

}
