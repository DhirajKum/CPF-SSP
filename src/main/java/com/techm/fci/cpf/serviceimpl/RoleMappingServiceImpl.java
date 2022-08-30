package com.techm.fci.cpf.serviceimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techm.fci.cpf.dao.RoleMappingDao;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.dto.RoleMappingBean;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.RoleMappingRequest;
import com.techm.fci.cpf.service.RoleMappingService;


@Service("roleMappingService")
@Transactional(readOnly = true)
public class RoleMappingServiceImpl implements RoleMappingService {
	@Autowired
	private RoleMappingDao roleMappingDao;
	
	public RoleMappingBean getRoleMappingBeanDetails(String empNum){
		return roleMappingDao.getRoleMappingBeanDetails(empNum);
	}
	public List<DropdownDto> getAllParentLocationList(){
		return roleMappingDao.getAllParentLocationList();
	}
	public List<DropdownDto> getAllLocationList(){
		return roleMappingDao.getAllLocationList();
	}
	public	RoleMappingRequest saveRoleMappingReq(RoleMappingRequest roleMappingRequest, Integer empNum){
		return roleMappingDao.saveRoleMappingReq(roleMappingRequest, empNum);
	}
	
	public List<RegisteredUser> getRoleByEmpNum(String empNum){
		return roleMappingDao.getRoleByEmpNum(empNum);
	}
	
	public List<RevokeRoleStatusDto> getAllAssignedRoleUser(){
		
		return roleMappingDao.getAllAssignedRoleUser();
	}
	
	public boolean revokeSelectedUserRole(String regId){
		
		return roleMappingDao.revokeSelectedUserRole(regId);
	}
	
}
