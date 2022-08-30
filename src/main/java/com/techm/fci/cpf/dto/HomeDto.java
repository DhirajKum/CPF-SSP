package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;

public class HomeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String empName;
	private String empFather;
	private String empUan;
	private String empDob;
	private String empGender;
	private String empAadhar;
	private String empPan;
	private String empBankAccNo;
	private String empMobile;
	private String empEmail;
	
	private String employeeContribute;
	private String employerContribute;
	private String vpfContribute;
	private String cpfFreezeYear; 
	
	private String filePath;
	private String fileName;
	
	public HomeDto(){
		
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpFather() {
		return empFather;
	}
	public void setEmpFather(String empFather) {
		this.empFather = empFather;
	}
	public String getEmpUan() {
		return empUan;
	}
	public void setEmpUan(String empUan) {
		this.empUan = empUan;
	}
	public String getEmpDob() {
		return empDob;
	}
	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getEmpAadhar() {
		return empAadhar;
	}
	public void setEmpAadhar(String empAadhar) {
		this.empAadhar = empAadhar;
	}
	public String getEmpPan() {
		return empPan;
	}
	public void setEmpPan(String empPan) {
		this.empPan = empPan;
	}
	public String getEmpBankAccNo() {
		return empBankAccNo;
	}
	public void setEmpBankAccNo(String empBankAccNo) {
		this.empBankAccNo = empBankAccNo;
	}
	public String getEmpMobile() {
		return empMobile;
	}
	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	public String getEmployeeContribute() {
		return employeeContribute;
	}

	public void setEmployeeContribute(String employeeContribute) {
		this.employeeContribute = employeeContribute;
	}

	public String getEmployerContribute() {
		return employerContribute;
	}

	public void setEmployerContribute(String employerContribute) {
		this.employerContribute = employerContribute;
	}

	public String getVpfContribute() {
		return vpfContribute;
	}

	public void setVpfContribute(String vpfContribute) {
		this.vpfContribute = vpfContribute;
	}

	public String getCpfFreezeYear() {
		return cpfFreezeYear;
	}

	public void setCpfFreezeYear(String cpfFreezeYear) {
		this.cpfFreezeYear = cpfFreezeYear;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "HomeDto [empName=" + empName + ", empFather=" + empFather + ", empUan=" + empUan + ", empDob=" + empDob
				+ ", empGender=" + empGender + ", empAadhar=" + empAadhar + ", empPan=" + empPan + ", empBankAccNo="
				+ empBankAccNo + ", empMobile=" + empMobile + ", empEmail=" + empEmail + ", employeeContribute="
				+ employeeContribute + ", employerContribute=" + employerContribute + ", vpfContribute=" + vpfContribute
				+ ", cpfFreezeYear=" + cpfFreezeYear + ", filePath=" + filePath + ", fileName=" + fileName + "]";
	}
		
}
