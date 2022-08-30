package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;

public class CpfSlipReportDto implements Serializable {
	
	private static final long serialVersionUID = -4608210626045556909L;

	private String empNum;
	private String fromYear;
	private String toYear;
	
	public CpfSlipReportDto(){
		
	}
	
	public CpfSlipReportDto(String empNum, String fromYear, String toYear) {
		super();
		this.empNum = empNum;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	@Override
	public String toString() {
		return "CpfSlipReportDto [empNum=" + empNum + ", fromYear=" + fromYear + ", toYear=" + toYear + "]";
	}
	
}
