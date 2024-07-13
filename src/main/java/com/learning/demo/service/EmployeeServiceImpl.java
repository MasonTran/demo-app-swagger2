package com.learning.demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.demo.model.Employee;
import com.learning.demo.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeByID(Long employeeID) {
		return employeeRepository.findById(employeeID);
	}

	@Override
	public Employee saveEmployee(@Valid Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee empDetails) {
		return employeeRepository.save(empDetails);
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

}
