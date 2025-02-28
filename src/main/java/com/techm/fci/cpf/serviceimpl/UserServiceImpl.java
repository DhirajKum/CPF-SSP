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

import com.techm.fci.cpf.dao.ClaimRequestDao;
import com.techm.fci.cpf.dao.EmployeeDao;
import com.techm.fci.cpf.dao.UserDao;
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
import com.techm.fci.cpf.service.UserService;

 
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private EmployeeDao empDao;
 
    @Autowired
    private ClaimRequestDao claimReqDao;
    
    @Override
    public RegisteredUser findById(int id) {
        return userDao.findById(id);
    }
 
    @Override
    public RegisteredUser findByEmpNum(String empNum) {
        return userDao.findByEmpNum(empNum);
    }
    
    @Override
    public RegisteredUser getRegUserDetailsbyMobile(String mobile){
    	return userDao.getRegUserDetailsbyMobile(mobile);
    }
    
    @Override
	public HomeDto getHomePageData(String empNum) {
		return empDao.getHomePageData(empNum);
	}
    
    @Override
    public EmpMaster getEmpDetailsByEmpNum(String empNum) {
        return empDao.getEmpDetailsByEmpNum(empNum);
    }
    
    @Override
    public Boolean saveRegData(RegisteredUser regUser) {
        return empDao.saveRegUser(regUser);
    }

	@Override
	public RegisteredUser checkEmpInRegisteredUserByEmpNum(String empNum) {
		return userDao.findByEmpNum(empNum);
	}

	@Override
	public ActClaimDto getBasicDetailsForClaim(String empNum){
		return claimReqDao.getBasicDetailsForClaim(empNum);
	}
	
	@Override
	public List<CpfClaimRequest> empClaimLookup(String empNum){
		return claimReqDao.empClaimLookup(empNum);
	}
	
	@Override
	public CpfClaimRequest saveClaimData(CpfClaimRequest claimRequest, String empNum, String locCode, String roleName) {
		return claimReqDao.saveClaimReq(claimRequest,empNum,locCode,roleName);
	}
	
	@Override
	public Boolean saveCpfClaimHistoryTrail(ClaimHistoryTrailDto claimHistoryTrailData, String empNum, String roleName) {
		return claimReqDao.saveCpfClaimHistory(claimHistoryTrailData, empNum, roleName);
	}

	@Override
	public List<ClaimRequestStatusDto> getMyAllPendingReq(String empNum, String roleName) {
		return claimReqDao.getMyAllPendingClaimReq(empNum,roleName);
	}
	
	@Override
	public List<ClaimRequestStatusDto> getAllPendingReq(String reqType,String empNum, String roleName) {
		return claimReqDao.getAllPendingClaimReq(reqType,empNum,roleName);
	}
	
	@Override
	public List<ClaimHistoryTrailDto> getClaimHistoryTrail(String reqId, String empNum, String roleName) {
		return claimReqDao.getClaimHistoryTrailDetail(reqId,empNum,roleName);
	}
	
	/*@Override
	public CpfClaimRequest getClaimReqDetails(String reqId){
		return claimReqDao.getClaimReqDetails(reqId);
	}*/
	
	@Override
	public ActClaimDto getClaimReqDetails(String reqId){
		return claimReqDao.getClaimReqDetails(reqId);
	}
	
	@Override
	public Boolean checkInputData(ActClaimDto actClaimDto) {
		return claimReqDao.checkInputData(actClaimDto);
	}
	
	@Override
	public Boolean updateClaimReq(ActClaimDto actClaimDto, String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone) {
		return claimReqDao.updateClaimReq(actClaimDto,reqType,reqId,empNum,empRole,locCode,parentZone);
	}

	@Override
	public Boolean reApplyClaimReq(ActClaimDto actClaimDto, String reqType, String reqId, String empNum, String empRole, String locCode, String parentZone) {
		return claimReqDao.reApplyClaimReq(actClaimDto,reqType,reqId,empNum,empRole,locCode,parentZone);
	}
	
	@Override
	public Boolean rejectClaimReq(String remarks, String reqType, String reqId, String empNum, String empRole, String casteDisp) {
		return claimReqDao.rejectClaimReq(remarks,reqType,reqId,empNum,empRole,casteDisp);
	}

	@Override
	public List<ClaimRequestStatusDto> getAllApprovedReq(String empNum, String roleName) {
		return claimReqDao.getAllApprovedClaimReq(empNum,roleName);
	}

	@Override
	public List<ClaimRequestStatusDto> getAllCompletedReq(String empNum, String roleName) {
		return claimReqDao.getCompletedClaimReq(empNum,roleName);
	}

	@Override
	public List<DropdownDto> getUnitList(String locId) {
		return claimReqDao.getAllUnitList(locId);
	}

	@Override
	public List<DropdownDto> getLocationList(String unitId) {
		return claimReqDao.getAllLocationList(unitId);
	}

	@Override
	public List<DropdownDto> getAdminList(String locId,String empNum) {
		return claimReqDao.getAllAdminList(locId,empNum);
	}

	@Override
	public List<DropdownDto> getCPFAdminByParentZone(String parentZone, String userRole, String empNum) {
		return claimReqDao.getCPFAdminByParentZone(parentZone,userRole,empNum);
	}
	
	@Override
	public Boolean assignToClaimReq(AssignToClaimDto assignToClaimDto, ActClaimDto actClaimDto, String reqType, String empNum, String empRole) {
		return claimReqDao.assignToClaimReq(assignToClaimDto,actClaimDto,reqType,empNum,empRole);
	}
	
	@Override
	public List<DropdownDto> getPurposeOfCPF(String radioValue) {
		return claimReqDao.getPurposeOfCPFList(radioValue);
	}

	@Override
	public boolean changePassword(String newPassword, String empNum) {

		return empDao.changePassword(newPassword, empNum);
	}

	@Override
	public Boolean saveEmpKycDoc(UserModel uModel, String path) {
		return empDao.saveKycDoc(uModel,path);
	}
	
	@Override
	public Boolean deleteEmpOtherDoc(UserModel uModel, String claimSubEmpID, String fileType, String reqId) {
		return empDao.deleteOtherDoc(uModel, claimSubEmpID, fileType, reqId);
	}
	
	@Override
	public Boolean saveEmpOtherDoc(UserModel uModel, String claimSubEmpID, String fileType, String reqId, String claimAppliedFor, String path) {
		return empDao.saveOtherDoc(uModel, claimSubEmpID, fileType, reqId, claimAppliedFor, path);
	}
	
	@Override
	public void insertOtpToSmsTable(String empNum,String mobile){
		empDao.insertOtpToSmsTable(empNum,mobile);
	}

	@Override
	public String getClaimStatus(String requestId) {
		return claimReqDao.getClaimStatus(requestId);
	}
	
	@Override
	public ClaimRequestStatusDto getClaimReqStatus(String requestId) {
		return claimReqDao.getClaimReqStatus(requestId);
	}

	@Override
	public String getMaxPermAmount(String empId, String sancType) {
		return claimReqDao.getMaxPermAmount(empId, sancType);
	}

	@Override
	public String getUploadedPath(String pathId, String fileType) {
		return empDao.getUploadedPath(pathId, fileType);
	}

	@Override
	public Boolean updateEmpOtherDoc(UserModel uModel, CpfClaimRequest cpfClaimReq) {
		return empDao.updateOtherDoc(uModel, cpfClaimReq);
	}
	
	@Override
	public Boolean checkTempAdvApplyAbility(String empNum) {
		return claimReqDao.checkTempAdvApplyAbility(empNum);
	}

	@Override
	public List<SavedClaimConditionCheckDto> checkSavedClaimStatus(String empNum, String claimType) {
		return claimReqDao.checkSavedClaimStatus(empNum, claimType);
	}

}