package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.example.enocabackend.exception.DepartmentNotFoundException;
import com.example.enocabackend.repository.DepartmentRepository;

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
				.name("IT")
				.build();
	}
	

	@DisplayName("JUnit test for createOneDepartment")
	@Test
	public void givenDepartment_whenCreateDepartment_thenReturnDepartment() {
		
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
	public void givenDepartmentId_whenDeleteOneDepartment_thenNothing() {

		// given
		long departmentId = 1L;
		given(departmentRepository.findById(departmentId)).willReturn(Optional.of(department));
		willDoNothing().given(departmentRepository).deleteById(departmentId);
		
		// when
		departmentService.deleteOneDepartmentById(departmentId);
		
		//then
		verify(departmentRepository, times(1)).deleteById(departmentId);
		
	}
	
	@DisplayName("JUnit test for getAllDepartments method")
	@Test
	public void givenDepartmentList_whenGetAllDepartments_thenReturnDepartmentList() {
		
		//given 
		Department department2 = Department.builder()
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
	public void givenDepartment_whenGetDepartmentById_thenReturnDepartment() {

		// given
		given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
		
		// when
		Department savedDepartment = departmentService.getDepartmentById(1L);
		
		//then
		assertThat(savedDepartment).isNotNull();
		
	}
	

	@DisplayName("JUnit test for getDepartmentById")
	@Test
	public void testDepartmentThrows() {
	
		assertThrows(DepartmentNotFoundException.class,
				() -> departmentService.getDepartmentById(3L));
		
	}
	
	
	@DisplayName("JUnit test for updateOneDepartmentById method")
	@Test
	public void givenDepartment_whenUpdateDepartment_thenReturnUpdateDepartment() {
		
		//given
		DepartmentUpdateRequestDto updateDepartmentRequest = new DepartmentUpdateRequestDto();
		long departmentId = 1L;
		given(departmentRepository.findById(1L)).willReturn(Optional.of(department));
		given(departmentRepository.save(department)).willReturn(department);
		updateDepartmentRequest.setName("Muhasebe");
	
		//when 
		Department updateDepartment = departmentService.updateOneDepartmentById(departmentId, updateDepartmentRequest);
		
		// then 
		assertThat(updateDepartment.getName()).isEqualTo("Muhasebe");
	
	}
	
	@DisplayName("JUnit test for updateOneDepartmentById method")
	@Test
	public void givenDepartment_whenUpdateDepartment_thenReturnThrowsDepartment() {
		
		DepartmentUpdateRequestDto updateDepartmentRequest = new DepartmentUpdateRequestDto();
		Long departmanId=1L;
		
		assertThrows(DepartmentNotFoundException.class, () -> {
			Department savedDepartment = departmentService.updateOneDepartmentById(departmanId, updateDepartmentRequest);
			savedDepartment.setId(null);
	    });
	
	}
	
	


}