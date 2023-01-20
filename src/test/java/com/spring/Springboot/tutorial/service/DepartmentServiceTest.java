package com.spring.Springboot.tutorial.service;

import com.spring.Springboot.tutorial.entity.Department;
import com.spring.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp() {
		Department department = Department.builder()
				.name("IT")
				.address("Zlín")
				.code("IT-03")
				.id(1L)
				.build();

		Mockito.when(departmentRepository.findByNameIgnoreCase("IT")).thenReturn(department);
	}

	@Test
	@DisplayName("Get data based on valid department name")
	public void whenValidDepartmentName_thenDepartmentShouldFound() {
		String departmentName = "IT";
		Department found = departmentService.fetchDepartmentByName(departmentName);

		assertEquals(departmentName, found.getName());
	}
}