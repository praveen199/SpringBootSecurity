package com.spring.boot.security.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.boot.security.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}