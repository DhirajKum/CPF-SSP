package com.techm.fci.cpf.dto;

public class UserBean {
	private Long userId;	
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private Short userType;
	private String phoneNo;
	private String emailId;
	private Short locked;
	
	private Long detailsId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String photo;
	private Short roleType;
	private String userName;
	private String password;
	
		
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Short getRoleType() {
		return roleType;
	}
	public void setRoleType(Short roleType) {
		this.roleType = roleType;
	}
	public Long getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Long detailsId) {
		this.detailsId = detailsId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Short getUserType() {
		return userType;
	}
	public void setUserType(Short userType) {
		this.userType = userType;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Short getLocked() {
		return locked;
	}
	public void setLocked(Short locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", salutation=" + salutation + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", userType=" + userType + ", phoneNo="
				+ phoneNo + ", emailId=" + emailId + ", locked=" + locked + ", detailsId=" + detailsId
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state="
				+ state + ", country=" + country + ", pincode=" + pincode + ", photo=" + photo + "]";
	}
	
	
	
}
