package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;
import java.util.Date;

public class CpfSlipIntCBCreditAsOnDataDto implements Serializable {
	
	private static final long serialVersionUID = -4478384183024478327L;

	private String intMatching;
	private String intComl;
	
	private String cbMatching;
	private String cbComl;
	
	private Date balToCreditAsOn;

	public CpfSlipIntCBCreditAsOnDataDto(){
		
	}
	
	public CpfSlipIntCBCreditAsOnDataDto(String intMatching, String intComl, String cbMatching, String cbComl,
			Date balToCreditAsOn) {
		super();
		this.intMatching = intMatching;
		this.intComl = intComl;
		this.cbMatching = cbMatching;
		this.cbComl = cbComl;
		this.balToCreditAsOn = balToCreditAsOn;
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

	public String getCbMatching() {
		return cbMatching;
	}

	public void setCbMatching(String cbMatching) {
		this.cbMatching = cbMatching;
	}

	public String getCbComl() {
		return cbComl;
	}

	public void setCbComl(String cbComl) {
		this.cbComl = cbComl;
	}

	public Date getBalToCreditAsOn() {
		return balToCreditAsOn;
	}

	public void setBalToCreditAsOn(Date balToCreditAsOn) {
		this.balToCreditAsOn = balToCreditAsOn;
	}

	@Override
	public String toString() {
		return "CpfSlipIntCBCreditAsOnDataDto [intMatching=" + intMatching + ", intComl=" + intComl + ", cbMatching="
				+ cbMatching + ", cbComl=" + cbComl + ", balToCreditAsOn=" + balToCreditAsOn + "]";
	}
}
