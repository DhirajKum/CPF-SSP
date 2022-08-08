package com.techm.fci.cpf.daoimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techm.fci.cpf.dao.RoleMappingDao;
import com.techm.fci.cpf.dao.UserDao;
import com.techm.fci.cpf.dto.ClaimStatusAssignToNewAdminDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.dto.RoleMappingBean;
import com.techm.fci.cpf.model.CpfClaimRequestStatus;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.RoleMappingRequest;
import com.techm.fci.cpf.model.UserModel;

@Repository("roleMappingDao")
public class RoleMappingDaoImpl implements RoleMappingDao {

	public static final Logger logger = LoggerFactory.getLogger(RoleMappingDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HttpSession httpSession;

	private Session session;
	
	@Autowired
	UserDao userDao;

	private UserModel getUserModel() {
		UserModel uModel = (UserModel) httpSession.getAttribute("userModel");
		return uModel;
	}
	
	
	@Override
	public RoleMappingBean getRoleMappingBeanDetails(String empNum) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		RoleMappingBean roleMappingBean = new RoleMappingBean();
		try {
			String query = "select emp_first_name||' '||emp_middle_name||' '||emp_last_name as \"empName\", designation_id as \"desigId\","
					+ "dsgn_desc as \"dsgnDesc\",pres_location_code as \"locCode\",c.loc_desc as \"locDesc\",father_name as \"fatherName\","
					+ "emp_birth_date as \"empDOB\",basic as \"basic\",staff_code as \"staffCode\",a.uan as \"uan\", "
					+ "comp_joining_date as \"comJoiningDate\",retirement_date as \"retirementDate\",emp_pan_no as \"empPan\","
					+ "r.emp_phone as \"empPhone\", r.emp_email as \"empEmail\",parent_zone as \"pzonecode\", REG_ID as \"regid\""
					+ "from pay_emp_mast a,pay_dsgn_mst b,com_loc_mst c,cpf_registered_users r "
					+ "where a.emp_num=:empNum and a.designation_id=b.dsgn_id and c.loc_id=pres_location_code  and a.emp_num=r.emp_num";

			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			System.out.print("QUERY=====" + hQuery);
			if (empNum != null) {
				hQuery.setParameter("empNum", empNum);
			}
			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				roleMappingBean.setREG_ID((Integer.parseInt(map.get("regid").toString())));
				roleMappingBean.setEmpName((map.get("empName").toString().trim()));
				roleMappingBean.setDesignation((map.get("dsgnDesc").toString().trim()));
				roleMappingBean.setPresentLocation((map.get("locDesc").toString().trim()));
				roleMappingBean.setEmpParentZone((map.get("pzonecode").toString().trim()));
				roleMappingBean.setPres_Loc_Code((map.get("locCode").toString().trim()));

				httpSession.setAttribute("locCode", map.get("locCode").toString());
				httpSession.setAttribute("desigId", map.get("desigId").toString());

			}
			// session.getTransaction().commit();
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return roleMappingBean;
	}

	@Override
	public List<DropdownDto> getAllLocationList() {
		List<DropdownDto> locList = new ArrayList<DropdownDto>();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "select loc_id as \"locId\", loc_desc as \"locDesc\" from com_loc_mst where loc_type_gbl in ('DO','RO','ZO','HQ') order by loc_desc asc";

		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = hQuery.list();
		for (Map<String, Object> map : list) {
			DropdownDto locDto = new DropdownDto();
			locDto.setAllLocKey(map.get("locId").toString());
			locDto.setAllLocValue(map.get("locDesc").toString());
			locList.add(locDto);
		}
		return locList;
	}

	@Override
	public List<DropdownDto> getAllParentLocationList() {
		List<DropdownDto> locList = new ArrayList<DropdownDto>();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String query = "select loc_id as \"locId\", loc_desc as \"locDesc\" from com_loc_mst where loc_type_gbl in ('ZO','HQ') order by loc_desc asc";

		Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = hQuery.list();

		for (Map<String, Object> map : list) {
			DropdownDto locDto = new DropdownDto();
			locDto.setCpfParentLocKey(map.get("locId").toString());
			locDto.setCpfParentLocValue(map.get("locDesc").toString());
			locList.add(locDto);
		}
		return locList;
	}

	@Override
	public RoleMappingRequest saveRoleMappingReq(RoleMappingRequest roleMappingRequest, Integer empNum) {
		RoleMappingRequest roleMappingReqForSave = new RoleMappingRequest();
		try {
			UserModel uModel = getUserModel();
			if(uModel!=null){
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			RoleMappingBean roleMappingBean = getRoleMappingBeanDetails(empNum.toString());
			RoleMappingBean existingRole = checkRoleByEmpNum(roleMappingRequest.getEMPID().toString());
			
			if(existingRole.getREG_ID()!=0 && existingRole.getEmpRole().equalsIgnoreCase(roleMappingRequest.getROLEASSIGNED())){
				roleMappingReqForSave.setINFO5("duplicate");
			} else {
				roleMappingReqForSave.setEMPID(new Integer(empNum));
				roleMappingReqForSave.setEMPNAME(roleMappingBean.getEmpName());
				roleMappingReqForSave.setDESIGNATION(roleMappingBean.getDesignation());
				if (roleMappingRequest.getEMPUNIT() == null || roleMappingRequest.getEMPUNIT().equals("")) {
					roleMappingReqForSave.setPRESLOCATION(new Integer(roleMappingBean.getPres_Loc_Code()));
				} else {
					roleMappingReqForSave.setPRESLOCATION(new Integer(roleMappingRequest.getEMPUNIT().trim()));
				}
				roleMappingReqForSave.setEMPUNIT(roleMappingRequest.getEMPUNIT());
				roleMappingReqForSave.setPARENTZONE(roleMappingRequest.getPARENTZONE());
				roleMappingReqForSave.setROLEASSIGNED(roleMappingRequest.getROLEASSIGNED());
				roleMappingReqForSave.setSTARTDATE(new Date());
				roleMappingReqForSave.setENDDATE(null);
				roleMappingReqForSave.setCREATED_DATE(new Date());
				roleMappingReqForSave.setCREATED_BY(empNum);
				session.persist(roleMappingReqForSave);

				logger.info("rollMappingForSave :::: " + roleMappingReqForSave.toString());

				String query = " update cpf_assigned_roles_dtl set ENDDATE =SYSDATE where REG_ID= :regId ";
				Query hQuery = session.createSQLQuery(query);
				hQuery.setParameter("regId", existingRole.getREG_ID());
				hQuery.executeUpdate();
				
				UserModel userModel = null;
				RegisteredUser roleList = new RegisteredUser();
				List<RegisteredUser> assignedRoleUserList = getRoleByEmpNum(empNum.toString().trim());
				roleList = assignedRoleUserList.get(0);

				/* Code support for multiple role assignment on single user at back-end but
				 * for front end support must be compatible on UI layer 
				userModel = new UserModel();
				String newRole = roleMappingRequest.getROLEASSIGNED();
				List<String> userRoleList = Arrays.asList(roleList.getUserRole().split(","));
				for (String i : userRoleList) {
					if (userRoleList.size() == 1 && i.equalsIgnoreCase("user")) {
						i = "";
						newRole = newRole + i;
					} else {
						newRole = newRole.concat("," + i);
					}
				}
				StringBuilder finalroles = new StringBuilder();
				Set<String> items = new HashSet<String>(Arrays.asList(newRole.split(",")));
				for (String i : items)
					finalroles = finalroles.append("," + i.trim());
				finalroles.deleteCharAt(0);
				 */
				Object o = session.load(RegisteredUser.class, roleMappingBean.getREG_ID());
				RegisteredUser rUpdateUser = (RegisteredUser) o;
				//rUpdateUser.setUserRole(finalroles.toString()); 
				rUpdateUser.setUserRole(roleMappingRequest.getROLEASSIGNED().toString().trim());
				session.update(rUpdateUser);
				logger.info("RegisteredUser Update as ::::: " + rUpdateUser.toString());

				String parentZone=null;
				if (roleMappingRequest.getROLEASSIGNED().equalsIgnoreCase("ADMIN")) {
					if(null!=roleMappingRequest.getPARENTZONE())
						parentZone=roleMappingRequest.getPARENTZONE().toString();
						List<ClaimStatusAssignToNewAdminDto> claimList = getClaimListByLocation(roleMappingRequest.getROLEASSIGNED(),roleMappingRequest.getEMPUNIT(),parentZone);

						for (ClaimStatusAssignToNewAdminDto cl : claimList) {
						CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();

						claimStatus.setREQUEST_ID(cl.getRequestId());
						claimStatus.setCLAIM_SUBMITTED_BY(cl.getClaimSubmittedBy());
						claimStatus.setCLAIM_SUBMITTED_DATE(cl.getClaimSubmittedDate());
						claimStatus.setADMIN_ACTION_TAKEN_BY(roleMappingRequest.getEMPID().toString());
						claimStatus.setSTATUS("1");

						session.persist(claimStatus);
						}
				}else if (roleMappingRequest.getROLEASSIGNED().equalsIgnoreCase("CPF_ADMIN")) {

					List<ClaimStatusAssignToNewAdminDto> claimList = getClaimListByLocation(roleMappingRequest.getROLEASSIGNED(),roleMappingBean.getPres_Loc_Code(),roleMappingRequest.getPARENTZONE().toString());

					for (ClaimStatusAssignToNewAdminDto cl : claimList) {
						CpfClaimRequestStatus claimStatus = new CpfClaimRequestStatus();

						claimStatus.setREQUEST_ID(cl.getRequestId());
						claimStatus.setCLAIM_SUBMITTED_BY(cl.getClaimSubmittedBy());
						claimStatus.setCLAIM_SUBMITTED_DATE(cl.getClaimSubmittedDate());
						
						claimStatus.setADMIN_ACTION(cl.getAdminAction());
						claimStatus.setADMIN_ACTION_TAKEN_BY(cl.getAdminActionTakenBy());
						claimStatus.setADMIN_ACTION_DATE(cl.getAdminActionDate());
						claimStatus.setADMIN_REMARKS(cl.getAdminRemarks());
						claimStatus.setADMIN_CLAIM_SUBMITTED_TO(roleMappingRequest.getEMPID().toString());
						claimStatus.setCPFSEC_ACTION_TAKEN_BY(roleMappingRequest.getEMPID().toString());
						claimStatus.setSTATUS("2");

						session.persist(claimStatus);
					}
				}
				roleMappingReqForSave.setINFO5("roleSuccess");
			}
			session.getTransaction().commit();
			}else{
				logger.info("Session Expired !!! Go for login ....");
			}
			return roleMappingReqForSave;
		} catch (Exception ex) {
			logger.info("Roll Mapping request not saved ......");
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		roleMappingReqForSave.setINFO5("failed");
		return null;
	}

	public List<ClaimStatusAssignToNewAdminDto> getClaimListByLocation(String roleName, String presentLoc, String parentZone) {
		List<ClaimStatusAssignToNewAdminDto> claimList = new ArrayList<ClaimStatusAssignToNewAdminDto>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
		Session session = sessionFactory.getCurrentSession();
		try {
			
			Set<String> finalLocCode = new HashSet<>();
			String query0="Select loc_desc as \"locDesc\", loc_type_gbl as \"locType\", loc_id as \"locId\" from com_loc_mst where parent_loc_id=:locCode";
			
			Query hQuery0 = session.createSQLQuery(query0).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (presentLoc != null) {
				hQuery0.setParameter("locCode", presentLoc);
			}
			List<Map<String, Object>> list0 = hQuery0.list();
			if(list0.size()>0){
			for (Map<String, Object> map : list0) {
				if(map.get("locType").toString().trim().equals("DP") || map.get("locType").toString().trim().equals("PO")){
					//finalLocCode= finalLocCode+','+map.get("locId").toString().trim();
					finalLocCode.add(map.get("locId").toString().trim());
				}else{
					finalLocCode.add(presentLoc);
				}
			}
			finalLocCode.add(presentLoc.trim());
			}

			String query =null;
			Query hQuery =null;
			if(null!=roleName && roleName.equalsIgnoreCase("ADMIN")){
				query = "select distinct cd.request_id as \"reqId\", cd.claim_submitted_by as \"claimSubBy\",cd.claim_submitted_date as \"claimSubDate\""
					+ " from cpf_claim_form_details cd, cpf_claim_form_status cs" 
					+ " where cd.request_id=cs.request_id"
					+ " and cd.present_location in (:presentLoc)" 
					+ " and cs.status=1";
				hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (presentLoc != null) {
					hQuery.setParameterList("presentLoc", finalLocCode);
				}
			}else if(null!=roleName && roleName.equalsIgnoreCase("CPF_ADMIN")){
				query = "select distinct cd.request_id as \"reqId\", cd.claim_submitted_by as \"claimSubBy\",cd.claim_submitted_date as \"claimSubDate\",cs.admin_action as \"adminAction\","
						+ "cs.admin_action_taken_by as \"adminActionTakenBy\", cs.admin_action_date as \"adminActionDate\", cs.admin_remarks as \"adminRemarks\""
						+ " from cpf_claim_form_details cd, cpf_claim_form_status cs" 
						+ " where cd.request_id=cs.request_id"
						+ " and cd.parent_zone=:parentZone" 
						+ " and cs.status=2";
				hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if(parentZone != null){
					hQuery.setParameter("parentZone", parentZone);
				}
			}

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				ClaimStatusAssignToNewAdminDto claimLt = new ClaimStatusAssignToNewAdminDto();
				claimLt.setRequestId(map.get("reqId").toString().trim());
				claimLt.setClaimSubmittedBy(map.get("claimSubBy").toString().trim());
				claimLt.setClaimSubmittedDate(format.parse(map.get("claimSubDate").toString().trim()));
				
				claimLt.setAdminAction(map.get("adminAction")!=null?map.get("adminAction").toString().trim():"");
				claimLt.setAdminActionTakenBy(map.get("adminActionTakenBy")!=null?map.get("adminActionTakenBy").toString().trim():"");
				claimLt.setAdminActionDate(map.get("adminActionDate")!=null?format.parse(map.get("adminActionDate").toString().trim()):null);
				claimLt.setAdminRemarks(map.get("adminRemarks")!=null?map.get("adminRemarks").toString().trim():"");

				claimList.add(claimLt);
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return claimList;
	}

	public RoleMappingBean checkRoleByEmpNum(String empNum) {
		
		Session session = sessionFactory.getCurrentSession();
		//String roleName = null;
		RoleMappingBean roleMappingBean= new RoleMappingBean();
		try {
			String query = "select ard.REG_ID as \"regID\", ard.roleassigned as \"roleName\" "
					+ "from cpf_assigned_roles_dtl ard "
					+ "where ard.empid=:empNum "
					+ "and ard.enddate is null";

			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", empNum);
			}

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				//roleName = map.get("roleName").toString();
				roleMappingBean.setEmpRole(map.get("roleName").toString());
				roleMappingBean.setREG_ID(Integer.parseInt(map.get("regID").toString().trim()));
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return roleMappingBean;
	}
	
	
	public List<RegisteredUser> getRoleByEmpNum(String empNum) {
		List<RegisteredUser> regRoleUserList = new ArrayList<RegisteredUser>();
		Session session = sessionFactory.getCurrentSession();
		try {
			String query = "select REG_ID as \"regId\" ,ROLE_NAME as \"roleName\" from cpf_registered_users r where r.emp_num=:empNum";

			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", new Integer(empNum));
			}

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				RegisteredUser regList = new RegisteredUser();
				regList.setUserRole(map.get("roleName").toString().trim());
				regList.setRegId(new Integer(map.get("regId").toString().trim()));
				regRoleUserList.add(regList);
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return regRoleUserList;
	}

	public String getEmpRoleByNum(String empNum) {
		Session session = sessionFactory.getCurrentSession();
		String Role = "";
		try {
			String query = "select ROLEASSIGNED as \"roleName\" from cpf_assigned_roles_dtl r where r.empid=:empNum and enddate is null";
			// select REG_ID,ROLEASSIGNED from FCIPAYROLL.cpf_assigned_roles_dtl
			// where empid=285640 and ROLEASSIGNED ='ADMIN' and enddate is null;
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (empNum != null) {
				hQuery.setParameter("empNum", new Integer(empNum));
			}

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				Role = map.get("roleName").toString().trim();
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
		return Role;
	}

	public List<RevokeRoleStatusDto> getAllAssignedRoleUser() {

		List<RevokeRoleStatusDto> listRevokeRoleStatusDto = new ArrayList<RevokeRoleStatusDto>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			String query = "select card.reg_id,card.empid as \"empnum\",pdm.dsgn_desc as \"designation\",card.empname as \"empname\", clm.loc_desc as \"place_of_posting\" ,"
					+ "card.roleassigned as \"assignedrole\" ,	to_date(card.startdate, 'dd-mm-yy') as \"startdate\" "
					+ "from fcipayroll.cpf_assigned_roles_dtl card, fcipayroll.pay_emp_mast pem, fcipayroll.pay_dsgn_mst pdm, fcipayroll.com_loc_mst clm"
					+ " where pdm.dsgn_id=pem.designation_id" + " and pem.pres_location_code=clm.loc_id"
					+ " and card.empid=pem.emp_num  and card.ENDDATE is null order by card.empid";

			System.out.print(query);
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
			DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			// RevokeRoleStatusDto revokeRoleStatusDto = new
			// RevokeRoleStatusDto();

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {
				RevokeRoleStatusDto revokeRoleStatusDto = new RevokeRoleStatusDto();
				
				if (map.get("REG_ID") == null)
					revokeRoleStatusDto.setRegId("0");
				else
					revokeRoleStatusDto.setRegId(map.get("REG_ID").toString());
				
				revokeRoleStatusDto.setEmpId(map.get("empnum").toString());
				revokeRoleStatusDto.setDesignation(map.get("designation").toString());
				revokeRoleStatusDto.setEmpName(map.get("empname").toString());
				revokeRoleStatusDto.setPresLocation(map.get("place_of_posting").toString());
				revokeRoleStatusDto.setAssignedRole(map.get("assignedrole").toString());
				revokeRoleStatusDto.setAssignedDate(myFormat.format(format.parse(map.get("startdate").toString().trim())));

				listRevokeRoleStatusDto.add(revokeRoleStatusDto);
			}
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRevokeRoleStatusDto;
	}

	public boolean revokeSelectedUserRole(String regId) {
		try {
			String empNum = null;
			String role = null;
			String presentLoc = null;
			String parentZone = null;
			
			UserModel uModel = getUserModel();
			if(uModel!=null){			
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			String query = " update cpf_assigned_roles_dtl set ENDDATE =SYSDATE where REG_ID= :regId ";
			Query hQuery = session.createSQLQuery(query);
			hQuery.setParameter("regId", regId);
			hQuery.executeUpdate();

			String query1 = "select EMPID as \"empNum\",ROLEASSIGNED as \"roleName\", PRESLOCATION as \"presentLoc\", PARENTZONE as \"parentZone\" "
					+ "from cpf_assigned_roles_dtl r " 
					+ "where r.REG_ID=:regId";

			Query hQuery1 = session.createSQLQuery(query1).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (regId != null) {
				hQuery1.setParameter("regId", new Integer(regId));
			}

			List<Map<String, Object>> list = hQuery1.list();
			for (Map<String, Object> map : list) {
				empNum = map.get("empNum").toString().trim();
				role = map.get("roleName").toString().trim();
				presentLoc = map.get("presentLoc").toString().trim();
				parentZone = map.get("parentZone")!=null?map.get("parentZone").toString().trim():"";
			}

			RegisteredUser roleList = new RegisteredUser();
			List<RegisteredUser> assignedRoleUserList = getRoleByEmpNum(empNum.toString().trim());
			roleList = assignedRoleUserList.get(0);
			String newRole = "";
			List<String> userRoleList = Arrays.asList(roleList.getUserRole().split(","));
			for (String i : userRoleList) {
				if (i.trim().equalsIgnoreCase(role.trim())) {
					i = "";
					newRole = newRole.concat(i);
				} else {

					newRole = newRole.concat("," + i);

				}
			}
			StringBuilder finalroles = new StringBuilder();
			Set<String> items = new HashSet<String>(Arrays.asList(newRole.trim().split(",")));
			for (String i : items)
				finalroles = finalroles.append(i.trim() + ",");
			finalroles.deleteCharAt(0);

			StringBuilder newFinalroles = new StringBuilder();
			List<String> newUserRoleList = Arrays.asList(finalroles.toString().split(","));
			for (String i : newUserRoleList)
				newFinalroles = newFinalroles.append("," + i.trim());
			newFinalroles.deleteCharAt(0);
			if (newFinalroles.length() == 0)
				newFinalroles.append("USER");
			System.out.println("newFinalroles=" + newFinalroles);

			Object o = session.load(RegisteredUser.class, roleList.getRegId());
			RegisteredUser rUpdateUser = (RegisteredUser) o;
			rUpdateUser.setUserRole(newFinalroles.toString()); 
			session.update(rUpdateUser);
			logger.info("rUpdateUser ::::: " + rUpdateUser.toString());

			if (role.equalsIgnoreCase("ADMIN")){
				
				String query2 = "select count(empid) as \"empIdCount\" from cpf_assigned_roles_dtl where preslocation=:presentLoc and roleassigned='ADMIN' and empid<>:empId and enddate is null";

				Query hQuery2 = session.createSQLQuery(query2).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (regId != null) {
					hQuery2.setParameter("presentLoc", presentLoc);
					hQuery2.setParameter("empId", empNum);
				}
				List<Map<String, Object>> list2 = hQuery2.list();
				if(list2.size()>0){
					deleteClaimListOnRoleRevoke(role, empNum, presentLoc, parentZone);
				}else{
					throw new Exception("Can't revoke this admin role, since there is no other admin at this location !!!");
				}	
			}else if (role.equalsIgnoreCase("CPF_ADMIN")){

				String query3 = "select count(empid) as \"empIdCount\" from cpf_assigned_roles_dtl where parentzone=:parentZone and roleassigned='CPF_ADMIN' and empid<>:empId and enddate is null";

				Query hQuery3 = session.createSQLQuery(query3).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (regId != null) {
					hQuery3.setParameter("parentZone", parentZone);
					hQuery3.setParameter("empId", empNum);
				}

				List<Map<String, Object>> list3 = hQuery3.list();
				if(list3.size()>0){
					deleteClaimListOnRoleRevoke(role, empNum, presentLoc, parentZone);
				}else{
					throw new Exception("Can't revoke this cpf_admin role, since there is no other cpf_admin at this location !!!");
				}
			}
			session.getTransaction().commit();
			}else{
				logger.info("Session Expired !!! Go for login ....");
			}
		} catch (Exception ex) {
			logger.info("Roll revoke request failed ......");
			session.getTransaction().rollback();
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteClaimListOnRoleRevoke(String role, String empNum, String presentLoc, String parentZone) {
		Session session = sessionFactory.getCurrentSession();
		String query=null;
		Query hQuery=null;
		try {
			if(null!=role && role.equalsIgnoreCase("ADMIN")){
				query = "select cs.status_id as \"statusId\", cd.request_id as \"reqId\", cd.claim_submitted_by as \"claimSubBy\",cd.claim_submitted_date as \"claimSubDate\""
						+ " from cpf_claim_form_details cd, cpf_claim_form_status cs" 
						+ " where cd.request_id=cs.request_id"
						/*+ " and cd.present_location=:presentLoc"*/ 
						+ " and cs.admin_action_taken_by=:empNum"
						+ " and cs.status=1";
	
				hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (presentLoc != null) {
					//hQuery.setParameter("presentLoc", presentLoc);
					hQuery.setParameter("empNum", empNum);
				}
			}else if(null!=role && role.equalsIgnoreCase("CPF_ADMIN")){
				query = "select cs.status_id as \"statusId\", cd.request_id as \"reqId\", cd.claim_submitted_by as \"claimSubBy\",cd.claim_submitted_date as \"claimSubDate\""
						+ " from cpf_claim_form_details cd, cpf_claim_form_status cs" 
						+ " where cd.request_id=cs.request_id"
						/*+ " and cd.parent_zone=:parentZone"*/ 
						+ " and cs.cpfsec_action_taken_by=:empNum"
						+ " and cs.status=2";

				hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				if (presentLoc != null) {
					/*hQuery.setParameter("parentZone", parentZone);*/
					hQuery.setParameter("empNum", empNum);
				}	
			}

			List<Map<String, Object>> list = hQuery.list();
			for (Map<String, Object> map : list) {

				Object object = session.load(CpfClaimRequestStatus.class, Integer.parseInt(map.get("statusId").toString()));
				session.delete(object);
			}
			return true;
		} catch (RuntimeException re) {
			logger.info("Find by example failed :::", re);
			throw re;
		}
	}

}
