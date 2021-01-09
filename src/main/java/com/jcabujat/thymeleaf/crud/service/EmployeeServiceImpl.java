package com.jcabujat.thymeleaf.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcabujat.thymeleaf.crud.dao.EmployeeRepository;
import com.jcabujat.thymeleaf.crud.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeDAO;
	
	@Autowired	
	public EmployeeServiceImpl(EmployeeRepository employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Optional<Employee> result = employeeDAO.findById(theId);
		
		Employee employee = null;
		
		if (result.isPresent()) {
			employee = result.get(); 
		} else {
			throw new RuntimeException ("Employee id not found: " + theId);
		}
		
		return employee;
		
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}
