package com.koko.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koko.spring.model.Department;

@RestController
@RequestMapping("/employees")
public class DepartmentResource {
	private static Map<Integer, Department> repository = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger(6);

	static {
		repository.put(1, new Department(1, "HR Department"));
		repository.put(2, new Department(2, "IT Department"));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<Department> getEmployees() {
		return new ArrayList<Department>(repository.values());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Department saveEmployee(@RequestBody Department employee) {
		
		employee.setId(counter.incrementAndGet());
		repository.put(employee.getId(), employee);
		return repository.get(employee.getId());
	}
}
