package com.example.demo.entity;

import java.sql.Date;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	private String accNo;
	private String fname;
	private String lname;
	private  Long mobileno;
	private String  email;
	private String address;
	private String city;
	private String state;
	private Integer zip;
	private String service;
	private Date dob;
	private String plans;
	 
	
}
