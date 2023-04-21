package com.techm.fci.cpf.dao;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.List;
import java.util.Map;

import com.techm.fci.cpf.dto.ActClaimDto;
import com.techm.fci.cpf.dto.AssignToClaimDto;
import com.techm.fci.cpf.dto.ClaimHistoryTrailDto;
import com.techm.fci.cpf.dto.ClaimRequestStatusDto;
import com.techm.fci.cpf.dto.SavedClaimConditionCheckDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.model.CpfClaimRequest;

public interface ClaimRequestDao {

	//public CpfClaimRequest findById(int id);
	public ActClaimDto getBasicDetailsForClaim(String empNum);
	public List<CpfClaimRequest> empClaimLookup(String empNum);
	public CpfClaimRequest saveClaimReq(CpfClaimRequest claimReq, String empNum, String locCode, String roleName);
	public List<ClaimRequestStatusDto> getMyAllPendingClaimReq(String empNum,String roleName);
	public List<ClaimRequestStatusDto> getAllPendingClaimReq(String reqType,String empNum,String roleName);
	public List<ClaimHistoryTrailDto> getClaimHistoryTrailDetail(String reqId,String empNum,String roleName);
	//public CpfClaimRequest getClaimReqDetails(String empNum);
	
	public ActClaimDto getClaimReqDetails(String reqId);
	public Boolean checkInputData(ActClaimDto actClaimDto);
	public Boolean updateClaimReq(ActClaimDto actClaimDto,String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone);
	public Boolean rejectClaimReq(String remarks,String reqType, String reqId, String empNum, String empRole, String casteDisp);
	public List<ClaimRequestStatusDto> getAllApprovedClaimReq(String empNum,String roleName);
	public List<ClaimRequestStatusDto> getCompletedClaimReq(String empNum,String roleName);
	
	public List<DropdownDto> getAllUnitList(String locId);
	public List<DropdownDto> getAllLocationList(String unitId);
	public List<DropdownDto> getAllAdminList(String locId,String empNum);
	public List<DropdownDto> getCPFAdminByParentZone(String parentZone,String userRole,String empNum);
	public Boolean assignToClaimReq(AssignToClaimDto assignToClaimDto, ActClaimDto actClaimDto, String reqType, String empNum, String empRole);
	
	public List<DropdownDto> getPurposeOfCPFList(String radioValue);
	public Map<String, Object> getCustomeRequestId();
	
	public List<ClaimRequestStatusDto> getClaimReqStatus(String reqId);
	public String getClaimStatus(String reqId);
	
	public boolean saveCpfClaimHistory(ClaimHistoryTrailDto claimHistoryTrailData, String empNum, String roleName);
	
	public String getMaxPermAmount(String empId, String sancType);
	public Boolean checkTempAdvApplyAbility(String empNum);
	public List<SavedClaimConditionCheckDto> checkSavedClaimStatus(String empNum, String claimType);
}
