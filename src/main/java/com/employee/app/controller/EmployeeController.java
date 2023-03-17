package com.employee.app.controller;


import com.employee.app.dto.Create;
import com.employee.app.dto.EmployeeDTO;
import com.employee.app.dto.Update;
import com.employee.app.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/create", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@Validated(Create.class)
	public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDto) {
		log.info("Inside create employee endpoint!");
		return employeeService.createEmployee(employeeDto);
	}

	@PutMapping(value = "/update", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@Validated(Update.class)
	public EmployeeDTO updateEmployee(@Valid @RequestBody EmployeeDTO employeeDto) {
		log.info("Inside update employee endpoint!");
		return employeeService.updateEmployee(employeeDto);
	}
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		log.info("Inside get all employees endpoint!");
		List<EmployeeDTO> employeeList = employeeService.getEmployees();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	@GetMapping(value = "/")
	@ResponseStatus(HttpStatus.OK)
	public EmployeeDTO getEmplyeeByEmployeeId(@RequestParam(value = "employeeId") long employeeId) {
		log.info("Inside get employee by Id endpoint!, employeeId:: {}", employeeId);
		return employeeService.findByEmployeeId(employeeId);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteEmployee(@RequestParam(value = "employeeId") long employeeId) {
		log.info("Inside employee delete endpoint!");
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
	
}

