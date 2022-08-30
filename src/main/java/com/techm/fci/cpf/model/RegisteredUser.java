package com.techm.fci.cpf.model;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cpf_registered_users")
public class RegisteredUser implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int regId;
	private String uan;
	private String empNum;
	private String empName;
	private String empEmail;
	private String empPhone;
	//@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private String userRole;
	private String state;
	private String password;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Date lastLoginDate;
	private String info1;
	private String info2;
	private String info3;
	private String info4;
	private String info5;
	
	public RegisteredUser(){
		
	}
	
	
	public RegisteredUser(int regId, String uan, String empNum, String empName, String empEmail, String empPhone,
			String userRole, String state, String password, String createdBy, Date createdDate, String modifiedBy,
			Date modifiedDate, Date lastLoginDate, String info1, String info2, String info3, String info4,
			String info5) {
		super();
		this.regId = regId;
		this.uan = uan;
		this.empNum = empNum;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPhone = empPhone;
		this.userRole = userRole;
		this.state = state;
		this.password = password;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.lastLoginDate = lastLoginDate;
		this.info1 = info1;
		this.info2 = info2;
		this.info3 = info3;
		this.info4 = info4;
		this.info5 = info5;
	}

	@Id
	@SequenceGenerator(name="cpfSeq", sequenceName="cpf_registered_users_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpfSeq" )
	@Column(name = "REG_ID", unique = true, nullable = false, precision = 5, scale = 0)
	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	@Column(name = "UAN", nullable = false, length = 20)
	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}
	
	@Column(name = "EMP_NUM", nullable = false, length = 10)
	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	@Column(name = "EMP_NAME", nullable = false, length = 100)
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name = "EMP_EMAIL", nullable = false, length = 100)
	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	@Column(name = "EMP_PHONE", nullable = false, length = 15)
	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	
	@Column(name = "ROLE_NAME", nullable = false, length = 5)
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Column(name = "STATE", nullable = false, length = 10)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "CREATED_BY", nullable = false, length = 20)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 20)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "MODIFIED_BY", nullable = false, length = 20)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", nullable = false, length = 20)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "LAST_LOGIN_DATE", nullable = false, length = 20)
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Column(name = "INFO_1", nullable = false, length = 15)
	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	@Column(name = "INFO_2", nullable = false, length = 15)
	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	@Column(name = "INFO_3", nullable = false, length = 15)
	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	@Column(name = "INFO_4", nullable = false, length = 15)
	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	@Column(name = "INFO_5", nullable = false, length = 15)
	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	@Override
	public String toString() {
		return "RegisteredUser [regId=" + regId + ", uan=" + uan + ", empNum=" + empNum + ", empName=" + empName
				+ ", empEmail=" + empEmail + ", empPhone=" + empPhone + ", userRole=" + userRole + ", state=" + state
				+ ", password=" + password + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", lastLoginDate=" + lastLoginDate
				+ ", info1=" + info1 + ", info2=" + info2 + ", info3=" + info3 + ", info4=" + info4 + ", info5=" + info5
				+ "]";
	}
	
}
