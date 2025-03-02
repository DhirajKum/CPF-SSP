package com.techm.fci.cpf.dao;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import com.techm.fci.cpf.dto.HomeDto;
import com.techm.fci.cpf.model.CpfClaimRequest;
import com.techm.fci.cpf.model.CpfOtpMaster;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.UserModel;

public interface EmployeeDao {
	
	HomeDto getHomePageData(String empNum);
	
	EmpMaster getEmpDetailsByEmpNum(String empNum);
	
	Boolean saveRegUser(RegisteredUser regUser);
	
	CpfOtpMaster getOtpData(String mobile);
	
	Boolean addOtpData(String mobile, String otp, String expiryTime);
	
	Boolean addOtpData(String mobile, String otp, String expiryTime, String userName, String pin, String empNum, String message, String signature, String del_by_smsportal, String dlt_entity_id, String dlt_template_id);
	
	Boolean updateOtpData(String mobile, String otp, String expiryTime);
	
	Boolean deleteExpiredOtp(int otpID);
	
	Boolean changePassword(String newPass, String empNum);
	
	Boolean saveKycDoc(UserModel uModel, String path);
	
	Boolean deleteOtherDoc(UserModel uModel, String claimSubEmpID, String fileType, String reqId);
	
	Boolean saveOtherDoc(UserModel uModel, String claimSubEmpID, String fileType, String reqId, String claimAppliedFor, String path);
	
	Boolean updateOtherDoc(UserModel uModel, CpfClaimRequest cpfClaimReq);
	
	public void insertOtpToSmsTable(String empNum,String mobile);
	
	public String getUploadedPath(String pathId, String fileType);
}
