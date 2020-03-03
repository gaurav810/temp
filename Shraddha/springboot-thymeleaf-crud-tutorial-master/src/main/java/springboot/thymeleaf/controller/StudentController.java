package springboot.thymeleaf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.thymeleaf.entity.Student;
import springboot.thymeleaf.repository.StudentRepository;

@Controller
//@RequestMapping("/students")
public class StudentController {
 
	@Autowired
	private final StudentRepository studentRepository;
	
	@GetMapping("/admin")
	public String admin() {
		return ("Hello Admin");
	}

	@GetMapping("/user")
	public String user() {
		return ("Hello User");
	}
	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(Student student) {
		return "add-student";
	}
	
	
	@RequestMapping(value = "/login")
	public String login() {
		return "index";
	}

	@RequestMapping(value="/list")
	public String showUpdateForm(HttpServletRequest request,Model model) {
		 int page = 0; //default page number is 0 (yes it is weird)
	        int size = 5; //default page size is 5
	        
	        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
	        
			model.addAttribute("students", studentRepository.findAll(PageRequest.of(page, size)));
	       
		return "student-list";
	}

	@PostMapping("add")
	public String addStudent(@Valid Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student";
		}

		studentRepository.save(student);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("student", student);
		return "update-student";
	}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		studentRepository.save(student);
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		studentRepository.delete(student);
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}
}
