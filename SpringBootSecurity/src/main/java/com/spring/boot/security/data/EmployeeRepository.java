package com.spring.boot.security.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.boot.security.model.Employee;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

}