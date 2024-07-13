package com.learning.demo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.learning.demo.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeByID(Long employeeID);

	public Employee saveEmployee(@Valid Employee employee);

	public Employee updateEmployee(Employee empDetails);

	public void deleteEmployee(Employee employee);
}
