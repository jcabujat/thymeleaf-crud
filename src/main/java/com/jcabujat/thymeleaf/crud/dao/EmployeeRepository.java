package com.jcabujat.thymeleaf.crud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcabujat.thymeleaf.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();

}
