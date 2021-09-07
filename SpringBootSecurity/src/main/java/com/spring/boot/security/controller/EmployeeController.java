package com.spring.boot.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.security.exception.RecordNotFoundException;
import com.spring.boot.security.model.Employee;
import com.spring.boot.security.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	@Autowired
	private EmployeeService service;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize,
														  @RequestParam(defaultValue = "email") String sortBy) {
		List<Employee> list = service.getAllEmployees(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		Employee entity = service.getEmployeeById(id);
		return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Employee> createOrUpdateEmployee(Employee employee) throws RecordNotFoundException {
		Optional<Employee> updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return HttpStatus.FORBIDDEN;
	}

}
