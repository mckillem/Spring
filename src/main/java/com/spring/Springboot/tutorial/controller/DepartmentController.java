package com.spring.Springboot.tutorial.controller;

import com.spring.Springboot.tutorial.entity.Department;
import com.spring.Springboot.tutorial.error.DepartmentNotFoundException;
import com.spring.Springboot.tutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody Department department) {
		LOGGER.info("Inside saveDepartment of DepartmentController");
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/departments")
	public List<Department> fetchDepartmentList() {
		LOGGER.info("Inside fetchDepartmentList of DepartmentController");
		return departmentService.fetchDepartmentList();
	}

	@GetMapping("/departments/{id}")
	public Department fetchDepartmentById(@PathVariable("id") Long id) throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentById(id);
	}

	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long id) {
		departmentService.deleteDepartmentById(id);
		return "Department deleted successfully";
	}

	@PutMapping("/departments/{id}")
	public Department updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
		return departmentService.updateDepartment(id, department);
	}

	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String name) {
		return departmentService.fetchDepartmentByName(name);
	}
}
