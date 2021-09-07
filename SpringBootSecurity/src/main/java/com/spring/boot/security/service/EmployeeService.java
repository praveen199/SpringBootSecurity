package com.spring.boot.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.spring.boot.security.data.EmployeeRepository;
import com.spring.boot.security.exception.RecordNotFoundException;
import com.spring.boot.security.model.Employee;

@Service
public class EmployeeService {
     
    @Autowired
    private EmployeeRepository repository;
     
    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }
     
    public Employee getEmployeeById(Integer id) throws RecordNotFoundException {
        Optional<Employee> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public Optional<Employee> createOrUpdateEmployee(Employee emp) throws RecordNotFoundException {
        Optional<Employee> employee = repository.findById(emp.getId());
         
        if(employee.isPresent()){
            Employee newemployee = employee.get();
            newemployee.setId(emp.getId());
            newemployee.setFirst_name(emp.getFirst_name());
            newemployee.setLast_name(emp.getLast_name());
            newemployee.setEmail(emp.getEmail());
            newemployee.setRoll_no(emp.getRoll_no());
            
            newemployee = repository.save(newemployee);
        }
		return employee;
    }
     
    public void deleteEmployeeById(Integer id) throws RecordNotFoundException {
        Optional<Employee> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
