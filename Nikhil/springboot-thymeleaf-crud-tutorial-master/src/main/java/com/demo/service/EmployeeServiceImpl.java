package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepository;
	
	@Override
	public Employee saveEmp(Employee emp) {
		// TODO Auto-generated method stub
		return empRepository.save(emp);
	}

	@Override
	public List<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return empRepository.findById(id).get();
	}

	@Override
	public Employee updateEmp(long id, Employee emp) {
		// TODO Auto-generated method stub
		Employee emp1=empRepository.findById(id).get();
		emp1.setName(emp.getName());
		emp1.setDesignation(emp.getDesignation());
		return empRepository.save(emp1);
	}

	@Override
	public void deleteEmp(long id) {
		// TODO Auto-generated method stub
		empRepository.deleteById(id);
	}

}
