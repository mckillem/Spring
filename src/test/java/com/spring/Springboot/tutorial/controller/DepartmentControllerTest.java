package com.spring.Springboot.tutorial.controller;

import com.spring.Springboot.tutorial.entity.Department;
import com.spring.Springboot.tutorial.error.DepartmentNotFoundException;
import com.spring.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	private Department department;

	@BeforeEach
	void setUp() {
		department = Department.builder()
				.name("ůlasdkhflůasdkhfa")
				.address("ůkjhhdsfůlhafshů")
				.code("ůjafvůajsdkfhůlasd")
				.id(1L)
				.build();
	}

	@Test
	void saveDepartment() throws Exception {
		Department inputDepartment = Department.builder()
				.name("ůlasdkhflůasdkhfa")
				.address("ůkjhhdsfůlhafshů")
				.code("ůjafvůajsdkfhůlasd")
				.build();

		Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

		mockMvc.perform(post("/departments")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n" +
						"    \"name\": \"ůlasdkhflůasdkhfa\",\n" +
						"    \"address\": \"ůkjhhdsfůlhafshů\",\n" +
						"    \"code\": \"ůjafvůajsdkfhůlasd\"\n" +
						"}"))
				.andExpect(status().isOk());
	}

	@Test
	void fetchDepartmentById() throws Exception {
		Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

		mockMvc.perform(get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(department.getName()));
	}
}