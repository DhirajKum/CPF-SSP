package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;
import java.util.Date;

public class CpfSlipHeaderDto  implements Serializable{

	private static final long serialVersionUID = -4454674059887712871L;
	
	private String empName;
	private String staffCode;
	private String designation;
	private String office;
	private String cpfNo;
	private String fpsNo;
	private Date fciJoiningDt;
	private Date cpfJoiningDt;
	private Date dob;
	private String uan;
	
	public CpfSlipHeaderDto(){
		
	}
	
	

	public CpfSlipHeaderDto(String empName, String staffCode, String designation, String office, String cpfNo,
			String fpsNo, Date fciJoiningDt, Date cpfJoiningDt, Date dob, String uan) {
		super();
		this.empName = empName;
		this.staffCode = staffCode;
		this.designation = designation;
		this.office = office;
		this.cpfNo = cpfNo;
		this.fpsNo = fpsNo;
		this.fciJoiningDt = fciJoiningDt;
		this.cpfJoiningDt = cpfJoiningDt;
		this.dob = dob;
		this.uan = uan;
	}



	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCpfNo() {
		return cpfNo;
	}

	public void setCpfNo(String cpfNo) {
		this.cpfNo = cpfNo;
	}

	public String getFpsNo() {
		return fpsNo;
	}

	public void setFpsNo(String fpsNo) {
		this.fpsNo = fpsNo;
	}
	
	public Date getFciJoiningDt() {
		return fciJoiningDt;
	}

	public void setFciJoiningDt(Date fciJoiningDt) {
		this.fciJoiningDt = fciJoiningDt;
	}

	public Date getCpfJoiningDt() {
		return cpfJoiningDt;
	}

	public void setCpfJoiningDt(Date cpfJoiningDt) {
		this.cpfJoiningDt = cpfJoiningDt;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	@Override
	public String toString() {
		return "CpfSlipHeaderDto [empName=" + empName + ", staffCode=" + staffCode + ", designation=" + designation
				+ ", office=" + office + ", cpfNo=" + cpfNo + ", fpsNo=" + fpsNo + ", fciJoiningDt=" + fciJoiningDt
				+ ", cpfJoiningDt=" + cpfJoiningDt + ", dob=" + dob + ", uan=" + uan + "]";
	}
}
