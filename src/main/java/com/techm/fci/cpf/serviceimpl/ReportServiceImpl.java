package com.techm.fci.cpf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techm.fci.cpf.dao.ReportDao;
import com.techm.fci.cpf.dto.ClaimRequestGenerateReportDto;
import com.techm.fci.cpf.dto.CpfSlipAdjustmentDataDto;
import com.techm.fci.cpf.dto.CpfSlipHeaderDto;
import com.techm.fci.cpf.dto.CpfSlipIntOBCreditAsOnDataDto;
import com.techm.fci.cpf.dto.CpfSlipMonthWiseDto;
import com.techm.fci.cpf.service.ReportService;

@Service("reportService")
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao reportDao;
	
	@Override
	public CpfSlipHeaderDto getHeaderDataCpfSlipReport(String empNum, String fromYear, String toYear, String locId) {
		CpfSlipHeaderDto cpfSlipHeaderDto = reportDao.getHeaderDataCpfSlipReport(empNum, fromYear, toYear, locId);
		return cpfSlipHeaderDto;
	}

	@Override
	public List<CpfSlipMonthWiseDto> getMonthWiseContrCpfSlipReport(String empNum, String fromYear, String toYear) {
		List<CpfSlipMonthWiseDto> cpfSlipMonthWiseDtoList = reportDao.getMonthWiseContrCpfSlipReport(empNum, fromYear, toYear);
		return cpfSlipMonthWiseDtoList;
	}

	@Override
	public CpfSlipIntOBCreditAsOnDataDto getIntDataCpfSlipReport(String empNum, String fromYear, String toYear) {
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntCBCreditAsOnDataDto = reportDao.getIntDataCpfSlipReport(empNum, fromYear, toYear);
		return cpfSlipIntCBCreditAsOnDataDto;
	}

	@Override
	public CpfSlipIntOBCreditAsOnDataDto getOBDataCpfSlipReport(String empNum, String fromYear, String toYear) {
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntCBCreditAsOnDataDto = reportDao.getOBDataCpfSlipReport(empNum, fromYear, toYear);
		return cpfSlipIntCBCreditAsOnDataDto;
	}

	@Override
	public CpfSlipIntOBCreditAsOnDataDto getCreditAsOnCpfSlipReport(String empNum, String fromYear) {
		CpfSlipIntOBCreditAsOnDataDto cpfSlipIntCBCreditAsOnDataDto = reportDao.getCreditAsOnCpfSlipReport(empNum, fromYear);
		return cpfSlipIntCBCreditAsOnDataDto;
	}

	@Override
	public List<CpfSlipAdjustmentDataDto> getAdjustmentDtCpfSlipReport(String empNum, String fromYear) {
		List<CpfSlipAdjustmentDataDto> cpfSlipAdjustmentDataDtoList = reportDao.getAdjustmentDtCpfSlipReport(empNum, fromYear);
		return cpfSlipAdjustmentDataDtoList;
	}

	
	@Override
	public List<ClaimRequestGenerateReportDto> getClaimReqReport(String empNum,String fromDate,String toDate,String claimType) {
		List<ClaimRequestGenerateReportDto> claimRequestGenerateReportDtoList = reportDao.getClaimReqReport(empNum,fromDate,toDate,claimType);
		return claimRequestGenerateReportDtoList;
	}
	
}
