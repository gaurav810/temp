package com.demo.service;

import java.util.List;

import com.demo.model.Employee;

public interface EmployeeService {

	public Employee saveEmp(Employee emp);
	
	public List<Employee> getEmployee();
	
	public Employee getEmployeeById(long id);
	
	public Employee updateEmp(long id,Employee emp);
	
	public void deleteEmp(long id);
}
