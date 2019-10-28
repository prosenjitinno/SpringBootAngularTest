package com.example.SpringBootAngular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootAngular.dao.EmployeeRepository;
import com.example.SpringBootAngular.exception.ResourceNotFoundException;
import com.example.SpringBootAngular.model.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("employee")
public class EmployeeController {

	@Autowired 
	public EmployeeRepository employeeRepository;
	
	@RequestMapping ("getAll")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
				
	}
	
	@RequestMapping ("getById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable (value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId) );
		return ResponseEntity.ok().body(employee);
	}
	
	@RequestMapping ("create")
	public Employee createEmployee(@Valid @RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@RequestMapping ("update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable (value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee = employeeRepository.findById(employeeId).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		employee.setName(employeeDetails.getName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setAddress(employeeDetails.getAddress());
		
		final Employee updateEmployee = employeeRepository.save(employee);
		return  ResponseEntity.ok().body(updateEmployee);		
	}
	
	@RequestMapping ("delete/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable (value = "id") Long employeeId) throws ResourceNotFoundException{
		
		Employee employee = employeeRepository.findById(employeeId).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
				
		employeeRepository.delete(employee);;
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", true);
		return response;
	}
}
