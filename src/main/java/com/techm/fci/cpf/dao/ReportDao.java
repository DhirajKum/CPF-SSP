package com.techm.fci.cpf.dao;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.List;

import com.techm.fci.cpf.dto.ClaimRequestGenerateReportDto;
import com.techm.fci.cpf.dto.CpfSlipAdjustmentDataDto;
import com.techm.fci.cpf.dto.CpfSlipHeaderDto;
import com.techm.fci.cpf.dto.CpfSlipIntOBCreditAsOnDataDto;
import com.techm.fci.cpf.dto.CpfSlipMonthWiseDto;

public interface ReportDao {
	public CpfSlipHeaderDto getHeaderDataCpfSlipReport(String empNum, String fromYear, String toYear, String locId);
	public List<CpfSlipMonthWiseDto> getMonthWiseContrCpfSlipReport(String empNum, String fromYear, String toYear);
	public CpfSlipIntOBCreditAsOnDataDto getIntDataCpfSlipReport(String empNum, String fromYear, String toYear);
	public CpfSlipIntOBCreditAsOnDataDto getOBDataCpfSlipReport(String empNum, String fromYear, String toYear);
	public CpfSlipIntOBCreditAsOnDataDto getCreditAsOnCpfSlipReport(String empNum, String fromYear);
	public List<CpfSlipAdjustmentDataDto> getAdjustmentDtCpfSlipReport(String empNum, String fromYear);
	public List<ClaimRequestGenerateReportDto> getClaimReqReport(String empNum,String fromDate,String toDate,String claimType,String claimStatusType,String parentZone);
	
	
}
