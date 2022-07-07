package com.techm.fci.cpf.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="PAY_TEST_DBUSER")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private int uan;
	private String username;
	private String roleName;
	private String password;
	private String state;
	private String createdBy;
	private Date createdDate;

	public User() {
	}

	public User(int userId, int uan, String username, String roleName, String password, String state, String createdBy,
			Date createdDate) {
		super();
		this.userId = userId;
		this.uan = uan;
		this.username = username;
		this.roleName = roleName;
		this.password = password;
		this.state = state;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}	
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 5, scale = 0)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "UAN", unique = true, nullable = false, length = 10)
	public int getUan() {
		return uan;
	}

	public void setUan(int uan) {
		this.uan = uan;
	}

	
	@Column(name = "USERNAME", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "ROLE_NAME", nullable = false, length = 20)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "PASSWORD", nullable = false, length = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Column(name = "STATE", nullable = false, length = 10)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 20)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
