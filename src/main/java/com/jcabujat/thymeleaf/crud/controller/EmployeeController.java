package com.jcabujat.thymeleaf.crud.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcabujat.thymeleaf.crud.entity.Employee;
import com.jcabujat.thymeleaf.crud.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	// load employee data
	@Autowired
	private EmployeeService employeeService;
	
	@PostConstruct
	private void loadData() {
			
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		List<Employee> employees = employeeService.findAll();
		//add data to the spring model
		theModel.addAttribute("employees", employees);
		
		return "employees/list-employees";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create the model attribute to bind the form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {
		
		// get the employee from service
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee as model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		//send over to our save employee form
		return "employees/employee-form";
				
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save the employee
		employeeService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		// delete the employee
		employeeService.deleteById(theId);
		
		// return to employees/list
		return "redirect:/employees/list";
	}
	
}
