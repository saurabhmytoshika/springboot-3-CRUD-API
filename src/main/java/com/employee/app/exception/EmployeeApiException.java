package com.employee.app.exception;

import com.employee.app.constants.EmployeeApiMessage;

public class EmployeeApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private EmployeeApiMessage employeeApiMessage;

	public EmployeeApiException() {
		super();
	}

	public EmployeeApiException(EmployeeApiMessage employeeApiMessage) {
		super();
		this.employeeApiMessage = employeeApiMessage;
	}

	public EmployeeApiMessage getEmployeeApiMessage() {
		return employeeApiMessage;
	}

}
