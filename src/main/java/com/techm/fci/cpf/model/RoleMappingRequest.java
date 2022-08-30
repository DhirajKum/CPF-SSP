package com.techm.fci.cpf.model;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cpf_assigned_roles_dtl")
public class RoleMappingRequest {

	private static final long serialVersionUID = 1L;
	
	

	private int regId;
	private String DESIGNATION;
	private String EMPUNIT;
	
	public Integer EMPID;	
	public String EMPNAME;	
	public String EMPCATEGORY;	
	public Integer PRESLOCATION;	
	public Integer PARENTZONE;	
	public String ROLEASSIGNED;	
	public Date  STARTDATE;	
	public Date  ENDDATE;	
	public Date  CREATED_DATE;	
	public Integer CREATED_BY;	
	public Date  MODIFIED_DATE;	
	public String MODIFIED_BY;	
	public String INFO1;	
	public String INFO2;	
	public String INFO3;	
	public String INFO4;	
	public String INFO5;


	public RoleMappingRequest(){
		
	}
	
	public RoleMappingRequest(int regId,String dESIGNATION,String eMPUNIT,Integer eMPID,String eMPNAME,String eMPCATEGORY,Integer pRESLOCATION,Integer pARENTZONE,
			String rOLEASSIGNED,Date  sTARTDATE,Date  eNDDATE,Date  cREATED_DATE,Integer cREATED_BY,Date  mODIFIED_DATE,
			String mODIFIED_BY,String iNFO1,String iNFO2,String iNFO3,String iNFO4,String iNFO5) {
		super();
		this.regId = regId;
		DESIGNATION=dESIGNATION;
		EMPUNIT=eMPUNIT;
		EMPID=	eMPID;
		EMPNAME=	eMPNAME;
		EMPCATEGORY=	eMPCATEGORY;
		PRESLOCATION=	pRESLOCATION;
		PARENTZONE=	pARENTZONE;
		ROLEASSIGNED=	rOLEASSIGNED;
		STARTDATE=	sTARTDATE;
		ENDDATE=	eNDDATE;
		CREATED_DATE=	cREATED_DATE;
		CREATED_BY=	cREATED_BY;
		MODIFIED_DATE=	mODIFIED_DATE;
		MODIFIED_BY=	mODIFIED_BY;
		INFO1=	iNFO1;
		INFO2=	iNFO2;
		INFO3=	iNFO3;
		INFO4=	iNFO4;
		INFO5=	iNFO5;
	}

	
	@Id
	@SequenceGenerator(name="roleSeq", sequenceName="cpf_assigned_roles_dtl_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="roleSeq" )
	@Column(name = "REG_ID", unique = true, nullable = false, precision = 5, scale = 0)
	
	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	

	public Integer getEMPID() {
		return EMPID;
	}

	public void setEMPID(Integer eMPID) {
		EMPID = eMPID;
	}

	public String getEMPNAME() {
		return EMPNAME;
	}

	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}

	public String getEMPCATEGORY() {
		return EMPCATEGORY;
	}

	public void setEMPCATEGORY(String eMPCATEGORY) {
		EMPCATEGORY = eMPCATEGORY;
	}

	

	public String getROLEASSIGNED() {
		return ROLEASSIGNED;
	}

	public void setROLEASSIGNED(String rOLEASSIGNED) {
		ROLEASSIGNED = rOLEASSIGNED;
	}



	public Date getSTARTDATE() {
		return STARTDATE;
	}

	public void setSTARTDATE(Date sTARTDATE) {
		STARTDATE = sTARTDATE;
	}

	public Date getENDDATE() {
		return ENDDATE;
	}

	public void setENDDATE(Date eNDDATE) {
		ENDDATE = eNDDATE;
	}

	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public Date getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(Date mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPRESLOCATION() {
		return PRESLOCATION;
	}

	public void setPRESLOCATION(Integer pRESLOCATION) {
		PRESLOCATION = pRESLOCATION;
	}

	public Integer getPARENTZONE() {
		return PARENTZONE;
	}

	public void setPARENTZONE(Integer pARENTZONE) {
		PARENTZONE = pARENTZONE;
	}

	public Integer getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(Integer cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public String getDESIGNATION() {
		return DESIGNATION;
	}

	public void setDESIGNATION(String dESIGNATION) {
		DESIGNATION = dESIGNATION;
	}

	public String getEMPUNIT() {
		return EMPUNIT;
	}

	public void setEMPUNIT(String eMPUNIT) {
		EMPUNIT = eMPUNIT;
	}


	
	
}
