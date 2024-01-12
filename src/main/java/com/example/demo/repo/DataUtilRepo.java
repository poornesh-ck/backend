package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CallDetails;
import com.example.demo.entity.DataUtilization;

public interface DataUtilRepo extends JpaRepository<DataUtilization, Integer> {
	public List<DataUtilization> findByMobileno(Long mobileno);

}
