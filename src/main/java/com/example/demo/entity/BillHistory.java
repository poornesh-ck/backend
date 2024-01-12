package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
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
public class BillHistory {
	@Id
	private String billno;
	private Long mobileno;
	private Date billingDate;
	private Double currentcharge;
	private Double paymentdue; 
	
	
	

}
