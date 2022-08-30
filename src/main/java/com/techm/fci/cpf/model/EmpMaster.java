package com.techm.fci.cpf.model;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pay_emp_mast")
public class EmpMaster implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMP_NUM", unique = true, nullable = false, precision = 5, scale = 0)
	public String EMP_NUM;
	public String EMP_FIRST_NAME;
	public String EMP_MIDDLE_NAME;
	public String EMP_LAST_NAME;
	public String EMP_SEX_CODE;
	public String EMP_BIRTH_DATE;
	public String COMP_JOINING_DATE;
	public String LOCATION_CODE;
	public String PRES_LOCATION_CODE;
	public String LAST_PROMOTION_DATE;
	public String EMP_STATUS;
	public String CADRE;
	public String STAFF_CODE;
	public String DESIGNATION_ID;
	public String PAY_SCALE_CODE;
	public String PAY_STATUS;
	public String PAY_STA_CHG_DT;
	public String BASIC;
	public String INCREMENT_STATUS;
	public String BANK_AC_NO;
	public String HANDICAPPED_FLAG;
	public String USER_ID_CREATED;
	public String CREATED_SITE_ID;
	public String CREATED_TIME_STAMP;
	public String USER_ID_MODIFIED;
	public String MODIFIED_SITE_ID;
	public String MODIFIED_TIME_STAMP;
	public String MARITAL_STATUS;
	public String PAY_MODE;
	public String EMP_CATEGORY;
	public String WASHING_ALLOW_FLAG;
	public String EMP_PAN_NO;
	public String BANK_CODE;
	public String BRANCH_CODE;
	public String RETIREMENT_DATE;
	public String CONV_ALLOW_FLAG;
	public String EMP_TYPE;
	public String PAY_SCALE_TYPE;
	public String DEPUT_ALLOW_FLAG;
	public String LUNCH_SUBS_FLAG;
	public String STAG_INCR;
	public String EMP_PF_TYPE;
	public String PAY_MODE_TYPE;
	public String CCA_FLAG;
	public String CITY_TYPE_HRA;
	public String NPA_FLAG;
	public String ACCOM_TYPE;
	public String LEASE_AMT;
	public String HRA_FLAG;
	public String LICENCE_FEE_DEM;
	public String CPF_CODE;
	public String HANDICAPPED_EFF_DATE;
	public String FATHER_NAME;
	public String HOMETOWN;
	public String HRA_EFF_DATE;
	public String FPS_NUM;
	public String SITE_JOINING_DATE;
	public String PERS_PAY_HIGH_QUAL;
	public String STAG_INCR_EFF_DATE;
	public String VCPF_SUBS_AMOUNT;
	public String HRA_STATUS;
	public String PLI_FLAG;
	public String DUST_OPERATOR_FLAG;
	public String DBL_HRA_FLAG;
	public String DBL_HRA_CITY;
	public String ADV_INCR_TYPE;
	public String ADV_INCR_EFF_DATE;
	public String SPL_COMP_ALLOW;
	public String SPL_PAY_SPORT_FLAG;
	public String SPL_PAY_SPORT_AMOUNT;
	public String SPL_PAY_CASH_FLAG;
	public String SPL_PAY_CASH_AMOUNT;
	public String PERS_PAY_SFN_FLAG;
	public String PERS_PAY_SFN_AMOUNT;
	public String PERS_PAY_HIGH_QUAL_FLAG;
	public String PERS_PAY_HIGH_QUAL_AMT;
	public String INFO_1;
	public String INFO_2;
	public String INFO_3;
	public String INFO_4;
	public String INFO_5;
	public String SPL_COMP_ALLOW_FLAG;
	public String STATION_COMP_FLAG;
	public String DEPUTATION_TYPE;
	public String PARENT_ZONE;
	public String PREFIX;
	public String SECTION_ID;
	public String INCR_EFF_DATE;
	public String OLD_BASIC;
	public String PAYROLL_FLAG;
	public String TRANSFER_DATE;
	public String PREV_CITY_TYPE;
	public String SUBS_ALLOW_PERC;
	public String SUSP_EFF_DT;
	public String SUSP_REVK_EFF_DT;
	public String REVOCATION_TYPE;
	public String ADV_INCR_AMT;
	public String ADV_INCR_ENDDT;
	public String PERS_PAY_FUT_INCR;
	public String EMP_LBR;
	public String OPERATIONAL_ALLOW_FLAG;
	public String HRA_EFF_END_DATE;
	public String ACCOM_TYPE_STRT_DATE;
	public String ACCOM_TYPE_END_DATE;
	public String OLD_CATEGORY;
	public String LAST_PYRL_YYMM;
	public String MHS_FLAG;
	public String MHSAMT;
	public String TOTMHSAMTRCVD;
	public String MHSINSTL;
	public String DBL_HRA_EFF_FROM;
	public String DBL_HRA_EFF_TILL;
	public String SPL_CMP_EFF_FROM;
	public String SPL_CMP_EFF_TILL;
	public String SP_SPRT_EFF_FROM;
	public String SP_SPRT_EFF_TILL;
	public String SP_CASH_EFF_FROM;
	public String SP_CASH_EFF_TILL;
	public String PP_SFN_EFF_FROM;
	public String PP_SFN_EFF_TILL;
	public String PP_HQUL_EFF_FROM;
	public String PP_HQUL_EFF_TILL;
	public String PP_HINDI_FLAG;
	public String PP_HINDI_NUM_INCR;
	public String PP_HINDI_AMT;
	public String PP_HINDI_EFF_FROM;
	public String PP_HINDI_EFF_TILL;
	public String EPS_FLAG;
	public String EPS_ENTLMNT_DT;
	public String EPS_SEP_DT;
	public String EPS_SEP_REASON;
	public String OLD_PAY_SCALE_CODE;
	public String REVISED_SUBS_ALLOW;
	public String REVISED_SUBS_ALLW_EFF_DT;
	public String OLD_DESIGNATION_ID;
	public String KOT_LBR_ID;
	public String SPL_DTY_ALLOW_FLAG;
	public String SPL_DTY_EFF_FROM;
	public String SPL_DTY_EFF_TILL;
	public String CPFRECSTOPYYMM;
	public String CPFRECSTOPFLAG;
	public String GRADE_PAY;
	public String OLD_GRADE_PAY;
	public String SPL_DTY_AREA_TYPE;
	public String PAY_REV_FLAG;
	public String GRADEPAY_CHANGE_DT;
	public String KOTWAL_LABOUR;
	public String VEHICLE_FLAG;
	public String LAST_INCREMENT_DATE;
	public String CASTE_CATEGORY;
	public String RELIGION;
	public String EX_SERVICEMEN;
	public String HANDICAPPED_TYPE;
	public String AADHAR_NUM;
	public String UAN;
	public String PENSION_DED_FLAG;
	public String PENSION_NUMBER;
	public String PENSION_ADMIN_FEE;
	public String ADDON_PAY;
	public String INCT_BASIC;
	public String INCT_OLD_BASIC;
	
	
	public EmpMaster(){
		
	}
	
	public EmpMaster(String eMP_NUM, String eMP_FIRST_NAME, String eMP_MIDDLE_NAME, String eMP_LAST_NAME,
			String eMP_SEX_CODE, String eMP_BIRTH_DATE, String cOMP_JOINING_DATE, String lOCATION_CODE,
			String pRES_LOCATION_CODE, String lAST_PROMOTION_DATE, String eMP_STATUS, String cADRE, String sTAFF_CODE,
			String dESIGNATION_ID, String pAY_SCALE_CODE, String pAY_STATUS, String pAY_STA_CHG_DT, String bASIC,
			String iNCREMENT_STATUS, String bANK_AC_NO, String hANDICAPPED_FLAG, String uSER_ID_CREATED,
			String cREATED_SITE_ID, String cREATED_TIME_STAMP, String uSER_ID_MODIFIED, String mODIFIED_SITE_ID,
			String mODIFIED_TIME_STAMP, String mARITAL_STATUS, String pAY_MODE, String eMP_CATEGORY,
			String wASHING_ALLOW_FLAG, String eMP_PAN_NO, String bANK_CODE, String bRANCH_CODE, String rETIREMENT_DATE,
			String cONV_ALLOW_FLAG, String eMP_TYPE, String pAY_SCALE_TYPE, String dEPUT_ALLOW_FLAG,
			String lUNCH_SUBS_FLAG, String sTAG_INCR, String eMP_PF_TYPE, String pAY_MODE_TYPE, String cCA_FLAG,
			String cITY_TYPE_HRA, String nPA_FLAG, String aCCOM_TYPE, String lEASE_AMT, String hRA_FLAG,
			String lICENCE_FEE_DEM, String cPF_CODE, String hANDICAPPED_EFF_DATE, String fATHER_NAME, String hOMETOWN,
			String hRA_EFF_DATE, String fPS_NUM, String sITE_JOINING_DATE, String pERS_PAY_HIGH_QUAL,
			String sTAG_INCR_EFF_DATE, String vCPF_SUBS_AMOUNT, String hRA_STATUS, String pLI_FLAG,
			String dUST_OPERATOR_FLAG, String dBL_HRA_FLAG, String dBL_HRA_CITY, String aDV_INCR_TYPE,
			String aDV_INCR_EFF_DATE, String sPL_COMP_ALLOW, String sPL_PAY_SPORT_FLAG, String sPL_PAY_SPORT_AMOUNT,
			String sPL_PAY_CASH_FLAG, String sPL_PAY_CASH_AMOUNT, String pERS_PAY_SFN_FLAG, String pERS_PAY_SFN_AMOUNT,
			String pERS_PAY_HIGH_QUAL_FLAG, String pERS_PAY_HIGH_QUAL_AMT, String iNFO_1, String iNFO_2, String iNFO_3,
			String iNFO_4, String iNFO_5, String sPL_COMP_ALLOW_FLAG, String sTATION_COMP_FLAG, String dEPUTATION_TYPE,
			String pARENT_ZONE, String pREFIX, String sECTION_ID, String iNCR_EFF_DATE, String oLD_BASIC,
			String pAYROLL_FLAG, String tRANSFER_DATE, String pREV_CITY_TYPE, String sUBS_ALLOW_PERC,
			String sUSP_EFF_DT, String sUSP_REVK_EFF_DT, String rEVOCATION_TYPE, String aDV_INCR_AMT,
			String aDV_INCR_ENDDT, String pERS_PAY_FUT_INCR, String eMP_LBR, String oPERATIONAL_ALLOW_FLAG,
			String hRA_EFF_END_DATE, String aCCOM_TYPE_STRT_DATE, String aCCOM_TYPE_END_DATE, String oLD_CATEGORY,
			String lAST_PYRL_YYMM, String mHS_FLAG, String mHSAMT, String tOTMHSAMTRCVD, String mHSINSTL,
			String dBL_HRA_EFF_FROM, String dBL_HRA_EFF_TILL, String sPL_CMP_EFF_FROM, String sPL_CMP_EFF_TILL,
			String sP_SPRT_EFF_FROM, String sP_SPRT_EFF_TILL, String sP_CASH_EFF_FROM, String sP_CASH_EFF_TILL,
			String pP_SFN_EFF_FROM, String pP_SFN_EFF_TILL, String pP_HQUL_EFF_FROM, String pP_HQUL_EFF_TILL,
			String pP_HINDI_FLAG, String pP_HINDI_NUM_INCR, String pP_HINDI_AMT, String pP_HINDI_EFF_FROM,
			String pP_HINDI_EFF_TILL, String ePS_FLAG, String ePS_ENTLMNT_DT, String ePS_SEP_DT, String ePS_SEP_REASON,
			String oLD_PAY_SCALE_CODE, String rEVISED_SUBS_ALLOW, String rEVISED_SUBS_ALLW_EFF_DT,
			String oLD_DESIGNATION_ID, String kOT_LBR_ID, String sPL_DTY_ALLOW_FLAG, String sPL_DTY_EFF_FROM,
			String sPL_DTY_EFF_TILL, String cPFRECSTOPYYMM, String cPFRECSTOPFLAG, String gRADE_PAY,
			String oLD_GRADE_PAY, String sPL_DTY_AREA_TYPE, String pAY_REV_FLAG, String gRADEPAY_CHANGE_DT,
			String kOTWAL_LABOUR, String vEHICLE_FLAG, String lAST_INCREMENT_DATE, String cASTE_CATEGORY,
			String rELIGION, String eX_SERVICEMEN, String hANDICAPPED_TYPE, String aADHAR_NUM, String uAN,
			String pENSION_DED_FLAG, String pENSION_NUMBER, String pENSION_ADMIN_FEE, String aDDON_PAY,
			String iNCT_BASIC, String iNCT_OLD_BASIC) {
		super();
		EMP_NUM = eMP_NUM;
		EMP_FIRST_NAME = eMP_FIRST_NAME;
		EMP_MIDDLE_NAME = eMP_MIDDLE_NAME;
		EMP_LAST_NAME = eMP_LAST_NAME;
		EMP_SEX_CODE = eMP_SEX_CODE;
		EMP_BIRTH_DATE = eMP_BIRTH_DATE;
		COMP_JOINING_DATE = cOMP_JOINING_DATE;
		LOCATION_CODE = lOCATION_CODE;
		PRES_LOCATION_CODE = pRES_LOCATION_CODE;
		LAST_PROMOTION_DATE = lAST_PROMOTION_DATE;
		EMP_STATUS = eMP_STATUS;
		CADRE = cADRE;
		STAFF_CODE = sTAFF_CODE;
		DESIGNATION_ID = dESIGNATION_ID;
		PAY_SCALE_CODE = pAY_SCALE_CODE;
		PAY_STATUS = pAY_STATUS;
		PAY_STA_CHG_DT = pAY_STA_CHG_DT;
		BASIC = bASIC;
		INCREMENT_STATUS = iNCREMENT_STATUS;
		BANK_AC_NO = bANK_AC_NO;
		HANDICAPPED_FLAG = hANDICAPPED_FLAG;
		USER_ID_CREATED = uSER_ID_CREATED;
		CREATED_SITE_ID = cREATED_SITE_ID;
		CREATED_TIME_STAMP = cREATED_TIME_STAMP;
		USER_ID_MODIFIED = uSER_ID_MODIFIED;
		MODIFIED_SITE_ID = mODIFIED_SITE_ID;
		MODIFIED_TIME_STAMP = mODIFIED_TIME_STAMP;
		MARITAL_STATUS = mARITAL_STATUS;
		PAY_MODE = pAY_MODE;
		EMP_CATEGORY = eMP_CATEGORY;
		WASHING_ALLOW_FLAG = wASHING_ALLOW_FLAG;
		EMP_PAN_NO = eMP_PAN_NO;
		BANK_CODE = bANK_CODE;
		BRANCH_CODE = bRANCH_CODE;
		RETIREMENT_DATE = rETIREMENT_DATE;
		CONV_ALLOW_FLAG = cONV_ALLOW_FLAG;
		EMP_TYPE = eMP_TYPE;
		PAY_SCALE_TYPE = pAY_SCALE_TYPE;
		DEPUT_ALLOW_FLAG = dEPUT_ALLOW_FLAG;
		LUNCH_SUBS_FLAG = lUNCH_SUBS_FLAG;
		STAG_INCR = sTAG_INCR;
		EMP_PF_TYPE = eMP_PF_TYPE;
		PAY_MODE_TYPE = pAY_MODE_TYPE;
		CCA_FLAG = cCA_FLAG;
		CITY_TYPE_HRA = cITY_TYPE_HRA;
		NPA_FLAG = nPA_FLAG;
		ACCOM_TYPE = aCCOM_TYPE;
		LEASE_AMT = lEASE_AMT;
		HRA_FLAG = hRA_FLAG;
		LICENCE_FEE_DEM = lICENCE_FEE_DEM;
		CPF_CODE = cPF_CODE;
		HANDICAPPED_EFF_DATE = hANDICAPPED_EFF_DATE;
		FATHER_NAME = fATHER_NAME;
		HOMETOWN = hOMETOWN;
		HRA_EFF_DATE = hRA_EFF_DATE;
		FPS_NUM = fPS_NUM;
		SITE_JOINING_DATE = sITE_JOINING_DATE;
		PERS_PAY_HIGH_QUAL = pERS_PAY_HIGH_QUAL;
		STAG_INCR_EFF_DATE = sTAG_INCR_EFF_DATE;
		VCPF_SUBS_AMOUNT = vCPF_SUBS_AMOUNT;
		HRA_STATUS = hRA_STATUS;
		PLI_FLAG = pLI_FLAG;
		DUST_OPERATOR_FLAG = dUST_OPERATOR_FLAG;
		DBL_HRA_FLAG = dBL_HRA_FLAG;
		DBL_HRA_CITY = dBL_HRA_CITY;
		ADV_INCR_TYPE = aDV_INCR_TYPE;
		ADV_INCR_EFF_DATE = aDV_INCR_EFF_DATE;
		SPL_COMP_ALLOW = sPL_COMP_ALLOW;
		SPL_PAY_SPORT_FLAG = sPL_PAY_SPORT_FLAG;
		SPL_PAY_SPORT_AMOUNT = sPL_PAY_SPORT_AMOUNT;
		SPL_PAY_CASH_FLAG = sPL_PAY_CASH_FLAG;
		SPL_PAY_CASH_AMOUNT = sPL_PAY_CASH_AMOUNT;
		PERS_PAY_SFN_FLAG = pERS_PAY_SFN_FLAG;
		PERS_PAY_SFN_AMOUNT = pERS_PAY_SFN_AMOUNT;
		PERS_PAY_HIGH_QUAL_FLAG = pERS_PAY_HIGH_QUAL_FLAG;
		PERS_PAY_HIGH_QUAL_AMT = pERS_PAY_HIGH_QUAL_AMT;
		INFO_1 = iNFO_1;
		INFO_2 = iNFO_2;
		INFO_3 = iNFO_3;
		INFO_4 = iNFO_4;
		INFO_5 = iNFO_5;
		SPL_COMP_ALLOW_FLAG = sPL_COMP_ALLOW_FLAG;
		STATION_COMP_FLAG = sTATION_COMP_FLAG;
		DEPUTATION_TYPE = dEPUTATION_TYPE;
		PARENT_ZONE = pARENT_ZONE;
		PREFIX = pREFIX;
		SECTION_ID = sECTION_ID;
		INCR_EFF_DATE = iNCR_EFF_DATE;
		OLD_BASIC = oLD_BASIC;
		PAYROLL_FLAG = pAYROLL_FLAG;
		TRANSFER_DATE = tRANSFER_DATE;
		PREV_CITY_TYPE = pREV_CITY_TYPE;
		SUBS_ALLOW_PERC = sUBS_ALLOW_PERC;
		SUSP_EFF_DT = sUSP_EFF_DT;
		SUSP_REVK_EFF_DT = sUSP_REVK_EFF_DT;
		REVOCATION_TYPE = rEVOCATION_TYPE;
		ADV_INCR_AMT = aDV_INCR_AMT;
		ADV_INCR_ENDDT = aDV_INCR_ENDDT;
		PERS_PAY_FUT_INCR = pERS_PAY_FUT_INCR;
		EMP_LBR = eMP_LBR;
		OPERATIONAL_ALLOW_FLAG = oPERATIONAL_ALLOW_FLAG;
		HRA_EFF_END_DATE = hRA_EFF_END_DATE;
		ACCOM_TYPE_STRT_DATE = aCCOM_TYPE_STRT_DATE;
		ACCOM_TYPE_END_DATE = aCCOM_TYPE_END_DATE;
		OLD_CATEGORY = oLD_CATEGORY;
		LAST_PYRL_YYMM = lAST_PYRL_YYMM;
		MHS_FLAG = mHS_FLAG;
		MHSAMT = mHSAMT;
		TOTMHSAMTRCVD = tOTMHSAMTRCVD;
		MHSINSTL = mHSINSTL;
		DBL_HRA_EFF_FROM = dBL_HRA_EFF_FROM;
		DBL_HRA_EFF_TILL = dBL_HRA_EFF_TILL;
		SPL_CMP_EFF_FROM = sPL_CMP_EFF_FROM;
		SPL_CMP_EFF_TILL = sPL_CMP_EFF_TILL;
		SP_SPRT_EFF_FROM = sP_SPRT_EFF_FROM;
		SP_SPRT_EFF_TILL = sP_SPRT_EFF_TILL;
		SP_CASH_EFF_FROM = sP_CASH_EFF_FROM;
		SP_CASH_EFF_TILL = sP_CASH_EFF_TILL;
		PP_SFN_EFF_FROM = pP_SFN_EFF_FROM;
		PP_SFN_EFF_TILL = pP_SFN_EFF_TILL;
		PP_HQUL_EFF_FROM = pP_HQUL_EFF_FROM;
		PP_HQUL_EFF_TILL = pP_HQUL_EFF_TILL;
		PP_HINDI_FLAG = pP_HINDI_FLAG;
		PP_HINDI_NUM_INCR = pP_HINDI_NUM_INCR;
		PP_HINDI_AMT = pP_HINDI_AMT;
		PP_HINDI_EFF_FROM = pP_HINDI_EFF_FROM;
		PP_HINDI_EFF_TILL = pP_HINDI_EFF_TILL;
		EPS_FLAG = ePS_FLAG;
		EPS_ENTLMNT_DT = ePS_ENTLMNT_DT;
		EPS_SEP_DT = ePS_SEP_DT;
		EPS_SEP_REASON = ePS_SEP_REASON;
		OLD_PAY_SCALE_CODE = oLD_PAY_SCALE_CODE;
		REVISED_SUBS_ALLOW = rEVISED_SUBS_ALLOW;
		REVISED_SUBS_ALLW_EFF_DT = rEVISED_SUBS_ALLW_EFF_DT;
		OLD_DESIGNATION_ID = oLD_DESIGNATION_ID;
		KOT_LBR_ID = kOT_LBR_ID;
		SPL_DTY_ALLOW_FLAG = sPL_DTY_ALLOW_FLAG;
		SPL_DTY_EFF_FROM = sPL_DTY_EFF_FROM;
		SPL_DTY_EFF_TILL = sPL_DTY_EFF_TILL;
		CPFRECSTOPYYMM = cPFRECSTOPYYMM;
		CPFRECSTOPFLAG = cPFRECSTOPFLAG;
		GRADE_PAY = gRADE_PAY;
		OLD_GRADE_PAY = oLD_GRADE_PAY;
		SPL_DTY_AREA_TYPE = sPL_DTY_AREA_TYPE;
		PAY_REV_FLAG = pAY_REV_FLAG;
		GRADEPAY_CHANGE_DT = gRADEPAY_CHANGE_DT;
		KOTWAL_LABOUR = kOTWAL_LABOUR;
		VEHICLE_FLAG = vEHICLE_FLAG;
		LAST_INCREMENT_DATE = lAST_INCREMENT_DATE;
		CASTE_CATEGORY = cASTE_CATEGORY;
		RELIGION = rELIGION;
		EX_SERVICEMEN = eX_SERVICEMEN;
		HANDICAPPED_TYPE = hANDICAPPED_TYPE;
		AADHAR_NUM = aADHAR_NUM;
		UAN = uAN;
		PENSION_DED_FLAG = pENSION_DED_FLAG;
		PENSION_NUMBER = pENSION_NUMBER;
		PENSION_ADMIN_FEE = pENSION_ADMIN_FEE;
		ADDON_PAY = aDDON_PAY;
		INCT_BASIC = iNCT_BASIC;
		INCT_OLD_BASIC = iNCT_OLD_BASIC;
	}
	public String getEMP_NUM() {
		return EMP_NUM;
	}
	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}
	public String getEMP_FIRST_NAME() {
		return EMP_FIRST_NAME;
	}
	public void setEMP_FIRST_NAME(String eMP_FIRST_NAME) {
		EMP_FIRST_NAME = eMP_FIRST_NAME;
	}
	public String getEMP_MIDDLE_NAME() {
		return EMP_MIDDLE_NAME;
	}
	public void setEMP_MIDDLE_NAME(String eMP_MIDDLE_NAME) {
		EMP_MIDDLE_NAME = eMP_MIDDLE_NAME;
	}
	public String getEMP_LAST_NAME() {
		return EMP_LAST_NAME;
	}
	public void setEMP_LAST_NAME(String eMP_LAST_NAME) {
		EMP_LAST_NAME = eMP_LAST_NAME;
	}
	public String getEMP_SEX_CODE() {
		return EMP_SEX_CODE;
	}
	public void setEMP_SEX_CODE(String eMP_SEX_CODE) {
		EMP_SEX_CODE = eMP_SEX_CODE;
	}
	public String getEMP_BIRTH_DATE() {
		return EMP_BIRTH_DATE;
	}
	public void setEMP_BIRTH_DATE(String eMP_BIRTH_DATE) {
		EMP_BIRTH_DATE = eMP_BIRTH_DATE;
	}
	public String getCOMP_JOINING_DATE() {
		return COMP_JOINING_DATE;
	}
	public void setCOMP_JOINING_DATE(String cOMP_JOINING_DATE) {
		COMP_JOINING_DATE = cOMP_JOINING_DATE;
	}
	public String getLOCATION_CODE() {
		return LOCATION_CODE;
	}
	public void setLOCATION_CODE(String lOCATION_CODE) {
		LOCATION_CODE = lOCATION_CODE;
	}
	public String getPRES_LOCATION_CODE() {
		return PRES_LOCATION_CODE;
	}
	public void setPRES_LOCATION_CODE(String pRES_LOCATION_CODE) {
		PRES_LOCATION_CODE = pRES_LOCATION_CODE;
	}
	public String getLAST_PROMOTION_DATE() {
		return LAST_PROMOTION_DATE;
	}
	public void setLAST_PROMOTION_DATE(String lAST_PROMOTION_DATE) {
		LAST_PROMOTION_DATE = lAST_PROMOTION_DATE;
	}
	public String getEMP_STATUS() {
		return EMP_STATUS;
	}
	public void setEMP_STATUS(String eMP_STATUS) {
		EMP_STATUS = eMP_STATUS;
	}
	public String getCADRE() {
		return CADRE;
	}
	public void setCADRE(String cADRE) {
		CADRE = cADRE;
	}
	public String getSTAFF_CODE() {
		return STAFF_CODE;
	}
	public void setSTAFF_CODE(String sTAFF_CODE) {
		STAFF_CODE = sTAFF_CODE;
	}
	public String getDESIGNATION_ID() {
		return DESIGNATION_ID;
	}
	public void setDESIGNATION_ID(String dESIGNATION_ID) {
		DESIGNATION_ID = dESIGNATION_ID;
	}
	public String getPAY_SCALE_CODE() {
		return PAY_SCALE_CODE;
	}
	public void setPAY_SCALE_CODE(String pAY_SCALE_CODE) {
		PAY_SCALE_CODE = pAY_SCALE_CODE;
	}
	public String getPAY_STATUS() {
		return PAY_STATUS;
	}
	public void setPAY_STATUS(String pAY_STATUS) {
		PAY_STATUS = pAY_STATUS;
	}
	public String getPAY_STA_CHG_DT() {
		return PAY_STA_CHG_DT;
	}
	public void setPAY_STA_CHG_DT(String pAY_STA_CHG_DT) {
		PAY_STA_CHG_DT = pAY_STA_CHG_DT;
	}
	public String getBASIC() {
		return BASIC;
	}
	public void setBASIC(String bASIC) {
		BASIC = bASIC;
	}
	public String getINCREMENT_STATUS() {
		return INCREMENT_STATUS;
	}
	public void setINCREMENT_STATUS(String iNCREMENT_STATUS) {
		INCREMENT_STATUS = iNCREMENT_STATUS;
	}
	public String getBANK_AC_NO() {
		return BANK_AC_NO;
	}
	public void setBANK_AC_NO(String bANK_AC_NO) {
		BANK_AC_NO = bANK_AC_NO;
	}
	public String getHANDICAPPED_FLAG() {
		return HANDICAPPED_FLAG;
	}
	public void setHANDICAPPED_FLAG(String hANDICAPPED_FLAG) {
		HANDICAPPED_FLAG = hANDICAPPED_FLAG;
	}
	public String getUSER_ID_CREATED() {
		return USER_ID_CREATED;
	}
	public void setUSER_ID_CREATED(String uSER_ID_CREATED) {
		USER_ID_CREATED = uSER_ID_CREATED;
	}
	public String getCREATED_SITE_ID() {
		return CREATED_SITE_ID;
	}
	public void setCREATED_SITE_ID(String cREATED_SITE_ID) {
		CREATED_SITE_ID = cREATED_SITE_ID;
	}
	public String getCREATED_TIME_STAMP() {
		return CREATED_TIME_STAMP;
	}
	public void setCREATED_TIME_STAMP(String cREATED_TIME_STAMP) {
		CREATED_TIME_STAMP = cREATED_TIME_STAMP;
	}
	public String getUSER_ID_MODIFIED() {
		return USER_ID_MODIFIED;
	}
	public void setUSER_ID_MODIFIED(String uSER_ID_MODIFIED) {
		USER_ID_MODIFIED = uSER_ID_MODIFIED;
	}
	public String getMODIFIED_SITE_ID() {
		return MODIFIED_SITE_ID;
	}
	public void setMODIFIED_SITE_ID(String mODIFIED_SITE_ID) {
		MODIFIED_SITE_ID = mODIFIED_SITE_ID;
	}
	public String getMODIFIED_TIME_STAMP() {
		return MODIFIED_TIME_STAMP;
	}
	public void setMODIFIED_TIME_STAMP(String mODIFIED_TIME_STAMP) {
		MODIFIED_TIME_STAMP = mODIFIED_TIME_STAMP;
	}
	public String getMARITAL_STATUS() {
		return MARITAL_STATUS;
	}
	public void setMARITAL_STATUS(String mARITAL_STATUS) {
		MARITAL_STATUS = mARITAL_STATUS;
	}
	public String getPAY_MODE() {
		return PAY_MODE;
	}
	public void setPAY_MODE(String pAY_MODE) {
		PAY_MODE = pAY_MODE;
	}
	public String getEMP_CATEGORY() {
		return EMP_CATEGORY;
	}
	public void setEMP_CATEGORY(String eMP_CATEGORY) {
		EMP_CATEGORY = eMP_CATEGORY;
	}
	public String getWASHING_ALLOW_FLAG() {
		return WASHING_ALLOW_FLAG;
	}
	public void setWASHING_ALLOW_FLAG(String wASHING_ALLOW_FLAG) {
		WASHING_ALLOW_FLAG = wASHING_ALLOW_FLAG;
	}
	public String getEMP_PAN_NO() {
		return EMP_PAN_NO;
	}
	public void setEMP_PAN_NO(String eMP_PAN_NO) {
		EMP_PAN_NO = eMP_PAN_NO;
	}
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}
	public String getRETIREMENT_DATE() {
		return RETIREMENT_DATE;
	}
	public void setRETIREMENT_DATE(String rETIREMENT_DATE) {
		RETIREMENT_DATE = rETIREMENT_DATE;
	}
	public String getCONV_ALLOW_FLAG() {
		return CONV_ALLOW_FLAG;
	}
	public void setCONV_ALLOW_FLAG(String cONV_ALLOW_FLAG) {
		CONV_ALLOW_FLAG = cONV_ALLOW_FLAG;
	}
	public String getEMP_TYPE() {
		return EMP_TYPE;
	}
	public void setEMP_TYPE(String eMP_TYPE) {
		EMP_TYPE = eMP_TYPE;
	}
	public String getPAY_SCALE_TYPE() {
		return PAY_SCALE_TYPE;
	}
	public void setPAY_SCALE_TYPE(String pAY_SCALE_TYPE) {
		PAY_SCALE_TYPE = pAY_SCALE_TYPE;
	}
	public String getDEPUT_ALLOW_FLAG() {
		return DEPUT_ALLOW_FLAG;
	}
	public void setDEPUT_ALLOW_FLAG(String dEPUT_ALLOW_FLAG) {
		DEPUT_ALLOW_FLAG = dEPUT_ALLOW_FLAG;
	}
	public String getLUNCH_SUBS_FLAG() {
		return LUNCH_SUBS_FLAG;
	}
	public void setLUNCH_SUBS_FLAG(String lUNCH_SUBS_FLAG) {
		LUNCH_SUBS_FLAG = lUNCH_SUBS_FLAG;
	}
	public String getSTAG_INCR() {
		return STAG_INCR;
	}
	public void setSTAG_INCR(String sTAG_INCR) {
		STAG_INCR = sTAG_INCR;
	}
	public String getEMP_PF_TYPE() {
		return EMP_PF_TYPE;
	}
	public void setEMP_PF_TYPE(String eMP_PF_TYPE) {
		EMP_PF_TYPE = eMP_PF_TYPE;
	}
	public String getPAY_MODE_TYPE() {
		return PAY_MODE_TYPE;
	}
	public void setPAY_MODE_TYPE(String pAY_MODE_TYPE) {
		PAY_MODE_TYPE = pAY_MODE_TYPE;
	}
	public String getCCA_FLAG() {
		return CCA_FLAG;
	}
	public void setCCA_FLAG(String cCA_FLAG) {
		CCA_FLAG = cCA_FLAG;
	}
	public String getCITY_TYPE_HRA() {
		return CITY_TYPE_HRA;
	}
	public void setCITY_TYPE_HRA(String cITY_TYPE_HRA) {
		CITY_TYPE_HRA = cITY_TYPE_HRA;
	}
	public String getNPA_FLAG() {
		return NPA_FLAG;
	}
	public void setNPA_FLAG(String nPA_FLAG) {
		NPA_FLAG = nPA_FLAG;
	}
	public String getACCOM_TYPE() {
		return ACCOM_TYPE;
	}
	public void setACCOM_TYPE(String aCCOM_TYPE) {
		ACCOM_TYPE = aCCOM_TYPE;
	}
	public String getLEASE_AMT() {
		return LEASE_AMT;
	}
	public void setLEASE_AMT(String lEASE_AMT) {
		LEASE_AMT = lEASE_AMT;
	}
	public String getHRA_FLAG() {
		return HRA_FLAG;
	}
	public void setHRA_FLAG(String hRA_FLAG) {
		HRA_FLAG = hRA_FLAG;
	}
	public String getLICENCE_FEE_DEM() {
		return LICENCE_FEE_DEM;
	}
	public void setLICENCE_FEE_DEM(String lICENCE_FEE_DEM) {
		LICENCE_FEE_DEM = lICENCE_FEE_DEM;
	}
	public String getCPF_CODE() {
		return CPF_CODE;
	}
	public void setCPF_CODE(String cPF_CODE) {
		CPF_CODE = cPF_CODE;
	}
	public String getHANDICAPPED_EFF_DATE() {
		return HANDICAPPED_EFF_DATE;
	}
	public void setHANDICAPPED_EFF_DATE(String hANDICAPPED_EFF_DATE) {
		HANDICAPPED_EFF_DATE = hANDICAPPED_EFF_DATE;
	}
	public String getFATHER_NAME() {
		return FATHER_NAME;
	}
	public void setFATHER_NAME(String fATHER_NAME) {
		FATHER_NAME = fATHER_NAME;
	}
	public String getHOMETOWN() {
		return HOMETOWN;
	}
	public void setHOMETOWN(String hOMETOWN) {
		HOMETOWN = hOMETOWN;
	}
	public String getHRA_EFF_DATE() {
		return HRA_EFF_DATE;
	}
	public void setHRA_EFF_DATE(String hRA_EFF_DATE) {
		HRA_EFF_DATE = hRA_EFF_DATE;
	}
	public String getFPS_NUM() {
		return FPS_NUM;
	}
	public void setFPS_NUM(String fPS_NUM) {
		FPS_NUM = fPS_NUM;
	}
	public String getSITE_JOINING_DATE() {
		return SITE_JOINING_DATE;
	}
	public void setSITE_JOINING_DATE(String sITE_JOINING_DATE) {
		SITE_JOINING_DATE = sITE_JOINING_DATE;
	}
	public String getPERS_PAY_HIGH_QUAL() {
		return PERS_PAY_HIGH_QUAL;
	}
	public void setPERS_PAY_HIGH_QUAL(String pERS_PAY_HIGH_QUAL) {
		PERS_PAY_HIGH_QUAL = pERS_PAY_HIGH_QUAL;
	}
	public String getSTAG_INCR_EFF_DATE() {
		return STAG_INCR_EFF_DATE;
	}
	public void setSTAG_INCR_EFF_DATE(String sTAG_INCR_EFF_DATE) {
		STAG_INCR_EFF_DATE = sTAG_INCR_EFF_DATE;
	}
	public String getVCPF_SUBS_AMOUNT() {
		return VCPF_SUBS_AMOUNT;
	}
	public void setVCPF_SUBS_AMOUNT(String vCPF_SUBS_AMOUNT) {
		VCPF_SUBS_AMOUNT = vCPF_SUBS_AMOUNT;
	}
	public String getHRA_STATUS() {
		return HRA_STATUS;
	}
	public void setHRA_STATUS(String hRA_STATUS) {
		HRA_STATUS = hRA_STATUS;
	}
	public String getPLI_FLAG() {
		return PLI_FLAG;
	}
	public void setPLI_FLAG(String pLI_FLAG) {
		PLI_FLAG = pLI_FLAG;
	}
	public String getDUST_OPERATOR_FLAG() {
		return DUST_OPERATOR_FLAG;
	}
	public void setDUST_OPERATOR_FLAG(String dUST_OPERATOR_FLAG) {
		DUST_OPERATOR_FLAG = dUST_OPERATOR_FLAG;
	}
	public String getDBL_HRA_FLAG() {
		return DBL_HRA_FLAG;
	}
	public void setDBL_HRA_FLAG(String dBL_HRA_FLAG) {
		DBL_HRA_FLAG = dBL_HRA_FLAG;
	}
	public String getDBL_HRA_CITY() {
		return DBL_HRA_CITY;
	}
	public void setDBL_HRA_CITY(String dBL_HRA_CITY) {
		DBL_HRA_CITY = dBL_HRA_CITY;
	}
	public String getADV_INCR_TYPE() {
		return ADV_INCR_TYPE;
	}
	public void setADV_INCR_TYPE(String aDV_INCR_TYPE) {
		ADV_INCR_TYPE = aDV_INCR_TYPE;
	}
	public String getADV_INCR_EFF_DATE() {
		return ADV_INCR_EFF_DATE;
	}
	public void setADV_INCR_EFF_DATE(String aDV_INCR_EFF_DATE) {
		ADV_INCR_EFF_DATE = aDV_INCR_EFF_DATE;
	}
	public String getSPL_COMP_ALLOW() {
		return SPL_COMP_ALLOW;
	}
	public void setSPL_COMP_ALLOW(String sPL_COMP_ALLOW) {
		SPL_COMP_ALLOW = sPL_COMP_ALLOW;
	}
	public String getSPL_PAY_SPORT_FLAG() {
		return SPL_PAY_SPORT_FLAG;
	}
	public void setSPL_PAY_SPORT_FLAG(String sPL_PAY_SPORT_FLAG) {
		SPL_PAY_SPORT_FLAG = sPL_PAY_SPORT_FLAG;
	}
	public String getSPL_PAY_SPORT_AMOUNT() {
		return SPL_PAY_SPORT_AMOUNT;
	}
	public void setSPL_PAY_SPORT_AMOUNT(String sPL_PAY_SPORT_AMOUNT) {
		SPL_PAY_SPORT_AMOUNT = sPL_PAY_SPORT_AMOUNT;
	}
	public String getSPL_PAY_CASH_FLAG() {
		return SPL_PAY_CASH_FLAG;
	}
	public void setSPL_PAY_CASH_FLAG(String sPL_PAY_CASH_FLAG) {
		SPL_PAY_CASH_FLAG = sPL_PAY_CASH_FLAG;
	}
	public String getSPL_PAY_CASH_AMOUNT() {
		return SPL_PAY_CASH_AMOUNT;
	}
	public void setSPL_PAY_CASH_AMOUNT(String sPL_PAY_CASH_AMOUNT) {
		SPL_PAY_CASH_AMOUNT = sPL_PAY_CASH_AMOUNT;
	}
	public String getPERS_PAY_SFN_FLAG() {
		return PERS_PAY_SFN_FLAG;
	}
	public void setPERS_PAY_SFN_FLAG(String pERS_PAY_SFN_FLAG) {
		PERS_PAY_SFN_FLAG = pERS_PAY_SFN_FLAG;
	}
	public String getPERS_PAY_SFN_AMOUNT() {
		return PERS_PAY_SFN_AMOUNT;
	}
	public void setPERS_PAY_SFN_AMOUNT(String pERS_PAY_SFN_AMOUNT) {
		PERS_PAY_SFN_AMOUNT = pERS_PAY_SFN_AMOUNT;
	}
	public String getPERS_PAY_HIGH_QUAL_FLAG() {
		return PERS_PAY_HIGH_QUAL_FLAG;
	}
	public void setPERS_PAY_HIGH_QUAL_FLAG(String pERS_PAY_HIGH_QUAL_FLAG) {
		PERS_PAY_HIGH_QUAL_FLAG = pERS_PAY_HIGH_QUAL_FLAG;
	}
	public String getPERS_PAY_HIGH_QUAL_AMT() {
		return PERS_PAY_HIGH_QUAL_AMT;
	}
	public void setPERS_PAY_HIGH_QUAL_AMT(String pERS_PAY_HIGH_QUAL_AMT) {
		PERS_PAY_HIGH_QUAL_AMT = pERS_PAY_HIGH_QUAL_AMT;
	}
	public String getINFO_1() {
		return INFO_1;
	}
	public void setINFO_1(String iNFO_1) {
		INFO_1 = iNFO_1;
	}
	public String getINFO_2() {
		return INFO_2;
	}
	public void setINFO_2(String iNFO_2) {
		INFO_2 = iNFO_2;
	}
	public String getINFO_3() {
		return INFO_3;
	}
	public void setINFO_3(String iNFO_3) {
		INFO_3 = iNFO_3;
	}
	public String getINFO_4() {
		return INFO_4;
	}
	public void setINFO_4(String iNFO_4) {
		INFO_4 = iNFO_4;
	}
	public String getINFO_5() {
		return INFO_5;
	}
	public void setINFO_5(String iNFO_5) {
		INFO_5 = iNFO_5;
	}
	public String getSPL_COMP_ALLOW_FLAG() {
		return SPL_COMP_ALLOW_FLAG;
	}
	public void setSPL_COMP_ALLOW_FLAG(String sPL_COMP_ALLOW_FLAG) {
		SPL_COMP_ALLOW_FLAG = sPL_COMP_ALLOW_FLAG;
	}
	public String getSTATION_COMP_FLAG() {
		return STATION_COMP_FLAG;
	}
	public void setSTATION_COMP_FLAG(String sTATION_COMP_FLAG) {
		STATION_COMP_FLAG = sTATION_COMP_FLAG;
	}
	public String getDEPUTATION_TYPE() {
		return DEPUTATION_TYPE;
	}
	public void setDEPUTATION_TYPE(String dEPUTATION_TYPE) {
		DEPUTATION_TYPE = dEPUTATION_TYPE;
	}
	public String getPARENT_ZONE() {
		return PARENT_ZONE;
	}
	public void setPARENT_ZONE(String pARENT_ZONE) {
		PARENT_ZONE = pARENT_ZONE;
	}
	public String getPREFIX() {
		return PREFIX;
	}
	public void setPREFIX(String pREFIX) {
		PREFIX = pREFIX;
	}
	public String getSECTION_ID() {
		return SECTION_ID;
	}
	public void setSECTION_ID(String sECTION_ID) {
		SECTION_ID = sECTION_ID;
	}
	public String getINCR_EFF_DATE() {
		return INCR_EFF_DATE;
	}
	public void setINCR_EFF_DATE(String iNCR_EFF_DATE) {
		INCR_EFF_DATE = iNCR_EFF_DATE;
	}
	public String getOLD_BASIC() {
		return OLD_BASIC;
	}
	public void setOLD_BASIC(String oLD_BASIC) {
		OLD_BASIC = oLD_BASIC;
	}
	public String getPAYROLL_FLAG() {
		return PAYROLL_FLAG;
	}
	public void setPAYROLL_FLAG(String pAYROLL_FLAG) {
		PAYROLL_FLAG = pAYROLL_FLAG;
	}
	public String getTRANSFER_DATE() {
		return TRANSFER_DATE;
	}
	public void setTRANSFER_DATE(String tRANSFER_DATE) {
		TRANSFER_DATE = tRANSFER_DATE;
	}
	public String getPREV_CITY_TYPE() {
		return PREV_CITY_TYPE;
	}
	public void setPREV_CITY_TYPE(String pREV_CITY_TYPE) {
		PREV_CITY_TYPE = pREV_CITY_TYPE;
	}
	public String getSUBS_ALLOW_PERC() {
		return SUBS_ALLOW_PERC;
	}
	public void setSUBS_ALLOW_PERC(String sUBS_ALLOW_PERC) {
		SUBS_ALLOW_PERC = sUBS_ALLOW_PERC;
	}
	public String getSUSP_EFF_DT() {
		return SUSP_EFF_DT;
	}
	public void setSUSP_EFF_DT(String sUSP_EFF_DT) {
		SUSP_EFF_DT = sUSP_EFF_DT;
	}
	public String getSUSP_REVK_EFF_DT() {
		return SUSP_REVK_EFF_DT;
	}
	public void setSUSP_REVK_EFF_DT(String sUSP_REVK_EFF_DT) {
		SUSP_REVK_EFF_DT = sUSP_REVK_EFF_DT;
	}
	public String getREVOCATION_TYPE() {
		return REVOCATION_TYPE;
	}
	public void setREVOCATION_TYPE(String rEVOCATION_TYPE) {
		REVOCATION_TYPE = rEVOCATION_TYPE;
	}
	public String getADV_INCR_AMT() {
		return ADV_INCR_AMT;
	}
	public void setADV_INCR_AMT(String aDV_INCR_AMT) {
		ADV_INCR_AMT = aDV_INCR_AMT;
	}
	public String getADV_INCR_ENDDT() {
		return ADV_INCR_ENDDT;
	}
	public void setADV_INCR_ENDDT(String aDV_INCR_ENDDT) {
		ADV_INCR_ENDDT = aDV_INCR_ENDDT;
	}
	public String getPERS_PAY_FUT_INCR() {
		return PERS_PAY_FUT_INCR;
	}
	public void setPERS_PAY_FUT_INCR(String pERS_PAY_FUT_INCR) {
		PERS_PAY_FUT_INCR = pERS_PAY_FUT_INCR;
	}
	public String getEMP_LBR() {
		return EMP_LBR;
	}
	public void setEMP_LBR(String eMP_LBR) {
		EMP_LBR = eMP_LBR;
	}
	public String getOPERATIONAL_ALLOW_FLAG() {
		return OPERATIONAL_ALLOW_FLAG;
	}
	public void setOPERATIONAL_ALLOW_FLAG(String oPERATIONAL_ALLOW_FLAG) {
		OPERATIONAL_ALLOW_FLAG = oPERATIONAL_ALLOW_FLAG;
	}
	public String getHRA_EFF_END_DATE() {
		return HRA_EFF_END_DATE;
	}
	public void setHRA_EFF_END_DATE(String hRA_EFF_END_DATE) {
		HRA_EFF_END_DATE = hRA_EFF_END_DATE;
	}
	public String getACCOM_TYPE_STRT_DATE() {
		return ACCOM_TYPE_STRT_DATE;
	}
	public void setACCOM_TYPE_STRT_DATE(String aCCOM_TYPE_STRT_DATE) {
		ACCOM_TYPE_STRT_DATE = aCCOM_TYPE_STRT_DATE;
	}
	public String getACCOM_TYPE_END_DATE() {
		return ACCOM_TYPE_END_DATE;
	}
	public void setACCOM_TYPE_END_DATE(String aCCOM_TYPE_END_DATE) {
		ACCOM_TYPE_END_DATE = aCCOM_TYPE_END_DATE;
	}
	public String getOLD_CATEGORY() {
		return OLD_CATEGORY;
	}
	public void setOLD_CATEGORY(String oLD_CATEGORY) {
		OLD_CATEGORY = oLD_CATEGORY;
	}
	public String getLAST_PYRL_YYMM() {
		return LAST_PYRL_YYMM;
	}
	public void setLAST_PYRL_YYMM(String lAST_PYRL_YYMM) {
		LAST_PYRL_YYMM = lAST_PYRL_YYMM;
	}
	public String getMHS_FLAG() {
		return MHS_FLAG;
	}
	public void setMHS_FLAG(String mHS_FLAG) {
		MHS_FLAG = mHS_FLAG;
	}
	public String getMHSAMT() {
		return MHSAMT;
	}
	public void setMHSAMT(String mHSAMT) {
		MHSAMT = mHSAMT;
	}
	public String getTOTMHSAMTRCVD() {
		return TOTMHSAMTRCVD;
	}
	public void setTOTMHSAMTRCVD(String tOTMHSAMTRCVD) {
		TOTMHSAMTRCVD = tOTMHSAMTRCVD;
	}
	public String getMHSINSTL() {
		return MHSINSTL;
	}
	public void setMHSINSTL(String mHSINSTL) {
		MHSINSTL = mHSINSTL;
	}
	public String getDBL_HRA_EFF_FROM() {
		return DBL_HRA_EFF_FROM;
	}
	public void setDBL_HRA_EFF_FROM(String dBL_HRA_EFF_FROM) {
		DBL_HRA_EFF_FROM = dBL_HRA_EFF_FROM;
	}
	public String getDBL_HRA_EFF_TILL() {
		return DBL_HRA_EFF_TILL;
	}
	public void setDBL_HRA_EFF_TILL(String dBL_HRA_EFF_TILL) {
		DBL_HRA_EFF_TILL = dBL_HRA_EFF_TILL;
	}
	public String getSPL_CMP_EFF_FROM() {
		return SPL_CMP_EFF_FROM;
	}
	public void setSPL_CMP_EFF_FROM(String sPL_CMP_EFF_FROM) {
		SPL_CMP_EFF_FROM = sPL_CMP_EFF_FROM;
	}
	public String getSPL_CMP_EFF_TILL() {
		return SPL_CMP_EFF_TILL;
	}
	public void setSPL_CMP_EFF_TILL(String sPL_CMP_EFF_TILL) {
		SPL_CMP_EFF_TILL = sPL_CMP_EFF_TILL;
	}
	public String getSP_SPRT_EFF_FROM() {
		return SP_SPRT_EFF_FROM;
	}
	public void setSP_SPRT_EFF_FROM(String sP_SPRT_EFF_FROM) {
		SP_SPRT_EFF_FROM = sP_SPRT_EFF_FROM;
	}
	public String getSP_SPRT_EFF_TILL() {
		return SP_SPRT_EFF_TILL;
	}
	public void setSP_SPRT_EFF_TILL(String sP_SPRT_EFF_TILL) {
		SP_SPRT_EFF_TILL = sP_SPRT_EFF_TILL;
	}
	public String getSP_CASH_EFF_FROM() {
		return SP_CASH_EFF_FROM;
	}
	public void setSP_CASH_EFF_FROM(String sP_CASH_EFF_FROM) {
		SP_CASH_EFF_FROM = sP_CASH_EFF_FROM;
	}
	public String getSP_CASH_EFF_TILL() {
		return SP_CASH_EFF_TILL;
	}
	public void setSP_CASH_EFF_TILL(String sP_CASH_EFF_TILL) {
		SP_CASH_EFF_TILL = sP_CASH_EFF_TILL;
	}
	public String getPP_SFN_EFF_FROM() {
		return PP_SFN_EFF_FROM;
	}
	public void setPP_SFN_EFF_FROM(String pP_SFN_EFF_FROM) {
		PP_SFN_EFF_FROM = pP_SFN_EFF_FROM;
	}
	public String getPP_SFN_EFF_TILL() {
		return PP_SFN_EFF_TILL;
	}
	public void setPP_SFN_EFF_TILL(String pP_SFN_EFF_TILL) {
		PP_SFN_EFF_TILL = pP_SFN_EFF_TILL;
	}
	public String getPP_HQUL_EFF_FROM() {
		return PP_HQUL_EFF_FROM;
	}
	public void setPP_HQUL_EFF_FROM(String pP_HQUL_EFF_FROM) {
		PP_HQUL_EFF_FROM = pP_HQUL_EFF_FROM;
	}
	public String getPP_HQUL_EFF_TILL() {
		return PP_HQUL_EFF_TILL;
	}
	public void setPP_HQUL_EFF_TILL(String pP_HQUL_EFF_TILL) {
		PP_HQUL_EFF_TILL = pP_HQUL_EFF_TILL;
	}
	public String getPP_HINDI_FLAG() {
		return PP_HINDI_FLAG;
	}
	public void setPP_HINDI_FLAG(String pP_HINDI_FLAG) {
		PP_HINDI_FLAG = pP_HINDI_FLAG;
	}
	public String getPP_HINDI_NUM_INCR() {
		return PP_HINDI_NUM_INCR;
	}
	public void setPP_HINDI_NUM_INCR(String pP_HINDI_NUM_INCR) {
		PP_HINDI_NUM_INCR = pP_HINDI_NUM_INCR;
	}
	public String getPP_HINDI_AMT() {
		return PP_HINDI_AMT;
	}
	public void setPP_HINDI_AMT(String pP_HINDI_AMT) {
		PP_HINDI_AMT = pP_HINDI_AMT;
	}
	public String getPP_HINDI_EFF_FROM() {
		return PP_HINDI_EFF_FROM;
	}
	public void setPP_HINDI_EFF_FROM(String pP_HINDI_EFF_FROM) {
		PP_HINDI_EFF_FROM = pP_HINDI_EFF_FROM;
	}
	public String getPP_HINDI_EFF_TILL() {
		return PP_HINDI_EFF_TILL;
	}
	public void setPP_HINDI_EFF_TILL(String pP_HINDI_EFF_TILL) {
		PP_HINDI_EFF_TILL = pP_HINDI_EFF_TILL;
	}
	public String getEPS_FLAG() {
		return EPS_FLAG;
	}
	public void setEPS_FLAG(String ePS_FLAG) {
		EPS_FLAG = ePS_FLAG;
	}
	public String getEPS_ENTLMNT_DT() {
		return EPS_ENTLMNT_DT;
	}
	public void setEPS_ENTLMNT_DT(String ePS_ENTLMNT_DT) {
		EPS_ENTLMNT_DT = ePS_ENTLMNT_DT;
	}
	public String getEPS_SEP_DT() {
		return EPS_SEP_DT;
	}
	public void setEPS_SEP_DT(String ePS_SEP_DT) {
		EPS_SEP_DT = ePS_SEP_DT;
	}
	public String getEPS_SEP_REASON() {
		return EPS_SEP_REASON;
	}
	public void setEPS_SEP_REASON(String ePS_SEP_REASON) {
		EPS_SEP_REASON = ePS_SEP_REASON;
	}
	public String getOLD_PAY_SCALE_CODE() {
		return OLD_PAY_SCALE_CODE;
	}
	public void setOLD_PAY_SCALE_CODE(String oLD_PAY_SCALE_CODE) {
		OLD_PAY_SCALE_CODE = oLD_PAY_SCALE_CODE;
	}
	public String getREVISED_SUBS_ALLOW() {
		return REVISED_SUBS_ALLOW;
	}
	public void setREVISED_SUBS_ALLOW(String rEVISED_SUBS_ALLOW) {
		REVISED_SUBS_ALLOW = rEVISED_SUBS_ALLOW;
	}
	public String getREVISED_SUBS_ALLW_EFF_DT() {
		return REVISED_SUBS_ALLW_EFF_DT;
	}
	public void setREVISED_SUBS_ALLW_EFF_DT(String rEVISED_SUBS_ALLW_EFF_DT) {
		REVISED_SUBS_ALLW_EFF_DT = rEVISED_SUBS_ALLW_EFF_DT;
	}
	public String getOLD_DESIGNATION_ID() {
		return OLD_DESIGNATION_ID;
	}
	public void setOLD_DESIGNATION_ID(String oLD_DESIGNATION_ID) {
		OLD_DESIGNATION_ID = oLD_DESIGNATION_ID;
	}
	public String getKOT_LBR_ID() {
		return KOT_LBR_ID;
	}
	public void setKOT_LBR_ID(String kOT_LBR_ID) {
		KOT_LBR_ID = kOT_LBR_ID;
	}
	public String getSPL_DTY_ALLOW_FLAG() {
		return SPL_DTY_ALLOW_FLAG;
	}
	public void setSPL_DTY_ALLOW_FLAG(String sPL_DTY_ALLOW_FLAG) {
		SPL_DTY_ALLOW_FLAG = sPL_DTY_ALLOW_FLAG;
	}
	public String getSPL_DTY_EFF_FROM() {
		return SPL_DTY_EFF_FROM;
	}
	public void setSPL_DTY_EFF_FROM(String sPL_DTY_EFF_FROM) {
		SPL_DTY_EFF_FROM = sPL_DTY_EFF_FROM;
	}
	public String getSPL_DTY_EFF_TILL() {
		return SPL_DTY_EFF_TILL;
	}
	public void setSPL_DTY_EFF_TILL(String sPL_DTY_EFF_TILL) {
		SPL_DTY_EFF_TILL = sPL_DTY_EFF_TILL;
	}
	public String getCPFRECSTOPYYMM() {
		return CPFRECSTOPYYMM;
	}
	public void setCPFRECSTOPYYMM(String cPFRECSTOPYYMM) {
		CPFRECSTOPYYMM = cPFRECSTOPYYMM;
	}
	public String getCPFRECSTOPFLAG() {
		return CPFRECSTOPFLAG;
	}
	public void setCPFRECSTOPFLAG(String cPFRECSTOPFLAG) {
		CPFRECSTOPFLAG = cPFRECSTOPFLAG;
	}
	public String getGRADE_PAY() {
		return GRADE_PAY;
	}
	public void setGRADE_PAY(String gRADE_PAY) {
		GRADE_PAY = gRADE_PAY;
	}
	public String getOLD_GRADE_PAY() {
		return OLD_GRADE_PAY;
	}
	public void setOLD_GRADE_PAY(String oLD_GRADE_PAY) {
		OLD_GRADE_PAY = oLD_GRADE_PAY;
	}
	public String getSPL_DTY_AREA_TYPE() {
		return SPL_DTY_AREA_TYPE;
	}
	public void setSPL_DTY_AREA_TYPE(String sPL_DTY_AREA_TYPE) {
		SPL_DTY_AREA_TYPE = sPL_DTY_AREA_TYPE;
	}
	public String getPAY_REV_FLAG() {
		return PAY_REV_FLAG;
	}
	public void setPAY_REV_FLAG(String pAY_REV_FLAG) {
		PAY_REV_FLAG = pAY_REV_FLAG;
	}
	public String getGRADEPAY_CHANGE_DT() {
		return GRADEPAY_CHANGE_DT;
	}
	public void setGRADEPAY_CHANGE_DT(String gRADEPAY_CHANGE_DT) {
		GRADEPAY_CHANGE_DT = gRADEPAY_CHANGE_DT;
	}
	public String getKOTWAL_LABOUR() {
		return KOTWAL_LABOUR;
	}
	public void setKOTWAL_LABOUR(String kOTWAL_LABOUR) {
		KOTWAL_LABOUR = kOTWAL_LABOUR;
	}
	public String getVEHICLE_FLAG() {
		return VEHICLE_FLAG;
	}
	public void setVEHICLE_FLAG(String vEHICLE_FLAG) {
		VEHICLE_FLAG = vEHICLE_FLAG;
	}
	public String getLAST_INCREMENT_DATE() {
		return LAST_INCREMENT_DATE;
	}
	public void setLAST_INCREMENT_DATE(String lAST_INCREMENT_DATE) {
		LAST_INCREMENT_DATE = lAST_INCREMENT_DATE;
	}
	public String getCASTE_CATEGORY() {
		return CASTE_CATEGORY;
	}
	public void setCASTE_CATEGORY(String cASTE_CATEGORY) {
		CASTE_CATEGORY = cASTE_CATEGORY;
	}
	public String getRELIGION() {
		return RELIGION;
	}
	public void setRELIGION(String rELIGION) {
		RELIGION = rELIGION;
	}
	public String getEX_SERVICEMEN() {
		return EX_SERVICEMEN;
	}
	public void setEX_SERVICEMEN(String eX_SERVICEMEN) {
		EX_SERVICEMEN = eX_SERVICEMEN;
	}
	public String getHANDICAPPED_TYPE() {
		return HANDICAPPED_TYPE;
	}
	public void setHANDICAPPED_TYPE(String hANDICAPPED_TYPE) {
		HANDICAPPED_TYPE = hANDICAPPED_TYPE;
	}
	public String getAADHAR_NUM() {
		return AADHAR_NUM;
	}
	public void setAADHAR_NUM(String aADHAR_NUM) {
		AADHAR_NUM = aADHAR_NUM;
	}
	public String getUAN() {
		return UAN;
	}
	public void setUAN(String uAN) {
		UAN = uAN;
	}
	public String getPENSION_DED_FLAG() {
		return PENSION_DED_FLAG;
	}
	public void setPENSION_DED_FLAG(String pENSION_DED_FLAG) {
		PENSION_DED_FLAG = pENSION_DED_FLAG;
	}
	public String getPENSION_NUMBER() {
		return PENSION_NUMBER;
	}
	public void setPENSION_NUMBER(String pENSION_NUMBER) {
		PENSION_NUMBER = pENSION_NUMBER;
	}
	public String getPENSION_ADMIN_FEE() {
		return PENSION_ADMIN_FEE;
	}
	public void setPENSION_ADMIN_FEE(String pENSION_ADMIN_FEE) {
		PENSION_ADMIN_FEE = pENSION_ADMIN_FEE;
	}
	public String getADDON_PAY() {
		return ADDON_PAY;
	}
	public void setADDON_PAY(String aDDON_PAY) {
		ADDON_PAY = aDDON_PAY;
	}
	public String getINCT_BASIC() {
		return INCT_BASIC;
	}
	public void setINCT_BASIC(String iNCT_BASIC) {
		INCT_BASIC = iNCT_BASIC;
	}
	public String getINCT_OLD_BASIC() {
		return INCT_OLD_BASIC;
	}
	public void setINCT_OLD_BASIC(String iNCT_OLD_BASIC) {
		INCT_OLD_BASIC = iNCT_OLD_BASIC;
	}
		
}
