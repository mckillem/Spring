package com.spring.Springboot.tutorial.service;

import com.spring.Springboot.tutorial.entity.Department;
import com.spring.Springboot.tutorial.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
	public Department saveDepartment(Department department);

	public List<Department> fetchDepartmentList();

	public Department fetchDepartmentById(Long id) throws DepartmentNotFoundException;

	public void deleteDepartmentById(Long id);

	public Department updateDepartment(Long id, Department department);

	public Department fetchDepartmentByName(String name);
}
