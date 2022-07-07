package com.techm.fci.cpf.dto;

import java.io.Serializable;
import java.util.Date;

public class CpfSlipIntOBCreditAsOnDataDto implements Serializable {
	
	private static final long serialVersionUID = -4478384183024478327L;

	private String intMatching;
	private String intComl;
	private String intVPF;
	
	private String obMatching;
	private String obComl;
	private String obVPF;
	
	private Date balToCreditAsOnDt;
	private String balToCreditAsOnAmt;

	public CpfSlipIntOBCreditAsOnDataDto(){
		
	}

	public CpfSlipIntOBCreditAsOnDataDto(String intMatching, String intComl, String intVPF, String obMatching,
			String obComl, String obVPF, Date balToCreditAsOnDt, String balToCreditAsOnAmt) {
		super();
		this.intMatching = intMatching;
		this.intComl = intComl;
		this.intVPF = intVPF;
		this.obMatching = obMatching;
		this.obComl = obComl;
		this.obVPF = obVPF;
		this.balToCreditAsOnDt = balToCreditAsOnDt;
		this.balToCreditAsOnAmt = balToCreditAsOnAmt;
	}

	public String getIntMatching() {
		return intMatching;
	}

	public void setIntMatching(String intMatching) {
		this.intMatching = intMatching;
	}

	public String getIntComl() {
		return intComl;
	}

	public void setIntComl(String intComl) {
		this.intComl = intComl;
	}

	public String getIntVPF() {
		return intVPF;
	}

	public void setIntVPF(String intVPF) {
		this.intVPF = intVPF;
	}

	public String getObMatching() {
		return obMatching;
	}

	public void setObMatching(String obMatching) {
		this.obMatching = obMatching;
	}

	public String getObComl() {
		return obComl;
	}

	public void setObComl(String obComl) {
		this.obComl = obComl;
	}

	public String getObVPF() {
		return obVPF;
	}

	public void setObVPF(String obVPF) {
		this.obVPF = obVPF;
	}

	public Date getBalToCreditAsOnDt() {
		return balToCreditAsOnDt;
	}

	public void setBalToCreditAsOnDt(Date balToCreditAsOnDt) {
		this.balToCreditAsOnDt = balToCreditAsOnDt;
	}

	public String getBalToCreditAsOnAmt() {
		return balToCreditAsOnAmt;
	}

	public void setBalToCreditAsOnAmt(String balToCreditAsOnAmt) {
		this.balToCreditAsOnAmt = balToCreditAsOnAmt;
	}

	@Override
	public String toString() {
		return "CpfSlipIntOBCreditAsOnDataDto [intMatching=" + intMatching + ", intComl=" + intComl + ", intVPF="
				+ intVPF + ", obMatching=" + obMatching + ", obComl=" + obComl + ", obVPF=" + obVPF
				+ ", balToCreditAsOnDt=" + balToCreditAsOnDt + ", balToCreditAsOnAmt=" + balToCreditAsOnAmt + "]";
	}

}
