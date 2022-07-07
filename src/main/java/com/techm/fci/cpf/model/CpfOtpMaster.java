package com.techm.fci.cpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cpf_otp_mas")
public class CpfOtpMaster implements java.io.Serializable {

	private static final long serialVersionUID = 6173350797614639751L;
	
	@Id
	@SequenceGenerator(name="cpfOtpMasterSeq", sequenceName="cpf_otp_mas_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpfOtpMasterSeq")
	@Column(name = "OTP_ID", unique = true, nullable = false)
	private int OTP_ID;
	private String MOBILE_NO;
	private String EMP_NUM;
	private String OTP;
	private String EXPIRYTIME;
	private String PIN;
	private String MESSAGE;
	private String SIGNATURE;
	private String DEL_BY_SMSPORTAL;
	private String DLT_ENTITY_ID;
	private String DLT_TEMPLATE_ID;
	private Date CREATED_DATE;
	private Date MODIFIED_DATE;
	
	public CpfOtpMaster(){
		
	}

	public CpfOtpMaster(int oTP_ID, String mOBILE_NO, String eMP_NUM, String oTP, String eXPIRYTIME, String pIN,
			String mESSAGE, String sIGNATURE, String dEL_BY_SMSPORTAL, String dLT_ENTITY_ID, String dLT_TEMPLATE_ID,
			Date cREATED_DATE, Date mODIFIED_DATE) {
		super();
		OTP_ID = oTP_ID;
		MOBILE_NO = mOBILE_NO;
		EMP_NUM = eMP_NUM;
		OTP = oTP;
		EXPIRYTIME = eXPIRYTIME;
		PIN = pIN;
		MESSAGE = mESSAGE;
		SIGNATURE = sIGNATURE;
		DEL_BY_SMSPORTAL = dEL_BY_SMSPORTAL;
		DLT_ENTITY_ID = dLT_ENTITY_ID;
		DLT_TEMPLATE_ID = dLT_TEMPLATE_ID;
		CREATED_DATE = cREATED_DATE;
		MODIFIED_DATE = mODIFIED_DATE;
	}

	public int getOTP_ID() {
		return OTP_ID;
	}

	public void setOTP_ID(int oTP_ID) {
		OTP_ID = oTP_ID;
	}

	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}

	public String getEMP_NUM() {
		return EMP_NUM;
	}

	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	public String getEXPIRYTIME() {
		return EXPIRYTIME;
	}

	public void setEXPIRYTIME(String eXPIRYTIME) {
		EXPIRYTIME = eXPIRYTIME;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getSIGNATURE() {
		return SIGNATURE;
	}

	public void setSIGNATURE(String sIGNATURE) {
		SIGNATURE = sIGNATURE;
	}

	public String getDEL_BY_SMSPORTAL() {
		return DEL_BY_SMSPORTAL;
	}

	public void setDEL_BY_SMSPORTAL(String dEL_BY_SMSPORTAL) {
		DEL_BY_SMSPORTAL = dEL_BY_SMSPORTAL;
	}

	public String getDLT_ENTITY_ID() {
		return DLT_ENTITY_ID;
	}

	public void setDLT_ENTITY_ID(String dLT_ENTITY_ID) {
		DLT_ENTITY_ID = dLT_ENTITY_ID;
	}

	public String getDLT_TEMPLATE_ID() {
		return DLT_TEMPLATE_ID;
	}

	public void setDLT_TEMPLATE_ID(String dLT_TEMPLATE_ID) {
		DLT_TEMPLATE_ID = dLT_TEMPLATE_ID;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public Date getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(Date mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

	@Override
	public String toString() {
		return "CpfOtpMaster [OTP_ID=" + OTP_ID + ", MOBILE_NO=" + MOBILE_NO + ", EMP_NUM=" + EMP_NUM + ", OTP=" + OTP
				+ ", EXPIRYTIME=" + EXPIRYTIME + ", PIN=" + PIN + ", MESSAGE=" + MESSAGE + ", SIGNATURE=" + SIGNATURE
				+ ", DEL_BY_SMSPORTAL=" + DEL_BY_SMSPORTAL + ", DLT_ENTITY_ID=" + DLT_ENTITY_ID + ", DLT_TEMPLATE_ID="
				+ DLT_TEMPLATE_ID + ", CREATED_DATE=" + CREATED_DATE + ", MODIFIED_DATE=" + MODIFIED_DATE + "]";
	}

			
}
