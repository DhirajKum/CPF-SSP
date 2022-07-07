package com.techm.fci.cpf.service.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.techm.fci.cpf.client.HttpClient;
import com.techm.fci.cpf.util.StringUtils;



@Component
@SuppressWarnings("deprecation")
@EnableAsync
public class SmsSender {
	
	private static String USERNAME = "fcilekha.sms";
	private static String PIN = "W3%24cV1%26tE5";
	private static String SIGNATURE = "FCIFAP";
	
	/**
	 * Send SMS
	 * @param mobileNumber
	 * @throws UnsupportedEncodingException 
	 */
	@Async
	public Boolean sendSms(String mobileNumber, String message) throws UnsupportedEncodingException{
		Boolean messageSent = Boolean.FALSE;
		mobileNumber = getValidatedNumber(mobileNumber);
		if(StringUtils.isNotNull(mobileNumber)){
			//Now we are going send sms
			String encodedMessage = message.replace(" ", "%20");
			//String encodedMessage = URLEncoder.encode(message);
			String smsHttpGetUrl = getSmsHTTPGetUrl(USERNAME, mobileNumber, encodedMessage);
			String response = HttpClient.getHttpPage(smsHttpGetUrl);
			System.out.println("Send SMS response = " + response);
			//HttpClient.getHttpPage(smsHttpGetUrl);
			messageSent = Boolean.TRUE;
		}
		return messageSent;
	}
	
	/**
	 * Send SMSes
	 * @param mobileNumbers
	 * @param message
	 */
	@Async
	public Boolean sendSmses(String mobileNumbers, String message){
		Boolean messageSent = Boolean.FALSE;
		//Guard Clause
		if(StringUtils.isNull(mobileNumbers)) return messageSent;
		String validatedNumbers = new String("");
		String[] numbers = mobileNumbers.split(",");
		for(String number : numbers){
			String validatedNumber = getValidatedNumber(number);
			if(StringUtils.isNotNull(validatedNumber)){
				validatedNumbers += validatedNumber + ",";
			}
		}
		validatedNumbers = StringUtils.removeLastComma(validatedNumbers);
		if(StringUtils.isNotNull(validatedNumbers)){
			//Now we are going send sms
			String encodedMessage = URLEncoder.encode(message);
			String smsHttpGetUrl = getSmsHTTPGetUrl(USERNAME, validatedNumbers, encodedMessage);
			String response = HttpClient.getHttpPage(smsHttpGetUrl);
			System.out.println("Send SMSes response = " + response);
			//HttpClient.getHttpPage(smsHttpGetUrl);
			messageSent = Boolean.TRUE;
		}
		return messageSent;
	}
	
	/**
	 * Returns validated number
	 */
	public String getValidatedNumber(String mobileNumber){
		//Guard Clause
		if(StringUtils.isNull(mobileNumber)) return null;
		String validNumber = StringUtils.getOnlyNumbers(mobileNumber);
		if(validNumber.startsWith("0")){
			validNumber = validNumber.substring(1, validNumber.length());
		}
		if(validNumber.length() == 10){
			//This is now a India number - add a prefix of 91
			validNumber = "91" + validNumber; 
		}
		//Till now if 91 prefix is not added then it will be added automatically
		if(validNumber.length() == 12 && validNumber.startsWith("91")){
			return validNumber;
		}
		//No need to handle other numbers - as of now we are only handling India numbers
		return null;
	}
	
	/**
	 * Create HTTP Get URL
	 */
	public String getSmsHTTPGetUrl(String userName,String to,String text){
		//return "http://bulkpush.mytoday.com/BulkSms/SingleMsgApi?feedid=" + feedId + "&To=" + to + "&Text=" + text + "&Username=" + USERNAME + "&Password=" + PASSWORD;
		return "https://smsgw.sms.gov.in/failsafe/HttpLink?username="+userName+"&pin="+PIN+"&message="+text+"&mnumber="+to+"&signature="+SIGNATURE+"&dlt_entity_id=1101611580000043694&dlt_template_id=1107161233369894498";
		//return "http://www.hindit.co.in/API/pushsms.aspx?loginID="+loginID+"&password="+PASSWORD+"&mobile="+to+"&text="+text+"&senderid="+SENDER_ID+"&route_id=2&Unicode=0";
	}
	
	public static void main(String[] args) {
		SmsSender smsSender = new SmsSender();
		//System.out.println(smsSender.sendSms("9311876584", "Hello Dhiraj"));
		//System.out.println(smsSender.sendSmses("9311876584,9311876584", "Hello Dhiraj2"));
		String otpMessage = "Dear {#var#}, OTP for CPF Self Service Registration is {#var#}. Team FCI FAP";
		otpMessage = otpMessage.replace("<otp>", "123456");
		otpMessage = otpMessage.replace("<minutes>", "30");
		System.out.println("otp message = " + otpMessage);
		System.out.println(smsSender.getValidatedNumber("9311876584"));
		System.out.println("Going to send message, status= " + smsSender.sendSmses("9311876584", otpMessage));
	}
	

}
