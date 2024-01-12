package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BillService;
import com.example.demo.Service.UserService;
import com.example.demo.entity.BillHistory;
import com.example.demo.entity.BillingStatus;
import com.example.demo.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillingController {
	
	private final BillService billService;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/BillStatus/{mobileNo}")
	    public BillingStatus getUserDetails(@PathVariable Long mobileNo) {
	        return billService.GenerateBill(mobileNo);
	    }
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updatepaymentStatus")
	public void updatepaymentstatus(@RequestBody Long mobileNo) {

		billService.updatepaymentStatus(mobileNo);	

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updatecycle")
	public void updatecycle(@RequestBody Long mobileNo) {
		System.out.println(mobileNo);
		billService.updateCycle((mobileNo));
		

		

	}
//	billHistory
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/BillHistory/{mobileNo}")
	    public List<BillHistory>  getbillHistory(@PathVariable Long mobileNo) {
	        return billService.getbillHistory(mobileNo);
	    }
	
	

}
