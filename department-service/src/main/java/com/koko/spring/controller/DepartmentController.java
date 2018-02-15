package com.koko.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koko.spring.model.Department;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	private static Map<Integer, Department> repository = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger(6);

	static {
		Department department = new Department(1, "HR Department");
		Set<Integer> employees = new HashSet<>();
		employees.add(1);
		employees.add(2);
		employees.add(2);
		department.setEmployees(employees);

		repository.put(1, department);
		repository.put(2, new Department(2, "IT Department"));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<Department> getDepartments() {
		return new ArrayList<Department>(repository.values());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Department addDepartment(@RequestBody Department department) {
		department.setId(counter.incrementAndGet());
		repository.put(department.getId(), department);
		return repository.get(department.getId());
	}
}
