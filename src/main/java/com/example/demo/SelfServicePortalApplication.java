package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.Conguration.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableConfigurationProperties
@RequiredArgsConstructor
public class SelfServicePortalApplication {
	private final TwilioConfig twilioConfig;
	
	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	

	public static void main(String[] args) {
		SpringApplication.run(SelfServicePortalApplication.class, args);
	}

}
