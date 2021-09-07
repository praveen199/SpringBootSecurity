package com.spring.boot.security.controller;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.boot.security.data.EmployeeRepository;
import com.spring.boot.security.model.Employee;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/getAllEmployee")
	public Iterable<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/getOneEmployee/{id}")
	public Optional<Employee> getOneEmployee(@PathVariable int id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(!employeeOptional.isPresent()) {
			System.out.println("Employee Not Found");
		}
		return employeeOptional;
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee e) {
		Employee emp = employeeRepository.save(e);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee e,@PathVariable int id) {
		Optional<Employee> employeeOptional  = employeeRepository.findById(id);
		
		if (!employeeOptional.isPresent()) {
			System.out.println("Employee Not Found");
		} else {
			e.setId(id);
		}
		employeeRepository.save(e);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteOneEmployee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		employeeRepository.deleteById(id);
		return "One Item Deleted...";
	}

}
