package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserEntity;
import java.util.List;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByMobileno(Long mobileno);

}
