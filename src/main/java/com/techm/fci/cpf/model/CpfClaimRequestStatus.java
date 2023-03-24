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
@Table(name="cpf_claim_form_status")
public class CpfClaimRequestStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="cpfClaimStatusSeq", sequenceName="cpf_claim_form_status_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpfClaimStatusSeq")
	@Column(name = "STATUS_ID", unique = true, nullable = false)
	public Integer STATUS_ID;
	public String REQUEST_ID;
	public String CLAIM_SUBMITTED_BY;
	public Date CLAIM_SUBMITTED_DATE;
	public String ADMIN_CLAIM_SUBMITTED_TO;
	public String ADMIN_ACTION;
	public Date ADMIN_ACTION_DATE;
	public String ADMIN_ACTION_TAKEN_BY;
	public String ADMIN_REMARKS;
	public String CPFSEC_SUBMITTED_TO;
	public String CPFSEC_ACTION;
	public Date CPFSEC_ACTION_DATE;
	public String CPFSEC_ACTION_TAKEN_BY;
	public String CPFSEC_REMARKS;
	public String STATUS;
	public String INFO1;
	public String INFO2;
	public String INFO3;
	public String INFO4;
	public String INFO5;
	public Date RE_CLAIM_SUBMITTED_DATE;
	
	
	public CpfClaimRequestStatus(){
		
	}
	
	public CpfClaimRequestStatus(Integer sTATUS_ID, String rEQUEST_ID, String cLAIM_SUBMITTED_BY,
			Date cLAIM_SUBMITTED_DATE, String aDMIN_CLAIM_SUBMITTED_TO, String aDMIN_ACTION, Date aDMIN_ACTION_DATE,
			String aDMIN_ACTION_TAKEN_BY, String aDMIN_REMARKS, String cPFSEC_SUBMITTED_TO, String cPFSEC_ACTION,
			Date cPFSEC_ACTION_DATE, String cPFSEC_ACTION_TAKEN_BY, String cPFSEC_REMARKS, String sTATUS, String iNFO1,
			String iNFO2, String iNFO3, String iNFO4, String iNFO5, Date rE_CLAIM_SUBMITTED_DATE) {
		super();
		STATUS_ID = sTATUS_ID;
		REQUEST_ID = rEQUEST_ID;
		CLAIM_SUBMITTED_BY = cLAIM_SUBMITTED_BY;
		CLAIM_SUBMITTED_DATE = cLAIM_SUBMITTED_DATE;
		ADMIN_CLAIM_SUBMITTED_TO = aDMIN_CLAIM_SUBMITTED_TO;
		ADMIN_ACTION = aDMIN_ACTION;
		ADMIN_ACTION_DATE = aDMIN_ACTION_DATE;
		ADMIN_ACTION_TAKEN_BY = aDMIN_ACTION_TAKEN_BY;
		ADMIN_REMARKS = aDMIN_REMARKS;
		CPFSEC_SUBMITTED_TO = cPFSEC_SUBMITTED_TO;
		CPFSEC_ACTION = cPFSEC_ACTION;
		CPFSEC_ACTION_DATE = cPFSEC_ACTION_DATE;
		CPFSEC_ACTION_TAKEN_BY = cPFSEC_ACTION_TAKEN_BY;
		CPFSEC_REMARKS = cPFSEC_REMARKS;
		STATUS = sTATUS;
		INFO1 = iNFO1;
		INFO2 = iNFO2;
		INFO3 = iNFO3;
		INFO4 = iNFO4;
		INFO5 = iNFO5;
		RE_CLAIM_SUBMITTED_DATE = rE_CLAIM_SUBMITTED_DATE;
	}

	public Integer getSTATUS_ID() {
		return STATUS_ID;
	}

	public void setSTATUS_ID(Integer sTATUS_ID) {
		STATUS_ID = sTATUS_ID;
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

	public Date getCLAIM_SUBMITTED_DATE() {
		return CLAIM_SUBMITTED_DATE;
	}

	public void setCLAIM_SUBMITTED_DATE(Date cLAIM_SUBMITTED_DATE) {
		CLAIM_SUBMITTED_DATE = cLAIM_SUBMITTED_DATE;
	}

	public String getADMIN_CLAIM_SUBMITTED_TO() {
		return ADMIN_CLAIM_SUBMITTED_TO;
	}

	public void setADMIN_CLAIM_SUBMITTED_TO(String aDMIN_CLAIM_SUBMITTED_TO) {
		ADMIN_CLAIM_SUBMITTED_TO = aDMIN_CLAIM_SUBMITTED_TO;
	}

	public String getADMIN_ACTION() {
		return ADMIN_ACTION;
	}

	public void setADMIN_ACTION(String aDMIN_ACTION) {
		ADMIN_ACTION = aDMIN_ACTION;
	}

	public Date getADMIN_ACTION_DATE() {
		return ADMIN_ACTION_DATE;
	}

	public void setADMIN_ACTION_DATE(Date aDMIN_ACTION_DATE) {
		ADMIN_ACTION_DATE = aDMIN_ACTION_DATE;
	}

	public String getADMIN_ACTION_TAKEN_BY() {
		return ADMIN_ACTION_TAKEN_BY;
	}

	public void setADMIN_ACTION_TAKEN_BY(String aDMIN_ACTION_TAKEN_BY) {
		ADMIN_ACTION_TAKEN_BY = aDMIN_ACTION_TAKEN_BY;
	}

	public String getADMIN_REMARKS() {
		return ADMIN_REMARKS;
	}

	public void setADMIN_REMARKS(String aDMIN_REMARKS) {
		ADMIN_REMARKS = aDMIN_REMARKS;
	}

	public String getCPFSEC_SUBMITTED_TO() {
		return CPFSEC_SUBMITTED_TO;
	}

	public void setCPFSEC_SUBMITTED_TO(String cPFSEC_SUBMITTED_TO) {
		CPFSEC_SUBMITTED_TO = cPFSEC_SUBMITTED_TO;
	}

	public String getCPFSEC_ACTION() {
		return CPFSEC_ACTION;
	}

	public void setCPFSEC_ACTION(String cPFSEC_ACTION) {
		CPFSEC_ACTION = cPFSEC_ACTION;
	}

	public Date getCPFSEC_ACTION_DATE() {
		return CPFSEC_ACTION_DATE;
	}

	public void setCPFSEC_ACTION_DATE(Date cPFSEC_ACTION_DATE) {
		CPFSEC_ACTION_DATE = cPFSEC_ACTION_DATE;
	}

	public String getCPFSEC_ACTION_TAKEN_BY() {
		return CPFSEC_ACTION_TAKEN_BY;
	}

	public void setCPFSEC_ACTION_TAKEN_BY(String cPFSEC_ACTION_TAKEN_BY) {
		CPFSEC_ACTION_TAKEN_BY = cPFSEC_ACTION_TAKEN_BY;
	}

	public String getCPFSEC_REMARKS() {
		return CPFSEC_REMARKS;
	}

	public void setCPFSEC_REMARKS(String cPFSEC_REMARKS) {
		CPFSEC_REMARKS = cPFSEC_REMARKS;
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
	
	public Date getRE_CLAIM_SUBMITTED_DATE() {
		return RE_CLAIM_SUBMITTED_DATE;
	}

	public void setRE_CLAIM_SUBMITTED_DATE(Date rE_CLAIM_SUBMITTED_DATE) {
		RE_CLAIM_SUBMITTED_DATE = rE_CLAIM_SUBMITTED_DATE;
	}

	@Override
	public String toString() {
		return "CpfClaimRequestStatus [STATUS_ID=" + STATUS_ID + ", REQUEST_ID=" + REQUEST_ID + ", CLAIM_SUBMITTED_BY="
				+ CLAIM_SUBMITTED_BY + ", CLAIM_SUBMITTED_DATE=" + CLAIM_SUBMITTED_DATE + ", ADMIN_CLAIM_SUBMITTED_TO="
				+ ADMIN_CLAIM_SUBMITTED_TO + ", ADMIN_ACTION=" + ADMIN_ACTION + ", ADMIN_ACTION_DATE="
				+ ADMIN_ACTION_DATE + ", ADMIN_ACTION_TAKEN_BY=" + ADMIN_ACTION_TAKEN_BY + ", ADMIN_REMARKS="
				+ ADMIN_REMARKS + ", CPFSEC_SUBMITTED_TO=" + CPFSEC_SUBMITTED_TO + ", CPFSEC_ACTION=" + CPFSEC_ACTION
				+ ", CPFSEC_ACTION_DATE=" + CPFSEC_ACTION_DATE + ", CPFSEC_ACTION_TAKEN_BY=" + CPFSEC_ACTION_TAKEN_BY
				+ ", CPFSEC_REMARKS=" + CPFSEC_REMARKS + ", STATUS=" + STATUS + ", INFO1=" + INFO1 + ", INFO2=" + INFO2
				+ ", INFO3=" + INFO3 + ", INFO4=" + INFO4 + ", INFO5=" + INFO5 + ", RE_CLAIM_SUBMITTED_DATE="
				+ RE_CLAIM_SUBMITTED_DATE + "]";
	}

}
