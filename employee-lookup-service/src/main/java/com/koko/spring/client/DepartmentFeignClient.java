package com.koko.spring.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.koko.spring.model.response.Department;

@FeignClient(path ="/departments", name="department-service" )
public interface DepartmentFeignClient {

	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public List<Department> getDepartments();

	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Department addDepartment(@RequestBody Department department);
}
