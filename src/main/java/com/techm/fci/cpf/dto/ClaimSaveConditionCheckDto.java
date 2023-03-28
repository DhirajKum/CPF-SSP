package com.techm.fci.cpf.dto;

import java.util.Date;

public class ClaimSaveConditionCheckDto {
	
	public String claimReqId;
	public String claimType;
	public String claimPurpose;
	public String claimStatus;
	public int claimCount;
	public Date empRegDate;
	public Date empRetirementDate;

	public ClaimSaveConditionCheckDto() {

	}

	public ClaimSaveConditionCheckDto(String claimReqId, String claimType, String claimPurpose, String claimStatus,
			int claimCount, Date empRegDate, Date empRetirementDate) {
		super();
		this.claimReqId = claimReqId;
		this.claimType = claimType;
		this.claimPurpose = claimPurpose;
		this.claimStatus = claimStatus;
		this.claimCount = claimCount;
		this.empRegDate = empRegDate;
		this.empRetirementDate = empRetirementDate;
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

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
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
		return "ClaimSaveConditionCheckDto [claimReqId=" + claimReqId + ", claimType=" + claimType + ", claimPurpose="
				+ claimPurpose + ", claimStatus=" + claimStatus + ", claimCount=" + claimCount + ", empRegDate="
				+ empRegDate + ", empRetirementDate=" + empRetirementDate + "]";
	}

}
