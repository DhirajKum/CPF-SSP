package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
public class ClaimRequestReportDto {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -4608210626045556909L;

	private String empNum;
	private String fromDate;
	private String toDate;
	private String claimType;
	
	public ClaimRequestReportDto(){
		
	}
	
	public ClaimRequestReportDto(String empNum, String fromDate, String toDate,String claimType) {
		super();
		this.empNum = empNum;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.claimType=claimType;
	}


	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	@Override
	public String toString() {
		return "CpfSlipReportDto [empNum=" + empNum + ", fromDate=" + fromDate + ", toDate=" + toDate + ", claimType=" + claimType + "]";
	}
	
}
