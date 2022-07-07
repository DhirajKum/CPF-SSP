package com.techm.fci.cpf.service;

public interface OTPService {

	boolean sendOtpSms(String mobile, String empNum, String empName);
	boolean verifyOtp(String mobile, String otp);
	
	
}
