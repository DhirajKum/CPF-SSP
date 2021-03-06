package com.techm.fci.cpf.service;

import java.util.List;

import com.techm.fci.cpf.dto.ClaimRequestStatusDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.dto.RoleMappingBean;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.RoleMappingRequest;

public interface RoleMappingService {
	public RoleMappingBean getRoleMappingBeanDetails(String empNum);
	public List<DropdownDto> getAllParentLocationList();
	public List<DropdownDto> getAllLocationList();
	public List<RegisteredUser> getRoleByEmpNum(String empNum);
	public	RoleMappingRequest saveRoleMappingReq(RoleMappingRequest roleMappingRequest, Integer empNum);
	public List<RevokeRoleStatusDto> getAllAssignedRoleUser();
	public boolean revokeSelectedUserRole(String regId);
	
}
