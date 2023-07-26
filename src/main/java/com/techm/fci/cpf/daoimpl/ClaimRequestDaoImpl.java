package com.techm.fci.cpf.daoimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techm.fci.cpf.dao.BaseDao;
import com.techm.fci.cpf.dao.ClaimRequestDao;
import com.techm.fci.cpf.dto.ActClaimDto;
import com.techm.fci.cpf.dto.AssignToClaimDto;
import com.techm.fci.cpf.dto.ClaimHistoryTrailDto;
import com.techm.fci.cpf.dto.ClaimRequestStatusDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.SavedClaimConditionCheckDto;
import com.techm.fci.cpf.model.AuditCpfClaimRequestStatus;
import com.techm.fci.cpf.model.CpfClaimHistoryTrail;
import com.techm.fci.cpf.model.CpfClaimRequest;
import com.techm.fci.cpf.model.CpfClaimRequestStatus;

@Repository("claimReqDao")
public class ClaimRequestDaoImpl extends BaseDao<Integer, CpfClaimRequest> implements ClaimRequestDao {

	public static final Logger logger = LoggerFactory.getLogger(ClaimRequestDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private HttpSession httpSession;
	
	private Session session;
	
	//Not supported by Hibernate core version using is 3.6.3.final. JPA 2.1 support this but JPA 2.1 support by hibernate 4.3.x or up
	//@PersistenceContext
	//private EntityManager entitymanager;
	
	/*@Override
	public CpfClaimRequest findById(int id) {
		return null;
	}*/
	
	@Override
	public List<CpfClaimRequest> empClaimLookup(String empNum){
		
		List<CpfClaimRequest> cpfClaimReq = null;
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	if(empNum!=null && !empNum.equals("anonymousUser")){
	    	Criteria crit = createEntityCriteria();
	        crit.add(Restrictions.eq("CLAIM_SUBMITTED_BY", empNum));
	        crit.addOrder(Order.asc("CLAIM_SUBMITTED_DATE"));
	        cpfClaimReq=(List<CpfClaimRequest>) crit.list(); //uniqueResult();
    	}
        session.getTransaction().commit();
		return cpfClaimReq;
	}

	
	@Override
	public CpfClaimRequest saveClaimReq(CpfClaimRequest claimReq, String empNum, String locCode, String roleName) {
		try{
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfClaimRequest cpfClaimForSave = new CpfClaimRequest();
		
		Map<String,BigDecimal> map = getOtherInfoByEmp(empNum);
		for (String mapKey : map.keySet()){
			if(mapKey.equals("dsgnCode")){
				cpfClaimForSave.setDESIGNATION(map.get(mapKey).toString());
			}
			if(mapKey.equals("locCode")){
				cpfClaimForSave.setPRESENT_LOCATION(map.get(mapKey).toString());
			}
			if(mapKey.equals("parentZone")){
				cpfClaimForSave.setPARENT_ZONE(map.get(mapKey).toString());
			}
		}
		cpfClaimForSave.setCLAIM_SUBMITTED_BY(empNum);
		cpfClaimForSave.setCLAIM_SUBMITTED_DATE(new Date());
		cpfClaimForSave.setCLAIM_APPLIED_FOR(claimReq.getCLAIM_APPLIED_FOR());
		cpfClaimForSave.setEMP_NAME(claimReq.getEMP_NAME());
		cpfClaimForSave.setFATHER_HUSBAND_NAME(claimReq.getFATHER_HUSBAND_NAME());
		cpfClaimForSave.setBASIC(claimReq.getBASIC());
		cpfClaimForSave.setUAN(claimReq.getUAN());
		cpfClaimForSave.setDOJ_FCI(claimReq.getDOJ_FCI());
		cpfClaimForSave.setPAN(claimReq.getPAN());
		cpfClaimForSave.setDATE_OF_BIRTH(claimReq.getDATE_OF_BIRTH());
		cpfClaimForSave.setCPF_ACCOUNT_NUMBER(claimReq.getCPF_ACCOUNT_NUMBER());
		cpfClaimForSave.setSTAFF_CODE(claimReq.getSTAFF_CODE());
		cpfClaimForSave.setMOBILE_NUMBER(claimReq.getMOBILE_NUMBER());
		cpfClaimForSave.setRETIREMENT_DATE(claimReq.getRETIREMENT_DATE());
		cpfClaimForSave.setPURPOSE(claimReq.getPURPOSE());
		cpfClaimForSave.setAMOUNT(claimReq.getAMOUNT());
		cpfClaimForSave.setINSTALLMENT_NUMBER(claimReq.getINSTALLMENT_NUMBER());
		cpfClaimForSave.setLAST_DRAWN_ADVANCE(claimReq.getLAST_DRAWN_ADVANCE());
		cpfClaimForSave.setADVANCE_AMOUNT(claimReq.getADVANCE_AMOUNT());
		cpfClaimForSave.setAMOUNT_REPAID(claimReq.getAMOUNT_REPAID());
		cpfClaimForSave.setOUTSTANDING_BAL(claimReq.getOUTSTANDING_BAL());
		cpfClaimForSave.setAMOUNT_90PARTFINAL_BEF_RETR(claimReq.getAMOUNT_90PARTFINAL_BEF_RETR());
		cpfClaimForSave.setPERMISSIBLE_AMOUNT(claimReq.getPERMISSIBLE_AMOUNT());
		cpfClaimForSave.setDEC_NOT_EMP_TWOMONTH(claimReq.getDEC_NOT_EMP_TWOMONTH());
		cpfClaimForSave.setEMP_DECLARATION(claimReq.getEMP_DECLARATION());
		cpfClaimForSave.setCASTE_DISPUTE_CERT(claimReq.getCASTE_DISPUTE_CERT()!=null?claimReq.getCASTE_DISPUTE_CERT().trim():"");
		
		if(claimReq.getCLAIM_COUNT()!=0){
			cpfClaimForSave.setCLAIM_COUNT(claimReq.getCLAIM_COUNT()+1);
		}else{
			cpfClaimForSave.setCLAIM_COUNT(1);
		}
		session.persist(cpfClaimForSave);
		logger.info("cpfClaimForSave :::: "+cpfClaimForSave.toString());
		
		List<String> adminId = getAdminToAssign(locCode, "ADMIN");
		if(adminId.size()>0){
			for(String admId : adminId){
			CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();
			
			claimStatus.setREQUEST_ID(cpfClaimForSave.getREQUEST_ID());
			claimStatus.setCLAIM_SUBMITTED_BY(cpfClaimForSave.getCLAIM_SUBMITTED_BY());
			claimStatus.setCLAIM_SUBMITTED_DATE(cpfClaimForSave.getCLAIM_SUBMITTED_DATE());
			claimStatus.setADMIN_ACTION_TAKEN_BY(admId);//admin employee number for a particular location  
			claimStatus.setSTATUS("1");
			
			session.persist(claimStatus);
			logger.info("claimStatus ::::: "+ claimStatus.toString());
			}
			session.getTransaction().commit();
			
		return cpfClaimForSave;
		}else{
			httpSession.setAttribute("adminfound", "N");
			session.getTransaction().rollback();
			return null;
		}
		}catch(Exception ex){
			logger.info("Claim request not saved ......");
			session.getTransaction().rollback();
			ex.printStackTrace();
		}finally{
			//session.close();
		}
		return null;
	}
	
	public boolean saveCpfClaimHistory(ClaimHistoryTrailDto claimHistoryTrailData, String empNum, String roleName) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			CpfClaimHistoryTrail cpfClaimHistoryTrail = new CpfClaimHistoryTrail();
			cpfClaimHistoryTrail.setREQUEST_ID(claimHistoryTrailData.getRequestId());
			cpfClaimHistoryTrail.setCLAIM_SUBMITTED_BY(claimHistoryTrailData.getClaimCreatedBy());
			cpfClaimHistoryTrail.setACTION_TAKEN_BY(claimHistoryTrailData.getActionTakenBy());
			cpfClaimHistoryTrail.setACTION_DATE(new Date());
			cpfClaimHistoryTrail.setACTION(claimHistoryTrailData.getAction());
			cpfClaimHistoryTrail.setREMARKS(claimHistoryTrailData.getRemarks());
			cpfClaimHistoryTrail.setSTATUS(claimHistoryTrailData.getStatus());
			cpfClaimHistoryTrail.setCREATED_BY(empNum);
			cpfClaimHistoryTrail.setCREATED_DATE(new Date());
			cpfClaimHistoryTrail.setMODIFIED_BY(claimHistoryTrailData.getActionTakenBy());
			cpfClaimHistoryTrail.setMODIFIED_DATE(new Date());
			session.persist(cpfClaimHistoryTrail);
			logger.info("CpfClaimHistoryTrail Saved :::: " + cpfClaimHistoryTrail.toString());
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.info("Claim History Trail not saved ......");
			session.getTransaction().rollback();
			ex.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	public List<String> getAdminToAssign(String locCode, String roleName){
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		List<String> adminList = new ArrayList<String>();
		try{
			String finalLocCode=null;
			String query0="Select loc_desc as \"locDesc\", loc_type_gbl as \"locType\", parent_loc_id as \"parentLocId\" from com_loc_mst where loc_id=:locCode";
			
			Query hQuery0 = session.createSQLQuery(query0).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (locCode != null) {
				hQuery0.setParameter("locCode", locCode);
			}
			List<Map<String, Object>> list0 = hQuery0.list();
			if(list0.size()>0){
			for (Map<String, Object> map : list0) {
				if(map.get("locType").toString().trim().equals("DP") || map.get("locType").toString().trim().equals("PO")){
					finalLocCode=map.get("parentLocId").toString().trim();
				}else{
					finalLocCode=locCode;
				}
			}
			}
			
			String query = "select EmpId as \"empId\" from CPF_ASSIGNED_ROLES_DTL where preslocation=:locCode and roleassigned=:roleName and enddate is null";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (finalLocCode != null) {
				hQuery.setParameter("locCode", finalLocCode);
				hQuery.setParameter("roleName", roleName);
			}
			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				adminList.add(map.get("empId").toString().trim());
			}
			//session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return adminList;
	}
	
	@Override
	public ActClaimDto getBasicDetailsForClaim(String empNum) {
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ActClaimDto claimDataForForm = new ActClaimDto();
		try{
		String query = "select cdu.emp_num as\"empNum\", emp_first_name||' '||emp_middle_name||' '||emp_last_name as \"empName\", designation_id as \"desigId\","
				+ "dsgn_desc as \"dsgnDesc\",pres_location_code as \"locCode\",loc_desc as \"locDesc\",parent_zone as \"parentZone\",father_name as \"fatherName\","
				+ "emp_birth_date as \"empDOB\",basic as \"basic\",staff_code as \"staffCode\",a.uan as \"uan\", "
				+ "comp_joining_date as \"comJoiningDate\",retirement_date as \"retirementDate\",emp_pan_no as \"empPan\","
				+ "r.emp_phone as \"empPhone\", r.emp_email as \"empEmail\", a.cpf_code as \"cpfcode\", cdu.file_path as \"kycFilePath\", cdu.doc_id as \"kycDocId\", a.emp_status as \"empStatus\" "
				+ "from pay_emp_mast a,pay_dsgn_mst b,com_loc_mst c,cpf_registered_users r,CPF_DOC_UPLOADS cdu "
				+ "where a.emp_num=:empNum and a.designation_id=b.dsgn_id and c.loc_id=pres_location_code and a.emp_num=r.emp_num "
				+ "and r.emp_num=cdu.emp_num and cdu.file_type=1";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
		}
		List<Map<String, Object>> list = hQuery.list();
		if(list.size()>0){
		for (Map<String, Object> map : list) {
			claimDataForForm.setEMP_NAME(map.get("empName").toString().trim());
			claimDataForForm.setDESIGNATION(map.get("dsgnDesc").toString().trim());
			claimDataForForm.setPRESENT_LOCATION(map.get("locDesc").toString().trim());
			claimDataForForm.setFATHER_HUSBAND_NAME(map.get("fatherName")!=null?map.get("fatherName").toString().trim():"");
			claimDataForForm.setDATE_OF_BIRTH(format.parse(map.get("empDOB").toString().trim()));
			claimDataForForm.setBASIC(map.get("basic")!=null?map.get("basic").toString().trim():"");
			claimDataForForm.setUAN(map.get("uan")!=null?map.get("uan").toString().trim():"");
			claimDataForForm.setDOJ_FCI(format.parse(map.get("comJoiningDate").toString().trim()));
			claimDataForForm.setRETIREMENT_DATE(format.parse(map.get("retirementDate").toString().trim()));
			claimDataForForm.setPAN(map.get("empPan")!=null?map.get("empPan").toString().trim():"");
			claimDataForForm.setCPF_ACCOUNT_NUMBER(map.get("cpfcode")!=null?map.get("cpfcode").toString().trim():"");
			claimDataForForm.setSTAFF_CODE(map.get("staffCode")!=null?map.get("staffCode").toString().trim():"");
			claimDataForForm.setMOBILE_NUMBER(map.get("empPhone")!=null?map.get("empPhone").toString().trim():"");
			
			claimDataForForm.setKycFilePath(map.get("kycDocId")!=null?map.get("kycDocId").toString().trim():"");
			File file = new File(map.get("kycFilePath").toString().trim());
			claimDataForForm.setKycFileName(file.getName());
			
			String query3 = "select du.file_path as \"filePath\", du.CLAIM_APPLIED_FOR as \"claimAppliedFor\", du.doc_id as \"docId\" "
					+ "from cpf_doc_uploads du "
					+ "where du.emp_num=:empNum and du.file_type=3 and request_id=0";
			Query hQuery3 = session.createSQLQuery(query3).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if(empNum!=null){
				hQuery3.setParameter("empNum", empNum.trim());
			}
			List<Map<String, Object>> list3 = hQuery3.list();
			if(list3!=null && list3.size()>0){
				Map<String,String> fileMap = new HashMap();
				for (Map<String, Object> map3 : list3) {
					File file1 = new File(map3.get("filePath").toString().trim());
					//fileMap.put(file1.getName(), map3.get("filePath")!=null?map3.get("filePath").toString().trim():"");
					fileMap.put(file1.getName(),map3.get("docId")!=null?map3.get("docId").toString().trim():"");
					claimDataForForm.setUserOtherFiles(fileMap);
					claimDataForForm.setCLAIM_APPLIED_FOR(map3.get("claimAppliedFor")!=null?map3.get("claimAppliedFor").toString().trim():"");
				}
			}
			
			claimDataForForm.setEmpStatus(map.get("empStatus")!=null?map.get("empStatus").toString().trim():"");
					
			String query1 = "select count(*) from pay_loan_hdr where loan_type=87 and invoice_num is not null and emp_num=:empNum";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (empNum != null) {
				hQuery1.setParameter("empNum", empNum);
			}
			BigDecimal count = (BigDecimal)hQuery1.uniqueResult();
			if(count.intValue()>0){
				String query2 = "select sanction_date as \"LastDrawnAdvance\", loan_amount as \"advanceAmount\", amount_paid as \"amountRePaid\", "
						+ "exist_loan_balance as \"balanceOutstanding\" "
						+ "from pay_loan_hdr "
						+ "where loan_type=87 and invoice_num is not null and emp_num=:empNum and sanction_date=(select max(sanction_date) from pay_loan_hdr where emp_num=:empNum)";
				
				Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery2.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list2 = hQuery2.list();
				for(Map<String, Object> map2 : list2){
					claimDataForForm.setADVANCE_AMOUNT(map2.get("advanceAmount").toString());
					claimDataForForm.setAMOUNT_REPAID(map2.get("amountRePaid").toString());
					claimDataForForm.setOUTSTANDING_BAL(map2.get("balanceOutstanding").toString());
				}
			}
			httpSession.setAttribute("locCode", map.get("locCode").toString());
			httpSession.setAttribute("desigId", map.get("desigId").toString());
		}
		}
		//session.getTransaction().commit();
	} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return claimDataForForm;
	}
	
	public Map<String,BigDecimal> getOtherInfoByEmp(String empNum) {
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		try{
		String query = "select designation_id as \"dsgnCode\",dsgn_desc as \"dsgnDesc\",pres_location_code as \"locCode\",loc_desc as \"locDesc\",parent_zone as \"parentZone\" "
				+ "from pay_emp_mast a,pay_dsgn_mst b,com_loc_mst c "
				+ "where emp_num=:empNum and a.designation_id=b.dsgn_id and c.loc_id=pres_location_code";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
		}
		List<Map<String, BigDecimal>> list = hQuery.list();

		//session.getTransaction().commit();
		return list.get(0);
	} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	} 
	}

	@Override
	public List<ClaimRequestStatusDto> getMyAllPendingClaimReq(String empNum,String roleName) {
		List<ClaimRequestStatusDto> listCpfClaimStatusDto = new ArrayList<ClaimRequestStatusDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			if(roleName!=null && roleName.equals("CPF_ADMIN")){
				String query = "select request_id,reg.emp_name as \"claim_submitted_by\",claim_submitted_date,(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) "
						+ "cpfsec_action_taken_by, cpfsec_action_date,status, admin_remarks "
						+ "from cpf_claim_form_status st,cpf_registered_users reg "
						+ "where st.claim_submitted_by=reg.emp_num and status=2 and cpfsec_action='assignTo' and cpfsec_action_taken_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString());
					if(map.get("CPFSEC_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("CPFSEC_ACTION_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("CPFSEC_ACTION_TAKEN_BY").toString());
					//cpfClaimRequestStatusDto.setRemarks(map.get("admin_remarks").toString());
					cpfClaimRequestStatusDto.setStatus("Pending At CPF Admin");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return listCpfClaimStatusDto;
	}
	
	private String getEmployeeWithCPFCode(String empNum) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String empNameWithCPFCode = "";
		try {
			String query2 = "select distinct rg.emp_name ||'-'|| pem.cpf_code as \"empNameWithCpfCode\" "
					+ "from cpf_registered_users rg, pay_emp_mast pem "
					+ "where rg.emp_num=pem.emp_num "
					+ "and rg.emp_num=:empNum";

			Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery2.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list2 = hQuery2.list();
			for (Map<String, Object> map2 : list2) {
				empNameWithCPFCode = map2.get("empNameWithCpfCode") != null ? map2.get("empNameWithCpfCode").toString()	: "";
			}
			//session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			re.printStackTrace();
			throw re;
		} 
		return empNameWithCPFCode;
	}
	
	@Override
	public List<ClaimRequestStatusDto> getAllPendingClaimReq(String reqType,String empNum,String roleName) {
		List<ClaimRequestStatusDto> listCpfClaimStatusDto = new ArrayList<ClaimRequestStatusDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			if(roleName!=null && roleName.equals("USER")){
				if(reqType.equals("myReq")){
				String query = "select st.request_id, reg.emp_name as \"claim_submitted_by\", st.claim_submitted_date,"
						+ "admin_action_date, st.admin_action_taken_by as \"adminActionTakenByID\", status, admin_remarks as \"adminRemarks\","
						+ "st.cpfsec_action_taken_by as \"cpfActionTakenByID\", st.cpfsec_action_date as \"cpfsecActionDate\", st.cpfsec_remarks as \"cpfsecRemarks\", "
						+ "dsm.dsgn_desc as \"desig\", cfd.claim_applied_for, cfd.purpose, cfd.cpf_account_number "
						+ "from cpf_claim_form_status st, cpf_registered_users reg, cpf_claim_form_details cfd, pay_dsgn_mst dsm "
						+ "where st.claim_submitted_by=reg.emp_num "
						+ "and cfd.claim_submitted_by=reg.emp_num "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and cfd.claim_submitted_date=st.claim_submitted_date "
						+ "and st.status>-1 "
						+ "and st.claim_submitted_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID"));
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString().trim()+" ("+map.get("CPF_ACCOUNT_NUMBER").toString().trim()+")");
					cpfClaimRequestStatusDto.setDesignation(map.get("desig").toString());
					//String claimAppFor=map.get("CLAIM_APPLIED_FOR").toString().trim();
					String claimAppFor=null;
					switch(map.get("CLAIM_APPLIED_FOR").toString().trim()){
					case "CpfFinalSettlement":
						claimAppFor="Final Settlement";
						break;
					case "CpfPartFinalWithdrawal":
						claimAppFor="Part Final Settlement";
						break;
					case "90%Withdrawal":
						claimAppFor="90% Withdrawal";
						break;
					case "TempAdv":
						claimAppFor="Temp. Advance";
						break;
					default:
					}
					String claimPur=map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"";
					cpfClaimRequestStatusDto.setClaimType(claimAppFor+" ("+claimPur+")");
					
					if(map.get("STATUS").toString().equals("0")){
						cpfClaimRequestStatusDto.setStatus("Request Rejected");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
						if(map.get("cpfsecRemarks")!=null){
							cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
						}else{
							cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks").toString());	
						}
						
						if(map.get("cpfsecActionDate")!=null){
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
						}else{
							if(map.get("ADMIN_ACTION_DATE")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString()));
						}
						
					}else if(map.get("STATUS").toString().equals("1")){
						cpfClaimRequestStatusDto.setStatus("Request Pending At Admin Section");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());

						String adminNameWithCpfCode = getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString().trim());
						if(map.get("ADMIN_ACTION_DATE")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
						cpfClaimRequestStatusDto.setAdminActionTakenBy(adminNameWithCpfCode!=""?adminNameWithCpfCode:"Admin Not Yet Register");
						
					}else if(map.get("STATUS").toString().equals("2")){
						cpfClaimRequestStatusDto.setStatus("Request pending at CPF-Admin Section");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
						cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks").toString());
						String cpfAdminNameWithCpfCode = getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim());
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						cpfClaimRequestStatusDto.setAdminActionTakenBy(cpfAdminNameWithCpfCode!=""?cpfAdminNameWithCpfCode:"CPF Admin Not Yet Register");
					}else if(map.get("STATUS").toString().equals("3")){
						cpfClaimRequestStatusDto.setStatus("Request approved by CPF-Admin Section and pending for sanction");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
						if(map.get("cpfsecActionDate")==null)
						cpfClaimRequestStatusDto.setRemarks("");
						else
						cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks")!=null?map.get("cpfsecRemarks").toString().trim():"");
							
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						if(map.get("cpfActionTakenByID")==null)	
						cpfClaimRequestStatusDto.setAdminActionTakenBy("");
						else
						cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim()));
							
					}else if(map.get("STATUS").toString().equals("4")){
						cpfClaimRequestStatusDto.setStatus("Amount sanctioned and request completed");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
						cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
					}
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
			}
			}else if(roleName!=null && roleName.equals("ADMIN")){
				if(reqType.equals("myReq")){
					String query = "select st.request_id,reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date, "
							+ "st.admin_action_taken_by as \"adminActionTakenByID\", admin_action_date, status, admin_remarks as \"adminRemarks\", "
							+ "st.cpfsec_action_taken_by as \"cpfActionTakenByID\", cpfsec_action_date as \"cpfsecActionDate\", cpfsec_remarks as \"cpfsecRemarks\", "
							+ "dsm.dsgn_desc as \"desig\", cfd.claim_applied_for, cfd.purpose, cfd.cpf_account_number "
							+ "from cpf_claim_form_status st, cpf_registered_users reg, cpf_claim_form_details cfd, pay_dsgn_mst dsm "
							+ "where st.claim_submitted_by=reg.emp_num "
							+ "and cfd.claim_submitted_by=reg.emp_num "
							+ "and cfd.designation=dsm.dsgn_id "
							+ "and st.status>-1 "
							+ "and st.claim_submitted_by=:empNum";
					
					Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery.setParameter("empNum", empNum);
					}
					List<Map<String, Object>> list = hQuery.list();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
					DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
					for (Map<String, Object> map : list) {
						ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
						
						String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
								+ "where claim_request_id =:requestId";

						Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						if (empNum != null) {
							hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
						}
						List<Map<String, Object>> list1 = hQuery1.list();
						for(Map<String, Object> map1 : list1){
							cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
							cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
						}
						cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
						cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
						cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString()+" ("+map.get("CPF_ACCOUNT_NUMBER").toString().trim()+")");
						cpfClaimRequestStatusDto.setDesignation(map.get("desig").toString());
						//String claimAppFor=map.get("CLAIM_APPLIED_FOR").toString().trim();
						String claimAppFor=null;
						switch(map.get("CLAIM_APPLIED_FOR").toString().trim()){
						case "CpfFinalSettlement":
							claimAppFor="Final Settlement";
							break;
						case "CpfPartFinalWithdrawal":
							claimAppFor="Part Final Settlement";
							break;
						case "90%Withdrawal":
							claimAppFor="90% Withdrawal";
							break;
						case "TempAdv":
							claimAppFor="Temp. Advance";
							break;
						default:
						}
						String claimPur=map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"";
						cpfClaimRequestStatusDto.setClaimType(claimAppFor+" ("+claimPur+")");
						
						if(map.get("STATUS").toString().equals("0")){
							cpfClaimRequestStatusDto.setStatus("Request Rejected");
							cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
							if(map.get("cpfsecRemarks")!=null){
								cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
							}else{
								cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks").toString());	
							}
							
							if(map.get("cpfsecActionDate")!=null){
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
								cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
							}else{
								if(map.get("ADMIN_ACTION_DATE")!=null)
									cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
								cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString()));
							}
							
						}else if(map.get("STATUS").toString().equals("1")){
							cpfClaimRequestStatusDto.setStatus("Request Pending At Admin Section");
							if(map.get("ADMIN_ACTION_DATE")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
							String adminNameWithCPFCode = getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString().trim());
							cpfClaimRequestStatusDto.setAdminActionTakenBy(adminNameWithCPFCode!=""?adminNameWithCPFCode:"Admin Not Yet Register");
							
						}else if(map.get("STATUS").toString().equals("2")){
							cpfClaimRequestStatusDto.setStatus("Request pending at CPF-Admin Section");
							cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks")!=null?map.get("adminRemarks").toString().trim():"");
							if(map.get("cpfsecActionDate")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
							String cpfAdminNameWithCPFCode = getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim());
							cpfClaimRequestStatusDto.setAdminActionTakenBy(cpfAdminNameWithCPFCode!=""?cpfAdminNameWithCPFCode:"CPF Admin Not Yet Register");
							
						}else if(map.get("STATUS").toString().equals("3")){
							cpfClaimRequestStatusDto.setStatus("Request approved by CPF-Admin Section and pending for sanction");
							if(map.get("cpfsecActionDate")==null)
								cpfClaimRequestStatusDto.setRemarks("");
							else
								cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks")!=null?map.get("cpfsecRemarks").toString().trim():"");
								
							if(map.get("cpfsecActionDate")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
							if(map.get("cpfsecActionTakenBy")==null)	
								cpfClaimRequestStatusDto.setAdminActionTakenBy("");
							else
								cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim()));
								
						}else if(map.get("STATUS").toString().equals("4")){
							cpfClaimRequestStatusDto.setStatus("Amount sanctioned and request completed");
							cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
							if(map.get("cpfsecActionDate")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim()));
						}
						listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
					}

				}else if(reqType.equals("otherReq")){
				/*String query = "select st.request_id,reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date,(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.admin_action_taken_by) "
						+ "admin_action_taken_by, st.admin_action_date,st.status, st.admin_remarks, dsm.dsgn_desc as \"desig\" "
						+ "from cpf_claim_form_status st,cpf_registered_users reg, cpf_claim_form_details cfd, pay_dsgn_mst dsm "
						+ "where st.claim_submitted_by=reg.emp_num "
						+ "and cfd.claim_submitted_by=reg.emp_num "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and st.status=1 "
						+ "and st.claim_submitted_by<>st.admin_action_taken_by "
						+ "and st.admin_action_taken_by=:empNum";*/
				
				String query = "select st.request_id,st.claim_submitted_by as \"empNum\", reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date, "
						+ "st.admin_action_taken_by as \"adminActionTakenByID\", dsm.dsgn_desc as \"desig\", "
						+ "st.admin_action_date,st.status, st.admin_remarks, cfd.claim_applied_for, cfd.purpose, cfd.cpf_account_number "
						+ "FROM cpf_claim_form_status st, cpf_claim_form_details cfd, cpf_registered_users reg, pay_dsgn_mst dsm "
						+ "WHERE st.claim_submitted_by=reg.emp_num "
						+ "and cfd.claim_submitted_by in (select Claim_Submitted_By from cpf_claim_form_status where admin_action_taken_by=:empNum) "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and cfd.claim_submitted_date=st.claim_submitted_date "
						+ "and st.status=1 "
						+ "and st.claim_submitted_by<>st.admin_action_taken_by "
						+ "and st.admin_action_taken_by=:empNum order by st.claim_submitted_date desc";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					
					String claimSubmitted_CPFCode=map.get("CPF_ACCOUNT_NUMBER").toString().trim();
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString().trim() +" ("+claimSubmitted_CPFCode+")");
					
					/*if(map.get("ADMIN_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));*/
					
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					
					cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString().trim()));
					//cpfClaimRequestStatusDto.setRemarks(map.get("admin_remarks").toString());
					//String claimAppFor=map.get("CLAIM_APPLIED_FOR").toString().trim();
					String claimAppFor=null;
					switch(map.get("CLAIM_APPLIED_FOR").toString().trim()){
					case "CpfFinalSettlement":
						claimAppFor="Final Settlement";
						break;
					case "CpfPartFinalWithdrawal":
						claimAppFor="Part Final Settlement";
						break;
					case "90%Withdrawal":
						claimAppFor="90% Withdrawal";
						break;
					case "TempAdv":
						claimAppFor="Temp. Advance";
						break;
					default:
					}
					String claimPur=map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"";
					cpfClaimRequestStatusDto.setClaimType(claimAppFor+" ("+claimPur+")");
					
					cpfClaimRequestStatusDto.setStatus("Pending at admin");
					cpfClaimRequestStatusDto.setDesignation(map.get("desig")!=null?map.get("desig").toString():"");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
			}
			}else if(roleName!=null && roleName.equals("CPF_ADMIN")){				
				if(reqType.equals("myReq")){
				String query = "select st.request_id,reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date,"
						+ "st.admin_action_taken_by as \"adminActionTakenByID\", st.admin_action_date, st.status, st.admin_remarks as \"adminRemarks\", "
						+ "st.cpfsec_action_taken_by as \"cpfActionTakenByID\", st.cpfsec_action_date as \"cpfsecActionDate\", st.cpfsec_remarks as \"cpfsecRemarks\", "
						+ "dsm.dsgn_desc as \"desig\", cfd.claim_applied_for, cfd.purpose, cfd.cpf_account_number "
						+ "from cpf_claim_form_status st, cpf_registered_users reg, cpf_claim_form_details cfd, pay_dsgn_mst dsm "
						+ "where st.claim_submitted_by=reg.emp_num "
						+ "and cfd.claim_submitted_by=reg.emp_num "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and st.status>-1 "
						+ "and st.claim_submitted_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString()+" ("+map.get("CPF_ACCOUNT_NUMBER").toString().trim()+")");
					cpfClaimRequestStatusDto.setDesignation(map.get("desig").toString());
					//String claimAppFor=map.get("CLAIM_APPLIED_FOR").toString().trim();
					String claimAppFor=null;
					switch(map.get("CLAIM_APPLIED_FOR").toString().trim()){
					case "CpfFinalSettlement":
						claimAppFor="Final Settlement";
						break;
					case "CpfPartFinalWithdrawal":
						claimAppFor="Part Final Settlement";
						break;
					case "90%Withdrawal":
						claimAppFor="90% Withdrawal";
						break;
					case "TempAdv":
						claimAppFor="Temp. Advance";
						break;
					default:
					}
					String claimPur=map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"";
					cpfClaimRequestStatusDto.setClaimType(claimAppFor+" ("+claimPur+")");
					
					if(map.get("STATUS").toString().equals("0")){
						cpfClaimRequestStatusDto.setStatus("Request Rejected");
						cpfClaimRequestStatusDto.setStatusCode(map.get("STATUS").toString());
						if(map.get("cpfsecRemarks")!=null){
							cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
						}else{
							cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks").toString());	
						}
						
						if(map.get("cpfsecActionDate")!=null){
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
						}else{
							if(map.get("ADMIN_ACTION_DATE")!=null)
								cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString()));
						}
						
					}else if(map.get("STATUS").toString().equals("1")){
						cpfClaimRequestStatusDto.setStatus("Request Pending At Admin Section");
						if(map.get("ADMIN_ACTION_DATE")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
						String adminNameWithCPFCode = getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString().trim());
						cpfClaimRequestStatusDto.setAdminActionTakenBy(adminNameWithCPFCode!=""?adminNameWithCPFCode:"Admin Not Yet Register");
					}else if(map.get("STATUS").toString().equals("2")){
						cpfClaimRequestStatusDto.setStatus("Request pending at CPF-Admin Section");
						cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks").toString());
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						String cpfAdminNameWithCPFCode = getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString().trim());
						cpfClaimRequestStatusDto.setAdminActionTakenBy(cpfAdminNameWithCPFCode!=""?cpfAdminNameWithCPFCode:"CPF Admin Not Yet Register");
					}else if(map.get("STATUS").toString().equals("3")){
						cpfClaimRequestStatusDto.setStatus("Request approved by CPF-Admin Section and pending for sanction");
						if(map.get("cpfsecActionDate")==null)
							cpfClaimRequestStatusDto.setRemarks("");
						else
							cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks")!=null?map.get("cpfsecRemarks").toString().trim():"");
							
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						if(map.get("cpfsecActionTakenBy")==null)	
							cpfClaimRequestStatusDto.setAdminActionTakenBy("");
						else
							cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
							
					}else if(map.get("STATUS").toString().equals("4")){
						cpfClaimRequestStatusDto.setStatus("Amount sanctioned and request completed");
						cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks").toString());
						if(map.get("cpfsecActionDate")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("cpfActionTakenByID").toString()));
					}
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}

			}else if(reqType.equals("otherReq")){
				/*String query = "select st.request_id,reg.emp_num as \"claimSubmittEmpNum\",reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date,"
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) "
						+ "cpfsec_action_taken_by, st.cpfsec_action_date, st.status, st.cpfsec_remarks, dsm.dsgn_desc as \"desig\" "
						+ "from cpf_claim_form_status st,cpf_registered_users reg, cpf_claim_form_details cfd, pay_dsgn_mst dsm "
						+ "where st.claim_submitted_by=reg.emp_num "
						+ "and cfd.claim_submitted_by=reg.emp_num "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and st.status=2 "
						+ "and st.claim_submitted_by<>st.cpfsec_action_taken_by "
						+ "and st.cpfsec_action_taken_by=:empNum";*/
				
				String query = "SELECT st.request_id,reg.emp_num as \"claimSubmitEmpNum\",reg.emp_name as \"claim_submitted_by\",st.claim_submitted_date, "
						+ "st.cpfsec_action_date, st.status, st.cpfsec_remarks, st.ADMIN_ACTION_DATE, cfd.claim_applied_for, cfd.purpose, cfd.cpf_account_number, "
						+ "st.admin_action_taken_by as \"adminActionTakenByID\", dsm.dsgn_desc as \"desig\" "
						+ "FROM cpf_claim_form_status st, cpf_claim_form_details cfd, cpf_registered_users reg, pay_dsgn_mst dsm "
						+ "WHERE reg.emp_num=st.claim_submitted_by "
						+ "and cfd.claim_submitted_by in (select  Claim_Submitted_By from cpf_claim_form_status where cpfsec_action_taken_by=:empNum) "
						+ "and cfd.designation=dsm.dsgn_id "
						+ "and cfd.claim_submitted_date=st.claim_submitted_date "
						+ "and st.status=2 "
						+ "and st.claim_submitted_by<>st.cpfsec_action_taken_by "
						+ "and st.cpfsec_action_taken_by=:empNum order by st.ADMIN_ACTION_DATE desc";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedEmpId(map.get("claimSubmitEmpNum").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString()+" ("+map.get("CPF_ACCOUNT_NUMBER").toString().trim()+")");
					
					/*if(map.get("CPFSEC_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("CPFSEC_ACTION_DATE").toString().trim())));*/
					
					if(map.get("ADMIN_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
					
					cpfClaimRequestStatusDto.setAdminActionTakenBy(getEmployeeWithCPFCode(map.get("adminActionTakenByID").toString()));
					//cpfClaimRequestStatusDto.setRemarks(map.get("admin_remarks").toString());
					//map.get("CLAIM_APPLIED_FOR").toString().trim();
					String claimAppFor=null;
					switch(map.get("CLAIM_APPLIED_FOR").toString().trim()){
					case "CpfFinalSettlement":
						claimAppFor="Final Settlement";
						break;
					case "CpfPartFinalWithdrawal":
						claimAppFor="Part Final Settlement";
						break;
					case "90%Withdrawal":
						claimAppFor="90% Withdrawal";
						break;
					case "TempAdv":
						claimAppFor="Temp. Advance";
						break;
					default:
					}
					String claimPur=map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"";
					cpfClaimRequestStatusDto.setClaimType(claimAppFor+" ("+claimPur+")");
					
					cpfClaimRequestStatusDto.setStatus("Pending At CPF Admin");
					cpfClaimRequestStatusDto.setDesignation(map.get("desig")!=null?map.get("desig").toString():"");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
				
			}
			}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		re.printStackTrace();
		throw re;
	}catch (ParseException e) {
		e.printStackTrace();
	}
		return listCpfClaimStatusDto;
	}
	
	@Override
	public ActClaimDto getClaimReqDetails(String reqId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		//CpfClaimRequest claimDataForForm = new CpfClaimRequest();
		ActClaimDto claimDataForForm = new ActClaimDto();
		
		try{
		String query ="select CLAIM_SUBMITTED_BY, CLAIM_SUBMITTED_DATE, CLAIM_APPLIED_FOR, EMP_NAME, FATHER_HUSBAND_NAME, a.BASIC, a.UAN, DOJ_FCI, a.PAN, b.dsgn_desc, "
				+ "c.loc_id, c.loc_desc, a.PARENT_ZONE, DATE_OF_BIRTH, CPF_ACCOUNT_NUMBER, MOBILE_NUMBER, a.RETIREMENT_DATE, PURPOSE, AMOUNT, INSTALLMENT_NUMBER, "
				+ "LAST_DRAWN_ADVANCE, ADVANCE_AMOUNT, AMOUNT_REPAID, OUTSTANDING_BAL, AMOUNT_90PARTFINAL_BEF_RETR, PERMISSIBLE_AMOUNT, DEC_NOT_EMP_TWOMONTH, "
				+ "EMP_DECLARATION,a.STAFF_CODE,CASTE_DISPUTE_CERT,AMOUNT_SANCTION "
				+ "from cpf_claim_form_details a,pay_dsgn_mst b,com_loc_mst c, pay_emp_mast d "
				+ "where a.claim_submitted_by=d.emp_num and a.designation=b.dsgn_id and c.loc_id=d.pres_location_code and REQUEST_ID = :reqId";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (reqId != null) {
			hQuery.setParameter("reqId", reqId);
		}
		List<Map<String, Object>> list = hQuery.list();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		
		for (Map<String, Object> map : list) {
			
			claimDataForForm.setREQUEST_ID(reqId);
			claimDataForForm.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString().trim());
			if(map.get("CLAIM_SUBMITTED_DATE")!=null)
			claimDataForForm.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim()));
			
			claimDataForForm.setClaimSubmittedDate(map.get("CLAIM_SUBMITTED_DATE").toString().trim());
			claimDataForForm.setCLAIM_APPLIED_FOR(map.get("CLAIM_APPLIED_FOR")!=null?map.get("CLAIM_APPLIED_FOR").toString().trim():"");
			claimDataForForm.setEMP_NAME(map.get("EMP_NAME").toString().trim());
			claimDataForForm.setDESIGNATION(map.get("DSGN_DESC").toString().trim());
			claimDataForForm.setPRESENT_LOCATION(map.get("LOC_DESC")!=null?map.get("LOC_DESC").toString().trim():"");
			claimDataForForm.setFATHER_HUSBAND_NAME(map.get("FATHER_HUSBAND_NAME")!=null?map.get("FATHER_HUSBAND_NAME").toString().trim():"");
			
			if(map.get("DATE_OF_BIRTH")!=null)
			claimDataForForm.setDATE_OF_BIRTH(format.parse(map.get("DATE_OF_BIRTH").toString().trim()));
			
			claimDataForForm.setBASIC(map.get("BASIC")!=null?map.get("BASIC").toString().trim():"");
			claimDataForForm.setUAN(map.get("UAN")!=null?map.get("UAN").toString().trim():"");
			
			if(map.get("DOJ_FCI")!=null)
			claimDataForForm.setDOJ_FCI(format.parse(map.get("DOJ_FCI").toString().trim()));
			
			if(map.get("RETIREMENT_DATE")!=null)
			claimDataForForm.setRETIREMENT_DATE(format.parse(map.get("RETIREMENT_DATE").toString().trim()));
			
			claimDataForForm.setPAN(map.get("PAN")!=null?map.get("PAN").toString().trim():"");
			claimDataForForm.setCPF_ACCOUNT_NUMBER(map.get("CPF_ACCOUNT_NUMBER")!=null?map.get("CPF_ACCOUNT_NUMBER").toString().trim():"");
			claimDataForForm.setSTAFF_CODE(map.get("STAFF_CODE")!=null?map.get("STAFF_CODE").toString().trim():"");
			claimDataForForm.setMOBILE_NUMBER(map.get("MOBILE_NUMBER")!=null?map.get("MOBILE_NUMBER").toString().trim():"");
			claimDataForForm.setADVANCE_AMOUNT(map.get("ADVANCE_AMOUNT")!=null?map.get("ADVANCE_AMOUNT").toString().trim():"");
			claimDataForForm.setAMOUNT(map.get("AMOUNT")!=null?map.get("AMOUNT").toString().trim():"");
			claimDataForForm.setAMOUNT_90PARTFINAL_BEF_RETR(map.get("AMOUNT_90PARTFINAL_BEF_RETR")!=null?map.get("AMOUNT_90PARTFINAL_BEF_RETR").toString():"");
			claimDataForForm.setAMOUNT_REPAID(map.get("AMOUNT_REPAID")!=null?map.get("AMOUNT_REPAID").toString().trim():"");
			if(map.get("DEC_NOT_EMP_TWOMONTH").equals("1")){
				claimDataForForm.setDEC_NOT_EMP_TWOMONTH(new Boolean("true"));
			}else{
				claimDataForForm.setDEC_NOT_EMP_TWOMONTH(new Boolean("false"));
			}
			
			if(map.get("EMP_DECLARATION").equals("1")){
				claimDataForForm.setEMP_DECLARATION(new Boolean("true"));
			}else{
				claimDataForForm.setEMP_DECLARATION(new Boolean("false"));
			}
			
			claimDataForForm.setCASTE_DISPUTE_CERT(map.get("CASTE_DISPUTE_CERT")!=null?map.get("CASTE_DISPUTE_CERT").toString().trim():"");
			
			claimDataForForm.setINSTALLMENT_NUMBER(map.get("INSTALLMENT_NUMBER")!=null?map.get("INSTALLMENT_NUMBER").toString().trim():"");
			
			if(map.get("LAST_DRAWN_ADVANCE").equals("1")){
				claimDataForForm.setLAST_DRAWN_ADVANCE(new Boolean("true"));
			}else{
				claimDataForForm.setLAST_DRAWN_ADVANCE(new Boolean("false"));
			}
			
			claimDataForForm.setOUTSTANDING_BAL(map.get("OUTSTANDING_BAL")!=null?map.get("OUTSTANDING_BAL").toString().trim():"");
			if(map.get("PERMISSIBLE_AMOUNT").equals("1")){
				claimDataForForm.setPERMISSIBLE_AMOUNT(new Boolean("true"));
			}else{
				claimDataForForm.setPERMISSIBLE_AMOUNT(new Boolean("false"));
			}
			claimDataForForm.setPURPOSE(map.get("PURPOSE")!=null?map.get("PURPOSE").toString().trim():"");
			claimDataForForm.setLocId(map.get("LOC_ID")!=null?map.get("LOC_ID").toString().trim():"0");
			claimDataForForm.setParentZone(map.get("PARENT_ZONE")!=null?map.get("PARENT_ZONE").toString().trim():"0");
			claimDataForForm.setAMOUNT_SANCTION(map.get("AMOUNT_SANCTION")!=null?map.get("AMOUNT_SANCTION").toString().trim():"0");
			
			String query2 = "select du.file_path as \"filePath\", du.doc_id as \"docId\" "
					+ "from cpf_doc_uploads du "
					+ "where du.emp_num=:empNum and du.file_type=1";
			Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			hQuery2.setParameter("empNum", map.get("CLAIM_SUBMITTED_BY").toString().trim());
			
			List<Map<String, Object>> list2 = hQuery2.list();
			if(list2!=null && list2.size()>0){
				for (Map<String, Object> map2 : list2) {
					File file = new File(map2.get("filePath").toString().trim());
					claimDataForForm.setKycFileName(file.getName());
					//claimDataForForm.setKycFilePath(map2.get("filePath")!=null?map2.get("filePath").toString().trim():"");
					claimDataForForm.setKycFilePath(map2.get("docId")!=null?map2.get("docId").toString().trim():"");
				}
			}
			
			String query3 = "select du.file_path as \"filePath\", du.doc_id as \"docId\" "
					+ "from cpf_doc_uploads du "
					+ "where du.emp_num=:empNum and du.request_id=:reqId and du.file_type=2";
			Query hQuery3 = session.createSQLQuery(query3).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if(reqId!=null){
				hQuery3.setParameter("empNum", map.get("CLAIM_SUBMITTED_BY").toString().trim());
				hQuery3.setParameter("reqId", reqId);
			}
			List<Map<String, Object>> list3 = hQuery3.list();
			if(list3!=null && list3.size()>0){
				Map<String,String> fileMap = new HashMap();
				for (Map<String, Object> map3 : list3) {
					File file = new File(map3.get("filePath").toString().trim());
					//fileMap.put(file.getName(), map3.get("filePath")!=null?map3.get("filePath").toString().trim():"");
					fileMap.put(file.getName(), map3.get("docId")!=null?map3.get("docId").toString().trim():"");
					claimDataForForm.setOtherFiles(fileMap);
				}
			}
			
			String query4 = "select du.file_path as \"filePath\", du.doc_id as \"docId\" "
					+ "from cpf_doc_uploads du "
					+ "where du.emp_num=:empNum "
					+ "and du.request_id=:reqId "
					+ "and du.file_type=3";
			Query hQuery4 = session.createSQLQuery(query4).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if(reqId!=null){
				hQuery4.setParameter("empNum", map.get("CLAIM_SUBMITTED_BY").toString().trim());
				hQuery4.setParameter("reqId", reqId);
			}
			List<Map<String, Object>> list4 = hQuery4.list();
			if(list4!=null && list4.size()>0){
				Map<String,String> fileMap = new HashMap();
				for (Map<String, Object> map4 : list4) {
					File file = new File(map4.get("filePath").toString().trim());
					//fileMap.put(file.getName(), map4.get("filePath")!=null?map4.get("filePath").toString().trim():"");
					fileMap.put(file.getName(),map4.get("docId")!=null?map4.get("docId").toString().trim():"");
					claimDataForForm.setUserOtherFiles(fileMap);
				}
			}
		}
		session.getTransaction().commit();
	} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return claimDataForForm;
	}

	@Override
	public Boolean checkInputData(ActClaimDto actClaimDto){
        String regex = "[A-Za-z0-9 A-Za-z0-9]*\\s*<(\"[^\"]*\"|'[^']*'|[^'\">])*>(.*<(\"[^\"]*\"|'[^']*'|[^'\">])*>)?\\s*[A-Za-z0-9 A-Za-z0-9]*";
		Pattern p = Pattern.compile(regex);
        if (actClaimDto.getRemarks() == null) {
            return false;
        }
        Matcher m = p.matcher(actClaimDto.getRemarks());
        return m.matches();
	}
	
	@Override
	public Boolean updateClaimReq(ActClaimDto actClaimDto, String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<String> cpfAdminIdList = new ArrayList<String>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		try{
			if(empRole.equals("ADMIN")){	
				if(!parentZone.equals("0") && !parentZone.equals("")){	
					cpfAdminIdList = getCpfAdminToAssign(parentZone, "CPF_ADMIN");
				}else{
					cpfAdminIdList = getCpfAdminToAssign("243", "CPF_ADMIN");
				}
				if(cpfAdminIdList.size()>0){
					Date currentDate = new Date();
					
					String query = "update cpf_claim_form_details set caste_dispute_cert = :castDisputeCert "
							+ "where request_id=:requestId and claim_submitted_by=:claimSubmittedBy";
					
					Query hQuery = session.createSQLQuery(query);
					if (reqId != null) {
						hQuery.setParameter("castDisputeCert", actClaimDto.getCASTE_DISPUTE_CERT());
						hQuery.setParameter("requestId", reqId);
						hQuery.setParameter("claimSubmittedBy", actClaimDto.getCLAIM_SUBMITTED_BY());
					}
					hQuery.executeUpdate();
					
					for(String cpfAdmId : cpfAdminIdList){
						
					/*String query = "update cpf_claim_form_status set admin_action = :reqType, admin_remarks = :remarks, admin_action_date =:adminActionDate, "
							+ "status =:status, admin_claim_submitted_to =:adminClaimSubmittedTo,cpfsec_action_taken_by =:cpfSecActionTakenBy "
							+ "where request_id=:requestId and admin_action_taken_by=:adminActionTakenBy";
					
					Query hQuery = session.createSQLQuery(query);
					if (reqId != null) {
						hQuery.setParameter("reqType", reqType);
						//hQuery.setParameter("remarks", actClaimDto.getRemarks());
						//hQuery.setParameter("adminActionDate", new Date());
						//hQuery.setParameter("status", "2");
						//hQuery.setParameter("requestId", reqId);
						//hQuery.setParameter("adminActionTakenBy", empNum);
						//hQuery.setParameter("adminClaimSubmittedTo", admId);  //CPF Admin ID
						hQuery.setParameter("cpfSecActionTakenBy", admId);    //CPF Admin ID
					}
					int result = hQuery.executeUpdate();*/
					
					CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();
					
					claimStatus.setREQUEST_ID(reqId);
					claimStatus.setCLAIM_SUBMITTED_BY(actClaimDto.getCLAIM_SUBMITTED_BY());
					try {
						claimStatus.setCLAIM_SUBMITTED_DATE(format.parse(actClaimDto.getClaimSubmittedDate()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					claimStatus.setADMIN_CLAIM_SUBMITTED_TO(cpfAdmId);
					claimStatus.setADMIN_ACTION(reqType);
					claimStatus.setADMIN_ACTION_DATE(currentDate);
					claimStatus.setADMIN_ACTION_TAKEN_BY(empNum);  
					claimStatus.setADMIN_REMARKS(actClaimDto.getRemarks());
					claimStatus.setCPFSEC_ACTION_TAKEN_BY(cpfAdmId);
					claimStatus.setSTATUS("2");
					
					session.persist(claimStatus);
					logger.info("claimStatus ::::: "+ claimStatus.toString());
					
					
					/*String query1 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
							+ "where request_id=:requestId and admin_action_taken_by<>:adminActionTakenBy";*/
					
					String query1 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
							+ "where request_id=:requestId and status=1";
					
					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (reqId != null) {
						hQuery1.setParameter("requestId", reqId);
						//hQuery1.setParameter("adminActionTakenBy", empNum);
					}
					List<Map<String, Object>> list = hQuery1.list();
					for(Map<String, Object> map : list){
						AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();
						
						auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map.get("STATUS_ID").toString()));
						auditCpfClaimRequestStatus.setREQUEST_ID(map.get("REQUEST_ID").toString()); 	
						auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString());
						try {
							auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						auditCpfClaimRequestStatus.setSTATUS("-1");
						
						session.persist(auditCpfClaimRequestStatus);
						
						Object object = session.load(CpfClaimRequestStatus.class,Integer.parseInt(map.get("STATUS_ID").toString()));
						session.delete(object);
					}
				}
			}
		session.getTransaction().commit();
		return true;
		}else if(empRole.equals("CPF_ADMIN")){
			
			String query = "update cpf_claim_form_status set cpfsec_action = :reqType, cpfsec_remarks = :remarks, cpfsec_action_date =:cpfsecActionDate, status =:status "
				+ "where request_id=:requestId and cpfsec_action_taken_by=:cpfsecActionTakenBy";
			Query hQuery = session.createSQLQuery(query);
			if (reqId != null) {
				hQuery.setParameter("reqType", reqType);
				hQuery.setParameter("remarks", actClaimDto.getRemarks());
				hQuery.setParameter("cpfsecActionDate", new Date());
				hQuery.setParameter("status", "3");
				hQuery.setParameter("requestId", reqId);
				hQuery.setParameter("cpfsecActionTakenBy", empNum);
				//hQuery.setParameter("adminClaimSubmittedTo", "160076");  //CPF Admin ID
				//hQuery.setParameter("cpfSecActionTakenBy", "160076");    //CPF Admin ID
			}
			int result = hQuery.executeUpdate();
			
			String query1 = "update cpf_claim_form_details set amount_sanction = :amountSanc "
					+ "where request_id=:requestId and claim_submitted_by=:claimSubmittedBy";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (reqId != null) {
				hQuery1.setParameter("amountSanc", actClaimDto.getAMOUNT_SANCTION()!=null?actClaimDto.getAMOUNT_SANCTION():"0");
				hQuery1.setParameter("requestId", reqId);
				hQuery1.setParameter("claimSubmittedBy", actClaimDto.getCLAIM_SUBMITTED_BY());
			}
			hQuery1.executeUpdate();
			
			
			/*String query1 = "update cpf_claim_form_status set status =:status "
					+ "where request_id=:requestId and admin_action_taken_by<>:adminActionTakenBy";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (reqId != null) {
				hQuery1.setBigDecimal("status", new BigDecimal(-1));
				hQuery1.setParameter("requestId", reqId);
				hQuery1.setParameter("adminActionTakenBy", empNum);
			}
			int result1 = hQuery1.executeUpdate();*/
				
			String query2 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
					+ "where request_id=:requestId and cpfsec_action_taken_by<>:cpfsecActionTakenBy";
			
			Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (reqId != null) {
				hQuery2.setParameter("requestId", reqId);
				hQuery2.setParameter("cpfsecActionTakenBy", empNum);
			}
			List<Map<String, Object>> list = hQuery2.list();
			for(Map<String, Object> map : list){
				AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();
				
				auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map.get("STATUS_ID").toString()));
				auditCpfClaimRequestStatus.setREQUEST_ID(map.get("REQUEST_ID").toString()); 	
				auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString());
				try {
					auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				auditCpfClaimRequestStatus.setSTATUS("-2");
				
				session.persist(auditCpfClaimRequestStatus);
				
				Object object = session.load(CpfClaimRequestStatus.class,Integer.parseInt(map.get("STATUS_ID").toString()));
				session.delete(object);
			}
			session.getTransaction().commit();
			return true;
		}
		return false;
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
	}
	
	
	@Override
	public Boolean reApplyClaimReq(ActClaimDto actClaimDto, String reqType, String reqId, String empNum, String empRole,
			String locCode, String parentZone) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
		try {
			List<String> adminId = getAdminToAssign(locCode, "ADMIN");
			if (adminId.size() > 0) {
				String query = "update cpf_claim_form_details set CLAIM_APPLIED_FOR = :claimAppliedFor, PURPOSE=:purpose, AMOUNT=:amount, INSTALLMENT_NUMBER=:installmentNo, "
						+ "DEC_NOT_EMP_TWOMONTH=:decNotEmpTwoMonth, EMP_DECLARATION=:empDeclaration, RE_CLAIM_SUBMITTED_DATE=:reClaimDate "
						+ "where request_id=:requestId";
				logger.info("Act Claim Data :::: " + actClaimDto.toString());
				Query hQuery = session.createSQLQuery(query);
				if (reqId != null) {
					hQuery.setParameter("claimAppliedFor", actClaimDto.getCLAIM_APPLIED_FOR());
					hQuery.setParameter("purpose", actClaimDto.getPURPOSE());
					hQuery.setParameter("amount", actClaimDto.getAMOUNT());
					hQuery.setParameter("installmentNo", actClaimDto.getINSTALLMENT_NUMBER() != null ? actClaimDto.getINSTALLMENT_NUMBER().toString() : "");

					if (actClaimDto.isDEC_NOT_EMP_TWOMONTH()) {
						hQuery.setParameter("decNotEmpTwoMonth", 1);
					} else {
						hQuery.setParameter("decNotEmpTwoMonth", 0);
					}

					if (actClaimDto.isEMP_DECLARATION()) {
						hQuery.setParameter("empDeclaration", 1);
					} else {
						hQuery.setParameter("empDeclaration", 0);
					}

					hQuery.setParameter("reClaimDate", new Date());
					hQuery.setParameter("requestId", reqId);
				}
				hQuery.executeUpdate();

				for (String admId : adminId) {
					CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();

					claimStatus.setREQUEST_ID(reqId);
					claimStatus.setCLAIM_SUBMITTED_BY(empNum);

					try {
						claimStatus.setCLAIM_SUBMITTED_DATE(format.parse(actClaimDto.getClaimSubmittedDate()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					claimStatus.setRE_CLAIM_SUBMITTED_DATE(new Date());
					claimStatus.setADMIN_ACTION_TAKEN_BY(admId);
					claimStatus.setSTATUS("1");

					session.persist(claimStatus);
				}

				String query1 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
						+ "where request_id=:requestId and status=0";

				Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (reqId != null) {
					hQuery1.setParameter("requestId", reqId);
				}
				List<Map<String, Object>> list = hQuery1.list();
				for (Map<String, Object> map : list) {
					AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();

					auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map.get("STATUS_ID").toString()));
					auditCpfClaimRequestStatus.setREQUEST_ID(map.get("REQUEST_ID").toString());
					auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString());
					try {
						auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					auditCpfClaimRequestStatus.setSTATUS("0");

					session.persist(auditCpfClaimRequestStatus);

					Object object = session.load(CpfClaimRequestStatus.class, Integer.parseInt(map.get("STATUS_ID").toString()));
					session.delete(object);
				}
				session.getTransaction().commit();
				return true;
			} else {
				httpSession.setAttribute("adminfound", "N");
				session.getTransaction().rollback();
				return null;
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
	}
	
	public List<String> getCpfAdminToAssign(String parentZone, String roleName){
		Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		List<String> adminList = new ArrayList<String>();
		try{
			String query = "select EmpId as \"empId\" from CPF_ASSIGNED_ROLES_DTL where parentzone=:parentZone and roleassigned=:roleName and enddate is null";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (parentZone != null) {
				hQuery.setParameter("parentZone", parentZone);
				hQuery.setParameter("roleName", roleName);
			}
			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				adminList.add(map.get("empId").toString().trim());
			}
			//session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return adminList;
	}
	
	
	
	
	@Override
	public Boolean rejectClaimReq(String remarks, String reqType, String reqId, String empNum, String empRole, String casteDisp) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		try{
		if(empRole.equals("ADMIN")){	
		String query = "update cpf_claim_form_status set admin_action = :reqType, admin_remarks = :remarks, admin_action_date =:adminActionDate, status =:status "
				+ "where request_id=:requestId and admin_action_taken_by=:adminActionTakenBy";
		
		Query hQuery = session.createSQLQuery(query);
		if (reqId != null) {
			hQuery.setParameter("reqType", reqType);
			hQuery.setParameter("remarks", remarks);
			hQuery.setParameter("adminActionDate", new Date());
			hQuery.setParameter("status", "0");
			hQuery.setParameter("requestId", reqId);
			hQuery.setParameter("adminActionTakenBy", empNum);
		}
		int result = hQuery.executeUpdate();
		
		/*String query1 = "update cpf_claim_form_status set status =:status "
				+ "where request_id=:requestId and admin_action_taken_by<>:adminActionTakenBy";
		
		Query hQuery1 = session.createSQLQuery(query1);
		if (reqId != null) {
			hQuery1.setBigDecimal("status", new BigDecimal(-1));
			hQuery1.setParameter("requestId", reqId);
			hQuery1.setParameter("adminActionTakenBy", empNum);
		}
		int result1 = hQuery1.executeUpdate();*/
		
		String query2 = "update cpf_claim_form_details set caste_dispute_cert = :casteDisp "
				+ "where request_id=:requestId";
		
		Query hQuery2 = session.createSQLQuery(query2);
		if (reqId != null) {
			hQuery2.setParameter("casteDisp", casteDisp);
			hQuery2.setParameter("requestId", reqId);
		}
		hQuery2.executeUpdate();
		
		String query3 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
				+ "where request_id=:requestId and status=1";
		
		Query hQuery3 = session.createSQLQuery(query3).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (reqId != null) {
			hQuery3.setParameter("requestId", reqId);
		}
		List<Map<String, Object>> list = hQuery3.list();
		for(Map<String, Object> map : list){
			AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();
			auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map.get("STATUS_ID").toString()));
			auditCpfClaimRequestStatus.setREQUEST_ID(map.get("REQUEST_ID").toString()); 	
			auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString());
			try {
				auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			auditCpfClaimRequestStatus.setSTATUS("-1");
			
			session.persist(auditCpfClaimRequestStatus);
			
			Object object = session.load(CpfClaimRequestStatus.class,Integer.parseInt(map.get("STATUS_ID").toString()));
			session.delete(object);
		}
		
		
		session.getTransaction().commit();
		return true;
		}else if(empRole.equals("CPF_ADMIN")){
			String query = "update cpf_claim_form_status set cpfsec_action = :reqType, cpfsec_remarks = :remarks, cpfsec_action_date =:cpfsecActionDate, status =:status "
					+ "where request_id=:requestId and cpfsec_action_taken_by=:cpfsecActionTakenBy";
			
			Query hQuery = session.createSQLQuery(query);
			if (reqId != null) {
				hQuery.setParameter("reqType", reqType);
				hQuery.setParameter("remarks", remarks);
				hQuery.setParameter("cpfsecActionDate", new Date());
				hQuery.setParameter("status", "0");
				hQuery.setParameter("requestId", reqId);
				hQuery.setParameter("cpfsecActionTakenBy", empNum);
			}
			int result = hQuery.executeUpdate();
			
			/*String query1 = "update cpf_claim_form_status set status =:status "
					+ "where request_id=:requestId and cpfsec_action_taken_by<>:cpfsecActionTakenBy";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (reqId != null) {
				hQuery1.setBigDecimal("status", new BigDecimal(-1));
				hQuery1.setParameter("requestId", reqId);
				hQuery1.setParameter("cpfsecActionTakenBy", empNum);
			}
			int result1 = hQuery1.executeUpdate();*/
			
			String query2 = "select status_id,request_id,claim_submitted_by,claim_submitted_date from cpf_claim_form_status "
					+ "where request_id=:requestId and cpfsec_action_taken_by<>:cpfsecActionTakenBy";
			
			Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (reqId != null) {
				hQuery2.setParameter("requestId", reqId);
				hQuery2.setParameter("cpfsecActionTakenBy", empNum);
			}
			List<Map<String, Object>> list = hQuery2.list();
			for(Map<String, Object> map : list){
				AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();
				
				auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map.get("STATUS_ID").toString()));
				auditCpfClaimRequestStatus.setREQUEST_ID(map.get("REQUEST_ID").toString()); 	
				auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map.get("CLAIM_SUBMITTED_BY").toString());
				try {
					auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				auditCpfClaimRequestStatus.setSTATUS("-2");
				
				session.persist(auditCpfClaimRequestStatus);
				
				Object object = session.load(CpfClaimRequestStatus.class,Integer.parseInt(map.get("STATUS_ID").toString()));
				session.delete(object);
			}
						
			session.getTransaction().commit();
			return true;	
		}
		return false;
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
	}
	
	@Override
	public List<ClaimRequestStatusDto> getAllApprovedClaimReq(String empNum,String roleName) {
		List<ClaimRequestStatusDto> listCpfClaimStatusDto = new ArrayList<ClaimRequestStatusDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			if(roleName!=null && roleName.equals("ADMIN")){
				String query = "select request_id,reg.emp_name as \"claim_submitted_by\",claim_submitted_date,"
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) "
						+ "cpfsecActionTakenBy, admin_action_date, status, admin_remarks as \"adminRemarks\""
						+ "from cpf_claim_form_status st,cpf_registered_users reg "
						+ "where st.claim_submitted_by=reg.emp_num and status>1 and admin_action_taken_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
								
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString());
					if(map.get("ADMIN_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("CPFSECACTIONTAKENBY").toString());
					cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks")!=null?map.get("adminRemarks").toString().trim():"");
					cpfClaimRequestStatusDto.setStatus("Approved By Admin");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}	
			}else if(roleName!=null && roleName.equals("CPF_ADMIN")){
				String query = "select request_id,reg.emp_name as \"claim_submitted_by\",claim_submitted_date,"
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) "
						+ "cpfsec_action_taken_by, cpfsec_action_date,status, cpfsec_remarks as \"cpfRemarks\""
						+ "from cpf_claim_form_status st,cpf_registered_users reg "
						+ "where st.claim_submitted_by=reg.emp_num and status>2 and cpfsec_action_taken_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString());
					if(map.get("CPFSEC_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("CPFSEC_ACTION_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("CPFSEC_ACTION_TAKEN_BY").toString());
					cpfClaimRequestStatusDto.setRemarks(map.get("cpfRemarks")!=null?map.get("cpfRemarks").toString().trim():"");
					cpfClaimRequestStatusDto.setStatus("Approved By CPF Admin");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
				
			}
			session.getTransaction().commit();
		} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		re.printStackTrace();
		throw re;
	}catch (ParseException e) {
		e.printStackTrace();
	}
		return listCpfClaimStatusDto;
	}
	
	
	@Override
	public List<ClaimRequestStatusDto> getCompletedClaimReq(String empNum,String roleName) {
		List<ClaimRequestStatusDto> listCpfClaimStatusDto = new ArrayList<ClaimRequestStatusDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			if(roleName!=null && roleName.equals("ADMIN")){
				String query = "select request_id,reg.emp_name as \"claim_submitted_by\",claim_submitted_date,"
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.admin_action_taken_by) "
						+ "admin_action_taken_by, admin_action_date,status, admin_remarks as \"adminRemarks\","
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) as \"cpfsecActionTakenBy\", "
						+ "cpfsec_action_date as \"cpfsecActionDate\", cpfsec_remarks as \"cpfsecRemarks\" "
						+ "from cpf_claim_form_status st, cpf_registered_users reg "
						+ "where st.claim_submitted_by=reg.emp_num and status in(0,4) and admin_action_taken_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString());
					
					if(map.get("cpfsecActionDate")!=null){
						cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("cpfsecActionDate").toString().trim())));
						cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("cpfsecActionTakenBy").toString());
						cpfClaimRequestStatusDto.setRemarks(map.get("cpfsecRemarks")!=null?map.get("cpfsecRemarks").toString().trim():"");
					}else{
						if(map.get("ADMIN_ACTION_DATE")!=null)
							cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
							cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("ADMIN_ACTION_TAKEN_BY").toString());
							cpfClaimRequestStatusDto.setRemarks(map.get("adminRemarks")!=null?map.get("adminRemarks").toString().trim():"");	
					}
					cpfClaimRequestStatusDto.setStatus("Request Completed");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}	
			}else if(roleName!=null && roleName.equals("CPF_ADMIN")){
				String query = "select request_id,reg.emp_name as \"claim_submitted_by\",claim_submitted_date,"
						+ "(select rg.emp_name from cpf_registered_users rg where rg.emp_num=st.cpfsec_action_taken_by) "
						+ "cpfsec_action_taken_by, cpfsec_action_date,status, cpfsec_remarks as \"cpfRemarks\""
						+ "from cpf_claim_form_status st,cpf_registered_users reg "
						+ "where st.claim_submitted_by=reg.emp_num and status in(0,4) and cpfsec_action_taken_by=:empNum";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("empNum", empNum);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimRequestStatusDto cpfClaimRequestStatusDto = new ClaimRequestStatusDto();
					
					String query1 ="select sanc_amt as \"sancAmount\", invoice_num as \"invoiceNo\" from pay_emp_cpf_sanc_dtl "
							+ "where claim_request_id =:requestId";

					Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					if (empNum != null) {
						hQuery1.setParameter("requestId", map.get("REQUEST_ID").toString());
					}
					List<Map<String, Object>> list1 = hQuery1.list();
					for(Map<String, Object> map1 : list1){
						cpfClaimRequestStatusDto.setSancAmount(map1.get("sancAmount")!=null?map1.get("sancAmount").toString():"");
						cpfClaimRequestStatusDto.setInvoiceNo(map1.get("invoiceNo")!=null?map1.get("invoiceNo").toString():"");
					}
					
					cpfClaimRequestStatusDto.setRequestId(map.get("REQUEST_ID").toString());
					cpfClaimRequestStatusDto.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setClaimSubmittedBy(map.get("claim_submitted_by").toString());
					if(map.get("CPFSEC_ACTION_DATE")!=null)
					cpfClaimRequestStatusDto.setAdminActionDate(myFormat.format(format.parse(map.get("CPFSEC_ACTION_DATE").toString().trim())));
					cpfClaimRequestStatusDto.setAdminActionTakenBy(map.get("CPFSEC_ACTION_TAKEN_BY").toString());
					cpfClaimRequestStatusDto.setRemarks(map.get("cpfRemarks")!=null?map.get("cpfRemarks").toString().trim():"");
					cpfClaimRequestStatusDto.setStatus("Request Completed");
					
					listCpfClaimStatusDto.add(cpfClaimRequestStatusDto);
				}
				
			}
			session.getTransaction().commit();
		} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	}catch (ParseException e) {
		e.printStackTrace();
	}
		return listCpfClaimStatusDto;
	}

	@Override
	public List<DropdownDto> getAllUnitList(String locId) {
		List<DropdownDto> unitList = new ArrayList<DropdownDto>();
		
		unitList.add(new DropdownDto("","---Select---"));
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "select unit_code from com_loc_mst where loc_type_gbl in ('DO','RO','ZO','HQ') and loc_id<>:locId order by unit_code asc";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (locId != null) {
			hQuery.setParameter("locId", locId);
		}
		List<Map<String, Object>> list = hQuery.list();
		
		for (Map<String,Object> map : list) {
			DropdownDto unitDto=new DropdownDto();
			unitDto.setUnitkey(map.get("UNIT_CODE").toString());
			unitDto.setUnitValue(map.get("UNIT_CODE").toString());
			unitList.add(unitDto);
		}
		return unitList;
	}

	@Override
	public List<DropdownDto> getAllLocationList(String unitId) {
		List<DropdownDto> locList = new ArrayList<DropdownDto>();
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "select loc_id as \"locId\", loc_desc as \"locDesc\" from com_loc_mst where loc_type_gbl in ('DO','RO','ZO','HQ') and unit_code=:unitCode";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (unitId != null) {
			hQuery.setParameter("unitCode", unitId);
		}
		List<Map<String, Object>> list = hQuery.list();
		
		for (Map<String,Object> map : list) {
			DropdownDto locDto=new DropdownDto();
			locDto.setLocationKey(map.get("locId").toString());
			locDto.setLocationDesc(map.get("locDesc").toString());
			locList.add(locDto);
		}
		return locList;
	}

	@Override
	public List<DropdownDto> getAllAdminList(String locId,String empNum) {
		List<DropdownDto> adminList = new ArrayList<DropdownDto>();
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "select empid as \"empId\", empname as \"empName\" from CPF_ASSIGNED_ROLES_DTL where empid<>:empNum and preslocation=:locCode and roleassigned='ADMIN' and enddate is null";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (locId != null) {
			hQuery.setParameter("locCode", locId);
			hQuery.setParameter("empNum", empNum);
		}
		List<Map<String, Object>> list = hQuery.list();
		
		for (Map<String,Object> map : list) {
			DropdownDto adminDto=new DropdownDto();
			adminDto.setAdminId(map.get("empId").toString());
			adminDto.setAdminName(map.get("empName").toString());
			adminList.add(adminDto);
		}
		return adminList;
	}

	@Override
	public Boolean assignToClaimReq(AssignToClaimDto assignToClaimDto, ActClaimDto actClaimDto, String reqType, String empNum, String empRole) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	try{
		if(empRole.equals("ADMIN")){	
		
			if(Integer.parseInt(assignToClaimDto.getLocationId().toString())!=Integer.parseInt(actClaimDto.getLocId().toString())){
				CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();
			
				claimStatus.setREQUEST_ID(actClaimDto.getREQUEST_ID());
				claimStatus.setCLAIM_SUBMITTED_BY(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimStatus.setCLAIM_SUBMITTED_DATE(actClaimDto.getCLAIM_SUBMITTED_DATE());
				//claimStatus.setADMIN_CLAIM_SUBMITTED_TO("307801");//cpf_admin employee number for a particular location
				claimStatus.setADMIN_ACTION(reqType);
				//claimStatus.setADMIN_ACTION_DATE(new Date());
				claimStatus.setADMIN_ACTION_TAKEN_BY(assignToClaimDto.getEmployeeId());//admin employee number for a particular location  
				claimStatus.setADMIN_REMARKS("");
				claimStatus.setSTATUS("1");
				claimStatus.setINFO1(assignToClaimDto.getUnitId());
				claimStatus.setINFO2(assignToClaimDto.getLocationId());
				session.persist(claimStatus);
				logger.info("claimStatus ::::: "+ claimStatus.toString());
			}else{
				
				String query1 = "update cpf_claim_form_status set admin_action_date =:adminActionDate, status=:status "
						+ "where request_id=:requestId and admin_action_taken_by=:adminActionTakenBy";
				
				Query hQuery1 = session.createSQLQuery(query1);

				hQuery1.setParameter("adminActionDate", new Date());
				hQuery1.setBigDecimal("status", new BigDecimal(1));
				//hQuery1.setParameter("status", "1");
				hQuery1.setParameter("requestId", actClaimDto.getREQUEST_ID());
				hQuery1.setParameter("adminActionTakenBy", assignToClaimDto.getEmployeeId());
				hQuery1.executeUpdate();
				
			}
			String query = "update cpf_claim_form_status set admin_action =:reqType, admin_remarks =:remarks, admin_action_date =:adminActionDate, "
					+ "status=:status, info3=:assigntoID "
					+ "where request_id=:requestId and admin_action_taken_by=:adminActionTakenBy";
			
			Query hQuery = session.createSQLQuery(query);

			hQuery.setParameter("reqType", reqType);
			hQuery.setParameter("remarks", assignToClaimDto.getRemarks());
			hQuery.setParameter("adminActionDate", new Date());
			hQuery.setParameter("status", "-1");
			hQuery.setParameter("requestId", actClaimDto.getREQUEST_ID());
			hQuery.setParameter("adminActionTakenBy", empNum);
			hQuery.setParameter("assigntoID", assignToClaimDto.getEmployeeId());
			hQuery.executeUpdate();
			
			session.getTransaction().commit();
		return true;
		}else if(empRole.equals("CPF_ADMIN")){	
		
			String query = "update cpf_claim_form_status set status =:status, cpfsec_remarks =:remarks, cpfsec_action_date =:cpfsecActionDate, "
					+ "info3=:assigntoID "
					+ "where request_id=:requestId and cpfsec_action_taken_by=:cpfsecActionTakenBy";
			
			Query hQuery = session.createSQLQuery(query);

			hQuery.setParameter("status", "-1");
			//hQuery.setParameter("reqType", reqType);
			hQuery.setParameter("remarks", assignToClaimDto.getRemarks());
			hQuery.setParameter("cpfsecActionDate", new Date());
			hQuery.setParameter("requestId", actClaimDto.getREQUEST_ID());
			hQuery.setParameter("cpfsecActionTakenBy", empNum);
			hQuery.setParameter("assigntoID", assignToClaimDto.getCpfAdminId());
			hQuery.executeUpdate();
			
			
			String query2 = "update cpf_claim_form_status set cpfsec_action =:reqType, cpfsec_action_date =:cpfsecActionDate, status =:status "
					+ "where request_id=:requestId and cpfsec_action_taken_by=:cpfsecActionTakenBy";
			
			Query hQuery2 = session.createSQLQuery(query2);
			
			hQuery2.setParameter("cpfsecActionDate", new Date());
			hQuery2.setParameter("status", "2");
			hQuery2.setParameter("reqType", reqType);
			hQuery2.setParameter("requestId", actClaimDto.getREQUEST_ID());
			hQuery2.setParameter("cpfsecActionTakenBy", assignToClaimDto.getCpfAdminId());
			hQuery2.executeUpdate();	
						
			
			
			/*DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
			
			String query1 = "select status_id,request_id,claim_submitted_by,claim_submitted_date,admin_action,admin_action_date,admin_action_taken_by,admin_remarks from cpf_claim_form_status "
					+ "where request_id=:requestId and cpfsec_action_taken_by=:cpfsecActionTakenBy";
			
			Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			hQuery1.setParameter("requestId", actClaimDto.getREQUEST_ID());
			hQuery1.setParameter("cpfsecActionTakenBy", empNum);
			List<Map<String, Object>> list = hQuery1.list();
			
			for(Map<String, Object> map : list){
				
				CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();
				
				claimStatus.setREQUEST_ID(actClaimDto.getREQUEST_ID());
				claimStatus.setCLAIM_SUBMITTED_BY(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimStatus.setCLAIM_SUBMITTED_DATE(actClaimDto.getCLAIM_SUBMITTED_DATE());
				//claimStatus.setADMIN_CLAIM_SUBMITTED_TO(assignToClaimDto.getCpfAdminId());//cpf_admin employee number for a particular location
				claimStatus.setADMIN_ACTION(map.get("ADMIN_ACTION").toString());
				try {
					claimStatus.setADMIN_ACTION_DATE(format.parse(map.get("ADMIN_ACTION_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				claimStatus.setADMIN_ACTION_TAKEN_BY(map.get("ADMIN_ACTION_TAKEN_BY").toString());//admin employee number for a particular location  
				claimStatus.setADMIN_REMARKS(map.get("ADMIN_REMARKS").toString());
				
				claimStatus.setCPFSEC_ACTION(reqType);
				claimStatus.setCPFSEC_ACTION_TAKEN_BY(assignToClaimDto.getCpfAdminId());
				claimStatus.setCPFSEC_REMARKS(assignToClaimDto.getRemarks());
				
				claimStatus.setSTATUS("2");
				
				session.persist(claimStatus);
				logger.info("claimStatus ::::: "+ claimStatus.toString());*/
				
				/*String query2 = "select status_id,request_id,claim_submitted_by,claim_submitted_date,admin_action,admin_action_date,admin_action_taken_by,admin_remarks from cpf_claim_form_status "
						+ "where request_id=:requestId and cpfsec_action_taken_by<>:cpfsecActionTakenBy";
				
				Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				hQuery2.setParameter("requestId", actClaimDto.getREQUEST_ID());
				hQuery2.setParameter("cpfsecActionTakenBy", empNum);
				List<Map<String, Object>> list2 = hQuery2.list();
				
				for(Map<String, Object> map2 : list2){
				AuditCpfClaimRequestStatus auditCpfClaimRequestStatus = new AuditCpfClaimRequestStatus();
				
				auditCpfClaimRequestStatus.setSTATUS_ID(Integer.parseInt(map2.get("STATUS_ID").toString()));
				auditCpfClaimRequestStatus.setREQUEST_ID(map2.get("REQUEST_ID").toString()); 	
				auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_BY(map2.get("CLAIM_SUBMITTED_BY").toString());
				try {
					auditCpfClaimRequestStatus.setCLAIM_SUBMITTED_DATE(format.parse(map2.get("CLAIM_SUBMITTED_DATE").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				auditCpfClaimRequestStatus.setSTATUS("-2");
				
				session.persist(auditCpfClaimRequestStatus);
				
				Object object = session.load(CpfClaimRequestStatus.class,Integer.parseInt(map2.get("STATUS_ID").toString()));
				session.delete(object);
				}*/
			//}
			session.getTransaction().commit();
		return true;
		}
	}catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
	}
	return false;
	}
	
	@Override
	public List<DropdownDto> getPurposeOfCPFList(String radioValue) {
		List<DropdownDto> cpfPurposeList = new ArrayList<DropdownDto>();
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "";
		
		if(radioValue.equals("CpfPartFinalWithdrawal"))
			query = "select CODE_SDESC as \"codeSdesc\", CODE_DESC as \"codeDesc\" from com_gbl_dtl where gbl_type = 'CPFPARTRSN' and CODE_SDESC<>'BFRRETR'";
		else if(radioValue.equals("TempAdv"))
			query ="select CODE_SDESC as \"codeSdesc\", CODE_DESC as \"codeDesc\" from com_gbl_dtl where gbl_type = 'CPFADVRSN'";
		
		if(!query.equals("")){
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		List<Map<String, Object>> list = hQuery.list();
		
		for (Map<String, Object> map : list) {
			DropdownDto cpfPurpose = new DropdownDto();
			cpfPurpose.setCpfPurposeKey(map.get("codeSdesc").toString());
			cpfPurpose.setCpfPurposeValue(map.get("codeDesc").toString());
			cpfPurposeList.add(cpfPurpose);
		}
		}
		return cpfPurposeList;
	}

	@Override
	public Map<String, Object> getCustomeRequestId() {
		Session session = sessionFactory.getCurrentSession();
    	//session.beginTransaction();
		 String query = "SELECT next_val FROM cpf_claim_form_details_seq";
         Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
         List<Map<String, Object>> list = hQuery.list();
         
         for (Map<String, Object> map : list) {
		
         }
		return null;
	}

	@Override
	public List<DropdownDto> getCPFAdminByParentZone(String parentZone, String userRole, String empNum) {
		
		List<DropdownDto> cpfAdminList = new ArrayList<DropdownDto>();
		cpfAdminList.add(new DropdownDto("","---Select---"));
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			String query = "select EmpId as \"empId\", empname as \"empName\" from CPF_ASSIGNED_ROLES_DTL where empid<>:empNum and parentzone=:parentZone and roleassigned=:roleName and enddate is null";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (parentZone != null) {
				hQuery.setParameter("empNum", empNum);
				hQuery.setParameter("parentZone", parentZone);
				hQuery.setParameter("roleName", userRole);
			}
			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				DropdownDto cpfAdmin=new DropdownDto();
				cpfAdmin.setCpfAdminId(map.get("empId").toString().trim());
				cpfAdmin.setCpfAdminName(map.get("empName").toString().trim());
				cpfAdminList.add(cpfAdmin);
			}
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return cpfAdminList;
	}

	@Override
	public String getClaimStatus(String reqId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String status = null;
		try {
			String query = "select distinct STATUS from cpf_claim_form_status s where s.REQUEST_ID = :reqId and status<>-1";

			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (reqId != null) {
				hQuery.setParameter("reqId", reqId);
			}
			HashMap resultMap =(HashMap) hQuery.uniqueResult();
			status = resultMap.get("STATUS").toString();
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			re.printStackTrace();
		}
		return status;
	}
	
	
	@Override
	public ClaimRequestStatusDto getClaimReqStatus(String reqId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		//List<ClaimRequestStatusDto> claimRequestStatusList = new ArrayList<>();
		ClaimRequestStatusDto claimRequestStatus = new ClaimRequestStatusDto();
		try{
		String query = "select CLAIM_SUBMITTED_BY, CLAIM_SUBMITTED_DATE, ADMIN_ACTION_TAKEN_BY, ADMIN_ACTION_DATE, ADMIN_REMARKS, ADMIN_ACTION, CPFSEC_ACTION_TAKEN_BY, CPFSEC_ACTION_DATE, CPFSEC_REMARKS, CPFSEC_ACTION, STATUS "
				+ "from cpf_claim_form_status s "
				+ "where s.REQUEST_ID = :reqId";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (reqId != null) {
			hQuery.setParameter("reqId", reqId);
		}
		List<Map<String, Object>> list = hQuery.list();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
		DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		try {
		for (Map<String, Object> map : list) {
			
			claimRequestStatus.setClaimSubmittedBy(map.get("CLAIM_SUBMITTED_BY").toString().trim());
			claimRequestStatus.setClaimSubmittedDate(myFormat.format(format.parse(map.get("CLAIM_SUBMITTED_DATE").toString().trim())));
			claimRequestStatus.setAdminActionTakenBy(map.get("ADMIN_ACTION_TAKEN_BY")!=null?map.get("ADMIN_ACTION_TAKEN_BY").toString().trim():"");
			claimRequestStatus.setAdminActionDate(myFormat.format(format.parse(map.get("ADMIN_ACTION_DATE").toString().trim())));
			claimRequestStatus.setAdminAction(map.get("ADMIN_ACTION")!=null?map.get("ADMIN_ACTION").toString().trim():"");
			claimRequestStatus.setCpfActionTakenBy(map.get("CPFSEC_ACTION_TAKEN_BY")!=null?map.get("CPFSEC_ACTION_TAKEN_BY").toString().trim():"");
			claimRequestStatus.setCpfActionDate(myFormat.format(format.parse(map.get("CPFSEC_ACTION_DATE").toString().trim())));
			claimRequestStatus.setCpfAction(map.get("CPFSEC_ACTION").toString().trim());
			//claimRequestStatus.setRemarks(map.get("ADMIN_REMARKS").toString().trim());
			claimRequestStatus.setStatus(map.get("STATUS").toString().trim());
		
			//claimRequestStatusList.add(claimRequestStatus);
		}
		}catch(ParseException e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			re.printStackTrace();
		}
		return claimRequestStatus;
	}


	@Override
	public List<ClaimHistoryTrailDto> getClaimHistoryTrailDetail(String reqId, String empNum, String roleName) {
		List<ClaimHistoryTrailDto> claimHistoryTrailList = new ArrayList<ClaimHistoryTrailDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			//if(roleName!=null && roleName.equals("USER")){
				String query = "select cht.request_id as \"reqId\",(select emp_first_name||' '||emp_middle_name||' '||emp_last_name from pay_emp_mast where emp_num=cht.claim_submitted_by) as claimSubmittedBy, "
						+ "em.emp_first_name||' '||em.emp_middle_name||' '||em.emp_last_name as \"actionTakenByName\", cht.action_taken_by as \"actionTakenBy\", "
						+ "cht.action_date as \"actionDate\", cht.action, cht.REMARKS, cht.STATUS, ru.role_name as \"roleName\" "
						+ "from cpf_claim_history_trail cht, pay_emp_mast em, cpf_registered_users ru "
						+ "where cht.action_taken_by=em.emp_num "
						+ "and cht.action_taken_by=ru.emp_num "
						+ "and cht.request_id=:reqId "
						+ "order by cht.action_date asc";
				
				Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (empNum != null) {
					hQuery.setParameter("reqId", reqId);
				}
				List<Map<String, Object>> list = hQuery.list();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
				DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa", Locale.ENGLISH);
				for (Map<String, Object> map : list) {
					ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
					claimHistoryTrailDto.setRequestId(map.get("reqId").toString());					
					claimHistoryTrailDto.setActionTakenBy(map.get("actionTakenByName").toString()+" ("+map.get("roleName").toString()+")");
					claimHistoryTrailDto.setActionDate(myFormat.format(format.parse(map.get("actionDate").toString().trim())));
					claimHistoryTrailDto.setAction(map.get("ACTION")!=null?map.get("ACTION").toString():"");
					claimHistoryTrailDto.setRemarks(map.get("REMARKS")!=null?map.get("REMARKS").toString():"");
					
					if(map.get("STATUS").toString().equals("0")){
						claimHistoryTrailDto.setStatus("Request Rejected");
					}else if(map.get("STATUS").toString().equals("1")){
						claimHistoryTrailDto.setStatus("Request Pending At Admin Section");
					}else if(map.get("STATUS").toString().equals("2")){
						claimHistoryTrailDto.setStatus("Request pending at CPF Section");
					}else if(map.get("STATUS").toString().equals("3")){
						claimHistoryTrailDto.setStatus("Request approved by CPF Section and pending for sanction");
					}else if(map.get("STATUS").toString().equals("4")){
						claimHistoryTrailDto.setStatus("Amount sanctioned and request completed");
					}
					claimHistoryTrailList.add(claimHistoryTrailDto);
				}
			//}
	}catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	}catch (ParseException e) {
		e.printStackTrace();
	}
		return claimHistoryTrailList;
	}


	@Override
	public String getMaxPermAmount(String empId, String sancType) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			/*
			 * entitymanager.getTransaction().begin(); StoredProcedureQuery sPQuery =
			 * entitymanager.createStoredProcedureQuery("PKG_CPF_FCI.proc_getcpfmaxlimit");
			 * 
			 * sPQuery.registerStoredProcedureParameter("pempno", String.class,
			 * ParameterMode.IN); sPQuery.registerStoredProcedureParameter("SANCTYPE",
			 * String.class, ParameterMode.IN);
			 * sPQuery.registerStoredProcedureParameter("MaxEligibleAmt", Double.class,
			 * ParameterMode.OUT); sPQuery.registerStoredProcedureParameter("perrorcode",
			 * Integer.class, ParameterMode.OUT);
			 * sPQuery.registerStoredProcedureParameter("perrmsg", String.class,
			 * ParameterMode.OUT); sPQuery.setParameter("pempno", empId);
			 * sPQuery.setParameter("SANCTYPE", sancType); sPQuery.execute();
			 * 
			 * Double maxEligibleAmt =
			 * (Double)sPQuery.getOutputParameterValue("MaxEligibleAmt");
			 * System.out.println("MaxEligibleAmt is: " + maxEligibleAmt);
			 * entitymanager.getTransaction().commit(); entitymanager.close();
			 */
	        
			Query query = session.createSQLQuery("CALL PKG_TEST1.proc_getcpfmaxlimit(:empId,:sancType,:maxAmount)")
					.setParameter("empId", empId)
					.setParameter("sancType", sancType);
			List result = query.list();
			for(int i=0; i<result.size(); i++){
				String maxAmount = result.get(i).toString();
			}
	}catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	}
		return "";
	}


	@Override
	public Boolean checkTempAdvApplyAbility(String empNum) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			String query = "Select count(*) as claimAllow From cpf_registered_users c left outer join pay_emp_mast b on b.EMP_NUM = c.emp_num where b.emp_num = :empNum" + 
					"       And b.EMP_STATUS Not In ('EMPSTATUS$RETD',\r\n" + 
					"                            'EMPSTATUS$EXP',\r\n" + 
					"                            'EMPSTATUS$RELVD',\r\n" + 
					"                            'EMPSTATUS$REPATR',\r\n" + 
					"                            'EMPSTATUS$DISMISS',\r\n" + 
					"                            'EMPSTATUS$COMRETD',\r\n" + 
					"                            'EMPSTATUS$RESGL',\r\n" + 
					"                            'EMPSTATUS$VOLRET',\r\n" + 
					"                            'EMPSTATUS$RESG',\r\n" + 
					"                            'EMPSTATUS$TERM',\r\n" + 
					"                            'EMPSTATUS$TRANS',\r\n" + 
					"                            'EMPSTATUS$DEPU')\r\n" + 
					"  And Not Exists\r\n" + 
					" (Select dt.EMP_NUM\r\n" + 
					"          From pay_emp_cpf_sanc_dtl dt\r\n" + 
					"         Where dt.EMP_NUM = :empNum" + 
					"           And dt.CANCEL_FLAG <> 'Y'\r\n" + 
					"           And Not Exists (Select ht.EMP_NUM\r\n" + 
					"                  From pay_loan_hdr ht\r\n" + 
					"                 Where ht.EMP_NUM = dt.EMP_NUM))\r\n" + 
					"  And Not Exists (Select dp.EMP_NUM\r\n" + 
					"          From pay_emp_cpf_sanc_dtl dp, pay_loan_hdr hd\r\n" + 
					"         Where dp.EMP_NUM = hd.EMP_NUM\r\n" + 
					"           And dp.EMP_NUM = :empNum" + 
					"           And hd.LOAN_TYPE = '87'\r\n" + 
					"           And hd.LOAN_STATUS = 'A')";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();
			for(Map<String, Object> map : list) {
				if(map.get("CLAIMALLOW").toString().equals("1")) {
					return true;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<SavedClaimConditionCheckDto> checkSavedClaimStatus(String empNum, String claimType) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<SavedClaimConditionCheckDto> savedClaimStatusList = new ArrayList<>();
		try {
			String query = "SELECT d.claim_submitted_by, d.request_id,d.claim_applied_for, d.purpose, s.status \r\n" + 
					"FROM cpf_claim_form_details d LEFT OUTER JOIN cpf_claim_form_status s \r\n" + 
					"ON d.request_id=s.request_id \r\n" + 
					"WHERE d.claim_applied_for=:claimType\r\n" + 
					"AND d.claim_submitted_by=:empNum\r\n" + 
					"GROUP BY d.claim_submitted_by, d.request_id,d.claim_applied_for, d.purpose, s.status";
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("claimType", claimType);
				hQuery.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();
			for(Map<String, Object> map : list) {
				SavedClaimConditionCheckDto savedClaimConditionCheck = new SavedClaimConditionCheckDto();
				savedClaimConditionCheck.setClaimSubmittedBy(map.get("CLAIM_SUBMITTED_BY").toString());
				savedClaimConditionCheck.setClaimReqId(map.get("REQUEST_ID").toString());
				savedClaimConditionCheck.setClaimType(map.get("CLAIM_APPLIED_FOR").toString());
				savedClaimConditionCheck.setClaimPurpose(map.get("PURPOSE").toString());
				savedClaimConditionCheck.setClaimStatus(Integer.parseInt(map.get("STATUS").toString()));
				savedClaimStatusList.add(savedClaimConditionCheck);
			}
		}catch(RuntimeException re) {
			re.printStackTrace();
		}
		return savedClaimStatusList;
	}

	
}
