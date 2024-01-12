package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BillingStatus;
import com.example.demo.entity.UserEntity;

public interface BillRepo extends JpaRepository<BillingStatus, Integer> {
	public BillingStatus findByMobileno(Long mobileno);

}
