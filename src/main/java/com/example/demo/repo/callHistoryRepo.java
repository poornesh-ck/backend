package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CallDetails;
import java.util.List;


public interface callHistoryRepo extends JpaRepository<CallDetails, Integer> {
	public List<CallDetails> findByMobileno(Long mobileno);

}
