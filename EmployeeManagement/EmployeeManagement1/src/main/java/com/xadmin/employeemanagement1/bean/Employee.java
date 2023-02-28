package com.xadmin.employeemanagement1.bean;

public class Employee {
	private int id;
	private String name;
	private String department;
	private String designation;
	
	public Employee(String name, String department, String designation) {
		super();
		this.name = name;
		this.department = department;
		this.designation = designation;
	}

	public Employee(int id, String name, String department, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.designation = designation;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	

}
