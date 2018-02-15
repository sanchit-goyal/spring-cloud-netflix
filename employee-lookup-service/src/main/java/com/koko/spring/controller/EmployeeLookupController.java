package com.koko.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.koko.spring.client.DepartmentFeignClient;
import com.koko.spring.model.response.Department;
import com.koko.spring.model.response.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/lookup")
public class EmployeeLookupController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	DepartmentFeignClient departmentClient;

	@GetMapping(value = "/departments", produces = MediaType.APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "getDummyDepartments")
	public List<Department> getDepartments() {
		return departmentClient.getDepartments();
	}

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON)
	@HystrixCommand(fallbackMethod = "getDummyEmployees")
	public List<Employee> getEmployees() {
		ResponseEntity<List<Employee>> rateResponse = restTemplate.exchange("http://employee-service/employees",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				});
		List<Employee> employees = rateResponse.getBody();
		return employees;
	}

	@SuppressWarnings("unused")
	private List<Department> getDummyDepartments() {
		List<Department> departments = new ArrayList<>();
		departments.add(new Department(999, "Department from Hystrix"));
		return departments;
	}

	@SuppressWarnings("unused")
	private List<Employee> getDummyEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(999, "Employee from Hystrix"));
		return employees;
	}

}
