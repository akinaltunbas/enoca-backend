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

import com.example.enocabackend.dto.DepartmentCreateRequestDto;
import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.entities.Department;
import com.example.enocabackend.entities.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	private Department department;
	
	@BeforeEach
	public void setup() {
		
		department = Department.builder()
				.id(1L)
				.name("IT")
				.build();
	}
	

	@DisplayName("JUnit test for createOneDepartment")
	@Test
	public void givenShare_whenCreateShare_thenReturnShare() {
		
		//given
		DepartmentCreateRequestDto departmentRequestDto = new DepartmentCreateRequestDto();
		departmentRequestDto.setName("Test-Name");
		
		
		//when
		Department shareMock = Mockito.mock(Department.class);
		when(shareMock.getId()).thenReturn(1L);
		Mockito.when(departmentRepository.save(ArgumentMatchers.any(Department.class))).thenReturn(shareMock);
		
		Department saveDepartment = departmentService.createOneDepartment(departmentRequestDto);

		//then
		assertEquals(saveDepartment.getId(),1L);
	}
	

	@DisplayName("junit test for deleteOneDepartmentById method")
	@Test
	public void givenTravelId_whenDeleteOneTravel_thenNothing() {
		
		//given
		long travelId = 1L;
		
		willDoNothing().given(departmentRepository).deleteById(travelId);
		
		//when
		departmentService.deleteOneDepartmentById(travelId);
		
		//then
		verify(departmentRepository, times(1)).deleteById(travelId);
		
	}
	
	@DisplayName("JUnit test for getAllDepartments method")
	@Test
	public void givenShareList_whenGetAllShares_thenReturnShareList() {
		
		//given 
		Department department2 = Department.builder()
				.id(2L)
				.name("IK")
				.build();
		given(departmentRepository.findAll()).willReturn(List.of(department,department2));
		
		
		//when
		List<Department> departmentList = departmentService.getAllDepartments();
		
		//then
		assertThat(departmentList).isNotNull();
		assertThat(departmentList.size()).isEqualTo(2);
	}
	
	@DisplayName("JUnit test for getDepartmentById")
	@Test
	public void givenShareId_whenGetShareById_thenReturnShare() {
		
		// given
		given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
		
		// when
		Department savedShare = departmentService.getDepartmentById(department.getId());
		
		//then
		assertThat(savedShare).isNotNull();
	}
	
	@DisplayName("JUnit test for updateOneShareById method")
	@Test
	public void givenShare_whenUpdateShare_thenReturnUpdateShare() {
		
		//given
		DepartmentUpdateRequestDto updateDepartmentRequest = new DepartmentUpdateRequestDto();
		long departmentId = 1L;
		given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
		given(departmentRepository.save(department)).willReturn(department);
		updateDepartmentRequest.setName("Muhasebe");
	
		//when 
		Department updateDepartment = departmentService.updateOneDepartmentById(departmentId, updateDepartmentRequest);
		
		// then -verify the output
		assertThat(updateDepartment.getName()).isEqualTo("Maliye");
	
	}
	
	


}
