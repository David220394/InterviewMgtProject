package com.accenture.interviewproj.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.interviewproj.entities.Employee;
import com.accenture.interviewproj.enums.Role;
import com.accenture.interviewproj.exceptions.EmployeeAlreadyExistsException;
import com.accenture.interviewproj.exceptions.EmployeeNotFoundException;
import com.accenture.interviewproj.exceptions.IdNotFoundException;
import com.accenture.interviewproj.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void createEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	/**
	 * 
	 * @param eid
	 * @throws IdNotFoundException
	 * Find an employee by id
	 */
	public Employee findById(String eid) throws IdNotFoundException {
		Employee employee = employeeRepository.getOne(eid);
		if(employee != null) {
			return employee;
		}else {
			throw new IdNotFoundException("Employee cannot be found");
		}
	}
	
	/**
	 * 
	 * @param employee
	 * @Registering HR
	 * @throws EmployeeAlreadyExistsException
	 */
	public Employee insertHR(Employee employee) throws EmployeeAlreadyExistsException {
		if(employeeRepository.findByEmployeeName(employee.getEmployeeName()) == null) {
			return employeeRepository.save(employee);
		}else {
			throw new EmployeeAlreadyExistsException("This job name already exists");
		}	
	}
	
	/**
	 * 
	 * @param employeeName
	 * @return employee
	 * search by employee name
	 */
	public Employee findByEmployeeName(String employeeName) {
		return employeeRepository.findByEmployeeName(employeeName);
	}
	
	/**
	 * 
	 * @return list of all registered HR
	 */
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	
	/**
	 * 
	 * @param employeeName
	 * @throws EmployeeNotFoundException
	 * Delete employee by employee name
	 */
	public void deleteEmployee(String employeeName) throws EmployeeNotFoundException {
		 Employee findEmployee = employeeRepository.findByEmployeeName(employeeName);
		if( findEmployee != null) {
			employeeRepository.delete(findEmployee);
			return;
		}else {
			throw new EmployeeNotFoundException("Delete failed. No employee found with the employee Name:" + employeeName);
		}
	}
	
	/**
	 * 
	 * @param employee
	 * @return updated employee
	 * @throws EmployeeNotFoundException
	 */
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
		Employee searchEmployee = employeeRepository.findByEmployeeName(employee.getEmployeeName());
		if(searchEmployee != null) {
			return employeeRepository.save(employee);
		}else {
			throw new EmployeeNotFoundException("Failed to update Employee Details");
		}
	}
	
	/**
	 * Insert employee details
	 */
	@PostConstruct
	public void init() {
		if(!employeeRepository.findById("sylvio.brandon.david").isPresent()) {
		Employee employee = new Employee();
		employee.setEmployeeId("sylvio.brandon.david");
		employee.setEmployeeName("David");
		employee.setEmployeePassword("Athena");
		employee.setRole(Role.ROLE_ADMIN);
		createEmployee(employee);
		}
	}

}
