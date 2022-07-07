package com.techm.fci.cpf.dto;

import java.io.Serializable;

public class ClaimHistoryTrailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String claimCreatedBy;
	private String requestId;
	private String actionDate;
	private String action;
	private String actionTakenBy;
	private String remarks;
	private String status;
	
	public ClaimHistoryTrailDto(){
		
	}

	public ClaimHistoryTrailDto(String claimCreatedBy, String requestId, String actionDate, String action,
			String actionTakenBy, String remarks, String status) {
		super();
		this.claimCreatedBy = claimCreatedBy;
		this.requestId = requestId;
		this.actionDate = actionDate;
		this.action = action;
		this.actionTakenBy = actionTakenBy;
		this.remarks = remarks;
		this.status = status;
	}

	public String getClaimCreatedBy() {
		return claimCreatedBy;
	}

	public void setClaimCreatedBy(String claimCreatedBy) {
		this.claimCreatedBy = claimCreatedBy;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionTakenBy() {
		return actionTakenBy;
	}

	public void setActionTakenBy(String actionTakenBy) {
		this.actionTakenBy = actionTakenBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClaimHistoryTrailDto [claimCreatedBy=" + claimCreatedBy + ", requestId=" + requestId + ", actionDate="
				+ actionDate + ", action=" + action + ", actionTakenBy=" + actionTakenBy + ", remarks=" + remarks
				+ ", status=" + status + "]";
	}
}
