package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.enocabackend.dto.UserCreateRequestDto;
import com.example.enocabackend.dto.UserUpdateRequestDto;
import com.example.enocabackend.entities.Role;
import com.example.enocabackend.entities.User;
import com.example.enocabackend.entities.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	private User user;
	
	private PasswordEncoder bcryptEncoder;
	
	@BeforeEach
	public void setup() {
		
		user = User.builder()
				   .id(1L)
				   .username("akito")
				   .password("1234")
				   .role(Role.ADMIN)
				   .build();
	}
	
	
	@DisplayName("Junit test for deleteOneUserById method")
	@Test
	public void givenUserId_whenDeleteOneUser_thenNothing() {
		
		//given
		long userId = 1L;
		
		willDoNothing().given(userRepository).deleteById(userId);
		
		//when
		userService.deleteOneUserById(userId);
		
		//then
		verify(userRepository,times(1)).deleteById(userId);
	}
	
	@DisplayName("Junit test for getOneUserById")
	@Test
	public void givenUserId_whenGetUserById_thenReturnUser() {
		
		//given
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		
		//when
		User savedUser = userService.getOneUserById(user.getId());
		
		//then
		assertThat(savedUser).isNotNull();	
		
	}
	
	@DisplayName("Junit test for createOneUser")
	@Test
	public void givenUser_whenCreateUser_thenReturnUser() {
		
		//given
		UserCreateRequestDto userRequestDto = new UserCreateRequestDto();
		userRequestDto.setUsername("Test-Username");
		userRequestDto.setPassword("Test-Password");
	
		
		//when
		User userMock = Mockito.mock(User.class);
		when(userMock.getId()).thenReturn(1L);
		Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userMock);
		User savedUser = userService.createOneUser(userRequestDto);
		
		//then
		assertEquals(savedUser.getId(),1L);
		
	}
	
	@DisplayName("Junit test for updateOneUserById method")
	@Test
	public void givenUser_whenUpdateUser_thenReturnUpdateUser() {
		
		//given
		UserUpdateRequestDto updateUserRequest = new UserUpdateRequestDto();
		long userId= 1L;
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given (userRepository.save(user)).willReturn(user);
	
		updateUserRequest.setUsername("akito");
		updateUserRequest.setPassword("1234");

		
		//when
		User updateUser = userService.updateOneUserById(userId, updateUserRequest);
		
		//then
		assertThat(updateUser.getUsername()).isEqualTo("akito");
		assertThat(updateUser.getPassword()).isEqualTo("1234");
			
	}
	
	@DisplayName("Junit test for updateOneUserById")
	@Test
	public void testUpdateOneUser_Null() {
		
		//given
		UserUpdateRequestDto updateUserRequest = new UserUpdateRequestDto();
		long userId= 1L;
		
		//when
		User savedUser = userService.updateOneUserById(userId, updateUserRequest);
		
		savedUser= null;
		
		//then
		assertThat(savedUser).isNull();
	}
	
	@DisplayName("Junit test for getAllUsers method")
	@Test
	public void givenUserList_whenGetAllUsers_thenReturnUserList() {
		
		//given
		User user = new User(1L,"akito","1234",Role.ADMIN);
		
		given(userRepository.findAll()).willReturn(List.of(user,user));
		
		//when
		List<User> userList = userService.getAllUsers();
		
		//then
		assertThat(userList).isNotNull();
		assertThat(userList.size()).isEqualTo(2);
	}
	
	@DisplayName("Junit test for getOneUserByUserName method")
	@Test
	public void givenUserName_whenGetUserName_thenReturnUser() {
		
		//given
		given(userRepository.findByUsername("akito")).willReturn(user);
		
		//when
		User savedUser = userService.getOneUserByUserName(user.getUsername());
		
		//then
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for saveOneUser")
	@Test
	public void givenUser_whenSaveUser_thenReturnUser() {
		
		//given
		given(userRepository.save(user)).willReturn(user);
		
		//when
		User savedUser = userService.saveOneUser(user);
		
		//then
		assertThat(savedUser).isNotNull();
		
	}
	

}
