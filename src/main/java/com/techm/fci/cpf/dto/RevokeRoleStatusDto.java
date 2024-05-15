package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;

public class RevokeRoleStatusDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String regId;
	private String empId;
	private String designation;
	private String empName;
	private String presLocation;
	//private String roleAssignedLocation;
	private String assignedRole;
	private String assignedDate;
	
	
	public RevokeRoleStatusDto() {

	}
	
	public RevokeRoleStatusDto(String regId, String empId, String designation, String empName, String presLocation,
			String assignedRole, String assignedDate) {
		super();
		this.regId = regId;
		this.empId = empId;
		this.designation = designation;
		this.empName = empName;
		this.presLocation = presLocation;
		this.assignedRole = assignedRole;
		this.assignedDate = assignedDate;
	}

	/*
	 * public RevokeRoleStatusDto(String regId, String empId, String designation,
	 * String empName, String presLocation, String roleAssignedLocation, String
	 * assignedRole, String assignedDate) { super(); this.regId = regId; this.empId
	 * = empId; this.designation = designation; this.empName = empName;
	 * this.presLocation = presLocation; this.roleAssignedLocation =
	 * roleAssignedLocation; this.assignedRole = assignedRole; this.assignedDate =
	 * assignedDate; }
	 */
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPresLocation() {
		return presLocation;
	}
	public void setPresLocation(String presLocation) {
		this.presLocation = presLocation;
	}
	public String getAssignedRole() {
		return assignedRole;
	}
	public void setAssignedRole(String assignedRole) {
		this.assignedRole = assignedRole;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	@Override
	public String toString() {
		return "RevokeRoleStatusDto [regId=" + regId + ", empId=" + empId + ", designation=" + designation
				+ ", empName=" + empName + ", presLocation=" + presLocation + ", assignedRole=" + assignedRole
				+ ", assignedDate=" + assignedDate + "]";
	}
	
}
