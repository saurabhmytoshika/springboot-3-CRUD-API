package com.employee.app.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private double salary;

}
