package com.techm.fci.cpf.dto;

import java.io.Serializable;

public class CpfSlipAdjustmentDataDto implements Serializable {

	private static final long serialVersionUID = -7239626866879554750L;
	
	private String month;
	private String adjustAmt;
	private String adjustType;
	private String remarks;
	
	public CpfSlipAdjustmentDataDto(){
		
	}
	
	public CpfSlipAdjustmentDataDto(String month, String adjustAmt, String adjustType, String remarks) {
		super();
		this.month = month;
		this.adjustAmt = adjustAmt;
		this.adjustType = adjustType;
		this.remarks = remarks;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAdjustAmt() {
		return adjustAmt;
	}

	public void setAdjustAmt(String adjustAmt) {
		this.adjustAmt = adjustAmt;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CpfSlipAdjustmentDataDto [month=" + month + ", adjustAmt=" + adjustAmt + ", adjustType=" + adjustType
				+ ", remarks=" + remarks + "]";
	}
	
}
