package com.techm.fci.cpf.dto;

import java.io.Serializable;
import java.util.Date;

public class ClaimStatusAssignToNewAdminDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4515958585217255635L;

	private String requestId;
	private String claimSubmittedBy;
	private Date claimSubmittedDate;
	
	private String adminAction;
	private String adminActionTakenBy;
	private Date adminActionDate;
	private String adminRemarks;
	
	public ClaimStatusAssignToNewAdminDto(){
		
	}
	
	public ClaimStatusAssignToNewAdminDto(String requestId, String claimSubmittedBy, Date claimSubmittedDate,
			String adminAction, String adminActionTakenBy, Date adminActionDate, String adminRemarks) {
		super();
		this.requestId = requestId;
		this.claimSubmittedBy = claimSubmittedBy;
		this.claimSubmittedDate = claimSubmittedDate;
		this.adminAction = adminAction;
		this.adminActionTakenBy = adminActionTakenBy;
		this.adminActionDate = adminActionDate;
		this.adminRemarks = adminRemarks;
	}

	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getClaimSubmittedBy() {
		return claimSubmittedBy;
	}
	public void setClaimSubmittedBy(String claimSubmittedBy) {
		this.claimSubmittedBy = claimSubmittedBy;
	}
	public Date getClaimSubmittedDate() {
		return claimSubmittedDate;
	}
	public void setClaimSubmittedDate(Date claimSubmittedDate) {
		this.claimSubmittedDate = claimSubmittedDate;
	}
	public String getAdminAction() {
		return adminAction;
	}
	public void setAdminAction(String adminAction) {
		this.adminAction = adminAction;
	}
	public String getAdminActionTakenBy() {
		return adminActionTakenBy;
	}
	public void setAdminActionTakenBy(String adminActionTakenBy) {
		this.adminActionTakenBy = adminActionTakenBy;
	}
	public Date getAdminActionDate() {
		return adminActionDate;
	}
	public void setAdminActionDate(Date adminActionDate) {
		this.adminActionDate = adminActionDate;
	}
	public String getAdminRemarks() {
		return adminRemarks;
	}
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}

	@Override
	public String toString() {
		return "ClaimStatusAssignToNewAdminDto [requestId=" + requestId + ", claimSubmittedBy=" + claimSubmittedBy
				+ ", claimSubmittedDate=" + claimSubmittedDate + ", adminAction=" + adminAction
				+ ", adminActionTakenBy=" + adminActionTakenBy + ", adminActionDate=" + adminActionDate
				+ ", adminRemarks=" + adminRemarks + "]";
	}
}
