package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.enocabackend.entities.User;
import com.example.enocabackend.repository.UserRepository;
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
				   .username("akın")
				   .password("1234")
				   .role(Role.ADMIN)
				   .build();
	}
	
	

	@DisplayName("JUnit test for loadUserByUsername")
	@Test
	public void testLoadUserByUsername() {
		
		
		//given
		User user = new User();
		user.setUsername("akın");
				
		//when	
		when(userRepository.findByUsername("akın")).thenReturn(user);
		userService.loadUserByUsername("akın");
		
		//then	
		assertEquals("akın",user.getUsername());
		verify(userRepository,times(1)).findByUsername("akın");
		
	}
	

	@DisplayName("JUnit test for loadUserById")
	@Test
	public void testLoadUserById() {
		
		//given
		User user = new User();
		user.setId(1L);
		
		//when
		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
		userService.loadUserById(1L);
	
		//then
		assertEquals(1L,user.getId());
		verify(userRepository,times(1)).findById(1L);
		
	}

}
