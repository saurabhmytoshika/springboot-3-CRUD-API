package com.employee.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class EmployeeDTO {

	@Null(groups = Create.class)
	@NotNull(groups = Update.class, message = "Id must not be null!")
	private Long id;

	private String firstName;
	private String lastName;

	@NotBlank(message = "Email is blank.")
	private String email;

	private String mobile;
	private double salary;

}
