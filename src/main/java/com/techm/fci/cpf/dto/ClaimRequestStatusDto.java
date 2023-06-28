package com.techm.fci.cpf.dto;

/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;

public class ClaimRequestStatusDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String requestId;
	private String claimSubmittedEmpId;
	private String claimSubmittedBy;
	private String claimSubmittedDate;
	private String adminActionDate;
	private String adminActionTakenBy;
	private String adminAction;
	private String cpfActionDate;
	private String cpfActionTakenBy;
	private String cpfAction;
	private String remarks;
	private String invoiceNo;
	private String sancAmount;
	private String status;
	private String statusCode;
	private String cpfCode;

	private String designation;
	private String claimType;

	public ClaimRequestStatusDto() {

	}

	public ClaimRequestStatusDto(String requestId, String claimSubmittedEmpId, String claimSubmittedBy,
			String claimSubmittedDate, String adminActionDate, String adminActionTakenBy, String adminAction,
			String cpfActionDate, String cpfActionTakenBy, String cpfAction, String remarks, String invoiceNo,
			String sancAmount, String status, String statusCode, String cpfCode, String designation, String claimType) {
		super();
		this.requestId = requestId;
		this.claimSubmittedEmpId = claimSubmittedEmpId;
		this.claimSubmittedBy = claimSubmittedBy;
		this.claimSubmittedDate = claimSubmittedDate;
		this.adminActionDate = adminActionDate;
		this.adminActionTakenBy = adminActionTakenBy;
		this.adminAction = adminAction;
		this.cpfActionDate = cpfActionDate;
		this.cpfActionTakenBy = cpfActionTakenBy;
		this.cpfAction = cpfAction;
		this.remarks = remarks;
		this.invoiceNo = invoiceNo;
		this.sancAmount = sancAmount;
		this.status = status;
		this.statusCode = statusCode;
		this.cpfCode = cpfCode;
		this.designation = designation;
		this.claimType = claimType;
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

	public String getClaimSubmittedDate() {
		return claimSubmittedDate;
	}

	public void setClaimSubmittedDate(String claimSubmittedDate) {
		this.claimSubmittedDate = claimSubmittedDate;
	}

	public String getAdminActionDate() {
		return adminActionDate;
	}

	public void setAdminActionDate(String adminActionDate) {
		this.adminActionDate = adminActionDate;
	}

	public String getAdminActionTakenBy() {
		return adminActionTakenBy;
	}

	public void setAdminActionTakenBy(String adminActionTakenBy) {
		this.adminActionTakenBy = adminActionTakenBy;
	}

	public String getAdminAction() {
		return adminAction;
	}

	public void setAdminAction(String adminAction) {
		this.adminAction = adminAction;
	}

	public String getCpfActionDate() {
		return cpfActionDate;
	}

	public void setCpfActionDate(String cpfActionDate) {
		this.cpfActionDate = cpfActionDate;
	}

	public String getCpfActionTakenBy() {
		return cpfActionTakenBy;
	}

	public void setCpfActionTakenBy(String cpfActionTakenBy) {
		this.cpfActionTakenBy = cpfActionTakenBy;
	}

	public String getCpfAction() {
		return cpfAction;
	}

	public void setCpfAction(String cpfAction) {
		this.cpfAction = cpfAction;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSancAmount() {
		return sancAmount;
	}

	public void setSancAmount(String sancAmount) {
		this.sancAmount = sancAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getClaimSubmittedEmpId() {
		return claimSubmittedEmpId;
	}

	public void setClaimSubmittedEmpId(String claimSubmittedEmpId) {
		this.claimSubmittedEmpId = claimSubmittedEmpId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getCpfCode() {
		return cpfCode;
	}

	public void setCpfCode(String cpfCode) {
		this.cpfCode = cpfCode;
	}

	@Override
	public String toString() {
		return "ClaimRequestStatusDto [requestId=" + requestId + ", claimSubmittedEmpId=" + claimSubmittedEmpId
				+ ", claimSubmittedBy=" + claimSubmittedBy + ", claimSubmittedDate=" + claimSubmittedDate
				+ ", adminActionDate=" + adminActionDate + ", adminActionTakenBy=" + adminActionTakenBy
				+ ", adminAction=" + adminAction + ", cpfActionDate=" + cpfActionDate + ", cpfActionTakenBy="
				+ cpfActionTakenBy + ", cpfAction=" + cpfAction + ", remarks=" + remarks + ", invoiceNo=" + invoiceNo
				+ ", sancAmount=" + sancAmount + ", status=" + status + ", statusCode=" + statusCode + ", cpfCode="
				+ cpfCode + ", designation=" + designation + ", claimType=" + claimType + "]";
	}

}
