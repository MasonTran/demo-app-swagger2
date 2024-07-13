package com.learning.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.learning.demo.model.Employee;
import com.learning.demo.model.EmployeeResponse;
import com.learning.demo.service.EmployeeService;
import com.learning.demo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.demo.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "View a List of available Employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Retrieved List"),
			@ApiResponse(code = 401, message = "You are not Authorized to View the Resource"),
			@ApiResponse(code = 403, message = "Accessing the Resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The Resource you were trying to Reach is Not Found") })
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@ApiOperation(value = "Get Employee by an ID")
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(
			@ApiParam(value = "Employee Id to retrieve the Employee Object", required = true) @PathVariable(value = "id") Long employeeID)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeByID(employeeID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found for the ID: " + employeeID));

		EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(), employee.getEmailID(), employee.getFirstName(), employee.getLastName());
		return ResponseEntity.ok().body(employeeResponse);
	}

	@ApiOperation(value = "Add New Employee Object")
	@PostMapping("/employees")
	public Employee createEmployee(
			@ApiParam(value = "Save Employee Object in Database Table", required = true) @Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@ApiOperation(value = "Update an Employee")
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(
			@ApiParam(value = "Employee ID to update Employee Object", required = true) @PathVariable(value = "id") Long employeeID,
			@ApiParam(value = "Employee Object to Update", required = true) @Valid @RequestBody Employee employeeDetail)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeByID(employeeID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found for the ID: " + employeeID));

		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setEmailID(employeeDetail.getEmailID());

		final Employee updatedEmployee = employeeService.updateEmployee(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@ApiOperation("Delete an Employee")
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(
			@ApiParam(value = "Employee ID from which the Employee Object will be deleted from Database", required = true) @PathVariable(value = "id") Long employeeID)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeByID(employeeID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found for the ID: " + employeeID));

		employeeService.deleteEmployee(employee);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
