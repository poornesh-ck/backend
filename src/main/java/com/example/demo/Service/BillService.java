package com.example.demo.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.demo.Conguration.TwilioConfig;
import com.example.demo.entity.BillHistory;
import com.example.demo.entity.BillingStatus;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.BillHistoryRepo;
import com.example.demo.repo.BillRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.repo.callHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {
	private final BillRepo billrepo;
	private final UserRepo uRepo;
	private final BillHistoryRepo billhistoryrepo;
	
	public BillingStatus GenerateBill(Long mobileno) {
		UserEntity uEntity = uRepo.findByMobileno(mobileno);
		Double charge= Double.parseDouble(uEntity.getPlans());
		 BillingStatus bs;
		if(uEntity.getPaymentstatus().equals("paid")&& uEntity.getIsfresher().equals(true)) {
			 bs=BillingStatus.builder().currentcharge(charge).duedate(addDaysToSqlDate(uEntity.getValidto(), 10))
					.fromdate(uEntity.getValidfrom()).mobileno(uEntity.getMobileno()).service(uEntity.getService()).receivedPayment(charge)
					.todate(uEntity.getValidto()).totalamount(uEntity.getPaymentdue()+charge+taxCalculate(charge)).tax(taxCalculate(charge)).build();
			
			billrepo.save(bs);
		}
		else if(uEntity.getPaymentstatus().equals("unpaid")) {
			 bs=BillingStatus.builder().currentcharge(charge).duedate(addDaysToSqlDate(uEntity.getValidto(), 10))
						.fromdate(uEntity.getValidfrom()).mobileno(uEntity.getMobileno()).service(uEntity.getService()).receivedPayment(0.0)
						.todate(uEntity.getValidto()).totalamount(uEntity.getPaymentdue()+charge+taxCalculate(charge)).tax(taxCalculate(charge)).build();
				
				billrepo.save(bs);
			
			 
//				
		}
		else {
//			bs=BillingStatus.builder().build();
			 bs=BillingStatus.builder().currentcharge(charge).duedate(addDaysToSqlDate(uEntity.getValidto(), 10))
						.fromdate(uEntity.getValidfrom()).mobileno(uEntity.getMobileno()).service(uEntity.getService()).receivedPayment(0.0)
						.todate(uEntity.getValidto()).totalamount(0.0).tax(0.0).build();
			
		}
		return bs;
		
		
		
	}
	
	
	
	
	public Double taxCalculate(Double charge) {
		 Double tax = charge * 0.10;
		    return tax;
	}
	
	
	
	
	public  java.sql.Date addDaysToSqlDate(java.sql.Date startDate, int daysToAdd) {
		  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        java.sql.Date newDate = new java.sql.Date(calendar.getTimeInMillis());

        return newDate;
    }
	public void updatepaymentStatus(Long mobileno) {
		UserEntity  us = uRepo.findByMobileno(mobileno);
//		List<BillingStatus> bs= billrepo.findByMobileno(mobileno);
//		for(BillingStatus bill:bs) {
//			bill.get
//			
//		}
		us.setPaymentstatus("paid");
		us.setPaymentdue(0.0);
		uRepo.save(us);
		
//		billing History		
		BillHistory billHistory=BillHistory.builder().billno(generateBillNumber(us.getBillingdate())).billingDate(us.getBillingdate()).currentcharge(Double.parseDouble(us.getPlans())).paymentdue(us.getPaymentdue()).mobileno(us.getMobileno()).build();
		billhistoryrepo.save(billHistory);
	}
	
	 public static String generateBillNumber(Date billDate) {
	        // Generate 3 random alphabets
	        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuilder randomAlphabets = new StringBuilder();
	        Random random = new Random();
	        for (int i = 0; i < 3; i++) {
	            char randomAlphabet = alphabets.charAt(random.nextInt(alphabets.length()));
	            randomAlphabets.append(randomAlphabet);
	        }
	        // Format the bill date in YYYYMMDD format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String formattedDate = dateFormat.format(billDate);
	        // Generate a random 10-digit number
	        long randomNumber = 1000000000L + (long) (Math.random() * 9000000000L);
	        // Combine alphabets, date, and random number to create the 16-digit bill number
	        String billNumber = randomAlphabets.toString() + formattedDate + randomNumber;
	        return billNumber;
	    }
	
	public void updateDaysLeft(Long mobileNo) {
		UserEntity  us=uRepo.findByMobileno(mobileNo);
		us.setDaysleft(0);
		uRepo.save(us);
	}
	public void updateCycle(Long mobileNo) {
		updateDaysLeft(mobileNo);
		UserEntity  us=uRepo.findByMobileno(mobileNo);
		if(us.getDaysleft().equals(0)) {
			us.setValidfrom(us.getBillingdate());
			if(us.getIsfresher().equals(true)) {
				us.setPaymentdue(us.getPaymentdue());
			}
			else {
				if(us.getPaymentstatus().equals("paid")) {
					us.setPaymentdue(us.getPaymentdue());
					
					
				}
				else {
					us.setPaymentdue(Double.parseDouble(us.getPlans())+us.getPaymentdue());
					
				}
				
				
			}
			us.setDataleft(us.getData());
			us.setDataused(0.0);
			us.setValidto(addDaysToSqlDate(us.getBillingdate(),30));
			us.setBillingdate(addDaysToSqlDate(us.getBillingdate(), 31));
			us.setPaymentstatus("unpaid");
			
			
			us.setDaysleft(30);
//			us.setPaymentdue(Double.parseDouble(us.getPlans()));
			us.setIsfresher(false);
			uRepo.save(us);
			
		}
		
		
	}
	
	

	public Integer expiringDaycalc(Date today,Date expiriydate) {

		     long diffInMillies = expiriydate.getTime() - today.getTime();

		     long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		     System.out.println("days inbetween "+daysBetween);

		     return (int)daysBetween;

	}
	
	public java.sql.Date validFromDate() {
		 Date currentDate = new Date(System.currentTimeMillis());
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(currentDate);
	        java.sql.Date currDate = java.sql.Date.valueOf(formattedDate);
	         return currDate;
	        
	}
	
//	get billHistory
	public List<BillHistory> getbillHistory(Long mobileNo) {
		List<BillHistory> bs=billhistoryrepo.findByMobileno(mobileNo);
		return bs;
		
	
	}
	
	
	

	
	
	

}
