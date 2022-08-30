package com.techm.fci.cpf.model;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cpf_claim_history_trail")
public class CpfClaimHistoryTrail implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpfClaimHistoryTrailSeq", sequenceName="cpf_claim_history_trail_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpfClaimHistoryTrailSeq")
	@Column(name = "HISTORY_ID", unique = true, nullable = false)
	private Integer HISTORY_ID;
	private String REQUEST_ID;
	private String CLAIM_SUBMITTED_BY;
	private String ACTION_TAKEN_BY;
	private Date ACTION_DATE;
	private String ACTION;
	private String REMARKS;
	private String STATUS;
	private String INFO1;
	private String INFO2;
	private String INFO3;
	private String INFO4;
	private String INFO5;
	private String CREATED_BY;
	private Date CREATED_DATE;
	private String MODIFIED_BY;
	private Date MODIFIED_DATE;
	
	public CpfClaimHistoryTrail(){
		
	}
	
	public CpfClaimHistoryTrail(Integer hISTORY_ID, String rEQUEST_ID, String cLAIM_SUBMITTED_BY,
			String aCTION_TAKEN_BY, Date aCTION_DATE, String aCTION, String rEMARKS, String sTATUS, String iNFO1,
			String iNFO2, String iNFO3, String iNFO4, String iNFO5, String cREATED_BY, Date cREATED_DATE,
			String mODIFIED_BY, Date mODIFIED_DATE) {
		super();
		HISTORY_ID = hISTORY_ID;
		REQUEST_ID = rEQUEST_ID;
		CLAIM_SUBMITTED_BY = cLAIM_SUBMITTED_BY;
		ACTION_TAKEN_BY = aCTION_TAKEN_BY;
		ACTION_DATE = aCTION_DATE;
		ACTION = aCTION;
		REMARKS = rEMARKS;
		STATUS = sTATUS;
		INFO1 = iNFO1;
		INFO2 = iNFO2;
		INFO3 = iNFO3;
		INFO4 = iNFO4;
		INFO5 = iNFO5;
		CREATED_BY = cREATED_BY;
		CREATED_DATE = cREATED_DATE;
		MODIFIED_BY = mODIFIED_BY;
		MODIFIED_DATE = mODIFIED_DATE;
	}

	public Integer getHISTORY_ID() {
		return HISTORY_ID;
	}

	public void setHISTORY_ID(Integer hISTORY_ID) {
		HISTORY_ID = hISTORY_ID;
	}

	public String getREQUEST_ID() {
		return REQUEST_ID;
	}

	public void setREQUEST_ID(String rEQUEST_ID) {
		REQUEST_ID = rEQUEST_ID;
	}

	public String getCLAIM_SUBMITTED_BY() {
		return CLAIM_SUBMITTED_BY;
	}

	public void setCLAIM_SUBMITTED_BY(String cLAIM_SUBMITTED_BY) {
		CLAIM_SUBMITTED_BY = cLAIM_SUBMITTED_BY;
	}

	public String getACTION_TAKEN_BY() {
		return ACTION_TAKEN_BY;
	}

	public void setACTION_TAKEN_BY(String aCTION_TAKEN_BY) {
		ACTION_TAKEN_BY = aCTION_TAKEN_BY;
	}

	public Date getACTION_DATE() {
		return ACTION_DATE;
	}

	public void setACTION_DATE(Date aCTION_DATE) {
		ACTION_DATE = aCTION_DATE;
	}
	
	public String getACTION() {
		return ACTION;
	}

	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getINFO1() {
		return INFO1;
	}

	public void setINFO1(String iNFO1) {
		INFO1 = iNFO1;
	}

	public String getINFO2() {
		return INFO2;
	}

	public void setINFO2(String iNFO2) {
		INFO2 = iNFO2;
	}

	public String getINFO3() {
		return INFO3;
	}

	public void setINFO3(String iNFO3) {
		INFO3 = iNFO3;
	}

	public String getINFO4() {
		return INFO4;
	}

	public void setINFO4(String iNFO4) {
		INFO4 = iNFO4;
	}

	public String getINFO5() {
		return INFO5;
	}

	public void setINFO5(String iNFO5) {
		INFO5 = iNFO5;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}

	public Date getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(Date mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

	@Override
	public String toString() {
		return "CpfClaimHistoryTrail [HISTORY_ID=" + HISTORY_ID + ", REQUEST_ID=" + REQUEST_ID + ", CLAIM_SUBMITTED_BY="
				+ CLAIM_SUBMITTED_BY + ", ACTION_TAKEN_BY=" + ACTION_TAKEN_BY + ", ACTION_DATE=" + ACTION_DATE
				+ ", ACTION=" + ACTION + ", REMARKS=" + REMARKS + ", STATUS=" + STATUS + ", INFO1=" + INFO1 + ", INFO2="
				+ INFO2 + ", INFO3=" + INFO3 + ", INFO4=" + INFO4 + ", INFO5=" + INFO5 + ", CREATED_BY=" + CREATED_BY
				+ ", CREATED_DATE=" + CREATED_DATE + ", MODIFIED_BY=" + MODIFIED_BY + ", MODIFIED_DATE=" + MODIFIED_DATE
				+ "]";
	}
	
}
