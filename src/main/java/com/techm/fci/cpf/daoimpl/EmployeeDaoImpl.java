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
		EmpMaster empMaster = null;
    	session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	if(empNum!=null){
	    	Criteria crit = createEntityCriteria();
	        crit.add(Restrictions.eq("EMP_NUM", empNum));
	        empMaster=(EmpMaster) crit.uniqueResult();
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
			docUpload.setCreated_by(uModel.getEmpName());
			docUpload.setCreated_date(new Date());
			docUpload.setModified_by(uModel.getEmpName());
			docUpload.setModified_date(new Date());
			session.persist(docUpload);
		}else{
			String query1 = "update cpf_doc_uploads set file_path = :newPath, modified_date=:modifiedDate where emp_num=:empNum";
			
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
	
	@Override
	public Boolean deleteOtherDoc(UserModel uModel, String claimSubEmpID, String reqId) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String empNum=uModel.getEmpNum();
		if(empNum!=null){
			String query = "select doc_id as \"docID\" from cpf_doc_uploads where emp_num=:empNum and request_id=:reqId and file_type=2";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", claimSubEmpID);
				hQuery.setParameter("reqId", reqId);
			}
			List<Map<String, Object>> list = hQuery.list();
			if(list!=null && list.size()>0){
				for (Map<String, Object> map : list) {
					Object object = session.load(DocumentsUpload.class,((BigDecimal)map.get("docID")).intValue());
					session.delete(object);
				}
			}
		}
		session.getTransaction().commit();
		return true;
	}
	
	@Override
	public Boolean saveOtherDoc(UserModel uModel, String claimSubEmpID, String reqId, String path) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
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
			docUpload.setEmp_email(map.get("empEmail").toString().trim());
			docUpload.setEmp_phone(map.get("empPhone").toString().trim());
			docUpload.setFile_type("2");
			docUpload.setFile_path(path);
			docUpload.setRole_name(map.get("roleName").toString().trim());
			docUpload.setRequest_id(reqId);
			docUpload.setCreated_by(uModel.getEmpNum());
			docUpload.setCreated_date(new Date());
			docUpload.setModified_by(uModel.getEmpNum());
			docUpload.setModified_date(new Date());
			session.persist(docUpload);
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

}	
	

