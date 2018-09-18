package com.accenture.interviewproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.interviewproj.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	public Employee findByEmployeeName(String employeeName);

}
