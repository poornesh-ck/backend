package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.dto.OtpResponseDto;
import com.example.demo.dto.OtpValidationRequest;
import com.example.demo.dto.Status;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.OtpRequest;
import com.example.demo.entity.UserEntity;
//import com.example.demo.entity.Product;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.CallDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
	
	private final UserService uService;
	
	//register

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/send-otp")
	public Status sendOtp(@RequestBody String otpRequest) {
		log.info("inside send-otp::"+otpRequest);
		String msg = uService.sendSMS(otpRequest);
		
		return new Status(msg);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/validate-otp")
	public Status validateOtp(@RequestBody String otpValidationRequest) {
		System.out.println("inside validateOtp::"+otpValidationRequest+""+otpValidationRequest);
		String msg=uService.validateOtp(otpValidationRequest);
		return new Status(msg);	
	}
	
	//Login
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public Status LoginOtp(@RequestBody String otpRequest) {
		log.info("inside send-otp::"+otpRequest);
		String msg = uService.LoginSms(otpRequest);
		
		return new Status(msg);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login-validate")
	public Status ValidateOtp(@RequestBody String otpValidationRequest) {
	
		String msg = uService.loginValidate(otpValidationRequest);
		
		return new Status(msg);
	}

	
	
	




// Add user in db	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	public Status addUser(@RequestBody UserDto ud) {
		
		String msg=uService.addUser(ud);

		return new Status(msg);
	}	

	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/users/{mobileNo}")
	    public UserEntity getUserDetails(@PathVariable Long mobileNo) {
	        return uService.userDetails(mobileNo);
	    }


//	call history	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/calldetails")
	public Status callDetails(@RequestBody CallDetails cdetails) {
		
		String msg=uService.callHistory(cdetails);

		return new Status(msg);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/callHistory/{mobileNo}")
	    public List<CallDetails> getcallDetails(@PathVariable Long mobileNo) {
	        return uService.getcallDetails(mobileNo);
	    }


}
