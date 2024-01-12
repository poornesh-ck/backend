package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.NotificationService;
import com.example.demo.Service.UserService;
import com.example.demo.dto.Status;
import com.example.demo.entity.CallDetails;
import com.example.demo.entity.DataUtilization;
import com.example.demo.entity.MessageUtility;
import com.example.demo.entity.Notification;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {
	private final UserService uService;
	private final NotificationService notifyService;
//	data Util
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/DataUtil")
	public Status DataUtil(@RequestBody DataUtilization dUtil) {
		
		String msg=uService.dataUtil(dUtil);

		return new Status(msg);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/datautilHistory/{mobileNo}")
	    public List<DataUtilization> getDataDetails(@PathVariable Long mobileNo) {
	        return uService.getdataDetails(mobileNo);
	    }

	
//	notification
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/Notifypost")
	public Status Notifypost(@RequestBody Notification notify) {
		String msg=notifyService.notifcationStore(notify);

		return new Status(msg);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/Notification/{mobileNo}")
	    public List<Notification> getNotification(@PathVariable Long mobileNo) {
	        return notifyService.getNotification(mobileNo);
	 }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteMapping/{notifcationId}")
    public void deleteNotification(@PathVariable Integer notifcationId) {
		System.out.println(notifcationId);
		notifyService.deleteNotification(notifcationId);
	}
	
//	Messaging
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/Message")
	public Status MessageUtil(@RequestBody MessageUtility mUtil) {
		
		String msg=uService.MessageUtil(mUtil);

		return new Status(msg);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/MessageDetails/{mobileNo}")
	    public List<MessageUtility> getMessageDetails(@PathVariable Long mobileNo) {
	        return uService.getMessageDetails(mobileNo);
	    }
	
	
	
	
	
	
}
