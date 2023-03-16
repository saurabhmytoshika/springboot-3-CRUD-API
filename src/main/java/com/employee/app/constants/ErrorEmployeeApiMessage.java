package com.employee.app.constants;

import java.util.Collections;
import java.util.List;

public final class ErrorEmployeeApiMessage implements EmployeeApiMessage {

	// Employee Api Error Message
	public static final EmployeeApiMessage EMAIL_ALREADY_EXIST = new ErrorEmployeeApiMessage(420, "Email is already exist");
	public static final EmployeeApiMessage EMPLOYEE_NOT_FOUND= new ErrorEmployeeApiMessage(421, "Employee does not exist in the DB for the given Id");
	public static final EmployeeApiMessage INVALID_EMPLOYEE_ID= new ErrorEmployeeApiMessage(422, "Invalid emplyeeId, please try different valid employeeId");
	
	
	
	final int resultCode;
	final String message;
	final List<String> errors;

	public ErrorEmployeeApiMessage(int resultCode, String message) {
		super();
		this.resultCode = resultCode;
		this.message = null;
		this.errors = Collections.singletonList(message);
	}

	public ErrorEmployeeApiMessage(int resultCode, String message, List<String> errors) {
		super();
		this.resultCode = resultCode;
		this.message = message;
		this.errors = errors;
	}

	@Override
	public int getResultCode() {
		return resultCode;

	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}
	
    @Override
    public void setErrors(List<String> errors) {
        errors = errors;
    }
}
