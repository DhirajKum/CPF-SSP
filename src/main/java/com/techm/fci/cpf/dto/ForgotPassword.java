package com.techm.fci.cpf.dto;

import java.io.Serializable;

public class ForgotPassword implements Serializable {
	
	private static final long serialVersionUID = -9129110289734659408L;

	private String empNum;
	private String newPassword;
	private String confNewPass;
	
	public ForgotPassword(){
		
	}
	
	public ForgotPassword(String empNum, String newPassword) {
		super();
		this.empNum = empNum;
		this.newPassword = newPassword;
	}
	
	public ForgotPassword(String empNum, String newPassword, String confNewPass) {
		super();
		this.empNum = empNum;
		this.newPassword = newPassword;
		this.confNewPass = confNewPass;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfNewPass() {
		return confNewPass;
	}

	public void setConfNewPass(String confNewPass) {
		this.confNewPass = confNewPass;
	}
	
	@Override
	public String toString() {
		return "ForgotPassword [empNum=" + empNum + ", newPassword=" + newPassword + ", confNewPass=" + confNewPass
				+ "]";
	}
}
