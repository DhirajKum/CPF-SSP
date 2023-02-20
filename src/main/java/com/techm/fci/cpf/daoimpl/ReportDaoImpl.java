package com.techm.fci.cpf.daoimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.techm.fci.cpf.dao.ReportDao;
import com.techm.fci.cpf.dto.ClaimRequestGenerateReportDto;
import com.techm.fci.cpf.dto.CpfSlipAdjustmentDataDto;
import com.techm.fci.cpf.dto.CpfSlipHeaderDto;
import com.techm.fci.cpf.dto.CpfSlipIntOBCreditAsOnDataDto;
import com.techm.fci.cpf.dto.CpfSlipMonthWiseDto;

@Repository("reportDao")
public class ReportDaoImpl implements ReportDao {

public static final Logger logger = LoggerFactory.getLogger(ReportDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public CpfSlipHeaderDto getHeaderDataCpfSlipReport(String empNum, String fromYear, String toYear, String locId) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfSlipHeaderDto cpfSlipHeaderDto = new CpfSlipHeaderDto();
		try{
		String query = "SELECT ALL mst.EMP_FIRST_NAME || ' ' || mst.EMP_MIDDLE_NAME || ' ' || mst.EMP_LAST_NAME as \"empName\", "
				+ "loc.LOC_DESC as \"office\", mst.STAFF_CODE as \"staffCode\", mst.CPF_CODE as \"cpfCode\", "
				+ "mst.FPS_NUM as \"fpsNum\", nvl(mst.uan,0)uan, mst.EMP_BIRTH_DATE as \"dob\", "
				+ "mst.COMP_JOINING_DATE as \"fciJoinDt\", mst.RETIREMENT_DATE, dsgn.DSGN_DESC as \"dsgn\", mst.EMP_NUM, "
				+ "max(dtl.wid_date) dt_last_part_final, max(dtl.adv_taken_date) last_adv_drwn "
				+ "FROM PAY_EMP_MAST mst, COM_LOC_MST loc, PAY_DSGN_MST dsgn, pay_emp_pf_hdr hdr, pay_emp_pf_dtl dtl "
				+ "WHERE mst.EMP_NUM = NVL(:empNum, mst.EMP_NUM) AND mst.DESIGNATION_ID = dsgn.DSGN_ID(+) and loc.loc_id= mst.pres_location_code "
				+ "and hdr.emp_num = mst.emp_num and hdr.from_year =:fromYear and hdr.to_year =:toYear and dtl.emp_num = hdr.emp_num "
				+ "and mst.parent_zone=decode(:locId,182,182,188,188,244,244,245,245,246,246,243,0,900,900,mst.parent_zone) "
				+ "group by mst.EMP_FIRST_NAME, mst.EMP_MIDDLE_NAME, mst.EMP_LAST_NAME, loc.LOC_DESC, mst.STAFF_CODE, mst.CPF_CODE, "
				+ "mst.FPS_NUM, mst.EMP_BIRTH_DATE, mst.COMP_JOINING_DATE, mst.RETIREMENT_DATE, dsgn.DSGN_DESC, mst.uan, mst.EMP_NUM";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
			hQuery.setParameter("toYear", toYear);
			hQuery.setParameter("locId", locId);
		}
		List<Map<String, Object>> list = hQuery.list();
		if(list.size()>0){
		for (Map<String, Object> map : list) {
			cpfSlipHeaderDto.setEmpName(map.get("empName").toString().trim());
			cpfSlipHeaderDto.setStaffCode(map.get("staffCode").toString().trim());
			cpfSlipHeaderDto.setDesignation(map.get("dsgn").toString().trim());
			cpfSlipHeaderDto.setOffice(map.get("office").toString().trim());
			cpfSlipHeaderDto.setCpfNo(map.get("cpfCode").toString().trim());
			cpfSlipHeaderDto.setFpsNo(map.get("fpsNum")!=null?map.get("fpsNum").toString().trim():"");
			cpfSlipHeaderDto.setFciJoiningDt(format.parse(map.get("fciJoinDt").toString().trim()));
			cpfSlipHeaderDto.setFciJoiningDt(format.parse(map.get("fciJoinDt").toString().trim()));
			cpfSlipHeaderDto.setDob(format.parse(map.get("dob").toString().trim()));
			cpfSlipHeaderDto.setUan(map.get("UAN")!=null?map.get("UAN").toString().trim():"");
		}
		}else{
			cpfSlipHeaderDto = null;
		}
		session.getTransaction().commit();
	} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return cpfSlipHeaderDto;
	}


	@Override
	public List<CpfSlipMonthWiseDto> getMonthWiseContrCpfSlipReport(String empNum, String fromYear, String toYear) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<CpfSlipMonthWiseDto> cpfSlipMonthWiseDtoList =new ArrayList<>();
		try{
		String query = "select month,type,COMPL,MATCHING,E_VPF,E_EPS,EMPLOYEE_PF_OPEN_BAL,EMPLOYER_PF_OPEN_BAL,E_FPS,"
				+ "EMP_INT,EMPR_INT,EMPLOYEE_PF_CLOSE_BAL,EMPLOYER_PF_CLOSE_BAL,EMP_NUM,amt_withdrawn,adv_refund,"
				+ "adv_taken,adv_taken_vpf,amt_withdrawn_vpf,vpf_open_bal,vpf_close_bal,amt_withdrawn_emplr,vpf_int,UNIT_CODE,a "
				+ "from pay_cpf_slip_data "
				+ "where FROM_YEAR =:fromYear and TO_YEAR =:toYear and emp_num =:empNum";
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
			hQuery.setParameter("toYear", toYear);
		}
		List<Map<String, Object>> list = hQuery.list();
		int count=0;
		int monthlyCorp =0;
		int monthlyMem = 0;
		int monthlyVPF = 0;
				
		for (Map<String, Object> map : list) {
			CpfSlipMonthWiseDto cpfSlipMonthWiseDto = new CpfSlipMonthWiseDto();
			cpfSlipMonthWiseDto.setMonth(map.get("MONTH").toString().trim());
			cpfSlipMonthWiseDto.setFciMatching(map.get("MATCHING").toString().trim());
			cpfSlipMonthWiseDto.setFciEPS(map.get("E_EPS").toString().trim());
			cpfSlipMonthWiseDto.setFciAmtWD(map.get("AMT_WITHDRAWN_EMPLR").toString().trim());
			cpfSlipMonthWiseDto.setMemCompl(map.get("COMPL").toString().trim());
			cpfSlipMonthWiseDto.setMemAdvTaken(map.get("ADV_TAKEN").toString().trim());
			cpfSlipMonthWiseDto.setMemAdvRef(map.get("ADV_REFUND").toString().trim());
			cpfSlipMonthWiseDto.setMemWD(map.get("AMT_WITHDRAWN").toString().trim());
			cpfSlipMonthWiseDto.setMemVPF(map.get("E_VPF").toString().trim());
			cpfSlipMonthWiseDto.setMemVPFAdvTaken(map.get("ADV_TAKEN_VPF").toString().trim());
			cpfSlipMonthWiseDto.setMemVPFAmtWD(map.get("AMT_WITHDRAWN_VPF").toString().trim());
			cpfSlipMonthWiseDto.setOffice(map.get("UNIT_CODE").toString().trim());
			
			if(count==0){
				monthlyCorp = ((BigDecimal)map.get("EMPLOYER_PF_OPEN_BAL")).intValue() + ((BigDecimal)map.get("MATCHING")).intValue() - ((BigDecimal)map.get("AMT_WITHDRAWN_EMPLR")).intValue();
			}else{
				monthlyCorp = monthlyCorp  + ((BigDecimal)map.get("MATCHING")).intValue();
			}
			cpfSlipMonthWiseDto.setMonthlyCorp(monthlyCorp+"");
			
			if(count==0)
				monthlyMem = ((BigDecimal)map.get("EMPLOYEE_PF_OPEN_BAL")).intValue() + ((BigDecimal)map.get("COMPL")).intValue() - ((BigDecimal)map.get("AMT_WITHDRAWN")).intValue();
			else
				monthlyMem = monthlyMem + ((BigDecimal)map.get("COMPL")).intValue();
			
			cpfSlipMonthWiseDto.setMonthlyMem(monthlyMem+"");
			
			if(count==0)
				monthlyVPF = ((BigDecimal)map.get("VPF_OPEN_BAL")).intValue() + ((BigDecimal)map.get("E_VPF")).intValue() - ((BigDecimal)map.get("AMT_WITHDRAWN_VPF")).intValue();
			else
				monthlyVPF = monthlyVPF + ((BigDecimal)map.get("E_VPF")).intValue();
			
			cpfSlipMonthWiseDto.setMonthlyVPF(monthlyVPF+"");
			
			
			cpfSlipMonthWiseDto.setObMatching(map.get("EMPLOYER_PF_OPEN_BAL").toString().trim());
			cpfSlipMonthWiseDto.setObCompl(map.get("EMPLOYEE_PF_OPEN_BAL").toString().trim());
			cpfSlipMonthWiseDto.setObVPF(map.get("VPF_OPEN_BAL").toString().trim());
			
			cpfSlipMonthWiseDtoList.add(cpfSlipMonthWiseDto);
			count++;
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} 
		return cpfSlipMonthWiseDtoList;
	}


	@Override
	public CpfSlipIntOBCreditAsOnDataDto getIntDataCpfSlipReport(String empNum, String fromYear, String toYear) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntCBCreditAsOnDataDto = new CpfSlipIntOBCreditAsOnDataDto();
		try{
		String query = "SELECT hdr.employee_pf_close_int_bal as \"empInt\", hdr.EMPLOYER_pf_close_int_bal as \"emprInt\", hdr.vpf_close_int_bal as \"vpfInt\", hdr.emp_num "
				+ "FROM PAY_EMP_PF_HDR hdr "
				+ "WHERE hdr.FROM_YEAR =:fromYear and hdr.TO_YEAR =:toYear and emp_num=:empNum";
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
			hQuery.setParameter("toYear", toYear);
		}
		List<Map<String, Object>> list = hQuery.list();
		for (Map<String, Object> map : list) {
			
			cpfSlipIntCBCreditAsOnDataDto.setIntMatching(map.get("emprInt")!=null?map.get("emprInt").toString().trim():"0");
			cpfSlipIntCBCreditAsOnDataDto.setIntComl(map.get("empInt")!=null?map.get("empInt").toString().trim():"0");
			cpfSlipIntCBCreditAsOnDataDto.setIntVPF(map.get("vpfInt")!=null?map.get("vpfInt").toString().trim():"0");
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} 
		return cpfSlipIntCBCreditAsOnDataDto;
	}


	@Override
	public CpfSlipIntOBCreditAsOnDataDto getOBDataCpfSlipReport(String empNum, String fromYear, String toYear) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntOBCreditAsOnDataDto = new CpfSlipIntOBCreditAsOnDataDto();
		try{
		String query = "SELECT nvl(hdr.EMPLOYEE_PF_OPEN_BAL,0) as \"empPFOpenBal\", nvl(hdr.EMPLOYER_PF_OPEN_BAL,0) as \"emprPFOpenBal\", "
				+ "nvl(hdr.vpf_open_bal, 0) as \"vpfOpenBal\", hdr.emp_num "
				+ "FROM PAY_EMP_PF_HDR hdr "
				+ "WHERE hdr.FROM_YEAR =:fromYear and hdr.TO_YEAR =:toYear and emp_num=:empNum";
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
			hQuery.setParameter("toYear", toYear);
		}
		List<Map<String, Object>> list = hQuery.list();
		for (Map<String, Object> map : list) {
			
			cpfSlipIntOBCreditAsOnDataDto.setObMatching(map.get("emprPFOpenBal").toString().trim());
			cpfSlipIntOBCreditAsOnDataDto.setObComl(map.get("empPFOpenBal").toString().trim());
			cpfSlipIntOBCreditAsOnDataDto.setObVPF(map.get("vpfOpenBal").toString().trim());
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} 
		return cpfSlipIntOBCreditAsOnDataDto;
	}


	@Override
	public CpfSlipIntOBCreditAsOnDataDto getCreditAsOnCpfSlipReport(String empNum, String fromYear) {
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy",Locale.ENGLISH);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntCBCreditAsOnDataDto = new CpfSlipIntOBCreditAsOnDataDto();
		try{
		String query = "SELECT emp_num, nvl(TO_CHAR(last_day(TO_DATE(decode(substr(max(dtl.yyyymm), 5, 2),12,concat((substr(max(dtl.yyyymm), 1, 4) + 1),'01'), "
				+ "max(dtl.yyyymm) + 1), 'YYYYMM')), 'DD/MON/YYYY'), to_char(last_day(sysdate),'DD/MON/YYYY')) as \"maxMonth\" "
				+ "from pay_emp_pf_dtl dtl "
				+ "where from_year=:fromYear and emp_num=:empNum group by emp_num";
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
		}
		List<Map<String, Object>> list = hQuery.list();
		for (Map<String, Object> map : list) {
			try {
				cpfSlipIntCBCreditAsOnDataDto.setBalToCreditAsOnDt(format.parse(map.get("maxMonth").toString().trim()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} 
		return cpfSlipIntCBCreditAsOnDataDto;
	}


	@Override
	public List<CpfSlipAdjustmentDataDto> getAdjustmentDtCpfSlipReport(String empNum, String fromYear) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<CpfSlipAdjustmentDataDto> cpfSlipAdjustmentDataDtoList = new ArrayList<>();
		try{
		String query = "select emp_num,for_year as \"forYear\",MONTH as \"month\",Adjust_type as \"adjType\",adjust_amt as \"adjAmt\",remark as \"remark\" "
				+ "from pay_cpf_slip_adjust "
				+ "where for_year=:fromYear and emp_num=:empNum";
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
			hQuery.setParameter("fromYear", fromYear);
		}
		List<Map<String, Object>> list = hQuery.list();
		for (Map<String, Object> map : list) {
			CpfSlipAdjustmentDataDto cpfSlipAdjustmentDataDto = new CpfSlipAdjustmentDataDto();
			cpfSlipAdjustmentDataDto.setMonth(map.get("month").toString().trim());
			cpfSlipAdjustmentDataDto.setAdjustAmt(map.get("adjAmt").toString().trim());
			cpfSlipAdjustmentDataDto.setAdjustType(map.get("adjType").toString().trim());
			cpfSlipAdjustmentDataDto.setRemarks(map.get("remark")!=null?map.get("remark").toString().trim():"");
			cpfSlipAdjustmentDataDtoList.add(cpfSlipAdjustmentDataDto);
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} 
				
		return cpfSlipAdjustmentDataDtoList;
	}

	
	public List<ClaimRequestGenerateReportDto> getClaimReqReport(String empNum,String fromDate,String toDate,String claimType){
		
		List<ClaimRequestGenerateReportDto> claimRequestGenerateReportDtoList = new ArrayList<ClaimRequestGenerateReportDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		    String nfromDate="";
		    String ntoDate="";
		    if (!StringUtils.isEmpty(fromDate)){ 
          	 nfromDate = sdf2.format(sdf.parse(fromDate));
		    }
		    if (!StringUtils.isEmpty(toDate)){ 
		    	ntoDate = sdf2.format(sdf.parse(toDate));
			    } 
			String query=new String(" select distinct a.CLAIM_SUBMITTED_BY AS \"empNum\" ,pem.emp_first_name||' '||pem.emp_middle_name||' '||pem.emp_last_name as \"empName\","
					+ "a.request_id AS  \"claimId\",a.CLAIM_APPLIED_FOR AS \"claimType\",a.CLAIM_SUBMITTED_DATE AS \"claimDate\", "
					+ "CASE st.status  "
					+ "WHEN 0  THEN 'Request Rejected' "
					+ "WHEN 1 THEN 'Pending At Admin Section' "
					+ "WHEN 2 THEN 'Pending At CPF Admin' "
					+ "WHEN 3 THEN 'pending for sanction' "
					+ "WHEN 4  THEN 'request completed' END AS \"claimStatus\" ,"
					+ "c1.loc_desc AS \"presLocation\",c2.loc_desc as \"PARENT_ZONE\" "
					+ "from cpf_claim_form_details a "
					+ "LEFT JOIN pay_emp_mast pem ON a.CLAIM_SUBMITTED_BY=pem.emp_num "
					+ "LEFT JOIN com_loc_mst c1 ON  c1.loc_id=a.present_location  "
					+ "LEFT JOIN com_loc_mst c2 ON  c2.loc_id=a.PARENT_ZONE "
					+ "LEFT JOIN cpf_claim_form_status st ON  st.request_id=a.request_id");
			
			if (!StringUtils.isEmpty(empNum) || !StringUtils.isEmpty(fromDate) || !StringUtils.isEmpty(claimType)) {
				query = query + " WHERE";
			}

			if (!StringUtils.isEmpty(empNum)) {
				query = query + " a.CLAIM_SUBMITTED_BY= '" + empNum + "' ";
			}

			if (!StringUtils.isEmpty(fromDate)) {
				if (!StringUtils.isEmpty(empNum)) {
					query = query + " AND";
				}
				query = query + " a.CLAIM_SUBMITTED_DATE BETWEEN DATE '" + nfromDate + "' AND DATE '" + ntoDate + "'";
			}

			if (!StringUtils.isEmpty(claimType)) {
				if (!StringUtils.isEmpty(empNum) || !StringUtils.isEmpty(fromDate)) {
					query = query + " AND ";
				}
				query = query + " a.CLAIM_APPLIED_FOR= '" + claimType + "'";
			}
			query= query + " and st.status <> -1 order by a.CLAIM_SUBMITTED_BY asc";

			System.out.print(query);
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
			DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				//RevokeRoleStatusDto revokeRoleStatusDto = new RevokeRoleStatusDto();

				List<Map<String, Object>> list = hQuery.list();
				for(Map<String, Object> map : list){
					ClaimRequestGenerateReportDto claimRequestGenerateReportDto = new ClaimRequestGenerateReportDto();
					claimRequestGenerateReportDto.setEmpNum(map.get("empNum").toString());
					claimRequestGenerateReportDto.setEmpName(map.get("empName").toString());
					claimRequestGenerateReportDto.setClaimId(map.get("claimId").toString());
					claimRequestGenerateReportDto.setClaimType(map.get("claimType").toString());
					claimRequestGenerateReportDto.setClaimDate(myFormat.format(format.parse(map.get("claimDate").toString().trim())));
					claimRequestGenerateReportDto.setClaimStatus(map.get("claimStatus")!=null?map.get("claimStatus").toString():"");
					claimRequestGenerateReportDto.setPresLocation(map.get("presLocation")!=null?map.get("presLocation").toString():"");
					claimRequestGenerateReportDto.setParentZone(map.get("PARENT_ZONE")!=null?map.get("PARENT_ZONE").toString():"");
						
					claimRequestGenerateReportDtoList.add(claimRequestGenerateReportDto);
				}
			}	
			catch (RuntimeException re) {
				logger.info("Find by example failed :::", re);
				throw re;
			}catch (Exception e) {
				e.printStackTrace();
			}
		return claimRequestGenerateReportDtoList;
	}
}
