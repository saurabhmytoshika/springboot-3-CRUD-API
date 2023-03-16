package com.employee.app.service;

import java.util.List;

import com.employee.app.dto.EmployeeDTO;

public interface EmployeeService {

	EmployeeDTO createEmployee(EmployeeDTO employeeDto);

	List<EmployeeDTO> getEmployees();

	EmployeeDTO findByEmployeeId(long employeeId);

	EmployeeDTO updateEmployee(EmployeeDTO employeeDto);
	
	void deleteEmployee(long employeeId);
 
}