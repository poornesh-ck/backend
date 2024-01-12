package com.example.demo.Service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Conguration.TwilioConfig;
import com.example.demo.entity.DataUtilization;
import com.example.demo.entity.Notification;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.DataUtilRepo;
import com.example.demo.repo.NotificationRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.repo.callHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final UserRepo uRepo;
	private final NotificationRepo notifyRepo;
	
	public  Double calculatePercentage(Double number, Double total) {
        if (total <= 0) {
            throw new IllegalArgumentException("Total must be greater than zero");
        }
        return (number / total) * 100.0;
    }
	public static int generateRandomTwoDigitNumber() {
        // Create a Random object
        Random random = new Random();

        // Generate a random two-digit number between 10 and 99 (inclusive)
        int randomNumber = random.nextInt(90) + 10;

        return randomNumber;
    }
	public String notifcationStore(Notification notification) {
		
		Notification notify=Notification.builder().nid(generateRandomTwoDigitNumber()).alert(notification.getAlert()).message(notification.getMessage()).mobileno(notification.getMobileno()).notificationName(notification.getNotificationName()).build();
		
		String msg;
		try {
			notifyRepo.save(notify);
			
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(notify);
		return msg;
	}
	
	public List<Notification>getNotification(Long mobileNo){
		
		List<Notification> notifyList = notifyRepo.findByMobileno(mobileNo);
		return notifyList;
	}
	
	 @Transactional
	public void deleteNotification(Integer notifcationId){
		
		System.out.println(notifcationId);
			notifyRepo.deleteBynid(notifcationId);
		
		
		
	}
	
	

}
