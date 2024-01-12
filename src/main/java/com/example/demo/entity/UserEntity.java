package com.example.demo.entity;

import java.sql.Date;

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
public class UserEntity {

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer uid;
	@Id
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
	private Double data;
	private Double dataleft;
	private Double dataused;
	private Date validfrom;
	private Date validto;
	private Integer daysleft;
	private Date billingdate;
	private Boolean isfresher;
	private String paymentstatus;
	private Double paymentdue; 
	private Integer SmsCount;
	

}
