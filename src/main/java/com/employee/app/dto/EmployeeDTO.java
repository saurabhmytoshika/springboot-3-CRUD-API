package com.employee.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDTO {
	
	private Long id;
	private String firstName;
	private String lastName;

	@NotBlank(message = "Email is blank.")
	private String email;

	private String mobile;
	private double salary;

}
