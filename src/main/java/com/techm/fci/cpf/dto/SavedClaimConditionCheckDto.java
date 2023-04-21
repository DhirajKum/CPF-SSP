package com.techm.fci.cpf.dto;

import java.util.Date;

public class SavedClaimConditionCheckDto {
	
	public String claimSubmittedBy;
	public String claimReqId;
	public String claimType;
	public String claimPurpose;
	public int claimStatus;
	public int claimCount;
	public Date empRegDate;
	public Date empRetirementDate;

	public SavedClaimConditionCheckDto() {

	}

	public SavedClaimConditionCheckDto(String claimSubmittedBy, String claimReqId, String claimType,
			String claimPurpose, int claimStatus, int claimCount, Date empRegDate, Date empRetirementDate) {
		super();
		this.claimSubmittedBy = claimSubmittedBy;
		this.claimReqId = claimReqId;
		this.claimType = claimType;
		this.claimPurpose = claimPurpose;
		this.claimStatus = claimStatus;
		this.claimCount = claimCount;
		this.empRegDate = empRegDate;
		this.empRetirementDate = empRetirementDate;
	}

	public String getClaimSubmittedBy() {
		return claimSubmittedBy;
	}

	public void setClaimSubmittedBy(String claimSubmittedBy) {
		this.claimSubmittedBy = claimSubmittedBy;
	}

	public String getClaimReqId() {
		return claimReqId;
	}

	public void setClaimReqId(String claimReqId) {
		this.claimReqId = claimReqId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimPurpose() {
		return claimPurpose;
	}

	public void setClaimPurpose(String claimPurpose) {
		this.claimPurpose = claimPurpose;
	}

	public int getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(int claimStatus) {
		this.claimStatus = claimStatus;
	}

	public int getClaimCount() {
		return claimCount;
	}

	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}

	public Date getEmpRegDate() {
		return empRegDate;
	}

	public void setEmpRegDate(Date empRegDate) {
		this.empRegDate = empRegDate;
	}

	public Date getEmpRetirementDate() {
		return empRetirementDate;
	}

	public void setEmpRetirementDate(Date empRetirementDate) {
		this.empRetirementDate = empRetirementDate;
	}

	@Override
	public String toString() {
		return "SavedClaimConditionCheckDto [claimSubmittedBy=" + claimSubmittedBy + ", claimReqId=" + claimReqId
				+ ", claimType=" + claimType + ", claimPurpose=" + claimPurpose + ", claimStatus=" + claimStatus
				+ ", claimCount=" + claimCount + ", empRegDate=" + empRegDate + ", empRetirementDate="
				+ empRetirementDate + "]";
	}
}
