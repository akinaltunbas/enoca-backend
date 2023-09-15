package com.example.enocabackend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
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

import com.example.enocabackend.dto.DepartmentUpdateRequestDto;
import com.example.enocabackend.dto.EmployeeCreateRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateProfileRequestDto;
import com.example.enocabackend.dto.EmployeeUpdateRequestDto;
import com.example.enocabackend.entities.Department;
import com.example.enocabackend.entities.Employee;
import com.example.enocabackend.exception.DepartmentNotFoundException;
import com.example.enocabackend.exception.EmployeeNotFoundException;
import com.example.enocabackend.repository.EmployeeRepository;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private DepartmentService departmentService;
	
	private Employee employee;
	
	@BeforeEach
	public void setup() {
		employee = Employee.builder()
						   .name("Akin")
						   .surname("Altun")
						   .salary(10000)
						   .email("akin@hotmail.com")
						   .department(departmentService.getDepartmentById(1L))
						   .password("1234")
						   .build();
	}
	
	@DisplayName("JUnit test for createOneEmployee")
	@Test
	public void givenEmployee_whenCreateEmployee_thenReturnEmployee() {
		
		//given	
		EmployeeCreateRequestDto employeeRequestDto = new EmployeeCreateRequestDto();
		employeeRequestDto.setName("Ali");
		employeeRequestDto.setSurname("Altun");
		employeeRequestDto.setSalary(1000);
		employeeRequestDto.setEmail("akin@hotmail.com");
		employeeRequestDto.setPassword("1234");
		employeeRequestDto.setDepartment(departmentService.getDepartmentById(1L));
	
		//when
		Employee employeeMock = Mockito.mock(Employee.class);
		when(employeeMock.getId()).thenReturn(2L);
		Mockito.when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employeeMock);
		
		Employee saveEmployee = employeeService.createOneEmployee(employeeRequestDto);

		//then
		assertEquals(saveEmployee.getId(),2L);
	}
	
	@DisplayName("JUnit test for getAllEmployees method")
	@Test
	public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {
		
		//given 
		Employee employee2 = Employee.builder()
				   .name("Ali")
				   .surname("Akyıldız")
				   .salary(10000)
				   .email("ali@hotmail.com")
				   .password("1111")
				   .build();
	   given(employeeRepository.findAll()).willReturn(List.of(employee,employee2));
		
		//when
		List<Employee> employeeList = employeeService.getAllEmployees();
		
		//then
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(2);
	}
	
	@DisplayName("junit test for deleteOneEmployeeById method")
	@Test
	public void testEmployeeFindById() {
		
		// given
		given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
		
		// when
		Employee savedEmployee =employeeService.getOneEmployeeById(1L);
		
		//then
		assertThat(savedEmployee).isNotNull();
		
	}
	

	@DisplayName("junit test for deleteOneEmployeeById method")
	@Test
	public void testDeleteEmployeeThrow() {
			
		assertThrows(EmployeeNotFoundException.class,
					() -> employeeService.getOneEmployeeById(3L));
			
		}
	
	
	
	@DisplayName("junit test for deleteOneEmployeeById method")
	@Test
	public void givenEmployeeId_whenDeleteOneEmployee_thenNothing() {
		
		// given
		long employeeId = 1L;
		given(employeeRepository.findById(employeeId)).willReturn(Optional.of(employee));
		willDoNothing().given(employeeRepository).deleteById(employeeId);
		
		// when
		employeeService.deleteOneEmployeeById(employeeId);
		
		//then
		verify(employeeRepository, times(1)).deleteById(employeeId);
	}
	

	
		
	
	@DisplayName("JUnit test for getEmployeeById")
	@Test
	public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() {
		
		// given
		given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
		
		// when
		Employee savedEmployee = employeeService.getOneEmployeeById(1L);
		
		//then
		assertThat(savedEmployee).isNotNull();
	}
	
	@DisplayName("JUnit test for getEmployeeById")
	@Test
	public void testGetEmployeeThrows() {
	
		assertThrows(EmployeeNotFoundException.class,
				() -> employeeService.getOneEmployeeById(3L));
		
	}
	
	
	@DisplayName("JUnit test for updateOneEmployeeById method")
	@Test
	public void givenEmployee_whenUpdateEmployee_thenReturnUpdateEmployee() {
		
		//given
		EmployeeUpdateRequestDto updateEmployeeRequest = new EmployeeUpdateRequestDto();
		long employeeId = 1L;
		given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
		given(employeeRepository.save(employee)).willReturn(employee);
		updateEmployeeRequest.setName("Akın");
		updateEmployeeRequest.setSurname("Altun");
		updateEmployeeRequest.setSalary(20000);
		updateEmployeeRequest.setEmail("akito@hotmail.com");
		updateEmployeeRequest.setPassword("1111");

		//when 
		Employee updateEmployee = employeeService.updateOneEmployeeById(employeeId, updateEmployeeRequest);
		Optional<Employee> employee = employeeRepository.findById(2l);
		
		
		// then -verify the output
		assertFalse(employee.isPresent());
		assertThat(updateEmployee.getName()).isEqualTo("Akın");
		assertThat(updateEmployee.getSurname()).isEqualTo("Altun");
		assertThat(updateEmployee.getSalary()).isEqualTo(20000);
		assertThat(updateEmployee.getEmail()).isEqualTo("akito@hotmail.com");
		assertThat(updateEmployee.getPassword()).isEqualTo("1111");
		
	}
	
	@DisplayName("JUnit test for updateOneEmployeetById method")
	@Test
	public void testUpdateEmployeeNullId() {
		
		EmployeeUpdateRequestDto updateEmployeeRequest = new EmployeeUpdateRequestDto();
		Long employeeId=1L;
		
		assertThrows(EmployeeNotFoundException.class, () -> {
			Employee savedEmployee = employeeService.updateOneEmployeeById(employeeId, updateEmployeeRequest);
			savedEmployee.setId(null);
	    });
	
	}
	
	
	@DisplayName("Junit test for updateEmployeeProfile method")
	@Test
	public void givenEmployee_whenUpdateEmployeeProfile_thenReturnUpdateEmployeeProfile() {
		
		//given
		EmployeeUpdateProfileRequestDto updateProfileRequest = EmployeeUpdateProfileRequestDto.builder().build();
		long employeeId= 1L;
		given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
		given (employeeRepository.save(employee)).willReturn(employee);
	
		updateProfileRequest.setName("Anıl");
		updateProfileRequest.setSurname("Altunbaş");
		updateProfileRequest.setEmail("anil@hotmail.com");
		updateProfileRequest.setPassword("1234");
		
		//when
		Employee updateEmployee = employeeService.updateEmployeeProfile(employeeId, updateProfileRequest);
		
		//then
		
		assertThat(updateEmployee.getName()).isEqualTo("Anıl");
		assertThat(updateEmployee.getSurname()).isEqualTo("Altunbaş");
		assertThat(updateEmployee.getEmail()).isEqualTo("anil@hotmail.com");
		assertThat(updateEmployee.getPassword()).isEqualTo("1234");
					
	}
	

	@DisplayName("JUnit test for updateEmployeeProfile method")
	@Test
	public void testUpdateEmployeeProfileNullId() {
		
		EmployeeUpdateProfileRequestDto updateEmployeeRequest = EmployeeUpdateProfileRequestDto.
				builder().name("Ayşe").surname("Altunbaş").email("ayse.altunbas@ziraat.com").password("1234").build();


		Long employeeId=1L;
		
		Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
			Employee savedEmployee = employeeService.updateEmployeeProfile(employeeId, updateEmployeeRequest);
	    });

		String expectedMessage = "Employee not found with id : 1";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	
	}
	
}