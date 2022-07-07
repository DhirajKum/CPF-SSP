package com.techm.fci.cpf.dto;

public class DropdownDto {

	private String unitkey;
	private String unitValue;
	private String unitDesc;
	private String unitActive;
	
	private String locationKey;
	private String locationValue;
	private String locationDesc;
	private String locationActive;
	
	private String adminId;
	private String adminName;
	private String adminActive;
	
	private String cpfPurposeKey;
	private String cpfPurposeValue;
	
	private String cpfParentLocKey;
	private String cpfParentLocValue;
	
	private String allLocKey;
	private String allLocValue;
	private String cpfAdminId;
	private String cpfAdminName;
	
	public DropdownDto(){
		
	}
	
	public DropdownDto(String unitKey, String unitValue){
		this.unitkey=unitKey;
		this.unitValue=unitValue;
		this.cpfAdminId=unitKey;
		this.cpfAdminName=unitValue;
	}
	
	public DropdownDto(String locationKey, String locationDesc, String locationActive){
		this.locationKey=locationKey;
		this.locationDesc=locationDesc;
		this.locationActive=locationActive;
	}
	
	public String getUnitkey() {
		return unitkey;
	}
	public void setUnitkey(String unitkey) {
		this.unitkey = unitkey;
	}
	public String getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}
	public String getUnitDesc() {
		return unitDesc;
	}
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	public String getUnitActive() {
		return unitActive;
	}
	public void setUnitActive(String unitActive) {
		this.unitActive = unitActive;
	}
	public String getLocationKey() {
		return locationKey;
	}
	public void setLocationKey(String locationKey) {
		this.locationKey = locationKey;
	}
	public String getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}
	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	public String getLocationActive() {
		return locationActive;
	}
	public void setLocationActive(String locationActive) {
		this.locationActive = locationActive;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminActive() {
		return adminActive;
	}
	public void setAdminActive(String adminActive) {
		this.adminActive = adminActive;
	}

	public String getCpfPurposeKey() {
		return cpfPurposeKey;
	}

	public void setCpfPurposeKey(String cpfPurposeKey) {
		this.cpfPurposeKey = cpfPurposeKey;
	}

	public String getCpfPurposeValue() {
		return cpfPurposeValue;
	}

	public void setCpfPurposeValue(String cpfPurposeValue) {
		this.cpfPurposeValue = cpfPurposeValue;
	}

	public String getCpfParentLocKey() {
		return cpfParentLocKey;
	}

	public void setCpfParentLocKey(String cpfParentLocKey) {
		this.cpfParentLocKey = cpfParentLocKey;
	}

	public String getCpfParentLocValue() {
		return cpfParentLocValue;
	}

	public void setCpfParentLocValue(String cpfParentLocValue) {
		this.cpfParentLocValue = cpfParentLocValue;
	}

	public String getAllLocKey() {
		return allLocKey;
	}

	public void setAllLocKey(String allLocKey) {
		this.allLocKey = allLocKey;
	}

	public String getAllLocValue() {
		return allLocValue;
	}

	public void setAllLocValue(String allLocValue) {
		this.allLocValue = allLocValue;
	}
	
	public String getCpfAdminId() {
		return cpfAdminId;
	}

	public void setCpfAdminId(String cpfAdminId) {
		this.cpfAdminId = cpfAdminId;
	}

	public String getCpfAdminName() {
		return cpfAdminName;
	}

	public void setCpfAdminName(String cpfAdminName) {
		this.cpfAdminName = cpfAdminName;
	}	
	
}
