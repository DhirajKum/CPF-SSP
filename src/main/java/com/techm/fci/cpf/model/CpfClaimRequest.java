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
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
//import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cpf_claim_form_details")
public class CpfClaimRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	public String REQUEST_ID;
	public String CLAIM_SUBMITTED_BY;
	public Date CLAIM_SUBMITTED_DATE;
	public String CLAIM_APPLIED_FOR;
	public String EMP_NAME;
	public String FATHER_HUSBAND_NAME;
	public String BASIC;
	public String UAN;
	public Date DOJ_FCI;
	public String PAN;
	public String DESIGNATION;
	public String PRESENT_LOCATION;
	public String PARENT_ZONE;
	public Date DATE_OF_BIRTH;
	public String STAFF_CODE;
	public String CPF_ACCOUNT_NUMBER;
	public String MOBILE_NUMBER;
	public Date RETIREMENT_DATE;
	public String PURPOSE;
	public String AMOUNT;
	public String INSTALLMENT_NUMBER;
	@org.hibernate.annotations.Type(type = "true_false")
	public boolean LAST_DRAWN_ADVANCE;
	public String ADVANCE_AMOUNT;
	public String AMOUNT_REPAID;
	public String OUTSTANDING_BAL;
	public String AMOUNT_90PARTFINAL_BEF_RETR;
	//public String FILE;
	public String INFO1;
	public String INFO2;
	public String INFO3;
	public String INFO4;
	public String CASTE_DISPUTE_CERT;
	@org.hibernate.annotations.Type(type = "true_false")
	// @NotNull
	public boolean PERMISSIBLE_AMOUNT;
	@org.hibernate.annotations.Type(type = "true_false")
	// @NotNull
	public boolean DEC_NOT_EMP_TWOMONTH;
	@org.hibernate.annotations.Type(type = "true_false")
	// @NotNull
	public boolean EMP_DECLARATION;
	
	public String AMOUNT_SANCTION;
	public Date RE_CLAIM_SUBMITTED_DATE;
	public int CLAIM_COUNT;	
	
	
	public CpfClaimRequest() {

	}

	public CpfClaimRequest(String rEQUEST_ID, String cLAIM_SUBMITTED_BY, Date cLAIM_SUBMITTED_DATE,
			String cLAIM_APPLIED_FOR, String eMP_NAME, String fATHER_HUSBAND_NAME, String bASIC, String uAN,
			Date dOJ_FCI, String pAN, String dESIGNATION, String pRESENT_LOCATION, String pARENT_ZONE,
			Date dATE_OF_BIRTH, String sTAFF_CODE, String cPF_ACCOUNT_NUMBER, String mOBILE_NUMBER,
			Date rETIREMENT_DATE, String pURPOSE, String aMOUNT, String iNSTALLMENT_NUMBER, boolean lAST_DRAWN_ADVANCE,
			String aDVANCE_AMOUNT, String aMOUNT_REPAID, String oUTSTANDING_BAL, String aMOUNT_90PARTFINAL_BEF_RETR,
			String iNFO1, String iNFO2, String iNFO3, String iNFO4, String cASTE_DISPUTE_CERT,
			boolean pERMISSIBLE_AMOUNT, boolean dEC_NOT_EMP_TWOMONTH, boolean eMP_DECLARATION, String aMOUNT_SANCTION,
			Date rE_CLAIM_SUBMITTED_DATE, int cLAIM_COUNT) {
		super();
		REQUEST_ID = rEQUEST_ID;
		CLAIM_SUBMITTED_BY = cLAIM_SUBMITTED_BY;
		CLAIM_SUBMITTED_DATE = cLAIM_SUBMITTED_DATE;
		CLAIM_APPLIED_FOR = cLAIM_APPLIED_FOR;
		EMP_NAME = eMP_NAME;
		FATHER_HUSBAND_NAME = fATHER_HUSBAND_NAME;
		BASIC = bASIC;
		UAN = uAN;
		DOJ_FCI = dOJ_FCI;
		PAN = pAN;
		DESIGNATION = dESIGNATION;
		PRESENT_LOCATION = pRESENT_LOCATION;
		PARENT_ZONE = pARENT_ZONE;
		DATE_OF_BIRTH = dATE_OF_BIRTH;
		STAFF_CODE = sTAFF_CODE;
		CPF_ACCOUNT_NUMBER = cPF_ACCOUNT_NUMBER;
		MOBILE_NUMBER = mOBILE_NUMBER;
		RETIREMENT_DATE = rETIREMENT_DATE;
		PURPOSE = pURPOSE;
		AMOUNT = aMOUNT;
		INSTALLMENT_NUMBER = iNSTALLMENT_NUMBER;
		LAST_DRAWN_ADVANCE = lAST_DRAWN_ADVANCE;
		ADVANCE_AMOUNT = aDVANCE_AMOUNT;
		AMOUNT_REPAID = aMOUNT_REPAID;
		OUTSTANDING_BAL = oUTSTANDING_BAL;
		AMOUNT_90PARTFINAL_BEF_RETR = aMOUNT_90PARTFINAL_BEF_RETR;
		INFO1 = iNFO1;
		INFO2 = iNFO2;
		INFO3 = iNFO3;
		INFO4 = iNFO4;
		CASTE_DISPUTE_CERT = cASTE_DISPUTE_CERT;
		PERMISSIBLE_AMOUNT = pERMISSIBLE_AMOUNT;
		DEC_NOT_EMP_TWOMONTH = dEC_NOT_EMP_TWOMONTH;
		EMP_DECLARATION = eMP_DECLARATION;
		AMOUNT_SANCTION = aMOUNT_SANCTION;
		RE_CLAIM_SUBMITTED_DATE = rE_CLAIM_SUBMITTED_DATE;
		CLAIM_COUNT = cLAIM_COUNT;
	}



	@Id
	@GenericGenerator(name = "cpfClaimSeq", strategy = "com.techm.fci.cpf.daoimpl.RequestIdGenerator")
	@GeneratedValue(generator = "cpfClaimSeq")
	@Column(name = "REQUEST_ID", unique = true, nullable = false)
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

	public String getCLAIM_APPLIED_FOR() {
		return CLAIM_APPLIED_FOR;
	}

	public void setCLAIM_APPLIED_FOR(String cLAIM_APPLIED_FOR) {
		CLAIM_APPLIED_FOR = cLAIM_APPLIED_FOR;
	}

	public String getEMP_NAME() {
		return EMP_NAME;
	}

	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}

	public String getFATHER_HUSBAND_NAME() {
		return FATHER_HUSBAND_NAME;
	}

	public void setFATHER_HUSBAND_NAME(String fATHER_HUSBAND_NAME) {
		FATHER_HUSBAND_NAME = fATHER_HUSBAND_NAME;
	}

	public String getBASIC() {
		return BASIC;
	}

	public void setBASIC(String bASIC) {
		BASIC = bASIC;
	}

	public String getUAN() {
		return UAN;
	}

	public void setUAN(String uAN) {
		UAN = uAN;
	}

	public Date getDOJ_FCI() {
		return DOJ_FCI;
	}

	public void setDOJ_FCI(Date dOJ_FCI) {
		DOJ_FCI = dOJ_FCI;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getDESIGNATION() {
		return DESIGNATION;
	}

	public void setDESIGNATION(String dESIGNATION) {
		DESIGNATION = dESIGNATION;
	}

	public String getPRESENT_LOCATION() {
		return PRESENT_LOCATION;
	}

	public void setPRESENT_LOCATION(String pRESENT_LOCATION) {
		PRESENT_LOCATION = pRESENT_LOCATION;
	}

	public String getPARENT_ZONE() {
		return PARENT_ZONE;
	}

	public void setPARENT_ZONE(String pARENT_ZONE) {
		PARENT_ZONE = pARENT_ZONE;
	}

	public Date getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}

	public void setDATE_OF_BIRTH(Date dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
	}

	public String getSTAFF_CODE() {
		return STAFF_CODE;
	}

	public void setSTAFF_CODE(String sTAFF_CODE) {
		STAFF_CODE = sTAFF_CODE;
	}

	public String getCPF_ACCOUNT_NUMBER() {
		return CPF_ACCOUNT_NUMBER;
	}

	public void setCPF_ACCOUNT_NUMBER(String cPF_ACCOUNT_NUMBER) {
		CPF_ACCOUNT_NUMBER = cPF_ACCOUNT_NUMBER;
	}

	public String getMOBILE_NUMBER() {
		return MOBILE_NUMBER;
	}

	public void setMOBILE_NUMBER(String mOBILE_NUMBER) {
		MOBILE_NUMBER = mOBILE_NUMBER;
	}

	public Date getRETIREMENT_DATE() {
		return RETIREMENT_DATE;
	}

	public void setRETIREMENT_DATE(Date rETIREMENT_DATE) {
		RETIREMENT_DATE = rETIREMENT_DATE;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}

	public String getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public String getINSTALLMENT_NUMBER() {
		return INSTALLMENT_NUMBER;
	}

	public void setINSTALLMENT_NUMBER(String iNSTALLMENT_NUMBER) {
		INSTALLMENT_NUMBER = iNSTALLMENT_NUMBER;
	}

	public boolean getLAST_DRAWN_ADVANCE() {
		return LAST_DRAWN_ADVANCE;
	}

	public void setLAST_DRAWN_ADVANCE(boolean lAST_DRAWN_ADVANCE) {
		LAST_DRAWN_ADVANCE = lAST_DRAWN_ADVANCE;
	}

	public String getADVANCE_AMOUNT() {
		return ADVANCE_AMOUNT;
	}

	public void setADVANCE_AMOUNT(String aDVANCE_AMOUNT) {
		ADVANCE_AMOUNT = aDVANCE_AMOUNT;
	}

	public String getAMOUNT_REPAID() {
		return AMOUNT_REPAID;
	}

	public void setAMOUNT_REPAID(String aMOUNT_REPAID) {
		AMOUNT_REPAID = aMOUNT_REPAID;
	}

	public String getOUTSTANDING_BAL() {
		return OUTSTANDING_BAL;
	}

	public void setOUTSTANDING_BAL(String oUTSTANDING_BAL) {
		OUTSTANDING_BAL = oUTSTANDING_BAL;
	}

	public String getAMOUNT_90PARTFINAL_BEF_RETR() {
		return AMOUNT_90PARTFINAL_BEF_RETR;
	}

	public void setAMOUNT_90PARTFINAL_BEF_RETR(String aMOUNT_90PARTFINAL_BEF_RETR) {
		AMOUNT_90PARTFINAL_BEF_RETR = aMOUNT_90PARTFINAL_BEF_RETR;
	}
	
	/*public String getFILE() {
		return FILE;
	}

	public void setFILE(String fILE) {
		FILE = fILE;
	}*/

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

	public String getCASTE_DISPUTE_CERT() {
		return CASTE_DISPUTE_CERT;
	}

	public void setCASTE_DISPUTE_CERT(String cASTE_DISPUTE_CERT) {
		CASTE_DISPUTE_CERT = cASTE_DISPUTE_CERT;
	}

	public boolean getPERMISSIBLE_AMOUNT() {
		return PERMISSIBLE_AMOUNT;
	}

	public void setPERMISSIBLE_AMOUNT(boolean pERMISSIBLE_AMOUNT) {
		PERMISSIBLE_AMOUNT = pERMISSIBLE_AMOUNT;
	}

	public boolean getDEC_NOT_EMP_TWOMONTH() {
		return DEC_NOT_EMP_TWOMONTH;
	}

	public void setDEC_NOT_EMP_TWOMONTH(boolean dEC_NOT_EMP_TWOMONTH) {
		DEC_NOT_EMP_TWOMONTH = dEC_NOT_EMP_TWOMONTH;
	}

	public boolean getEMP_DECLARATION() {
		return EMP_DECLARATION;
	}

	public void setEMP_DECLARATION(boolean eMP_DECLARATION) {
		EMP_DECLARATION = eMP_DECLARATION;
	}
	
	public String getAMOUNT_SANCTION() {
		return AMOUNT_SANCTION;
	}

	public void setAMOUNT_SANCTION(String aMOUNT_SANCTION) {
		AMOUNT_SANCTION = aMOUNT_SANCTION;
	}

	public Date getRE_CLAIM_SUBMITTED_DATE() {
		return RE_CLAIM_SUBMITTED_DATE;
	}

	public void setRE_CLAIM_SUBMITTED_DATE(Date rE_CLAIM_SUBMITTED_DATE) {
		RE_CLAIM_SUBMITTED_DATE = rE_CLAIM_SUBMITTED_DATE;
	}

	public int getCLAIM_COUNT() {
		return CLAIM_COUNT;
	}

	public void setCLAIM_COUNT(int cLAIM_COUNT) {
		CLAIM_COUNT = cLAIM_COUNT;
	}

	@Override
	public String toString() {
		return "CpfClaimRequest [REQUEST_ID=" + REQUEST_ID + ", CLAIM_SUBMITTED_BY=" + CLAIM_SUBMITTED_BY
				+ ", CLAIM_SUBMITTED_DATE=" + CLAIM_SUBMITTED_DATE + ", CLAIM_APPLIED_FOR=" + CLAIM_APPLIED_FOR
				+ ", EMP_NAME=" + EMP_NAME + ", FATHER_HUSBAND_NAME=" + FATHER_HUSBAND_NAME + ", BASIC=" + BASIC
				+ ", UAN=" + UAN + ", DOJ_FCI=" + DOJ_FCI + ", PAN=" + PAN + ", DESIGNATION=" + DESIGNATION
				+ ", PRESENT_LOCATION=" + PRESENT_LOCATION + ", PARENT_ZONE=" + PARENT_ZONE + ", DATE_OF_BIRTH="
				+ DATE_OF_BIRTH + ", STAFF_CODE=" + STAFF_CODE + ", CPF_ACCOUNT_NUMBER=" + CPF_ACCOUNT_NUMBER
				+ ", MOBILE_NUMBER=" + MOBILE_NUMBER + ", RETIREMENT_DATE=" + RETIREMENT_DATE + ", PURPOSE=" + PURPOSE
				+ ", AMOUNT=" + AMOUNT + ", INSTALLMENT_NUMBER=" + INSTALLMENT_NUMBER + ", LAST_DRAWN_ADVANCE="
				+ LAST_DRAWN_ADVANCE + ", ADVANCE_AMOUNT=" + ADVANCE_AMOUNT + ", AMOUNT_REPAID=" + AMOUNT_REPAID
				+ ", OUTSTANDING_BAL=" + OUTSTANDING_BAL + ", AMOUNT_90PARTFINAL_BEF_RETR="
				+ AMOUNT_90PARTFINAL_BEF_RETR + ", INFO1=" + INFO1 + ", INFO2=" + INFO2 + ", INFO3=" + INFO3
				+ ", INFO4=" + INFO4 + ", CASTE_DISPUTE_CERT=" + CASTE_DISPUTE_CERT + ", PERMISSIBLE_AMOUNT="
				+ PERMISSIBLE_AMOUNT + ", DEC_NOT_EMP_TWOMONTH=" + DEC_NOT_EMP_TWOMONTH + ", EMP_DECLARATION="
				+ EMP_DECLARATION + ", AMOUNT_SANCTION=" + AMOUNT_SANCTION + ", RE_CLAIM_SUBMITTED_DATE="
				+ RE_CLAIM_SUBMITTED_DATE + ", CLAIM_COUNT=" + CLAIM_COUNT + "]";
	}
}
