package com.example.demo.entity;

import java.sql.Date;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

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
public class BillingStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	private Long mobileno;
	
	private String billingno;
	private Double currentcharge;
	private Date duedate;
	private Date fromdate;
	private Double receivedPayment;
	private String service;
	private Double tax;
	private Date todate;
	private Double totalamount;
	
	 	 	 

	 

}
