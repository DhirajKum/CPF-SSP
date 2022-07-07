package com.techm.fci.cpf.dao;

import java.util.List;

import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.dto.RoleMappingBean;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.RoleMappingRequest;

public interface RoleMappingDao {
	
	public RoleMappingBean getRoleMappingBeanDetails(String empNum);
	public List<DropdownDto> getAllParentLocationList();
	public List<DropdownDto> getAllLocationList();
	public	RoleMappingRequest saveRoleMappingReq(RoleMappingRequest roleMappingRequest, Integer empNum);
	public List<RegisteredUser> getRoleByEmpNum(String empNum);
	public List<RevokeRoleStatusDto> getAllAssignedRoleUser();
	public boolean revokeSelectedUserRole(String regId);

}
