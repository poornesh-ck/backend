package com.example.demo.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.stream.Collectors;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
//import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

 

import javax.mail.Authenticator;

import javax.mail.Message;
//import com.twilio.rest.api.v2010.account.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Conguration.TwilioConfig;
import com.example.demo.dto.OtpResponseDto;
import com.example.demo.dto.OtpStatus;
import com.example.demo.dto.OtpValidationRequest;
import com.example.demo.dto.StoreOtp;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.CallDetails;
import com.example.demo.entity.DataUtilization;
import com.example.demo.entity.FeedBack;
import com.example.demo.entity.MessageUtility;
import com.example.demo.entity.OtpRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.DataUtilRepo;
import com.example.demo.repo.FeedbackRepo;
import com.example.demo.repo.MessageRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.repo.callHistoryRepo;
import com.twilio.rest.lookups.v1.PhoneNumber;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
	
	private final AdminRepo r;
	private final UserRepo uRepo;
	private final callHistoryRepo cRepo;
	private final DataUtilRepo dataRepo;
	private final MessageRepo messageRepo;
	private final FeedbackRepo FeedRepo;
	private final TwilioConfig twilioConfig;
	private  static AdminEntity storedDetails=AdminEntity.builder().build();
	private  static UserEntity LoginDetails=UserEntity.builder().build();
	
	
	
	
	
	public String addUser(UserDto ud) {
	 AdminEntity u = AdminEntity.builder().accNo(accontNumberGenerate()).fname(ud.getFname())
				.lname(ud.getLname()).mobileno(ud.getMobileno()).email(ud.getEmail()).dob(ud.getDob())
				.address(ud.getAddress()).city(ud.getCity()).state(ud.getState()).zip(ud.getZip()).service(ud.getService()).plans(ud.getPlans()).build();		

		String msg;
		try {
			r.save(u);
			sendMail(u.getEmail(), u.getAccNo(), u.getFname());
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(u);
		return msg;
	}
	
//	callDetails
	Double callcost=0.0;
	public String callHistory(CallDetails cd) {
		CallDetails cdetails=CallDetails.builder().callcost(callcost).dialedno(cd.getDialedno()).mobileno(cd.getMobileno()).duration(cd.getDuration()).time(cd.getTime()).build();
		String msg;
		try {
			cRepo.save(cdetails);
			
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(cdetails);
		return msg;
	}
	
	

	public void sendMail(String mail,String accno, String fname) throws UnsupportedEncodingException, MessagingException {

			System.out.println("Outlook Email Start");

	 

	        String smtpHostServer = "smtp.office365.com";

	        final String emailID = "prosync80329@outlook.com"; //Outlook email address

	        final String password = "Jerish@2002"; // outlook account password

	        String toEmail = mail;

	        String subject = "Subject: 🎉 Congratulations! You're Registered and Ready to Go! 🚀";

	        String messageBody = " Dear "+fname +",\r\n"
	        		+ "\r\n"
	        		+ "We are thrilled to welcome you to our community! 🌟\r\n"
	        		+ "\r\n"
	        		+ "You've successfully registered, and your SIM card is now activated, so you're all set to enjoy the incredible services and benefits that await you.\r\n"
	        		+ "\r\n"
	        		+ "Here are the details you'll need to get started:\r\n"
	        		+ "\r\n"
	        		+ "📋 Account Information:\r\n"
	        		+ "\r\n"
	        		+ "Account Number: "+ accno+"\r\n"
//	        		+ "Registered Email: [User's Email Address]\r\n"
	        		+ "SIM Card Activation: Complete ✅\r\n"
	        		+ "🎁 What's in Store for You:\r\n"
	        		+ "\r\n"
	        		+ "Lightning-fast mobile data\r\n"
	        		+ "Unlimited calls and texts\r\n"
	        		+ "Exclusive offers and promotions\r\n"
	        		+ "And so much more!\r\n"
	        		+ "We're excited to have you on board, and we promise to deliver an outstanding experience. If you have any questions or need assistance, don't hesitate to reach out to our dedicated support team at [Support Email Address] or call [Support Phone Number].\r\n"
	        		+ "\r\n"
	        		+ "Stay connected, stay happy, and enjoy the journey ahead! 📱✨\r\n"
	        		+ "\r\n"
	        		+ "Thank you for choosing us as your mobile service provider. Here's to many great moments together!\r\n"
	        		+ "\r\n"
	        		+ "Warm regards,\r\n"
	        		+ "\r\n"
	        		+ "Aetherix\r\n"
	        		
	        		+ "http://localhost:4200\r\n"
	        		+ "\r\n"
	        		+ "P.S. Follow us on social media for the latest updates, tips, and exclusive offers! 📢\r\n"
	        		+ "\r\n"
	        		+ "Facebook: [Facebook Page URL]\r\n"
	        		+ "Twitter: [Twitter Page URL]\r\n"
	        		+ "Instagram: [Instagram Page URL]"   ; //content

	        Properties props = new Properties();

	        props.put("mail.smtp.host", smtpHostServer);

	        props.put("mail.smtp.port", "587");

	        props.put("mail.smtp.auth", "true");

	        props.put("mail.smtp.starttls.enable", "true");

	 

	        Session session = Session.getInstance(props, new Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication(emailID, password);

	            }

	        });

	 

	        sendEmail(session, emailID, subject, messageBody,toEmail);

		}

		public static void sendEmail(Session session, String fromEmail, String subject, String body,String toEmail) throws MessagingException, UnsupportedEncodingException{

	        

	            MimeMessage msg = new MimeMessage(session);

	            //set message headers

	            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

	            msg.addHeader("format", "flowed");

	            msg.addHeader("Content-Transfer-Encoding", "8bit");

	 

	            msg.setFrom(new InternetAddress(fromEmail, "Aetherix"));

	 

	            msg.setReplyTo(InternetAddress.parse(toEmail, false));

	 

	            msg.setSubject(subject, "UTF-8");

	 

	            msg.setText(body, "UTF-8");

	 

	            msg.setSentDate(new Date());

	 

	            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

	            System.out.println("Message is ready");

	            Transport.send(msg);

	 

	            System.out.println("EMail Sent Successfully!!");

	        

	      

	        	

	        

		}

	 
//	account number generation method
	public String accontNumberGenerate() {

		Random rand=new Random();

		long accno=  (long) (Math.pow(11, 11)+ rand.nextInt(99));

		String accountNumber= Long.toString(accno);

		System.out.println(accountNumber);

		return accountNumber;

	}
//	send sms method
	
	public String sendSMS(String otpRequest) {
		OtpResponseDto otpResponseDto = null;
		String value;
		try {
		   storedDetails = r.findByAccNo(otpRequest);
			if(storedDetails != null) {
				com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber("+91"+storedDetails.getMobileno().toString());
				com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumber());//from
				System.out.println(to);
				String otp=generationOtp();
				System.out.println(otp);
				String otpMessage="hi daa "+otp;
				com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
			     to,
			      from,
			      otpMessage)
			    .create();
		
				
//		StoreOtp ot=StoreOtp.builder().(otp).PhoneNumber(Long.parseLong(to.toString())).build();
		 StoreOtp sto = new StoreOtp();
				 sto.setGeneratedOtp(otp);
				System.out.println(sto);
;				 value= "Deliverd";
//				otpResponseDto = new OtpResponseDto("",otpMessage);
			}
			else {
				System.out.println("not exist");
				 value="Not Exist";
//				otpResponseDto = new OtpResponseDto(OtpStatus.FAILED,"Invalid AccountNumber");
			}
			
			

			
			
			
			

			
		} catch (Exception e) {
			value="Failed";
//			otpResponseDto = new OtpResponseDto(OtpStatus.FAILED,e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return value;
		
		
		
	}
	//generate 6 digit otp
	public String generationOtp(){
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
		
	}
	
	public Double calculateData(String plans) {
		Double data=0.0;
		if(storedDetails.getPlans().equals("399")) {
			 data=75.0;
			
		}
		else if (storedDetails.getPlans().equals("699")) {
			data=100.0;
		}
		else if (storedDetails.getPlans().equals("999")) {
			data=150.0;
			
		}
		else if (storedDetails.getPlans().equals("1699")) {
			 data=0.0;
		}
		else {
			data=100.0;

		}
	return data;
		
	}
	public java.sql.Date validFromDate() {
		 Date currentDate = new Date(System.currentTimeMillis());
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(currentDate);
	        java.sql.Date currDate = java.sql.Date.valueOf(formattedDate);
	         return currDate;
	        
	}
	
	public  java.sql.Date addDaysToSqlDate(java.sql.Date startDate, int daysToAdd) {
  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        java.sql.Date newDate = new java.sql.Date(calendar.getTimeInMillis());

        return newDate;
    }
	
//	validate sms
	public String validateOtp(String otpValidationRequest) {
	
		
		System.out.println("-------");
		System.out.println(new StoreOtp().getGeneratedOtp());
		System.out.println("-------");
		System.out.println(otpValidationRequest);
		
	
		try {
			if (new StoreOtp().getGeneratedOtp().equals(otpValidationRequest)) {
				
				
				UserEntity user= UserEntity.builder().accNo(storedDetails.getAccNo()).fname(storedDetails.getFname()).lname(storedDetails.getLname())
						.dob(storedDetails.getDob()).email(storedDetails.getEmail()).address(storedDetails.getAddress())
						.mobileno(storedDetails.getMobileno()).city(storedDetails.getCity()).state(storedDetails.getState())
						.zip(storedDetails.getZip()).service(storedDetails.getService()).plans(storedDetails.getPlans()).data(calculateData(storedDetails.getPlans())).dataleft(calculateData(storedDetails.getPlans())).dataused(0.0)
						.validfrom(validFromDate()).validto(addDaysToSqlDate(validFromDate(),30)).daysleft(30).billingdate(addDaysToSqlDate(validFromDate(),31)).isfresher(true).paymentstatus("paid").paymentdue(0.0).SmsCount(0).build();
				System.out.println(user);
			uRepo.save(user);
				return "Otp valid";
			}
			else {
				return "Otp invalid";
				
			}
		} catch (Exception e) {
			return "Otp invalid";			
//			e.printStackTrace();
		}
			
		
		
		
	}
	
//	login ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String LoginSms(String otpRequest){
		String value;
		try {
			LoginDetails = uRepo.findByMobileno(Long.parseLong(otpRequest));
			if(LoginDetails != null) {
				com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber("+91"+LoginDetails.getMobileno().toString());
				com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumber());//from
				System.out.println(to);
				String otp=generationOtp();
				System.out.println(otp);
				String otpMessage="Hello,\r\n"
						+ "\r\n"
						+ "Your OTP (One-Time Password) for Aetherix  is: "+otp+".\r\n"
						+ "\r\n"
						+ "Please use this code to complete your authentication. Do not share this code with anyone. ";
				com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
			     to,
			      from,
			      otpMessage)
			    .create();
		
				
//		StoreOtp ot=StoreOtp.builder().(otp).PhoneNumber(Long.parseLong(to.toString())).build();
		 StoreOtp sto = new StoreOtp();
				 sto.setGeneratedOtp(otp);
				System.out.println(sto);
;				 value= "Deliverd";
//				otpResponseDto = new OtpResponseDto("",otpMessage);
			}
			else {
				System.out.println("not exist");
				 value="Not Exist";
			}
			
			

			
			
			
			

			
		} catch (Exception e) {
			System.out.println(e);
			value="Failed";

			
		}
		return value;
		
	}
	
	public String loginValidate(String otpValidationRequest) {

		System.out.println("-------");
		System.out.println(new StoreOtp().getGeneratedOtp());
		System.out.println("-------");
		System.out.println(otpValidationRequest);
	
		try {
			if (new StoreOtp().getGeneratedOtp().equals(otpValidationRequest)) {
			
//				UserEntity user= UserEntity.builder().accNo(storedDetails.getAccNo()).fname(storedDetails.getFname()).lname(storedDetails.getLname())
//						.dob(storedDetails.getDob()).email(storedDetails.getEmail()).address(storedDetails.getAddress())
//						.mobileno(storedDetails.getMobileno()).city(storedDetails.getCity()).state(storedDetails.getState())
//						.zip(storedDetails.getZip()).service(storedDetails.getService()).build();
//				uRepo.save(user);
				return "Otp valid";
			}
			else {
				return "Otp invalid";
				
			}
		} catch (Exception e) {
			System.out.println(e);
			return "Otp invalid";			// TODO Auto-generated catch block
			
		}
			
		
		
		
	}
	
	public UserEntity userDetails(Long mobileNo){
		return uRepo.findByMobileno(mobileNo);
	}
//	Retrieving call data
	public List<CallDetails>getcallDetails(Long mobileNo){
		
		List<CallDetails> callDetailsList = cRepo.findByMobileno(mobileNo);
		return callDetailsList;
	}
	
	
//	Data Utilization
	public String dataUtil(DataUtilization dUtil) {
		Double data=0.0;
		DataUtilization dDetails=DataUtilization.builder().data(dUtil.getData()).date(dUtil.getDate()).mobileno(dUtil.getMobileno()).time(dUtil.getTime()).type(dUtil.getType()).build();
		UserEntity  us=uRepo.findByMobileno(dUtil.getMobileno());
		data=convertToGB(dUtil.getData(), dUtil.getType());
		System.out.println(us.getData());
		us.setDataleft(us.getDataleft()-data);
		us.setDataused(data);
		uRepo.save(us);
		String msg;
		try {
			dataRepo.save(dDetails);
			
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(dDetails);
		return msg;
	}
	

	
	public static Double convertToGB(Double value, String unit) {
        double gbValue = 0.0;
        // Convert input value to lowercase for case-insensitive
        unit = unit.toLowerCase();
        switch (unit) {
            case "mb":
                gbValue = value / 1024; // 1 GB = 1024 MB
                break;
            case "kb":
                gbValue = value / (1024 * 1024); // 1 GB = 1024 * 1024 KB
                break;
            case "gb":
                gbValue = value; // Value is already in GB
                break;
            default:
                System.out.println("Invalid unit. Supported units: MB, KB, GB");
                break;
        }

        return gbValue;
    }
//	retrieving dataUtilHistory
	public List<DataUtilization>getdataDetails(Long mobileNo){
		
		List<DataUtilization> dUtilList = dataRepo.findByMobileno(mobileNo);
		return dUtilList;
	}
	
	
//	Message
	
	public static java.sql.Date getTodaysSqlDate() {
        // Get the current date
        LocalDate today = LocalDate.now();
        
        // Convert LocalDate to java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(today);
        
        return sqlDate;
    }
	
	public String MessageUtil(MessageUtility mUtil) {
		UserEntity  us = uRepo.findByMobileno(mUtil.getMobileno());
		java.sql.Date todayDate=getTodaysSqlDate();
		Boolean key;
		MessageUtility messageUtil=MessageUtility.builder().date(mUtil.getDate()).mobileno(mUtil.getMobileno()).time(mUtil.getTime())
				.messageSent(mUtil.getMessageSent()).recieverno(mUtil.getRecieverno()).messageBody(mUtil.getMessageBody()).build();

		String msg;
		Integer smscountuserdb=us.getSmsCount();
		System.out.println(smscountuserdb);
		System.out.println(todayDate);
		System.out.println( mUtil.getDate());
		System.out.println(mUtil.getMessageSent());
//		java.sql.Date d1=  ResultSet.getDate()
		int comparison = mUtil.getDate().compareTo(todayDate);
		System.out.println(comparison);
		
		if(mUtil.getMessageSent().equals(true)  ){
			System.out.println("first if");
			if(comparison==1){
				System.out.println("inside fuction");
				if(smscountuserdb < 10) {
					us.setSmsCount(us.getSmsCount()+1);
					System.out.println(us.getSmsCount());
					
				}
				else {
					key=false;
					System.out.println(key);
				}
			}
			
			
			
		}else {
			us.setSmsCount(0);
		}
		uRepo.save(us);
		try {
			messageRepo.save(messageUtil);
			
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(messageUtil);
		return msg;
	}
    public List<MessageUtility>getMessageDetails(Long mobileNo){
		
		List<MessageUtility> mUtilList = messageRepo.findByMobileno(mobileNo);
		return mUtilList;
	}
    
    
//    feedback
    public String feedBcak(FeedBack fd) {
		
    	FeedBack feed=FeedBack.builder().email(fd.getEmail()).message(fd.getMessage()).name(fd.getName()).phoneno(fd.getPhoneno()).build();
		
		String msg;
		try {
			FeedRepo.save(feed);
			
			msg="created";
		}catch(Exception e){
			msg="failed";
			
			
		}		
		System.out.println(feed);
		return msg;
	}
    
    public List<FeedBack>ViewFeedBack(){
		
  		List<FeedBack> findbyPhoneno = FeedRepo.findAll();
  		return findbyPhoneno;
  	}
    
	
	

	
	
	
	 

	 

}
