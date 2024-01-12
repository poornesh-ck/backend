package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.DataUtilization;
import com.example.demo.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, String> {
	public List<Notification> findByMobileno(Long mobileno);
	
	public void deleteBynid(Integer nid);


}
