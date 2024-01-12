package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FeedBack;
import java.util.List;


public interface FeedbackRepo extends JpaRepository<FeedBack, Integer> {
	List<FeedBack> findByPhoneno(Long phoneno);

}
