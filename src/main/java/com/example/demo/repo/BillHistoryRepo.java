package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BillHistory;
import com.example.demo.entity.BillingStatus;

public interface BillHistoryRepo extends JpaRepository<BillHistory, String> {
	public List<BillHistory> findByMobileno(Long mobileno);

}
