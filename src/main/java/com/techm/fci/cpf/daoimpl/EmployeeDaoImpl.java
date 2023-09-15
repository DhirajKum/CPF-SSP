package com.techm.fci.cpf.daoimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 * 
 */

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.techm.fci.cpf.dao.BaseDao;
import com.techm.fci.cpf.dao.EmployeeDao;
import com.techm.fci.cpf.dto.HomeDto;
import com.techm.fci.cpf.model.CpfClaimRequest;
import com.techm.fci.cpf.model.CpfOtpMaster;
import com.techm.fci.cpf.model.DocumentsUpload;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.UserModel;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDao<Integer, EmpMaster> implements EmployeeDao{
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private boolean otpVerificationFlag = Boolean.FALSE;
	Session session;
	
	@Override
	public EmpMaster getEmpDetailsByEmpNum(String empNum) {
		System.out.println("::::: CPF Self Service Module ::::: EmployeeDaoImpl.java ::::: Inside Get User Details ");
		EmpMaster empMaster = null;
    	session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	if(empNum!=null){
	    	Criteria crit = createEntityCriteria();
	        crit.add(Restrictions.eq("EMP_NUM", empNum));
	        empMaster=(EmpMaster) crit.uniqueResult();
	        System.out.println("::::: CPF Self Service Module ::::: EmployeeDaoImpl.java ::::: Get User Details ");
    	}
        session.getTransaction().commit();
        return empMaster;
	}

	@Override
	public Boolean saveRegUser(RegisteredUser regUser) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		RegisteredUser regUserForSave = new RegisteredUser();
		//getOtpData(regUser.getEmpPhone());

		if(regUser!=null && !regUser.getEmpEmail().equals("") && !regUser.getEmpPhone().equals("") && !regUser.getPassword().equals("")  && otpVerificationFlag){
		regUserForSave.setEmpNum(regUser.getEmpNum());
		regUserForSave.setEmpName(regUser.getEmpName());
		regUserForSave.setEmpEmail(regUser.getEmpEmail());
		regUserForSave.setEmpPhone(regUser.getEmpPhone());
		regUserForSave.setUan(!regUser.getUan().equals("")?regUser.getUan():"0");
		regUserForSave.setPassword(passwordEncoder.encode(regUser.getPassword()));
		regUserForSave.setState("Active");
		regUserForSave.setUserRole("USER");
		regUserForSave.setCreatedBy("system");
		regUserForSave.setCreatedDate(new Date());
		regUserForSave.setModifiedBy("system");
		regUserForSave.setModifiedDate(new Date());
		regUserForSave.setLastLoginDate(new Date());
		
		session.persist(regUserForSave);
		session.getTransaction().commit();
		}else{
			return false;
		}
		return true;
	}

	@Override
	public HomeDto getHomePageData(String empNum) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		HomeDto homeDto = new HomeDto();	
		try{
			String query = "select m.emp_first_name||' '||m.emp_middle_name||' '||m.emp_last_name as \"empName\","
					+ "m.father_name as \"fatherName\",m.emp_birth_date as \"empDOB\",m.uan as \"uan\","
					+ "m.emp_pan_no as \"empPan\",r.emp_phone as \"empPhone\", r.emp_email as \"empEmail\",m.aadhar_num as \"empAadhar\", m.bank_ac_no as \"bankAccNo\", "
					+ "m.emp_sex_code as \"empGender\""
					+ "from pay_emp_mast m,cpf_registered_users r "
					+ "where m.emp_num=:empNum and m.emp_num=r.emp_num";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",Locale.ENGLISH);
			DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			for (Map<String, Object> map : list) {
				
				homeDto.setEmpName(map.get("empName").toString().trim());
				homeDto.setEmpFather(map.get("fatherName")!=null?map.get("fatherName").toString().trim():"");
				homeDto.setEmpUan(map.get("uan")!=null?map.get("uan").toString().trim():"");
				homeDto.setEmpDob(myFormat.format(format.parse(map.get("empDOB").toString().trim())));
				homeDto.setEmpAadhar(map.get("empAadhar")!=null?map.get("empAadhar").toString().trim():"");
				homeDto.setEmpPan(map.get("empPan")!=null?map.get("empPan").toString().trim():"");
				homeDto.setEmpBankAccNo(map.get("bankAccNo")!=null?map.get("bankAccNo").toString().trim():"");
				homeDto.setEmpEmail(map.get("empEmail")!=null?map.get("empEmail").toString().trim():"");
				if(map.get("empGender")!=null)
				homeDto.setEmpGender(map.get("empGender").toString().equals("M")?"Male":"Female");
				homeDto.setEmpMobile(map.get("empPhone")!=null?map.get("empPhone").toString().trim():"");
				
				String query1 = "select employee_pf_close_bal as \"employeeContribution\", employer_pf_close_bal as \"employerContribution\", vpf_close_bal as \"vpfCloseBal\","
						+ "to_year as \"toYear\" "
						+ "from pay_emp_pf_hdr "
						+ "where emp_num=:empNum order by to_year desc";
				Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				hQuery1.setParameter("empNum", empNum);
				
				List<Map<String, Object>> list1 = hQuery1.list();
				if(list1!=null && list1.size()>0){
				homeDto.setEmployeeContribute(list1.get(0).get("employeeContribution")!=null?list1.get(0).get("employeeContribution").toString()+".00":"");
				homeDto.setEmployerContribute(list1.get(0).get("employerContribution")!=null?list1.get(0).get("employerContribution").toString()+".00":"");
				homeDto.setVpfContribute(list1.get(0).get("vpfCloseBal")!=null?list1.get(0).get("vpfCloseBal").toString()+".00":"");
				homeDto.setCpfFreezeYear(list1.get(0).get("toYear")!=null?list1.get(0).get("toYear").toString():"");
				}
				
				String query2 = "select du.file_path as \"filePath\", du.doc_id as \"docId\" "
						+ "from cpf_doc_uploads du "
						+ "where du.emp_num=:empNum and du.file_type=1";
				Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				hQuery2.setParameter("empNum", empNum);
				
				List<Map<String, Object>> list2 = hQuery2.list();
				if(list2!=null && list2.size()>0){
					for (Map<String, Object> map2 : list2) {
						File file = new File(map2.get("filePath").toString().trim());
						homeDto.setFileName(file.getName());
						//homeDto.setFilePath(map2.get("filePath")!=null?map2.get("filePath").toString().trim():"");
						homeDto.setFilePath(map2.get("docId")!=null?map2.get("docId").toString().trim():"");
					}
				}
				
			}
			session.getTransaction().commit();
			return homeDto;
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public CpfOtpMaster getOtpData(String mobile) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfOtpMaster cpfOtpMaster = new CpfOtpMaster();	
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",Locale.ENGLISH);
		try{
			String query = "select otp_id, mobile_no, otp, expirytime, created_date from cpf_otp_mas where mobile_no=:mobile";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (mobile != null) {
				hQuery.setParameter("mobile", mobile);
			}
			List<Map<String, Object>> list = hQuery.list();
			if(list.size()>0){
			for (Map<String, Object> map : list) {
				cpfOtpMaster.setOTP_ID(Integer.parseInt(map.get("OTP_ID").toString()));
				cpfOtpMaster.setOTP(map.get("OTP").toString());
				cpfOtpMaster.setEXPIRYTIME(map.get("EXPIRYTIME").toString());
				cpfOtpMaster.setCREATED_DATE(format.parse(map.get("CREATED_DATE").toString()));
			}
			otpVerificationFlag=Boolean.TRUE;
			session.getTransaction().commit();
			}else{
				//cpfOtpMaster=null;
				otpVerificationFlag=Boolean.FALSE;
			}
			
			}catch (RuntimeException re) {
				logger.info("Find by example failed :::", re);
				throw re;
			} catch (ParseException e) {
				
				e.printStackTrace();
			}		
		return cpfOtpMaster;
	}

	@Override
	public Boolean addOtpData(String mobile, String otp, String expiryTime) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfOtpMaster cpfOTP = new CpfOtpMaster();
		
		cpfOTP.setMOBILE_NO(mobile);
		cpfOTP.setOTP(otp);
		cpfOTP.setEXPIRYTIME(expiryTime);
		cpfOTP.setCREATED_DATE(new Date());
		cpfOTP.setMODIFIED_DATE(new Date());
		
		session.persist(cpfOTP);
		session.getTransaction().commit();
		return true;
	}
	
	
	@Override
	public Boolean addOtpData(String mobile, String otp, String expiryTime, String empNum, String userName, String pin, String message, String signature, String del_by_smsportal, String dlt_entity_id, String dlt_template_id) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		CpfOtpMaster cpfOTP = new CpfOtpMaster();
		
		cpfOTP.setMOBILE_NO(mobile);
		cpfOTP.setOTP(otp);
		cpfOTP.setEXPIRYTIME(expiryTime);
		cpfOTP.setEMP_NUM(empNum);
		cpfOTP.setPIN(pin);
		cpfOTP.setMESSAGE(message);
		cpfOTP.setSIGNATURE(signature);
		cpfOTP.setDLT_ENTITY_ID(dlt_entity_id);
		cpfOTP.setDLT_TEMPLATE_ID(dlt_template_id);
		cpfOTP.setDEL_BY_SMSPORTAL(del_by_smsportal);
		cpfOTP.setCREATED_DATE(new Date());
		cpfOTP.setMODIFIED_DATE(new Date());
		
		session.persist(cpfOTP);
		session.getTransaction().commit();
		return true;
	}
	

	@Override
	public Boolean updateOtpData(String mobile, String otp, String expiryTime) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	try{	
		String query = "update cpf_otp_mas set otp = :otp, expirytime = :expiryTime, del_by_smsportal=:delBySmsportal, modified_date =:modifiedDate "
				+ "where mobile_no=:mobile";
		
		Query hQuery = session.createSQLQuery(query);
		if (mobile != null) {
			hQuery.setParameter("otp", otp);
			hQuery.setParameter("expiryTime", expiryTime);
			hQuery.setParameter("delBySmsportal", "N");
			hQuery.setParameter("mobile",mobile);
			hQuery.setParameter("modifiedDate", new Date());
		}
		hQuery.executeUpdate();
		session.getTransaction().commit();
		return true;
	} catch (RuntimeException re) {
		logger.info("Find by example failed :::", re);
		throw re;
	}
	}

	@Override
	public Boolean deleteExpiredOtp(int otpID) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Object object = session.load(CpfOtpMaster.class,otpID);
		session.delete(object);
		session.getTransaction().commit();
		return null;
	}

	@Override
	public Boolean changePassword(String newPass, String empNum) {

		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String query = "update cpf_registered_users set password = :newPass, modified_date=:modifiedDate where emp_num=:empNum";
		
		Query hQuery = session.createSQLQuery(query);
		if (empNum != null) {
			hQuery.setParameter("newPass", passwordEncoder.encode(newPass));
			hQuery.setParameter("modifiedDate", new Date());
			hQuery.setParameter("empNum",empNum);
		}
		hQuery.executeUpdate();
		session.getTransaction().commit();
		return true;
	}

	@Override
	public Boolean saveKycDoc(UserModel uModel, String path) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String empNum=uModel.getEmpNum();
		String query = "select file_path as \"filePath\" from cpf_doc_uploads where emp_num=:empNum and file_type=1";
		
		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (empNum != null) {
			hQuery.setParameter("empNum", empNum);
		}
		List<Map<String, Object>> list = hQuery.list();

		if(list.size()<1) {
			DocumentsUpload docUpload = new DocumentsUpload();
			docUpload.setEmp_num(uModel.getEmpNum());
			docUpload.setEmp_email(uModel.getEmail());
			docUpload.setEmp_phone(uModel.getEmpPhone());
			docUpload.setFile_type("1");
			docUpload.setFile_path(path);
			docUpload.setRole_name(uModel.getRoleName());
			docUpload.setRequest_id("0");
			docUpload.setCreated_by(uModel.getEmpNum());
			docUpload.setCreated_date(new Date());
			docUpload.setModified_by(uModel.getEmpNum());
			docUpload.setModified_date(new Date());
			session.persist(docUpload);
		}else{
			String query1 = "update cpf_doc_uploads set file_path = :newPath, modified_date=:modifiedDate "
					+ "where emp_num=:empNum "
					+ "and file_type=1 "
					+ "and request_id=0";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (empNum != null) {
				hQuery1.setParameter("newPath", path);
				hQuery1.setParameter("modifiedDate", new Date());
				hQuery1.setParameter("empNum",empNum);
			}
			hQuery1.executeUpdate();
		}
		
		session.getTransaction().commit();
		return true;
	}
		
	private List<DocumentsUpload> getUploadDocDetails(UserModel uModel, String fileType, String claimSubEmpID, String reqId) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<DocumentsUpload> docUploadList = new ArrayList<>();
		String empNum=uModel.getEmpNum();
		if(empNum!=null){
			String query = "SELECT doc_id as \"docID\", emp_num as \"empNum\", request_id as \"reqId\", file_type as \"fileType\", emp_phone as \"empPhone\", "
					+ "emp_email as \"empEmail\", file_path as \"filePath\", claim_applied_for as \"claimAppliedFor\", role_name as \"roleName\" "
					+ "FROM cpf_doc_uploads "
					+ "WHERE emp_num=:empNum "
					+ "AND request_id=:reqId "
					+ "AND file_type=:fileType "
					+ "AND created_by=:createdBy";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if(claimSubEmpID!=null && !claimSubEmpID.equals(""))
				hQuery.setParameter("empNum", claimSubEmpID);
			else
				hQuery.setParameter("empNum", empNum);
			
			if(!reqId.equals(""))
				hQuery.setParameter("reqId", reqId);
			else
				hQuery.setParameter("reqId", "0");
			
			hQuery.setParameter("fileType", fileType);
			hQuery.setParameter("createdBy", empNum);

			List<Map<String, Object>> list = hQuery.list();
			if(list!=null && list.size()>0){
				for (Map<String, Object> map : list) {
					DocumentsUpload docUpload = new DocumentsUpload();
					docUpload.setDoc_id(((BigDecimal)map.get("docID")).intValue());
					docUpload.setEmp_num(map.get("empNum").toString().trim());
					docUpload.setRequest_id(map.get("reqId").toString().trim());
					docUpload.setFile_type(map.get("fileType").toString().trim());
					docUpload.setEmp_phone(map.get("empPhone").toString().trim());
					docUpload.setEmp_email(map.get("empEmail").toString().trim());
					docUpload.setRole_name(map.get("roleName").toString().trim());
					docUpload.setFile_path(map.get("filePath").toString().trim());
					docUpload.setCLAIM_APPLIED_FOR(map.get("claimAppliedFor").toString().trim());
					docUploadList.add(docUpload);
				}
			}
		}
		return docUploadList;
	}
	
	
	@Override
	public Boolean deleteOtherDoc(UserModel uModel, String claimSubEmpID, String reqId) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String empNum = uModel.getEmpNum();
		if (empNum != null && claimSubEmpID != null && !claimSubEmpID.equals("")) {
			List<DocumentsUpload> uploadedDocList = getUploadDocDetails(uModel, "2", claimSubEmpID, reqId);
			for (DocumentsUpload docList : uploadedDocList) {
				Object object = session.load(DocumentsUpload.class, docList.getDoc_id());
				session.delete(object);
			}
		} else {
			List<DocumentsUpload> uploadedDocList = getUploadDocDetails(uModel, "3", claimSubEmpID, reqId);
			for (DocumentsUpload docList : uploadedDocList) {
				Object object = session.load(DocumentsUpload.class, docList.getDoc_id());
				session.delete(object);
			}
		}
		
		/*String empNum=uModel.getEmpNum();
		if(empNum!=null && claimSubEmpID!=null && !claimSubEmpID.equals("")){
			String query = "select doc_id as \"docID\" from cpf_doc_uploads where emp_num=:empNum and request_id=:reqId and file_type=2 and created_by=:createdBy";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", claimSubEmpID);
				hQuery.setParameter("reqId", reqId);
				hQuery.setParameter("createdBy", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();
			if(list!=null && list.size()>0){
				for (Map<String, Object> map : list) {
					Object object = session.load(DocumentsUpload.class,((BigDecimal)map.get("docID")).intValue());
					session.delete(object);
				}
			}
		}*/
		/*else{
			String query = "select doc_id as \"docID\" from cpf_doc_uploads where emp_num=:empNum and file_type=3";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();
			if(list!=null && list.size()>0){
				for (Map<String, Object> map : list) {
					Object object = session.load(DocumentsUpload.class,((BigDecimal)map.get("docID")).intValue());
					session.delete(object);
				}
			}
		}*/
		session.getTransaction().commit();
		return true;
	}
	
	@Override
	public Boolean saveOtherDoc(UserModel uModel, String claimSubEmpID, String reqId, String claimAppliedFor, String path) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		if((uModel.getRoleName().equals("ADMIN") || uModel.getRoleName().equals("CPF_ADMIN")) && reqId!=null){		
		String query1 = "select cru.emp_num as \"empNum\", cru.emp_phone as \"empPhone\",cru.emp_email as \"empEmail\",cru.role_name as \"roleName\" "
				+ "from cpf_registered_users cru, cpf_claim_form_details ccfd "
				+ "where cru.emp_num=ccfd.claim_submitted_by and ccfd.request_id=:reqId";
		
		Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (reqId != null) {
			hQuery1.setParameter("reqId", reqId);
		}
		List<Map<String, Object>> list1 = hQuery1.list();

		for (Map<String, Object> map : list1) {
			DocumentsUpload docUpload = new DocumentsUpload();
			docUpload.setEmp_num(map.get("empNum").toString().trim());
			docUpload.setEmp_email(map.get("empEmail")!=null?map.get("empEmail").toString().trim():"");
			docUpload.setEmp_phone(map.get("empPhone")!=null?map.get("empPhone").toString().trim():"");
			docUpload.setFile_type("2");
			docUpload.setCLAIM_APPLIED_FOR(claimAppliedFor.toString().trim());
			docUpload.setFile_path(path);
			docUpload.setRole_name(map.get("roleName").toString().trim());
			docUpload.setRequest_id(reqId);
			docUpload.setCreated_by(uModel.getEmpNum());
			docUpload.setCreated_date(new Date());
			docUpload.setModified_by(uModel.getEmpNum());
			docUpload.setModified_date(new Date());
			session.persist(docUpload);
		}
		}
		if (uModel.getRoleName().equals("USER") || uModel.getRoleName().equals("ADMIN") || uModel.getRoleName().equals("CPF_ADMIN")){
			String query1 = null;
			/*
			 * if(reqId!=null && !reqId.equals("")){ query1 =
			 * "select cru.emp_num as \"empNum\", cru.emp_phone as \"empPhone\",cru.emp_email as \"empEmail\",cru.role_name as \"roleName\" "
			 * + "from cpf_registered_users cru, cpf_claim_form_details ccfd " +
			 * "where cru.emp_num=ccfd.claim_submitted_by and ccfd.request_id=:reqId"; }else
			 */
			if(reqId==null || reqId.equals("")){
				query1 = "select cru.emp_num as \"empNum\", cru.emp_phone as \"empPhone\",cru.emp_email as \"empEmail\",cru.role_name as \"roleName\" "
						+ "from cpf_registered_users cru "
						+ "where cru.emp_num=:claimSubEmpID";
			
			
			Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			/*
			 * if (reqId != null || !reqId.equals("")) { hQuery1.setParameter("reqId",
			 * reqId); }else
			 */
			if (reqId == null || reqId.equals("")){
				hQuery1.setParameter("claimSubEmpID", uModel.getEmpNum());
			}
			
			List<Map<String, Object>> list1 = hQuery1.list();

			for (Map<String, Object> map : list1) {
				DocumentsUpload docUpload = new DocumentsUpload();
				docUpload.setEmp_num(map.get("empNum").toString().trim());
				docUpload.setEmp_email(map.get("empEmail")!=null?map.get("empEmail").toString().trim():"");
				docUpload.setEmp_phone(map.get("empPhone")!=null?map.get("empPhone").toString().trim():"");
				docUpload.setFile_type("3");
				docUpload.setCLAIM_APPLIED_FOR(claimAppliedFor.toString().trim());
				docUpload.setFile_path(path.toString().trim());
				docUpload.setRole_name(map.get("roleName").toString().trim());
				if(reqId!=null && !reqId.equals("")) {
					docUpload.setRequest_id(reqId);
				}else{
					docUpload.setRequest_id("0");	
				}
				docUpload.setCreated_by(uModel.getEmpNum());
				docUpload.setCreated_date(new Date());
				docUpload.setModified_by(uModel.getEmpNum());
				docUpload.setModified_date(new Date());
				session.persist(docUpload);			
			}
		}
		}
		/*else{
			String query1 = "update cpf_doc_uploads set file_path = :newPath, modified_date=:modifiedDate where emp_num=:empNum";
			
			Query hQuery1 = session.createSQLQuery(query1);
			if (empNum != null) {
				hQuery1.setParameter("newPath", path);
				hQuery1.setParameter("modifiedDate", new Date());
				hQuery1.setParameter("empNum",empNum);
			}
			hQuery1.executeUpdate();
		}*/
		
		session.getTransaction().commit();
		return true;
	}

	
	
	@Override
	public void insertOtpToSmsTable(String empNum,String mobile){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
		String query = "insert into cust.XXFCI_SMS_SERVICE_STG_1(PIN,MNUMBER,SIGNATURE,DLT_ENTITY_ID,DLT_TEMPLATE_ID,PICK_BY_SMSPORTAL,DEL_BY_SMSPORTAL,MSG_STATUS,INSERT_DATE) "
				+ "values('W3%24cV1%26tE5',?,'FCIFAP','1101611580000043694','1107161233369894498','Y','N','Accepted',sysdate)";	
		
		Query hQuery = session.createSQLQuery(query);
		hQuery.setParameter(0, mobile);
		hQuery.executeUpdate();
		session.getTransaction().commit();
		}
		catch (RuntimeException re) {
			logger.info("Insertion failed :::", re);
			throw re;
	
}
	}

	@Override
	public String getUploadedPath(String pathId, String fileType) {

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			String query2 = "select du.file_path as \"filePath\" "
					+ "from cpf_doc_uploads du "
					+ "where du.doc_id=:docId and du.file_type=:fileType";

			Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			hQuery2.setParameter("docId", pathId);
			hQuery2.setParameter("fileType", fileType);

			List<Map<String, Object>> list2 = hQuery2.list();
			String filePath = "";
			if (list2 != null && list2.size() > 0) {
				for (Map<String, Object> map2 : list2) {
					File file = new File(map2.get("filePath").toString().trim());
					filePath = file.getPath();
				}
			}
			return filePath;
		} catch (RuntimeException re) {
			logger.info("File path exception :::", re);
			throw re;
		}
	}

	@Override
	public Boolean updateOtherDoc(UserModel uModel, CpfClaimRequest cpfClaimReq) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String folderPath = null;
		try {
			
			if (!uModel.getEmpNum().equals("")) {
			 // folderPath = "/prodshare/cpf_out/" + uModel.getEmpNum().trim() + "_" + cpfClaimReq.getREQUEST_ID() + "_OTHERS"; //For Production server
				folderPath = "/fapshare/cpf_out/" + uModel.getEmpNum().trim() + "_" + cpfClaimReq.getREQUEST_ID() + "_OTHERS"; //For Dev server
			}
			
			String query = "update cpf_doc_uploads set REQUEST_ID = :reqId where EMP_NUM= :empNum and FILE_TYPE= :fileType and REQUEST_ID = 0 ";
			
			Query hQuery = session.createSQLQuery(query);
			if (uModel.getEmpNum() != null) {
				hQuery.setParameter("reqId", cpfClaimReq.getREQUEST_ID());
				
				hQuery.setParameter("empNum",uModel.getEmpNum());
				hQuery.setParameter("fileType",3);
			}
			hQuery.executeUpdate();
			
			
			List<DocumentsUpload> uploadedDocList = getUploadDocDetails(uModel, "3", uModel.getEmpNum(), cpfClaimReq.getREQUEST_ID());
			for (DocumentsUpload docList : uploadedDocList) {
				 Object object = session.load(DocumentsUpload.class, docList.getDoc_id());
				 session.delete(object);
			}
			
			File[] files = new File("/fapshare/cpf_out/" + uModel.getEmpNum().trim() + "__OTHERS").listFiles();
			if(files!=null) {
			for(File fileList : files) {

				DocumentsUpload docUpload = new DocumentsUpload();
				docUpload.setEmp_num(uploadedDocList.get(0).getEmp_num().trim());
				docUpload.setEmp_email(uploadedDocList.get(0).getEmp_email().trim());
				docUpload.setEmp_phone(uploadedDocList.get(0).getEmp_phone().trim());
				docUpload.setFile_type(uploadedDocList.get(0).getFile_type());
				docUpload.setCLAIM_APPLIED_FOR(uploadedDocList.get(0).getCLAIM_APPLIED_FOR().trim());
				docUpload.setFile_path(folderPath+"/"+fileList.getName().trim());
				docUpload.setRole_name(uploadedDocList.get(0).getRole_name().trim());
				docUpload.setRequest_id(uploadedDocList.get(0).getRequest_id());
				
				docUpload.setCreated_by(uModel.getEmpNum());
				docUpload.setCreated_date(new Date());
				docUpload.setModified_by(uModel.getEmpNum());
				docUpload.setModified_date(new Date());
				session.persist(docUpload);	
				
				/*String query1 = "update cpf_doc_uploads set REQUEST_ID = :reqId, FILE_PATH= :filePath, MODIFIED_DATE= :modifiedDate "
						+ "where EMP_NUM= :empNum "
						//+ "and CLAIM_APPLIED_FOR= :claimAppliedFor "
						+ "and FILE_TYPE= :fileType";
				
				Query hQuery1 = session.createSQLQuery(query1);
				if (uModel.getEmpNum() != null) {
					hQuery1.setParameter("reqId", cpfClaimReq.getREQUEST_ID());
					hQuery1.setParameter("filePath",folderPath+"/"+fileList.getName());
					hQuery1.setParameter("modifiedDate", new Date());
					
					hQuery1.setParameter("empNum",uModel.getEmpNum());
					//hQuery1.setParameter("claimAppliedFor",cpfClaimReq.getCLAIM_APPLIED_FOR());
					hQuery1.setParameter("fileType",3);
				}
				hQuery1.executeUpdate();*/
			}
			}
			
			File dir = new File("/fapshare/cpf_out/" + uModel.getEmpNum().trim() + "__OTHERS");
			File renameDir = new File(folderPath);
			dir.renameTo(renameDir);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		session.getTransaction().commit();
		return true;
	}
}	
