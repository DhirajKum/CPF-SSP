package com.techm.fci.cpf.service;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

public interface OTPService {

	boolean sendOtpSms(String mobile, String empNum, String empName);
	boolean verifyOtp(String mobile, String otp);
	
	
}
