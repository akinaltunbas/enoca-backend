package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.enocabackend.entities.User;
import com.example.enocabackend.entities.repository.UserRepository;
import com.example.enocabackend.entities.Role;


@ExtendWith(MockitoExtension.class)
public class UserDetailsSeviceImpl {
	
	@InjectMocks
	private UserDetailsServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		
		user = User.builder()
				   .id(1L)
				   .username("akın altunbas")
				   .password("1234")
				   .role(Role.ADMIN)
				   .build();
	}
	
	

	@DisplayName("JUnit test for loadUserByUsername")
	@Test
	public void testLoadUserByUsername() {
		
		
		//given
		given(userRepository.findByUsername("akın altunbas")).willReturn(user);
		given(userRepository.findByUsername(null));
				
		//when	
		UserDetails savedUser = userService.loadUserByUsername(user.getUsername());
		
		//then	
		assertThat(savedUser).isNotNull();
		
	}
	

	@DisplayName("JUnit test for loadUserById")
	@Test
	public void testLoadUserById() {
		
		//given
		given(userRepository.findById(1L));
		given(userRepository.findById(null));
				
		//when	
		UserDetails savedUser = userService.loadUserById(user.getId());
		
		//then	
		assertThat(savedUser).isNotNull();
		
	}

}
