package com.techm.fci.cpf.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="cpf_doc_uploads")
public class DocumentsUpload implements Serializable {

	private static final long serialVersionUID = 7504370528765099382L;
	
	private int doc_id;
	private String emp_num;
	private String request_id;
	private String file_type;
	private String emp_phone;
	private String emp_email;
	private String role_name;
	private String file_path;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String info_1;
	private String info_2;
	private String info_3;
	private String info_4;
	private String info_5;
	
	public DocumentsUpload(){
		
	}
	
	public DocumentsUpload(int doc_id, String emp_num, String request_id, String file_type, String emp_phone,
			String emp_email, String role_name, String file_path, String created_by, Date created_date,
			String modified_by, Date modified_date, String info_1, String info_2, String info_3, String info_4,
			String info_5) {
		super();
		this.doc_id = doc_id;
		this.emp_num = emp_num;
		this.request_id = request_id;
		this.file_type = file_type;
		this.emp_phone = emp_phone;
		this.emp_email = emp_email;
		this.role_name = role_name;
		this.file_path = file_path;
		this.created_by = created_by;
		this.created_date = created_date;
		this.modified_by = modified_by;
		this.modified_date = modified_date;
		this.info_1 = info_1;
		this.info_2 = info_2;
		this.info_3 = info_3;
		this.info_4 = info_4;
		this.info_5 = info_5;
	}

	@Id
	@SequenceGenerator(name="cpfDocSeq", sequenceName="cpf_doc_uploads_seq", allocationSize=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpfDocSeq" )
	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getEmp_num() {
		return emp_num;
	}

	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getInfo_1() {
		return info_1;
	}

	public void setInfo_1(String info_1) {
		this.info_1 = info_1;
	}

	public String getInfo_2() {
		return info_2;
	}

	public void setInfo_2(String info_2) {
		this.info_2 = info_2;
	}

	public String getInfo_3() {
		return info_3;
	}

	public void setInfo_3(String info_3) {
		this.info_3 = info_3;
	}

	public String getInfo_4() {
		return info_4;
	}

	public void setInfo_4(String info_4) {
		this.info_4 = info_4;
	}

	public String getInfo_5() {
		return info_5;
	}

	public void setInfo_5(String info_5) {
		this.info_5 = info_5;
	}

	@Override
	public String toString() {
		return "DocumentsUpload [doc_id=" + doc_id + ", emp_num=" + emp_num + ", request_id=" + request_id
				+ ", file_type=" + file_type + ", emp_phone=" + emp_phone + ", emp_email=" + emp_email + ", role_name="
				+ role_name + ", file_path=" + file_path + ", created_by=" + created_by + ", created_date="
				+ created_date + ", modified_by=" + modified_by + ", modified_date=" + modified_date + ", info_1="
				+ info_1 + ", info_2=" + info_2 + ", info_3=" + info_3 + ", info_4=" + info_4 + ", info_5=" + info_5
				+ "]";
	}
	
}
