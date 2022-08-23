package com.techm.fci.cpf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.techm.fci.cpf.dto.ClaimRequestGenerateReportDto;
import com.techm.fci.cpf.dto.ClaimRequestReportDto;
import com.techm.fci.cpf.dto.CpfSlipAdjustmentDataDto;
import com.techm.fci.cpf.dto.CpfSlipHeaderDto;
import com.techm.fci.cpf.dto.CpfSlipIntOBCreditAsOnDataDto;
import com.techm.fci.cpf.dto.CpfSlipMonthWiseDto;
import com.techm.fci.cpf.dto.CpfSlipReportDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.UserModel;
import com.techm.fci.cpf.service.ReportService;
import com.techm.fci.cpf.service.UserService;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

	public static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private HttpSession session;
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;

	private UserModel getUserModel() {
		UserModel uModel = (UserModel) session.getAttribute("userModel");
		return uModel;
	}

	@RequestMapping(value = { "/cpfSlip" })
	public ModelAndView cpfSlip(@RequestParam(name = "operation", required = false) String operation,
			@ModelAttribute("cpfSlipReportDto") CpfSlipReportDto cpfSlipReportDto) {

		DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "CPF Slip Report");
		mv.addObject("clickCpfSlip", true);
		mv.addObject("showNavBar", true);

		UserModel uModel = getUserModel();
		if (uModel != null) {
			// userService.getCpfSlipHeaderDataReport(uModel.getEmpNum(),);
			// mv.addObject("empDetails",empDetails);
			mv.addObject("userModel", uModel);
		} else {
			mv.addObject("message", "Session Expired, Kindly Go For Login !!!");
		}
		return mv;
	}

	@RequestMapping(value = { "/generateCpfSlip" })
	public ModelAndView generateCpfSlip(@ModelAttribute("cpfSlipReportDto") CpfSlipReportDto cpfSlipReportDto) {
		String empNum = cpfSlipReportDto.getEmpNum();
		String fromYear = cpfSlipReportDto.getFromYear();
		String toYear = cpfSlipReportDto.getToYear();

		DateFormat myFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "CPF Slip Report");
		mv.addObject("clickCpfSlip", true);
		mv.addObject("showNavBar", true);

		UserModel uModel = getUserModel();
		if (uModel != null) {
			EmpMaster empMast = userService.getEmpDetailsByEmpNum(uModel.getEmpNum());
			CpfSlipHeaderDto cpfSlipHeaderDto = reportService.getHeaderDataCpfSlipReport(uModel.getEmpNum(), fromYear,
					toYear, empMast.getPARENT_ZONE());
			if (cpfSlipHeaderDto != null) {
				String fciJoiningDt = myFormat.format(cpfSlipHeaderDto.getFciJoiningDt());
				String cpfJoiningDt = myFormat.format(cpfSlipHeaderDto.getFciJoiningDt());
				String dob = myFormat.format(cpfSlipHeaderDto.getDob());
				mv.addObject("fciJoiningDt", fciJoiningDt);
				mv.addObject("cpfJoiningDt", cpfJoiningDt);
				mv.addObject("dob", dob);
				mv.addObject("cpfSlipHeader", cpfSlipHeaderDto);
			}
			List<CpfSlipMonthWiseDto> cpfSlipMonthWiseList = reportService
					.getMonthWiseContrCpfSlipReport(uModel.getEmpNum(), fromYear, toYear);

			int fciMatchingTot = 0;
			int fciEPSTot = 0;
			int fciAmtWDTot = 0;
			int memComplTot = 0;
			int memAdvTakenTot = 0;
			int memAdvRefTot = 0;
			int memWDTot = 0;
			int memVPFTot = 0;
			int memVPFAdvTakenTot = 0;
			int memVPFAmtWDTot = 0;
			int monthlyCorpTot = 0;
			int monthlyMemTot = 0;
			int monthlyVPFTot = 0;

			/*
			 * String empPFOB =""; String empVPFOB=""; String emprPFOB="";
			 */

			for (CpfSlipMonthWiseDto cpfSlipMonthWiseDto : cpfSlipMonthWiseList) {
				/*
				 * emprPFOB=cpfSlipMonthWiseDto.getObMatching();
				 * empPFOB=cpfSlipMonthWiseDto.getObCompl();
				 * empVPFOB=cpfSlipMonthWiseDto.getObVPF();
				 */

				fciMatchingTot = fciMatchingTot + Integer.parseInt(cpfSlipMonthWiseDto.getFciMatching());
				fciEPSTot = fciEPSTot + Integer.parseInt(cpfSlipMonthWiseDto.getFciEPS());
				fciAmtWDTot = fciAmtWDTot + Integer.parseInt(cpfSlipMonthWiseDto.getFciAmtWD());
				memComplTot = memComplTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemCompl());
				memAdvTakenTot = memAdvTakenTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemAdvTaken());
				memAdvRefTot = memAdvRefTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemAdvRef());
				memWDTot = memWDTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemWD());
				memVPFTot = memVPFTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemVPF());
				memVPFAdvTakenTot = memVPFAdvTakenTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemVPFAdvTaken());
				memVPFAmtWDTot = memVPFAmtWDTot + Integer.parseInt(cpfSlipMonthWiseDto.getMemVPFAmtWD());

				monthlyCorpTot = monthlyCorpTot + Integer.parseInt(cpfSlipMonthWiseDto.getMonthlyCorp());
				monthlyMemTot = monthlyMemTot + Integer.parseInt(cpfSlipMonthWiseDto.getMonthlyMem());
				monthlyVPFTot = monthlyVPFTot + Integer.parseInt(cpfSlipMonthWiseDto.getMonthlyVPF());
			}

			mv.addObject("fciMatchingTot", fciMatchingTot);
			mv.addObject("fciEPSTot", fciEPSTot);
			mv.addObject("fciAmtWDTot", fciAmtWDTot);
			mv.addObject("memComplTot", memComplTot);
			mv.addObject("memAdvTakenTot", memAdvTakenTot);
			mv.addObject("memAdvRefTot", memAdvRefTot);
			mv.addObject("memWDTot", memWDTot);
			mv.addObject("memVPFTot", memVPFTot);
			mv.addObject("memVPFAdvTakenTot", memVPFAdvTakenTot);
			mv.addObject("memVPFAmtWDTot", memVPFAmtWDTot);
			mv.addObject("monthlyCorpTot", monthlyCorpTot);
			mv.addObject("monthlyMemTot", monthlyMemTot);
			mv.addObject("monthlyVPFTot", monthlyVPFTot);

			/*
			 * mv.addObject("emprPFOB", emprPFOB); mv.addObject("empPFOB",
			 * empPFOB); mv.addObject("empVPFOB", empVPFOB);
			 */

			CpfSlipIntOBCreditAsOnDataDto cpfSlipIntDataDto = reportService.getIntDataCpfSlipReport(uModel.getEmpNum(),
					fromYear, toYear);

			CpfSlipIntOBCreditAsOnDataDto cpfSlipOBDataDto = reportService.getOBDataCpfSlipReport(uModel.getEmpNum(),
					fromYear, toYear);

			CpfSlipIntOBCreditAsOnDataDto cpfSlipCreditAsOnDataDto = reportService
					.getCreditAsOnCpfSlipReport(uModel.getEmpNum(), fromYear);
			if (cpfSlipCreditAsOnDataDto.getBalToCreditAsOnDt() != null) {
				String creditAsOn = myFormat.format(cpfSlipCreditAsOnDataDto.getBalToCreditAsOnDt());
				mv.addObject("creditAsOn", creditAsOn);
			} else {
				mv.addObject("creditAsOn", "");
			}
			List<CpfSlipAdjustmentDataDto> cpfSlipAdjustmentDataList = reportService
					.getAdjustmentDtCpfSlipReport(uModel.getEmpNum(), fromYear);

			mv.addObject("cpfSlipMonthWiseList", cpfSlipMonthWiseList);
			mv.addObject("cpfSlipIntDataDto", cpfSlipIntDataDto);
			mv.addObject("cpfSlipOBDataDto", cpfSlipOBDataDto);

			int cbMatching = 0;
			int cbComl = 0;
			int cbVPF = 0;
			if (cpfSlipOBDataDto.getObMatching() != null && cpfSlipOBDataDto.getObComl() != null
					&& cpfSlipOBDataDto.getObVPF() != null && cpfSlipIntDataDto.getIntMatching() != null
					&& cpfSlipIntDataDto.getIntComl() != null && cpfSlipIntDataDto.getIntVPF() != null) {
				cbMatching = Integer.parseInt(cpfSlipOBDataDto.getObMatching()) + fciMatchingTot
						+ Integer.parseInt(cpfSlipIntDataDto.getIntMatching()) - fciAmtWDTot;
				cbComl = Integer.parseInt(cpfSlipOBDataDto.getObComl()) + memComplTot
						+ Integer.parseInt(cpfSlipIntDataDto.getIntComl()) - memWDTot;
				cbVPF = Integer.parseInt(cpfSlipOBDataDto.getObVPF()) + memVPFTot
						+ Integer.parseInt(cpfSlipIntDataDto.getIntVPF()) - memVPFAmtWDTot;
				mv.addObject("cbMatching", cbMatching);
				mv.addObject("cbComl", cbComl);
				mv.addObject("cbVPF", cbVPF);
			} else {
				mv.addObject("cbMatching", cbMatching);
				mv.addObject("cbComl", cbComl);
				mv.addObject("cbVPF", cbVPF);
			}

			mv.addObject("cpfSlipAdjustmentDataList", cpfSlipAdjustmentDataList);

			int totalBal = cbMatching + cbComl + cbVPF;
			mv.addObject("totalBal", totalBal);

			mv.addObject("fromYear", fromYear);
			mv.addObject("toYear", toYear);
			mv.addObject("userModel", uModel);

		} else {
			mv.addObject("message", "Session Expired, Kindly Go For Login !!!");
		}
		return mv;
	}

	@RequestMapping(value = { "/claimReport" })
	public ModelAndView cpfSlip(@RequestParam(name = "operation", required = false) String operation) {

		//DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Claim Request Report");
		mv.addObject("clickClaimReqReport", true);
		mv.addObject("showNavBar", true);

		UserModel uModel = getUserModel();
		if (uModel != null) {
			// userService.getCpfSlipHeaderDataReport(uModel.getEmpNum(),);
			// mv.addObject("empDetails",empDetails);
			mv.addObject("userModel", uModel);
		} else {
			mv.addObject("message", "Session Expired, Kindly Go For Login !!!");
		}
		return mv;
	}

	/*
	 * @RequestMapping(value={"/generateClaimRequestReport"}) public
	 * ModelAndView
	 * generateClaimRequestReport(@ModelAttribute("claimRequestReportDto")
	 * ClaimRequestReportDto claimRequestReportDto){ String empNum =
	 * claimRequestReportDto.getEmpNum(); String fromDate =
	 * claimRequestReportDto.getFromDate(); String toDate =
	 * claimRequestReportDto.getToDate(); String
	 * claimType=claimRequestReportDto.getClaimType();
	 * 
	 * DateFormat myFormat = new SimpleDateFormat("dd/MMM/yyyy",
	 * Locale.ENGLISH); ModelAndView mv = new ModelAndView("masterpage");
	 * mv.addObject("title","Claim Request Report");
	 * mv.addObject("clickClaimReqReport",true);
	 * mv.addObject("showNavBar",true);
	 * 
	 * List<ClaimRequestGenerateReportDto> claimReportList = new
	 * ArrayList<ClaimRequestGenerateReportDto>(); UserModel
	 * uModel=getUserModel(); if(uModel!=null){ claimReportList =
	 * reportService.getClaimReqReport(empNum, fromDate,toDate,claimType);
	 * mv.addObject("claimReportList",claimReportList);
	 * mv.addObject("empNum",empNum); mv.addObject("fromDate",fromDate);
	 * mv.addObject("toDate",toDate); mv.addObject("claimType",claimType);
	 * mv.addObject("userModel",uModel); } else{
	 * mv.addObject("message","Session Expired, Kindly Go For Login !!!"); }
	 * return mv; }
	 */

	@RequestMapping(value = { "/generateClaimRequestReport" })
	public @ResponseBody List<ClaimRequestGenerateReportDto> generateClaimRequestReport(
			@RequestParam(name = "toDate", required = false) String toDate,
			@RequestParam(name = "fromDate", required = false) String fromDate,
			@RequestParam(name = "empNum", required = false) String empNum,
			@RequestParam(name = "claimType", required = false) String claimType) {
	
		List<ClaimRequestGenerateReportDto> claimReportList = new ArrayList<ClaimRequestGenerateReportDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			claimReportList = reportService.getClaimReqReport(empNum, fromDate, toDate, claimType);
		}
		return claimReportList;
	}

}
