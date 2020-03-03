package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;
import com.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees/")
public class EmployeeController {

	@Autowired
	private  EmployeeService empService;

	
	@GetMapping("signup")
	public String showSignUpForm(Model model) {
		
		model.addAttribute("emp", new Employee());
		return "add-employee";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("employees", empService.getEmployee());
		return "index";
	}

	@PostMapping("add")
	public String addStudent(@Valid Employee emp, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-employee";
		}

		empService.saveEmp(emp);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Employee emp = empService.getEmployeeById(id);
					model.addAttribute("employee", emp);
		return "update-employee";
	}

	@PostMapping("update/{id}")
	public String updateEmployee(@PathVariable("id") long id, @Valid Employee emp, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			emp.setId(id);
			return "update-employee";
		}

		empService.saveEmp(emp);
		model.addAttribute("employees", empService.getEmployee());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		empService.deleteEmp(id);
		model.addAttribute("employees", empService.getEmployee());
		return "index";
	}
}
