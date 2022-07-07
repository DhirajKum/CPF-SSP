package com.techm.fci.cpf.dto;

import java.io.Serializable;

public class ClaimRequestGenerateReportDto implements Serializable {

private static final long serialVersionUID = 1L;
	
	private String empNum;
	private String empName;
	private String claimId;
	private String claimType;
	private String claimDate;
	private String claimStatus;
	private String presLocation;
	private String parentZone;
	
	public ClaimRequestGenerateReportDto() {

	}

	public ClaimRequestGenerateReportDto(String empNum, String empName, String claimId,
			String claimType, String claimDate, String claimStatus, String presLocation, String parentZone) {
		super();
		this.empNum = empNum;
		this.empName = empName;
		this.claimId = claimId;
		this.claimType = claimType;
		this.claimDate = claimDate;
		this.claimStatus = claimStatus;
		this.presLocation = presLocation;
		this.parentZone = parentZone;
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

	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getPresLocation() {
		return presLocation;
	}

	public void setPresLocation(String presLocation) {
		this.presLocation = presLocation;
	}

	public String getParentZone() {
		return parentZone;
	}

	public void setParentZone(String parentZone) {
		this.parentZone = parentZone;
	}

	
	@Override
	public String toString() {
		return "ClaimRequestGenerateReportDto [empNum=" + empNum + ", empName=" + empName
				+ ", claimId=" + claimId + ", claimType=" + claimType
				+ ", claimDate=" + claimDate + ", claimStatus=" + claimStatus + ",presLocation=" + presLocation + ", parentZone=" + parentZone
				+ "]";
	}

}
