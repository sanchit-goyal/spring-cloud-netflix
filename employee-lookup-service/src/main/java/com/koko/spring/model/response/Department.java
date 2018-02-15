package com.koko.spring.model.response;

import java.util.Set;

public class Department {
	private int id;
	private String name;
	Set<Integer> employees;

	public Department() {
	}

	public Department(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Integer> employees) {
		this.employees = employees;
	}

}
