package com.koko.spring.resource;

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

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	private static Map<Integer, Employee> repository = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger(6);

	static {
		repository.put(1, new Employee(1, "Sandeep"));
		repository.put(2, new Employee(2, "Aditya"));
		repository.put(3, new Employee(3, "Vivaan"));
		repository.put(4, new Employee(4, "Pooja"));
		repository.put(5, new Employee(5, "Nishant"));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		return new ArrayList<Employee>(repository.values());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		employee.setId(counter.incrementAndGet());
		repository.put(employee.getId(), employee);
		return repository.get(employee.getId());
	}
}
