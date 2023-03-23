package com.employee.app.controller.advice;

import com.employee.app.dto.GenericResponse;
import com.employee.app.dto.Status;
import com.employee.app.exception.EmployeeApiException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
		log.info("GlobalExceptionHandler -> handleMethodArgumentNotValid method call");
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(errors);;
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest request) {
		log.error("GlobalExceptionHandler -> handleMethodArgumentTypeMismatch method call, ex:: {}", exception);
		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(exception.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {
		log.error("GlobalExceptionHandler -> handleConstraintViolation method call, ex:: {}", exception);
		GenericResponse body=new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(exception.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeApiException.class)
	public ResponseEntity<Object> handleException(EmployeeApiException exception, WebRequest request) {
		log.info("GenericExceptionHandler -> handleEmployeeApiException method call");
		GenericResponse body = new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(exception.getEmployeeApiMessage());
		return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception exception, WebRequest request) {
		log.error("GenericExceptionHandler -> handleOtherException method call, ex:: {}", exception);
		GenericResponse body = new GenericResponse();
		body.setStatus(Status.FAILURE);
		body.setError(exception.getMessage());
		return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
