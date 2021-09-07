package com.spring.boot.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spring_boot_employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="Roll_No")
	private Integer roll_no;
	
	@Column(name="First_Name")
	private String first_name;
	
	@Column(name="Last_Name")
	private String last_name;
	
	@Column(name="Email")
	private String email;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Integer getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(Integer roll_no) {
		this.roll_no = roll_no;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 
}
