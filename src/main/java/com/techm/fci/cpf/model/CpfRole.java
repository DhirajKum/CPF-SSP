package com.techm.fci.cpf.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cpf_role")
public class CpfRole implements java.io.Serializable{

	private static final long serialVersionUID = 9100137759002960724L;

	private int roleId;
	private String roleName;
	private String createBy;
	private Date createDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	
	public CpfRole(){
		
	}
		
	public CpfRole(int roleId, String roleName, String createBy, Date createDate, String modifiedBy, Date modifiedDate) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createBy = createBy;
		this.createDate = createDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
		
}
