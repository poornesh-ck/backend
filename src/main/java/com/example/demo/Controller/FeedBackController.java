package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.NotificationService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.Status;
import com.example.demo.entity.DataUtilization;
import com.example.demo.entity.FeedBack;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/FeedBack")
@RequiredArgsConstructor
public class FeedBackController {
	
	private final UserService uService;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/feedbacksave")
	public Status feedBack(@RequestBody FeedBack feedBack) {
		
		String msg=uService.feedBcak(feedBack);

		return new Status(msg);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/Viewfeedback")
	    public List<FeedBack> getDataDetails() {
	        return uService.ViewFeedBack();
	    }

	

}
