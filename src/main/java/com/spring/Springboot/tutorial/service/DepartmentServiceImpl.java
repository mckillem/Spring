package com.spring.Springboot.tutorial.service;

import com.spring.Springboot.tutorial.entity.Department;
import com.spring.Springboot.tutorial.error.DepartmentNotFoundException;
import com.spring.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long id) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepository.findById(id);

		if (!department.isPresent()) {
			throw new DepartmentNotFoundException("Department not available");
		}

		return department.get();
	}

	@Override
	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public Department updateDepartment(Long id, Department department) {
		Department depDB = departmentRepository.findById(id).get();

		if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
			depDB.setName(department.getName());
		}

		if (Objects.nonNull(department.getAddress()) && !"".equalsIgnoreCase(department.getAddress())) {
			depDB.setAddress(department.getAddress());
		}

		if (Objects.nonNull(department.getCode()) && !"".equalsIgnoreCase(department.getCode())) {
			depDB.setCode(department.getCode());
		}
		return departmentRepository.save(depDB);
	}

	@Override
	public Department fetchDepartmentByName(String name) {
		return departmentRepository.findByNameIgnoreCase(name);
	}
}