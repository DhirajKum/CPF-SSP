package com.techm.fci.cpf.serviceimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techm.fci.cpf.constants.CPFCommonConstants;
import com.techm.fci.cpf.dao.EmployeeDao;
import com.techm.fci.cpf.model.CpfOtpMaster;
import com.techm.fci.cpf.service.OTPService;
import com.techm.fci.cpf.util.CPFCommonPropertyFileReader;
import com.techm.fci.cpf.util.CPFUtils;
import com.techm.fci.cpf.util.StringUtils;

@Component
public class OTPServiceImpl implements OTPService {
	
	private static final Logger logger = LoggerFactory.getLogger(OTPServiceImpl.class);
	
	
	private static String USERNAME = "fcilekha.sms";
	private static String PIN = "W3%24cV1%26tE5";
	private static String SIGNATURE = "FCIFAP";
	private static String DLT_ENTITY_ID="1101611580000043694";
	private static String DLT_TEMPLATE_ID="1107161233369894498";
	private static String DEL_BY_SMSPORTAL = "N";
	
	
	@Autowired
	EmployeeDao empDao;
	
	
	@Override
	public boolean sendOtpSms(String mobileNumber, String empNum, String empName) {
		//SmsSender smsSender = new SmsSender();
		CpfOtpMaster cpfOtpMaster = new CpfOtpMaster();	
		String otp=null;
		String otpExpiryTime = CPFCommonConstants.OTP_EXPIRY_TIME;
		String otpMessage = CPFCommonPropertyFileReader.getValue(CPFCommonConstants.OTP_MESSAGE);
		
		if(StringUtils.isNotNull(mobileNumber)){
			mobileNumber = getValidatedNumber(mobileNumber);	
		}

		cpfOtpMaster = empDao.getOtpData(mobileNumber);
		if(cpfOtpMaster!=null && StringUtils.isNull(cpfOtpMaster.getOTP())){
			otp = new Integer(CPFUtils.generateOtp()).toString();
			//empDao.addOtpData(mobileNumber, otp, otpExpiryTime);
			otpMessage = otpMessage.replace("<otp>", otp);
			otpMessage = otpMessage.replace("<name>", empName);
			String encodedMessage = otpMessage.replace(" ", "%20");
			empDao.addOtpData(mobileNumber, otp, otpExpiryTime, empNum, USERNAME, PIN, encodedMessage, SIGNATURE, DEL_BY_SMSPORTAL, DLT_ENTITY_ID, DLT_TEMPLATE_ID);
		}else{
			empDao.updateOtpData(mobileNumber,cpfOtpMaster.getOTP(),otpExpiryTime);
			otp=cpfOtpMaster.getOTP();
		}
		
		logger.info("Going to send sms for opt...");
		boolean sendSMSStatus=false;
		try {
			//runScript("/prod/apps/fs2/EBSapps/appl/fci/12.0.0/bin/smsSender.sh", empNum);//For Production server
			runScript("/fuat/apps/fs1/EBSapps/appl/fci/12.0.0/bin/smsSender.sh", empNum);//For Dev server
			sendSMSStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OTP generated :::::: "+otp);
		logger.info("OTP Send SMS Successfully...");
		if(sendSMSStatus==true){
			
			empDao.insertOtpToSmsTable(empNum.trim(),mobileNumber.trim());
		}
		
		return sendSMSStatus;
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
	
	public static void runScript(String path, String... args) {
	    try {
	        String[] cmd = new String[args.length + 1];
	        cmd[0] = path;
	        int count = 0;
	        for (String s : args) {
	            cmd[++count] = args[count - 1];
	        }
	        Process process = Runtime.getRuntime().exec(cmd);
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        try {
	            process.waitFor();
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        while (bufferedReader.ready()) {
	            System.out.println("Received from script: " + bufferedReader.readLine());
	        }
	    } catch (Exception ex) {
	    	logger.info(ex.getMessage());
	    }
	}
	
	@Override
	public boolean verifyOtp(String mobile, String otp) {
		Boolean verifyStatus = Boolean.FALSE;
		CpfOtpMaster cpfOtpMaster = new CpfOtpMaster();
		long difference;
		if(StringUtils.isNotNull(mobile)){
			mobile = getValidatedNumber(mobile);	
		}
		cpfOtpMaster = empDao.getOtpData(mobile);
		try {
			if (cpfOtpMaster !=null && StringUtils.isNotNull(cpfOtpMaster.getOTP()) && cpfOtpMaster.getOTP().equals(otp)) {
			//otpRedisManager.deleteOtpData(mobile);
			
			SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
			Date date1 = format.parse(cpfOtpMaster.getCREATED_DATE().toString());
			Date date2 = new Date();
			difference = date2.getTime() - date1.getTime();
			if(difference > -1){
				long diffInMinute = difference / (60*1000)%60;
			
				if(diffInMinute > Long.parseLong(cpfOtpMaster.getEXPIRYTIME())){
					verifyStatus = Boolean.FALSE;
					empDao.deleteExpiredOtp(cpfOtpMaster.getOTP_ID());
				}else{
					verifyStatus = Boolean.TRUE;
					empDao.deleteExpiredOtp(cpfOtpMaster.getOTP_ID());
				}
			}
			
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return verifyStatus;
	}
}
