package com.employee.app.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.constants.ErrorEmployeeApiMessage;
import com.employee.app.domain.Employee;
import com.employee.app.dto.EmployeeDTO;
import com.employee.app.exception.EmployeeApiException;
import com.employee.app.repository.EmployeeRepository;
import com.employee.app.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
		log.info("Creating new employee with employeeDto: [{}]", employeeDto);
		employeeRepository.findByEmail(employeeDto.getEmail()).ifPresent(e -> {
			throw new EmployeeApiException(ErrorEmployeeApiMessage.EMAIL_ALREADY_EXIST);
		});

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		employeeRepository.save(employee);
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		log.info("Trying to get all employees");
		List<Employee> employeeList = employeeRepository.findAll();
		return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>() {
		}.getType());
	}

	@Override
	public EmployeeDTO findByEmployeeId(long employeeId) {
		log.info("Trying to get employee by employeeId: [{}]", employeeId);
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeApiException(ErrorEmployeeApiMessage.INVALID_EMPLOYEE_ID));
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDto) {
		log.info("Updating existing employee with employeeDto: [{}]", employeeDto);
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeDto.getId())
				.orElseThrow(() -> new EmployeeApiException(ErrorEmployeeApiMessage.INVALID_EMPLOYEE_ID));
		employee.setFirstName((employeeDto.getFirstName()));
		employee.setLastName((employeeDto.getLastName()));
		employee.setMobile((employeeDto.getMobile()));
		employeeRepository.save(employee);
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	@Override
	public void deleteEmployee(long employeeId) {
		log.info("Trying to delete employee for employeeId: [{}]", employeeId);
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId)
				.orElseThrow(() -> new EmployeeApiException(ErrorEmployeeApiMessage.INVALID_EMPLOYEE_ID));
		employee.setIsDeleted(Boolean.TRUE);
		employeeRepository.save(employee);
	}

}
