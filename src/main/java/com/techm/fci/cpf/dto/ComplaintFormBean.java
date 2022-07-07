package com.techm.fci.cpf.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ComplaintFormBean {
	private String grivFirstName;
	private String grivMiddleName;
	private String grivLastName;
	private String grivPhone;
	private String grivEmail;
	private String grivAddressLine1;
	private String grivAddressLine2;
	private String grivCity;
	private String grivState;
	private String grivCountry;
	private String grivZipCode;
	private String grivConfidentiality;
	private String grivComplainType;
	private String grivSubject;
	private String grivComplaintDescription;
	private String captcha;
	private String filePath;
	private String grivDepartmentList;
	private String grivConfidentiality_view;
	//private String userType;
	
	 private CommonsMultipartFile file = null;
	 //private byte[] data;
	 public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	/*public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}*/

	
	
	/*public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}*/

	private String processInstanceId;

	public String getGrivDepartmentList() {
		return grivDepartmentList;
	}

	public void setGrivDepartmentList(String grivDepartmentList) {
		this.grivDepartmentList = grivDepartmentList;
	}

	public String getGrivFirstName() {
		return grivFirstName;
	}

	public void setGrivFirstName(String grivFirstName) {
		this.grivFirstName = grivFirstName;
	}

	public String getGrivMiddleName() {
		return grivMiddleName;
	}

	public void setGrivMiddleName(String grivMiddleName) {
		this.grivMiddleName = grivMiddleName;
	}

	public String getGrivLastName() {
		return grivLastName;
	}

	public void setGrivLastName(String grivLastName) {
		this.grivLastName = grivLastName;
	}

	public String getGrivPhone() {
		return grivPhone;
	}

	public void setGrivPhone(String grivPhone) {
		this.grivPhone = grivPhone;
	}

	public String getGrivEmail() {
		return grivEmail;
	}

	public void setGrivEmail(String grivEmail) {
		this.grivEmail = grivEmail;
	}

	public String getGrivAddressLine1() {
		return grivAddressLine1;
	}

	public void setGrivAddressLine1(String grivAddressLine1) {
		this.grivAddressLine1 = grivAddressLine1;
	}

	public String getGrivAddressLine2() {
		return grivAddressLine2;
	}

	public void setGrivAddressLine2(String grivAddressLine2) {
		this.grivAddressLine2 = grivAddressLine2;
	}

	public String getGrivCity() {
		return grivCity;
	}

	public void setGrivCity(String grivCity) {
		this.grivCity = grivCity;
	}

	public String getGrivState() {
		return grivState;
	}

	public void setGrivState(String grivState) {
		this.grivState = grivState;
	}

	public String getGrivCountry() {
		return grivCountry;
	}

	public void setGrivCountry(String grivCountry) {
		this.grivCountry = grivCountry;
	}

	public String getGrivZipCode() {
		return grivZipCode;
	}

	public void setGrivZipCode(String grivZipCode) {
		this.grivZipCode = grivZipCode;
	}

	public String getGrivConfidentiality() {
		return grivConfidentiality;
	}

	public void setGrivConfidentiality(String grivConfidentiality) {
		this.grivConfidentiality = grivConfidentiality;
	}

	public String getGrivComplainType() {
		return grivComplainType;
	}

	public void setGrivComplainType(String grivComplainType) {
		this.grivComplainType = grivComplainType;
	}

	public String getGrivSubject() {
		return grivSubject;
	}

	public void setGrivSubject(String grivSubject) {
		this.grivSubject = grivSubject;
	}

	public String getGrivComplaintDescription() {
		return grivComplaintDescription;
	}

	public void setGrivComplaintDescription(String grivComplaintDescription) {
		this.grivComplaintDescription = grivComplaintDescription;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getGrivConfidentiality_view() {
		return grivConfidentiality_view;
	}

	public void setGrivConfidentiality_view(String grivConfidentiality_view) {
		this.grivConfidentiality_view = grivConfidentiality_view;
	}
	

}
