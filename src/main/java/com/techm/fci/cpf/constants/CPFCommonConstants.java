package com.techm.fci.cpf.constants;
/**
 * 
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

public class CPFCommonConstants {
	
	
	/**
	 * Constant related to mailers
	 */
	public static final String TEST_ENVIRONMENT_EMAILS = "dhiraj.4767@gmail.com";
	public static final Long SAMPLE_MAILER_TEMPLATE_ID = 1L;
	
		
	/**
	 * User status
	 */
	public static final Integer INACTIVE = 0;
	public static final Integer ACTIVE = 1;
	public static final Integer DEACTIVATED = 2;
	
	/**
	 * Send OTP message
	 */
	public static final String OTP_MESSAGE = "Dear <name>, OTP for CPF Self Service Registration is <otp>. Team FCI FAP";
	public static final String OTP_EXPIRY_TIME = "15";
	
	/**
	 * Gender Types
	 */
	public static final String GENDER_MALE = "Male";
	public static final String GENDER_FEMALE = "Female";
	
		
	/**
	 * Document Types
	 */
	public static final String DOCUMENT_TYPE_CANCELLED_CHEQUE = "Cancelled Cheque";
	public static final String DOCUMENT_TYPE_DOB_PROOF = "DOB Proof";
	public static final String DOCUMENT_TYPE_ADDRESS_PROOF = "Address Proof";
	public static final String DOCUMENT_TYPE_ID_PROOF = "Id Proof";
	
	
	public static final Integer POPUP_TYPE_OTP = 0;
	public static final Integer POPUP_TYPE_OTP_FAILED = 1;
	public static final Integer POPUP_TYPE_CREATE_PASSWORD = 2;
	
}
