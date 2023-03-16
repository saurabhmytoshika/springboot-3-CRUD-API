package com.employee.app.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.employee.app.dto.GenericResponse;
import com.employee.app.dto.Status;
import com.employee.app.exception.EmployeeApiException;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(EmployeeApiException.class)
	public ResponseEntity<Object> handleDException(EmployeeApiException exception, WebRequest request) {
		log.info("GenericExceptionHandler -> handleEmployeeApiException method call");
		GenericResponse body = new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(exception.getEmployeeApiMessage());
		return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
	}

}
