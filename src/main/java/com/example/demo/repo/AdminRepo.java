package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdminEntity;
import java.util.List;


public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {
      public  AdminEntity findByAccNo(String accNo);
      public AdminEntity findByMobileno(Long mobileno);

}
