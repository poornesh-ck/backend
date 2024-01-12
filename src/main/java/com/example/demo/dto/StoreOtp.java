package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreOtp {
	
	public static String getGeneratedOtp() {
		return generatedOtp;
	}

	public static void setGeneratedOtp(String generatedOtp) {
		StoreOtp.generatedOtp = generatedOtp;
	}

	private static String generatedOtp ;
	
	private Long PhoneNumber;

}
