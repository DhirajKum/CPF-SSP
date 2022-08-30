package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;


public class AssignToClaimDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String unitId;
	private String locationId;
	private String employeeId;
	private String cpfAdminId;
	private String remarks;
	
	
	
	public AssignToClaimDto(){
		
	}
	
	public AssignToClaimDto(String unitId, String locationId, String employeeId, String cpfAdminId, String remarks) {
		super();
		this.unitId = unitId;
		this.locationId = locationId;
		this.employeeId = employeeId;
		this.cpfAdminId = cpfAdminId;
		this.remarks = remarks;
		
		
	}
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCpfAdminId() {
		return cpfAdminId;
	}
	public void setCpfAdminId(String cpfAdminId) {
		this.cpfAdminId = cpfAdminId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	@Override
	public String toString() {
		return "AssignToClaimDto [unitId=" + unitId + ", locationId=" + locationId + ", employeeId=" + employeeId
				+ ", cpfAdminId=" + cpfAdminId + ", remarks=" + remarks + "]";
	}			
}
