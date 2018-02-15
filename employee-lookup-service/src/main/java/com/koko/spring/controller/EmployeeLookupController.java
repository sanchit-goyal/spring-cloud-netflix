package com.koko.spring.controller;

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

@RestController
@RequestMapping("/lookup")
public class EmployeeLookupController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	DepartmentFeignClient departmentClient;

	@GetMapping(value = "/departments", produces = MediaType.APPLICATION_JSON)
	public List<Department> getDepartments() {
		return departmentClient.getDepartments();
	}

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		ResponseEntity<List<Employee>> rateResponse = restTemplate.exchange("http://employee-service/employees",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				});
		List<Employee> employees = rateResponse.getBody();
		return employees;
	}

}
