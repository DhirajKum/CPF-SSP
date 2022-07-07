package com.techm.fci.cpf.model;

import java.io.Serializable;

public class UserModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uan;
	private String empNum;
	private String empName;
	private String email;
	private String empPhone;
	private String roleName;
	
	public UserModel(){
		
	}
	
	public UserModel(String uan, String empNum, String empName, String email, String empPhone, String roleName) {
		super();
		this.uan = uan;
		this.empNum = empNum;
		this.empName = empName;
		this.email = email;
		this.empPhone = empPhone;
		this.roleName = roleName;
	}
	public String getUan() {
		return uan;
	}
	public void setUan(String uan) {
		this.uan = uan;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "UserModel [uan=" + uan + ", empNum=" + empNum + ", empName=" + empName + ", email=" + email
				+ ", empPhone=" + empPhone + ", roleName=" + roleName + "]";
	}
	
}
