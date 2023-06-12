package com.techm.fci.cpf.service;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.List;

import com.techm.fci.cpf.dto.ActClaimDto;
import com.techm.fci.cpf.dto.AssignToClaimDto;
import com.techm.fci.cpf.dto.ClaimHistoryTrailDto;
import com.techm.fci.cpf.dto.ClaimRequestStatusDto;
import com.techm.fci.cpf.dto.SavedClaimConditionCheckDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.HomeDto;
import com.techm.fci.cpf.model.CpfClaimRequest;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.UserModel;
 
public interface UserService {
 
	public RegisteredUser findById(int id);
     
	public RegisteredUser findByEmpNum(String empNum);
	
	public RegisteredUser getRegUserDetailsbyMobile(String mobile);
	
	public EmpMaster getEmpDetailsByEmpNum(String empNum);
	
	public HomeDto getHomePageData(String empNum);
	
	public RegisteredUser checkEmpInRegisteredUserByEmpNum(String empNum);
	
	public Boolean saveRegData(RegisteredUser regUser);
    
	public ActClaimDto getBasicDetailsForClaim(String empNum);
	
	public List<CpfClaimRequest> empClaimLookup(String empNum);
	
	public CpfClaimRequest saveClaimData(CpfClaimRequest claimRequest, String empNum, String locCode, String roleName);
	
	public List<ClaimRequestStatusDto> getMyAllPendingReq(String empNum, String roleName);
	
	public List<ClaimRequestStatusDto> getAllPendingReq(String reqType, String empNum, String roleName);
	
	public List<ClaimHistoryTrailDto> getClaimHistoryTrail(String reqId, String empNum, String roleName);
	
	//public CpfClaimRequest getClaimReqDetails(String empNum);
	public ActClaimDto getClaimReqDetails(String reqId);
	
	public Boolean checkInputData(ActClaimDto actClaimDto);
	
	public Boolean updateClaimReq(ActClaimDto actClaimDto,String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone);
	
	public Boolean reApplyClaimReq(ActClaimDto actClaimDto,String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone);
	
	public Boolean rejectClaimReq(String remarks,String reqType, String reqId, String empNum, String empRole, String casteDisp);
	
	public List<ClaimRequestStatusDto> getAllApprovedReq(String empNum, String roleName);
	
	public List<ClaimRequestStatusDto> getAllCompletedReq(String empNum, String roleName);
	
	public List<DropdownDto> getUnitList(String locId);
	
	public List<DropdownDto> getLocationList(String unitId);
	
	public List<DropdownDto> getAdminList(String locId,String empNum);
	
	public List<DropdownDto> getCPFAdminByParentZone(String parentZone, String userRole, String empNum);
	
	public Boolean assignToClaimReq(AssignToClaimDto assignToClaimDto, ActClaimDto actClaimDto, String reqType, String empNum, String empRole);
	
	public List<DropdownDto> getPurposeOfCPF(String radioValue);
	
	boolean changePassword(String newPassword, String empNum);
	
	public Boolean saveEmpKycDoc(UserModel uModel, String path);
	
	public Boolean deleteEmpOtherDoc(UserModel uModel,String claimSubEmpID, String reqId);
	
	public Boolean saveEmpOtherDoc(UserModel uModel,String claimSubEmpID, String reqId, String claimAppliedFor, String path);
	public Boolean updateEmpOtherDoc(UserModel uModel, CpfClaimRequest cpfClaimReq);
	
	public void insertOtpToSmsTable(String empNum,String mobile);
	
	public String getClaimStatus(String requestId);
	public ClaimRequestStatusDto getClaimReqStatus(String requestId);
	public Boolean saveCpfClaimHistoryTrail(ClaimHistoryTrailDto claimHistoryTrailData, String empNum, String roleName);
	public String getMaxPermAmount(String empId, String sancType);
	
	public String getUploadedPath(String pathId, String fileType);
	public Boolean checkTempAdvApplyAbility(String empNum);
	public List<SavedClaimConditionCheckDto> checkSavedClaimStatus(String empNum, String claimType);
}