package com.employee.app.constants;

import java.util.List;

public interface EmployeeApiMessage {

	public int getResultCode();

	public String getMessage();

	public List<String> getErrors();

    void setErrors(List<String> errors);
	
}
