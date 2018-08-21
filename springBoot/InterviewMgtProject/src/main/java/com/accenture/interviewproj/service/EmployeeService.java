package com.accenture.interviewproj.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Employee;
import com.accenture.interviewproj.enums.Role;
import com.accenture.interviewproj.exception.IdNotFoundException;
import com.accenture.interviewproj.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void createEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Employee findById(String eid) throws IdNotFoundException {
		Employee employee = employeeRepository.getOne(eid);
		if(employee != null) {
			return employee;
		}else {
			throw new IdNotFoundException("Employee cannot be found");
		}
	}
	
	@PostConstruct
	public void init() {
		if(employeeRepository.getOne("sylvio.brandon.david") == null) {
		Employee employee = new Employee();
		employee.setEmployeeId("sylvio.brandon.david");
		employee.setEmployeeName("David");
		employee.setEmployeePassword("Athena");
		employee.setRole(Role.ROLE_ADMIN);
		createEmployee(employee);
		}
	}

}
