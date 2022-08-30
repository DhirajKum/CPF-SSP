package com.techm.fci.cpf.dto;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;

public class CpfSlipMonthWiseDto implements Serializable {

	
	private static final long serialVersionUID = 1551371009327336167L;

	private String month;
	private String fciMatching;
	private String fciEPS;
	private String fciAmtWD;
	private String memCompl;
	private String memAdvTaken;
	private String memAdvRef;
	private String memWD;
	private String memVPF;
	private String memVPFAdvTaken;
	private String memVPFAmtWD;
	private String office;
	private String monthlyCorp;
	private String monthlyMem;
	private String monthlyVPF;
	
	private String fciMatchingTotal;
	private String fciEPSTotal;
	private String fciAmtWDTotal;
	private String memComplTotal;
	private String memAdvTakenTotal;
	private String memAdvRefTotal;
	private String memWDTotal;
	private String memVPFTotal;
	private String memVPFAdvTakenTotal;
	private String memVPFAmtWDTotal;
	private String officeTotal;
	private String monthlyCorpTotal;
	private String monthlyMemTotal;
	private String monthlyVPFTotal; 
	
	private String obMatching;
	private String obCompl;
	private String obVPF;
	
	public CpfSlipMonthWiseDto(){
		
	}

	public CpfSlipMonthWiseDto(String month, String fciMatching, String fciEPS, String fciAmtWD, String memCompl,
			String memAdvTaken, String memAdvRef, String memWD, String memVPF, String memVPFAdvTaken,
			String memVPFAmtWD, String office, String monthlyCorp, String monthlyMem, String monthlyVPF,
			String fciMatchingTotal, String fciEPSTotal, String fciAmtWDTotal, String memComplTotal,
			String memAdvTakenTotal, String memAdvRefTotal, String memWDTotal, String memVPFTotal,
			String memVPFAdvTakenTotal, String memVPFAmtWDTotal, String officeTotal, String monthlyCorpTotal,
			String monthlyMemTotal, String monthlyVPFTotal, String obMatching, String obCompl, String obVPF) {
		super();
		this.month = month;
		this.fciMatching = fciMatching;
		this.fciEPS = fciEPS;
		this.fciAmtWD = fciAmtWD;
		this.memCompl = memCompl;
		this.memAdvTaken = memAdvTaken;
		this.memAdvRef = memAdvRef;
		this.memWD = memWD;
		this.memVPF = memVPF;
		this.memVPFAdvTaken = memVPFAdvTaken;
		this.memVPFAmtWD = memVPFAmtWD;
		this.office = office;
		this.monthlyCorp = monthlyCorp;
		this.monthlyMem = monthlyMem;
		this.monthlyVPF = monthlyVPF;
		this.fciMatchingTotal = fciMatchingTotal;
		this.fciEPSTotal = fciEPSTotal;
		this.fciAmtWDTotal = fciAmtWDTotal;
		this.memComplTotal = memComplTotal;
		this.memAdvTakenTotal = memAdvTakenTotal;
		this.memAdvRefTotal = memAdvRefTotal;
		this.memWDTotal = memWDTotal;
		this.memVPFTotal = memVPFTotal;
		this.memVPFAdvTakenTotal = memVPFAdvTakenTotal;
		this.memVPFAmtWDTotal = memVPFAmtWDTotal;
		this.officeTotal = officeTotal;
		this.monthlyCorpTotal = monthlyCorpTotal;
		this.monthlyMemTotal = monthlyMemTotal;
		this.monthlyVPFTotal = monthlyVPFTotal;
		this.obMatching = obMatching;
		this.obCompl = obCompl;
		this.obVPF = obVPF;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFciMatching() {
		return fciMatching;
	}

	public void setFciMatching(String fciMatching) {
		this.fciMatching = fciMatching;
	}

	public String getFciEPS() {
		return fciEPS;
	}

	public void setFciEPS(String fciEPS) {
		this.fciEPS = fciEPS;
	}

	public String getFciAmtWD() {
		return fciAmtWD;
	}

	public void setFciAmtWD(String fciAmtWD) {
		this.fciAmtWD = fciAmtWD;
	}

	public String getMemCompl() {
		return memCompl;
	}

	public void setMemCompl(String memCompl) {
		this.memCompl = memCompl;
	}

	public String getMemAdvTaken() {
		return memAdvTaken;
	}

	public void setMemAdvTaken(String memAdvTaken) {
		this.memAdvTaken = memAdvTaken;
	}

	public String getMemAdvRef() {
		return memAdvRef;
	}

	public void setMemAdvRef(String memAdvRef) {
		this.memAdvRef = memAdvRef;
	}

	public String getMemWD() {
		return memWD;
	}

	public void setMemWD(String memWD) {
		this.memWD = memWD;
	}

	public String getMemVPF() {
		return memVPF;
	}

	public void setMemVPF(String memVPF) {
		this.memVPF = memVPF;
	}

	public String getMemVPFAdvTaken() {
		return memVPFAdvTaken;
	}

	public void setMemVPFAdvTaken(String memVPFAdvTaken) {
		this.memVPFAdvTaken = memVPFAdvTaken;
	}

	public String getMemVPFAmtWD() {
		return memVPFAmtWD;
	}

	public void setMemVPFAmtWD(String memVPFAmtWD) {
		this.memVPFAmtWD = memVPFAmtWD;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getMonthlyCorp() {
		return monthlyCorp;
	}

	public void setMonthlyCorp(String monthlyCorp) {
		this.monthlyCorp = monthlyCorp;
	}

	public String getMonthlyMem() {
		return monthlyMem;
	}

	public void setMonthlyMem(String monthlyMem) {
		this.monthlyMem = monthlyMem;
	}

	public String getMonthlyVPF() {
		return monthlyVPF;
	}

	public void setMonthlyVPF(String monthlyVPF) {
		this.monthlyVPF = monthlyVPF;
	}

	public String getFciMatchingTotal() {
		return fciMatchingTotal;
	}

	public void setFciMatchingTotal(String fciMatchingTotal) {
		this.fciMatchingTotal = fciMatchingTotal;
	}

	public String getFciEPSTotal() {
		return fciEPSTotal;
	}

	public void setFciEPSTotal(String fciEPSTotal) {
		this.fciEPSTotal = fciEPSTotal;
	}

	public String getFciAmtWDTotal() {
		return fciAmtWDTotal;
	}

	public void setFciAmtWDTotal(String fciAmtWDTotal) {
		this.fciAmtWDTotal = fciAmtWDTotal;
	}

	public String getMemComplTotal() {
		return memComplTotal;
	}

	public void setMemComplTotal(String memComplTotal) {
		this.memComplTotal = memComplTotal;
	}

	public String getMemAdvTakenTotal() {
		return memAdvTakenTotal;
	}

	public void setMemAdvTakenTotal(String memAdvTakenTotal) {
		this.memAdvTakenTotal = memAdvTakenTotal;
	}

	public String getMemAdvRefTotal() {
		return memAdvRefTotal;
	}

	public void setMemAdvRefTotal(String memAdvRefTotal) {
		this.memAdvRefTotal = memAdvRefTotal;
	}

	public String getMemWDTotal() {
		return memWDTotal;
	}

	public void setMemWDTotal(String memWDTotal) {
		this.memWDTotal = memWDTotal;
	}

	public String getMemVPFTotal() {
		return memVPFTotal;
	}

	public void setMemVPFTotal(String memVPFTotal) {
		this.memVPFTotal = memVPFTotal;
	}

	public String getMemVPFAdvTakenTotal() {
		return memVPFAdvTakenTotal;
	}

	public void setMemVPFAdvTakenTotal(String memVPFAdvTakenTotal) {
		this.memVPFAdvTakenTotal = memVPFAdvTakenTotal;
	}

	public String getMemVPFAmtWDTotal() {
		return memVPFAmtWDTotal;
	}

	public void setMemVPFAmtWDTotal(String memVPFAmtWDTotal) {
		this.memVPFAmtWDTotal = memVPFAmtWDTotal;
	}

	public String getOfficeTotal() {
		return officeTotal;
	}

	public void setOfficeTotal(String officeTotal) {
		this.officeTotal = officeTotal;
	}

	public String getMonthlyCorpTotal() {
		return monthlyCorpTotal;
	}

	public void setMonthlyCorpTotal(String monthlyCorpTotal) {
		this.monthlyCorpTotal = monthlyCorpTotal;
	}

	public String getMonthlyMemTotal() {
		return monthlyMemTotal;
	}

	public void setMonthlyMemTotal(String monthlyMemTotal) {
		this.monthlyMemTotal = monthlyMemTotal;
	}

	public String getMonthlyVPFTotal() {
		return monthlyVPFTotal;
	}

	public void setMonthlyVPFTotal(String monthlyVPFTotal) {
		this.monthlyVPFTotal = monthlyVPFTotal;
	}

	public String getObMatching() {
		return obMatching;
	}

	public void setObMatching(String obMatching) {
		this.obMatching = obMatching;
	}

	public String getObCompl() {
		return obCompl;
	}

	public void setObCompl(String obCompl) {
		this.obCompl = obCompl;
	}

	public String getObVPF() {
		return obVPF;
	}

	public void setObVPF(String obVPF) {
		this.obVPF = obVPF;
	}

	@Override
	public String toString() {
		return "CpfSlipMonthWiseDto [month=" + month + ", fciMatching=" + fciMatching + ", fciEPS=" + fciEPS
				+ ", fciAmtWD=" + fciAmtWD + ", memCompl=" + memCompl + ", memAdvTaken=" + memAdvTaken + ", memAdvRef="
				+ memAdvRef + ", memWD=" + memWD + ", memVPF=" + memVPF + ", memVPFAdvTaken=" + memVPFAdvTaken
				+ ", memVPFAmtWD=" + memVPFAmtWD + ", office=" + office + ", monthlyCorp=" + monthlyCorp
				+ ", monthlyMem=" + monthlyMem + ", monthlyVPF=" + monthlyVPF + ", fciMatchingTotal=" + fciMatchingTotal
				+ ", fciEPSTotal=" + fciEPSTotal + ", fciAmtWDTotal=" + fciAmtWDTotal + ", memComplTotal="
				+ memComplTotal + ", memAdvTakenTotal=" + memAdvTakenTotal + ", memAdvRefTotal=" + memAdvRefTotal
				+ ", memWDTotal=" + memWDTotal + ", memVPFTotal=" + memVPFTotal + ", memVPFAdvTakenTotal="
				+ memVPFAdvTakenTotal + ", memVPFAmtWDTotal=" + memVPFAmtWDTotal + ", officeTotal=" + officeTotal
				+ ", monthlyCorpTotal=" + monthlyCorpTotal + ", monthlyMemTotal=" + monthlyMemTotal
				+ ", monthlyVPFTotal=" + monthlyVPFTotal + ", obMatching=" + obMatching + ", obCompl=" + obCompl
				+ ", obVPF=" + obVPF + "]";
	}
		
}
