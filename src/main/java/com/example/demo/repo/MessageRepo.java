package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MessageUtility;
import java.util.List;


public interface MessageRepo extends JpaRepository<MessageUtility, Integer> {
	List<MessageUtility> findByMobileno(Long mobileno);

}
