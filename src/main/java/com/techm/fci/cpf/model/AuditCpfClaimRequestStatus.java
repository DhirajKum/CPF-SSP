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
@Table(name="audit_cpf_claim_form_status")
public class AuditCpfClaimRequestStatus  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="auditCpfClaimStatusSeq", sequenceName="audit_cpf_claim_status_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="auditCpfClaimStatusSeq")
	@Column(name = "AUDIT_ID", unique = true, nullable = false)
	public Integer AUDIT_ID;
	public Integer STATUS_ID;
	public String REQUEST_ID;
	public String CLAIM_SUBMITTED_BY;
	public Date CLAIM_SUBMITTED_DATE;
	public String STATUS;
	public String INFO1;
	public String INFO2;
	public String INFO3;
	public String INFO4;
	public String INFO5;
	
	public AuditCpfClaimRequestStatus(){
		
	}
	
	public AuditCpfClaimRequestStatus(Integer aUDIT_ID, Integer sTATUS_ID, String rEQUEST_ID, String cLAIM_SUBMITTED_BY,
			Date cLAIM_SUBMITTED_DATE, String sTATUS, String iNFO1, String iNFO2, String iNFO3, String iNFO4,
			String iNFO5) {
		super();
		AUDIT_ID = aUDIT_ID;
		STATUS_ID = sTATUS_ID;
		REQUEST_ID = rEQUEST_ID;
		CLAIM_SUBMITTED_BY = cLAIM_SUBMITTED_BY;
		CLAIM_SUBMITTED_DATE = cLAIM_SUBMITTED_DATE;
		STATUS = sTATUS;
		INFO1 = iNFO1;
		INFO2 = iNFO2;
		INFO3 = iNFO3;
		INFO4 = iNFO4;
		INFO5 = iNFO5;
	}

	public Integer getAUDIT_ID() {
		return AUDIT_ID;
	}

	public void setAUDIT_ID(Integer aUDIT_ID) {
		AUDIT_ID = aUDIT_ID;
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

	@Override
	public String toString() {
		return "AuditCpfClaimRequestStatus [AUDIT_ID=" + AUDIT_ID + ", STATUS_ID=" + STATUS_ID + ", REQUEST_ID="
				+ REQUEST_ID + ", CLAIM_SUBMITTED_BY=" + CLAIM_SUBMITTED_BY + ", CLAIM_SUBMITTED_DATE="
				+ CLAIM_SUBMITTED_DATE + ", STATUS=" + STATUS + ", INFO1=" + INFO1 + ", INFO2=" + INFO2 + ", INFO3="
				+ INFO3 + ", INFO4=" + INFO4 + ", INFO5=" + INFO5 + "]";
	}

}


