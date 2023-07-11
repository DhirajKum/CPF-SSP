package com.techm.fci.cpf.controller;

/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.techm.fci.cpf.dto.ActClaimDto;
import com.techm.fci.cpf.dto.AssignToClaimDto;
import com.techm.fci.cpf.dto.ClaimHistoryTrailDto;
import com.techm.fci.cpf.dto.ClaimRequestStatusDto;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.SavedClaimConditionCheckDto;
import com.techm.fci.cpf.model.CpfClaimRequest;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.UserModel;
import com.techm.fci.cpf.service.UserService;
import com.techm.fci.cpf.util.DateUtils;

@Controller
@RequestMapping(value = "/claim")
public class ClaimController {

	public static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@Autowired
	private HttpSession session;
	@Autowired
	private UserService userService;

	private UserModel getUserModel() {
		UserModel uModel = (UserModel) session.getAttribute("userModel");
		return uModel;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		binder.registerCustomEditor(Date.class, "DATE_OF_BIRTH", new CustomDateEditor(simpleDateFormat, false));
		binder.registerCustomEditor(Date.class, "DOJ_FCI", new CustomDateEditor(simpleDateFormat, false));
		binder.registerCustomEditor(Date.class, "RETIREMENT_DATE", new CustomDateEditor(simpleDateFormat, false));
	}

	@RequestMapping(value = { "/raiseClaimReq" })
	public ModelAndView raiseClaimReq(@RequestParam(name = "reqId", required = false) String reqId,
			@RequestParam(name = "operation", required = false) String operation,
			@RequestParam(name = "uploadfiles", required = false) String uploadfiles) {
		DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		// CpfClaimRequest cpfClaimReq = new CpfClaimRequest();
		ActClaimDto cpfClaimReq = new ActClaimDto();
		ModelAndView mv = new ModelAndView("masterpage");

		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);
		if (uModel != null) {
			if (reqId == null || reqId.equals("")) {
				mv.addObject("title", "Raise Claim Request");
				mv.addObject("clickClaimReq", true);
				mv.addObject("showNavBar", true);

				cpfClaimReq = userService.getBasicDetailsForClaim(uModel.getEmpNum());
				if (cpfClaimReq.getKycFilePath() != null && !cpfClaimReq.getKycFilePath().equals("")) {
					String dob = myFormat.format(cpfClaimReq.getDATE_OF_BIRTH());
					String dojFci = myFormat.format(cpfClaimReq.getDOJ_FCI());
					String retirementDate = myFormat.format(cpfClaimReq.getRETIREMENT_DATE());
					String empStatus = cpfClaimReq.getEmpStatus() != null ? cpfClaimReq.getEmpStatus().substring(10)
							: "";
					mv.addObject("dob", dob);
					mv.addObject("dojFci", dojFci);
					mv.addObject("retirementDate", retirementDate);
					mv.addObject("empStatus", empStatus);
					mv.addObject("kycUpdate", "1");
				} else {
					mv.addObject("kycUpdate", "0");
					mv.addObject("message",
							"First You have to upload your KYC document, befor raise your claim request...!!!");
				}
			} else {
				mv.addObject("title", "Update Claim Request");
				mv.addObject("clickUpdateClaimReq", true);
				mv.addObject("showNavBar", true);

				cpfClaimReq = userService.getClaimReqDetails(reqId);
				if (cpfClaimReq.getKycFilePath() != null && !cpfClaimReq.getKycFilePath().equals("")) {
					String dob = myFormat.format(cpfClaimReq.getDATE_OF_BIRTH());
					String dojFci = myFormat.format(cpfClaimReq.getDOJ_FCI());
					String retirementDate = myFormat.format(cpfClaimReq.getRETIREMENT_DATE());
					String empStatus = cpfClaimReq.getEmpStatus() != null ? cpfClaimReq.getEmpStatus().substring(10)
							: "";
					mv.addObject("dob", dob);
					mv.addObject("dojFci", dojFci);
					mv.addObject("retirementDate", retirementDate);
					mv.addObject("empStatus", empStatus);
					mv.addObject("reqId", reqId);
					mv.addObject("kycUpdate", "1");
					mv.addObject("reqType", "myReq");
				}

			}
		} else {
			mv.addObject("message", "Session Expired, Kindly Go For Login !!!");
		}
		mv.addObject("claimData", cpfClaimReq);
		mv.addObject("actClaimDto", cpfClaimReq);

		if (operation != null) {
			if (operation.equals("failed")) {
				mv.addObject("message", "Claim submission failed ...!");
			}
			if (operation.equals("cfWDuplicate")) {
				mv.addObject("message", "You have exceeded the limit for Final Withdrawal claim apply ...!");
			}
			if (operation.equals("cfWTimeLimit")) {
				mv.addObject("message", "You are not eligible for Final Withdrawal claim apply ...!");
			}
			if (operation.equals("covidDuplicate")) {
				mv.addObject("message", "You have exceeded the limit for COVID-19 claim apply ...!");
			}
			if (operation.equals("limitExceed")) {
				mv.addObject("message", "You have exceeded the limit for Part Final Withdrawal claim apply ...!");
			}
			if (operation.equals("nintyPWDuplicate")) {
				mv.addObject("message", "You have exceeded the limit for 90% Withdrawal claim apply ...!");
			}
			if (operation.equals("nintyPWTimeLimit")) {
				mv.addObject("message", "You are not eligible for 90% Withdrawal claim apply ...!");
			}
			if (operation.equals("tempAdvDuplicate")) {
				mv.addObject("message", "You are not eligible for Temp Adv. claim apply ...!");
			}
			if (operation.equals("duplicate")) {
				mv.addObject("message", "Claim already submitted ...!");
			}
			if (operation.equals("NOADMIN")) {
				mv.addObject("message",	"Claim submission failed. As of now, admin/s not found for this location for approval your claim request ...!!!");
			}
		}
		return mv;
	}

	@RequestMapping(value = { "/saveClaimRequest" }, method = { RequestMethod.POST })
	public String saveClaimRequestData(@Valid @ModelAttribute("claimData") CpfClaimRequest cpfClaim,
			@RequestParam String js_enabled) {
		logger.info("::::: In side save claim request method :::::");
		boolean recordFound = false;
		boolean covidFlag = false;
		boolean cpfwFlag = false;
		boolean cfwLimitFlag = false;
		boolean cfwTimeFlag = false;
		boolean nintyPWLimitFlage = false;
		boolean nintyPWTimeFlage = false;
		boolean tempAdvFlage = false;
		Long totalDays = 0L;
		UserModel uModel = getUserModel();

		if (uModel != null && js_enabled.equals("1")) {

			List<CpfClaimRequest> cpfClaimReqList = new ArrayList<CpfClaimRequest>();
			Map<String, Long> claimPurposeCount = null;
			cpfClaimReqList = userService.empClaimLookup(uModel.getEmpNum());
			EmpMaster empMaster = userService.getEmpDetailsByEmpNum(uModel.getEmpNum());
			String empStatus = empMaster.getEMP_STATUS() != null ? empMaster.getEMP_STATUS().substring(10) : "";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			Map<String, Integer> map = cpfClaimReqList.stream()
					.collect(Collectors.groupingBy(CpfClaimRequest::getCLAIM_APPLIED_FOR, Collectors.collectingAndThen(
							Collectors.mapping(CpfClaimRequest::getPURPOSE, Collectors.toList()), List::size)));

			switch (cpfClaim.getCLAIM_APPLIED_FOR()) {
			case "CpfFinalSettlement":
				if (empMaster.getRETIREMENT_DATE() != null) {
					totalDays = DateUtils.dateDiffByDays(empMaster.getRETIREMENT_DATE());

					if (!map.isEmpty() && map.get("CpfFinalSettlement") != null && map.get("CpfFinalSettlement") >= 1) {
						recordFound = true;
						cfwLimitFlag = true;
					} else {
						if (!empStatus.equals("RESG")) {
							//isBefore() return true -- local date (01-01-2022) and retirement date (01-01-2023)
							if (LocalDate.now().isBefore(LocalDate.parse(empMaster.getRETIREMENT_DATE(), formatter))) {
								totalDays = DateUtils.dateDiffByDays(empMaster.getRETIREMENT_DATE()) * -1;
								if (totalDays > 60) {
									recordFound = true;
									cfwTimeFlag = true;
								}
							}
						} else {
							//isBefore() return true -- local date (01-01-2022) and retirement date (01-01-2023)
							if (LocalDate.now().isBefore(LocalDate.parse(empMaster.getRETIREMENT_DATE(), formatter))) {
								recordFound = true;
								cfwTimeFlag = true;
							}
						}
					}
				}
				break;
			case "CpfPartFinalWithdrawal":
				claimPurposeCount = cpfClaimReqList.stream().filter(p -> p.getPURPOSE() != null).collect(Collectors.groupingBy(CpfClaimRequest::getPURPOSE, Collectors.counting()));
				if (!map.isEmpty() && map.get("CpfPartFinalWithdrawal") != null) {
					int cpfwCount = map.get("CpfPartFinalWithdrawal") - (claimPurposeCount.get("COVID-19") != null ? claimPurposeCount.get("COVID-19").intValue() : 0);

					if (!cpfClaim.getPURPOSE().equals("COVID-19") && cpfwCount >= 6) {
						recordFound = true;
						cpfwFlag = true;
					}
					if (cpfClaim.getPURPOSE().equals("COVID-19") && claimPurposeCount.get("COVID-19") != null
							&& claimPurposeCount.get("COVID-19") >= 2) {
						recordFound = true;
						covidFlag = true;
					}
				}
				break;
			case "90%Withdrawal":
				if (empMaster.getRETIREMENT_DATE() != null) {
					totalDays = DateUtils.dateDiffByDays(empMaster.getRETIREMENT_DATE()) * -1;

					if (!map.isEmpty() && map.get("90%Withdrawal") != null && map.get("90%Withdrawal") >= 1) {
						recordFound = true;
						nintyPWLimitFlage = true;
					} else {
						if (!empStatus.equals("PERM")) {
							recordFound = true;
							nintyPWLimitFlage = true;
						} else {
							//isAfter() return true -- local date (01-01-2023) and retirement date (01-01-2022)
							if (LocalDate.now().isAfter(LocalDate.parse(empMaster.getRETIREMENT_DATE(), formatter))) {
								recordFound = true;
								nintyPWTimeFlage = true;
							} else if (LocalDate.now().isBefore(LocalDate.parse(empMaster.getRETIREMENT_DATE(), formatter)) && totalDays >= 365) {
								recordFound = true;
								nintyPWTimeFlage = true;
							}
						}
					}
				}
				break;
			case "TempAdv":
				List<SavedClaimConditionCheckDto> savedClaimStatusList = userService.checkSavedClaimStatus(uModel.getEmpNum(), cpfClaim.getCLAIM_APPLIED_FOR());

				for (SavedClaimConditionCheckDto savedClaimCondition : savedClaimStatusList) {
					if (userService.checkTempAdvApplyAbility(uModel.getEmpNum()) && savedClaimCondition.getClaimStatus() < 4) {
						recordFound = true;
						tempAdvFlage = true;
					} else if (!userService.checkTempAdvApplyAbility(uModel.getEmpNum())) {
						recordFound = true;
						tempAdvFlage = true;
					}
				}
			
				break;
			default:
				recordFound = false;
				break;
			}

			if (!recordFound) {
				String locCode = session.getAttribute("locCode").toString();
				CpfClaimRequest claimRequest = userService.saveClaimData(cpfClaim, uModel.getEmpNum(), locCode,
						uModel.getRoleName());
				if (claimRequest != null) {
					session.setAttribute("claimId", claimRequest.getREQUEST_ID());

					ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
					claimHistoryTrailDto.setRequestId(claimRequest.getREQUEST_ID());
					claimHistoryTrailDto.setClaimCreatedBy(claimRequest.getCLAIM_SUBMITTED_BY());
					claimHistoryTrailDto.setActionTakenBy(claimRequest.getCLAIM_SUBMITTED_BY());
					claimHistoryTrailDto.setAction("Create");
					claimHistoryTrailDto.setRemarks("Claim has been created.");
					claimHistoryTrailDto.setStatus("1");
					userService.saveCpfClaimHistoryTrail(claimHistoryTrailDto, uModel.getEmpNum(),
							uModel.getRoleName());
					userService.updateEmpOtherDoc(uModel, claimRequest);

					return "redirect:/home?operation=claimSuccess";
				} else {
					String adminFound = session.getAttribute("adminfound").toString();
					if (adminFound.equals("N")) {
						return "redirect:/claim/raiseClaimReq?operation=NOADMIN";
					} else {
						return "redirect:/claim/raiseClaimReq?operation=failed";
					}
				}
			} else {
				if (cfwLimitFlag)
					return "redirect:/claim/raiseClaimReq?operation=cfWDuplicate";
				else if (cfwTimeFlag)
					return "redirect:/claim/raiseClaimReq?operation=cfWTimeLimit";
				else if (covidFlag)
					return "redirect:/claim/raiseClaimReq?operation=covidDuplicate";
				else if (cpfwFlag)
					return "redirect:/claim/raiseClaimReq?operation=limitExceed";
				else if (nintyPWLimitFlage)
					return "redirect:/claim/raiseClaimReq?operation=nintyPWDuplicate";
				else if (nintyPWTimeFlage)
					return "redirect:/claim/raiseClaimReq?operation=nintyPWTimeLimit";
				else if (tempAdvFlage)
					return "redirect:/claim/raiseClaimReq?operation=tempAdvDuplicate";
				else
					return "redirect:/claim/raiseClaimReq?operation=duplicate";
			}
		} else if (js_enabled.equals("0")) {
			session.setAttribute("regInfo",
					"Your javascript is disabled. Kindly enable it before going to raise your claim.");
			return "redirect:/claim/raiseClaimReq?operation=failed";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = { "/myPendingReq" })
	public ModelAndView myPendingReq(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "My Pending Request");
		mv.addObject("clickMyPendingReq", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);

		if (operation != null) {
			if (operation.equals("updateSuccessfully")) {
				mv.addObject("message", "Claim update successfully ...!");
			}
			if (operation.equals("updateFail")) {
				mv.addObject("message", "Claim updation filed ...!");
			}
		}
		return mv;
	}

	@RequestMapping(value = { "/pendingReq" })
	public ModelAndView pendingReq(@RequestParam(name = "reqType", required = true) String reqType,
			@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Pending Request");
		mv.addObject("clickPendingReq", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);
		mv.addObject("reqType", reqType);

		if (operation != null) {
			if (operation.equals("updateSuccessfully")) {
				mv.addObject("message", "Claim update successfully ...!");
			}
			if (operation.equals("updateFail")) {
				mv.addObject("message", "Claim updation filed ...!");
			}
		}
		return mv;
	}

	@RequestMapping(value = "/myAllPendingClaim")
	public @ResponseBody List<ClaimRequestStatusDto> getMyAllPendingClaim(HttpSession session) {

		List<ClaimRequestStatusDto> cpfClaimStatus = new ArrayList<ClaimRequestStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			cpfClaimStatus = userService.getMyAllPendingReq(uModel.getEmpNum(), uModel.getRoleName());
		}
		return cpfClaimStatus;
	}

	@RequestMapping(value = "/allPendingClaim")
	public @ResponseBody List<ClaimRequestStatusDto> getAllPendingClaimReq(
			@RequestParam(name = "reqType", required = true) String reqType, HttpSession session) {

		List<ClaimRequestStatusDto> cpfClaimStatus = new ArrayList<ClaimRequestStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			cpfClaimStatus = userService.getAllPendingReq(reqType, uModel.getEmpNum(), uModel.getRoleName());
		}
		return cpfClaimStatus;
	}

	@RequestMapping(value = { "/claimHistoryTrail" })
	public ModelAndView claimHistoryTrail(@RequestParam(name = "reqId", required = true) String reqId) {

		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Claim History Trail");
		mv.addObject("clickHistoryClaimReq", true);
		mv.addObject("showNavBar", false);
		mv.addObject("reqId", reqId);
		return mv;
	}

	@RequestMapping(value = "/completeHistoryTrail")
	public @ResponseBody List<ClaimHistoryTrailDto> getAllHistoryTrail(
			@RequestParam(name = "reqId", required = true) String reqId, HttpSession session) {

		List<ClaimHistoryTrailDto> claimHistoryTrail = new ArrayList<ClaimHistoryTrailDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			claimHistoryTrail = userService.getClaimHistoryTrail(reqId, uModel.getEmpNum(), uModel.getRoleName());
		}
		return claimHistoryTrail;
	}

	@RequestMapping(value = { "/viewClaimReq" })
	public ModelAndView viewClaimReq(@RequestParam(name = "reqId", required = true) String reqId) {
		DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		ActClaimDto actClaimDto = new ActClaimDto();

		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "View Raised Claim Request");
		mv.addObject("clickViewClaimReq", true);
		mv.addObject("showNavBar", false);

		actClaimDto = userService.getClaimReqDetails(reqId);

		String dob = myFormat.format(actClaimDto.getDATE_OF_BIRTH());
		String dojFci = myFormat.format(actClaimDto.getDOJ_FCI());
		String retirementDate = myFormat.format(actClaimDto.getRETIREMENT_DATE());
		mv.addObject("dob", dob);
		mv.addObject("dojFci", dojFci);
		mv.addObject("retirementDate", retirementDate);
		mv.addObject("claimData", actClaimDto);

		return mv;
	}

	@RequestMapping(value = { "/actClaimReq" })
	public ModelAndView actClaimReq(@RequestParam(name = "reqType", required = true) String reqType,
			@RequestParam(name = "reqId", required = true) String reqId,
			@RequestParam(name = "uploadfiles", required = false) String uploadfiles,
			@RequestParam(name = "validationMessage", required = false) String validationMessage) {
		DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		ActClaimDto actClaimDto = new ActClaimDto();

		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Take Action On Raised Claim Request");
		mv.addObject("clickActClaimReq", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);

		actClaimDto = userService.getClaimReqDetails(reqId);
		String dob = myFormat.format(actClaimDto.getDATE_OF_BIRTH());
		String dojFci = myFormat.format(actClaimDto.getDOJ_FCI());
		String retirementDate = myFormat.format(actClaimDto.getRETIREMENT_DATE());
		mv.addObject("dob", dob);
		mv.addObject("dojFci", dojFci);
		mv.addObject("retirementDate", retirementDate);
		mv.addObject("reqType", reqType);
		mv.addObject("reqId", reqId);
		mv.addObject("actClaimDto", actClaimDto);
		mv.addObject("message", uploadfiles);
		if (validationMessage != null) {
			if (validationMessage.equals("htmltagvalidation")) {
				mv.addObject("validationMessage", "Kindly pass valid comment in remarks field (not used html tags)");
			}
		}
		return mv;
	}

	@RequestMapping(value = { "/updateClaimRequest" }, method = { RequestMethod.POST })
	public String updateClaimRequest(@Valid @ModelAttribute("actClaimDto") ActClaimDto actClaimDto,
			@RequestParam(name = "reqType") String rqType, @RequestParam(name = "claimReq") String reqType,
			@RequestParam(name = "reqId") String reqId, @RequestParam(required = true) String js_enabled) {

		UserModel uModel = getUserModel();
		boolean result = false;
		if (uModel != null && js_enabled.equals("1")) {
			if (!userService.checkInputData(actClaimDto)) {
				result = userService.updateClaimReq(actClaimDto, reqType, reqId, uModel.getEmpNum(),
						uModel.getRoleName(), actClaimDto.getLocId(), actClaimDto.getParentZone());

				ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
				claimHistoryTrailDto.setRequestId(reqId);
				claimHistoryTrailDto.setClaimCreatedBy(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimHistoryTrailDto.setActionTakenBy(uModel.getEmpNum());
				claimHistoryTrailDto.setRemarks(actClaimDto.getRemarks());
				claimHistoryTrailDto.setAction("Approve");
				if (uModel.getRoleName().equals("USER")) {
					claimHistoryTrailDto.setStatus("1");
					claimHistoryTrailDto.setAction("Re-Create");
					claimHistoryTrailDto.setRemarks("Claim has been created.");
				} else if (uModel.getRoleName().equals("ADMIN")) {
					claimHistoryTrailDto.setStatus("2");
				} else if (uModel.getRoleName().equals("CPF_ADMIN")) {
					claimHistoryTrailDto.setStatus("3");
				}
				userService.saveCpfClaimHistoryTrail(claimHistoryTrailDto, uModel.getEmpNum(), uModel.getRoleName());
			} else {
				return "redirect:/claim/actClaimReq?reqType=" + rqType + "&reqId=" + reqId
						+ "&validationMessage=htmltagvalidation";
			}
			if (result) {
				return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateSuccessfully";
			} else {
				return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateFail";
			}

		} else if (js_enabled.equals("0")) {
			session.setAttribute("regInfo", "Your javascript is disabled. Kindly enable it, before take any action.");
			return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateFail";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = { "/reApplyClaimRequest" }, method = { RequestMethod.POST })
	public String reApplyClaimRequest(@Valid @ModelAttribute("actClaimDto") ActClaimDto actClaimDto,
			@RequestParam(name = "reqType") String rqType, @RequestParam(name = "claimReq") String reqType,
			@RequestParam(name = "reqId") String reqId, @RequestParam(required = true) String js_enabled) {

		UserModel uModel = getUserModel();
		boolean result = false;
		if (uModel != null && js_enabled.equals("1")) {
			if (!userService.checkInputData(actClaimDto)) {
				result = userService.reApplyClaimReq(actClaimDto, reqType, reqId, uModel.getEmpNum(),
						uModel.getRoleName(), actClaimDto.getLocId(), actClaimDto.getParentZone());

				ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
				claimHistoryTrailDto.setRequestId(reqId);
				claimHistoryTrailDto.setClaimCreatedBy(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimHistoryTrailDto.setActionTakenBy(uModel.getEmpNum());
				claimHistoryTrailDto.setRemarks(actClaimDto.getRemarks());
				claimHistoryTrailDto.setStatus("1");
				claimHistoryTrailDto.setAction("Re-Create");
				claimHistoryTrailDto.setRemarks("Claim has been created.");
				
				userService.saveCpfClaimHistoryTrail(claimHistoryTrailDto, uModel.getEmpNum(), uModel.getRoleName());
			} else {
				return "redirect:/claim/actClaimReq?reqType=" + rqType + "&reqId=" + reqId
						+ "&validationMessage=htmltagvalidation";
			}
			if (result) {
				return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateSuccessfully";
			} else {
				return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateFail";
			}

		} else if (js_enabled.equals("0")) {
			session.setAttribute("regInfo", "Your javascript is disabled. Kindly enable it, before take any action.");
			return "redirect:/claim/pendingReq?reqType=" + rqType + "&operation=updateFail";
		} else {
			return "redirect:/login";
		}
	}
		
	@RequestMapping(value = { "/rejectClaimRequest" })
	public String rejectClaimRequest(@RequestParam(name = "claimType") String reqType,
			@RequestParam(name = "reqId") String reqId, @RequestParam(name = "remark") String remarks,
			@RequestParam(name = "casteDisp") String casteDisp) {
		boolean result = false;
		ActClaimDto actClaimDto = new ActClaimDto();
		actClaimDto.setRemarks(remarks);

		UserModel uModel = getUserModel();
		if (uModel != null) {
			if (!userService.checkInputData(actClaimDto)) {
				result = userService.rejectClaimReq(remarks, reqType, reqId, uModel.getEmpNum(), uModel.getRoleName(),
						casteDisp);

				ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
				claimHistoryTrailDto.setRequestId(reqId);
				// claimHistoryTrailDto.setClaimCreatedBy(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimHistoryTrailDto.setActionTakenBy(uModel.getEmpNum());
				claimHistoryTrailDto.setRemarks(remarks);
				claimHistoryTrailDto.setAction("Reject");
				// if(uModel.getRoleName().equals("ADMIN"))
				claimHistoryTrailDto.setStatus("0");

				userService.saveCpfClaimHistoryTrail(claimHistoryTrailDto, uModel.getEmpNum(), uModel.getRoleName());
			} else {
				return "redirect:/claim/actClaimReq?reqType=" + reqType + "&reqId=" + reqId
						+ "&validationMessage=htmltagvalidation";
			}
			if (result) {
				return "redirect:/claim/pendingReq?reqType=otherReq&operation=updateSuccessfully";
			} else {
				return "redirect:/claim/pendingReq?reqType=otherReq&operation=updateFail";
			}
		}
		return "";
	}

	@RequestMapping(value = { "/approvedReq" })
	public ModelAndView approvedReq(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Approved Request");
		mv.addObject("clickApprovedReq", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);

		return mv;
	}

	@RequestMapping(value = "/allApprovedClaim")
	public @ResponseBody List<ClaimRequestStatusDto> getAllApprovedClaimReq(HttpSession session) {

		List<ClaimRequestStatusDto> cpfClaimStatus = new ArrayList<ClaimRequestStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			cpfClaimStatus = userService.getAllApprovedReq(uModel.getEmpNum(), uModel.getRoleName());
		}
		return cpfClaimStatus;
	}

	@RequestMapping(value = { "/completedReq" })
	public ModelAndView completedReq(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Completed Request");
		mv.addObject("clickCompletedReq", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);

		if (operation != null) {
			if (operation.equals("updateSuccessfully")) {
				mv.addObject("message", "Claim update successfully ...!");
			}
			if (operation.equals("updateFail")) {
				mv.addObject("message", "Claim updation filed ...!");
			}
		}
		return mv;
	}

	@RequestMapping(value = "/allCompletedClaim")
	public @ResponseBody List<ClaimRequestStatusDto> getAllCompletedClaimReq(HttpSession session) {

		List<ClaimRequestStatusDto> cpfClaimStatus = new ArrayList<ClaimRequestStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			cpfClaimStatus = userService.getAllCompletedReq(uModel.getEmpNum(), uModel.getRoleName());
		}
		return cpfClaimStatus;
	}

	@RequestMapping(value = { "/assignClaimRequest" })
	public ModelAndView assignClaimReq(@RequestParam(name = "claimType") String reqType,
			@RequestParam(name = "reqId", required = true) String reqId,
			@ModelAttribute("assignToClaimDto") AssignToClaimDto assignToClaimDto) {

		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Take Action To Assign Others");
		mv.addObject("clickAssignClaimReq", true);
		mv.addObject("showNavBar", true);
		UserModel userModel = getUserModel();
		ActClaimDto actClaimDto = userService.getClaimReqDetails(reqId);
		session.setAttribute("locId", actClaimDto.getLocId());
		mv.addObject("reqId", reqId);
		if (userModel != null) {
			mv.addObject("userRole", userModel.getRoleName());
			mv.addObject("empNum", userModel.getEmpNum());
		}
		mv.addObject("parentZone", actClaimDto.getParentZone());
		mv.addObject("assignToClaimDto", assignToClaimDto);

		return mv;
	}

	// @ModelAttribute("unitlist")
	@RequestMapping(value = "/getUnitCodeList", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getUnitList() {
		String locId = (String) session.getAttribute("locId");
		return userService.getUnitList(locId);
	}

	@RequestMapping(value = "/getLocationByUnit", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getEmpDetailsByEmpNum(@RequestParam String unitId, HttpSession session) {
		return userService.getLocationList(unitId);
	}

	@RequestMapping(value = "/getAdminByLocation", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getAdminByLocation(@RequestParam String locId, @RequestParam String empNum,
			HttpSession session) {
		return userService.getAdminList(locId, empNum);
	}

	@RequestMapping(value = "/getCPFAdminByParentZone", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getCPFAdminByParentZone(@RequestParam String parentZone, @RequestParam String userRole,
			@RequestParam String empNum, HttpSession session) {
		return userService.getCPFAdminByParentZone(parentZone, userRole, empNum);
	}

	@RequestMapping(value = { "/assignToClaimRequest" })
	public String assignToClaimReq(@RequestParam(name = "claimType") String reqType,
			@RequestParam(name = "reqId", required = true) String reqId,
			@ModelAttribute("assignToClaimDto") AssignToClaimDto assignToClaimDto) {

		ActClaimDto actClaimDtoOnlyForRemarks = new ActClaimDto();
		actClaimDtoOnlyForRemarks.setRemarks(assignToClaimDto.getRemarks());
		UserModel uModel = getUserModel();

		if (uModel != null) {
			if (!userService.checkInputData(actClaimDtoOnlyForRemarks)) {
				ActClaimDto actClaimDto = userService.getClaimReqDetails(reqId);
				boolean result = userService.assignToClaimReq(assignToClaimDto, actClaimDto, reqType,
						uModel.getEmpNum(), uModel.getRoleName());

				ClaimHistoryTrailDto claimHistoryTrailDto = new ClaimHistoryTrailDto();
				claimHistoryTrailDto.setRequestId(reqId);
				claimHistoryTrailDto.setClaimCreatedBy(actClaimDto.getCLAIM_SUBMITTED_BY());
				claimHistoryTrailDto.setRemarks(assignToClaimDto.getRemarks());
				claimHistoryTrailDto.setActionTakenBy(uModel.getEmpNum());
				if (uModel.getRoleName().equals("ADMIN")) {
					EmpMaster empMaster = userService.getEmpDetailsByEmpNum(assignToClaimDto.getEmployeeId());
					claimHistoryTrailDto.setAction("Assign To - " + empMaster.getEMP_FIRST_NAME() + " "
							+ (empMaster.getEMP_MIDDLE_NAME() != null ? empMaster.getEMP_MIDDLE_NAME() : "")
							+ (empMaster.getEMP_LAST_NAME() != null ? empMaster.getEMP_LAST_NAME() : "") + " ("
							+ assignToClaimDto.getEmployeeId() + ")");
					claimHistoryTrailDto.setStatus("1");
				} else if (uModel.getRoleName().equals("CPF_ADMIN")) {
					EmpMaster empMaster = userService.getEmpDetailsByEmpNum(assignToClaimDto.getCpfAdminId());
					claimHistoryTrailDto.setAction("Assign To - " + empMaster.getEMP_FIRST_NAME() + " "
							+ (empMaster.getEMP_MIDDLE_NAME() != null ? empMaster.getEMP_MIDDLE_NAME() : "")
							+ (empMaster.getEMP_LAST_NAME() != null ? empMaster.getEMP_LAST_NAME() : "") + "("
							+ assignToClaimDto.getCpfAdminId() + ")");
					claimHistoryTrailDto.setStatus("2");
				}
				userService.saveCpfClaimHistoryTrail(claimHistoryTrailDto, uModel.getEmpNum(), uModel.getRoleName());

				if (result) {
					return "redirect:/claim/pendingReq?reqType=otherReq&operation=updateSuccessfully";
				} else {
					return "redirect:/claim/pendingReq?reqType=otherReq&operation=updateFail";
				}
			} else {
				return "redirect:/claim/actClaimReq?reqType=" + reqType + "&reqId=" + reqId
						+ "&validationMessage=htmltagvalidation";
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/getPurposeOfCPF", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getPurposeOfCPF(@RequestParam String radioValue, HttpSession session) {
		return userService.getPurposeOfCPF(radioValue);
	}

	@RequestMapping(value = { "/uplodCpfDoc" }, method = { RequestMethod.POST })
	public String upload(@RequestParam CommonsMultipartFile file, HttpSession session) {
		// String path=session.getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		try {
			UserModel uModel = getUserModel();
			if (uModel != null) {
				if (filename.toUpperCase().endsWith(".PDF") || filename.toUpperCase().endsWith(".JPG")
						|| filename.toUpperCase().endsWith(".JPEG")) {
					String folderPath = null;
					if (!uModel.getEmpNum().equals("")) {
						// folderPath = "/prodshare/cpf_out/"+uModel.getEmpNum().trim()+"_KYC";//For
						// Production server
						folderPath = "/fapshare/cpf_out/" + uModel.getEmpNum().trim() + "_KYC";// For Dev server
					}
					Path pathLoc = Paths.get(folderPath);
					if (!Files.exists(pathLoc))
						Files.createDirectories(pathLoc);

					logger.info(pathLoc + "/" + filename);

					Boolean saveStatus = userService.saveEmpKycDoc(uModel, pathLoc + "/" + filename);
					if (saveStatus) {
						byte barr[] = file.getBytes();
						BufferedOutputStream bout = new BufferedOutputStream(
								new FileOutputStream(pathLoc + "/" + filename));
						bout.write(barr);
						bout.flush();
						bout.close();
					}
				} else {
					return "redirect:/home?uploadfiletype=Kindly upload proper file formate !!!";
				}
			} else {
				return "redirect:/login";
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/home?uploadfile=" + filename + " file successfully uploaded";
	}

	@RequestMapping(value = { "/downloadCpfDoc" }, method = { RequestMethod.GET })
	public void download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "pathId") String pathId, @RequestParam(name = "fileType") String fileType) {

		String filePath = userService.getUploadedPath(pathId, fileType);

		File file = new File(filePath);
		String filename = file.getName().trim();
		int lastIndex = filename.lastIndexOf(".");

		try {
			UserModel uModel = getUserModel();
			if (uModel != null) {
				Path fl = Paths.get(filePath);
				if (Files.exists(fl)) {
					if (lastIndex != -1 && filename.substring(lastIndex + 1).equals("pdf"))
						response.setContentType("application/pdf");
					else
						response.setContentType("image/jpg");

					response.addHeader("Content-Disposition", "inline; filename=" + filename);

					Files.copy(fl, response.getOutputStream());
					response.getOutputStream().flush();
				} else {
					logger.info("File not found !!!");
				}
			} else {
				logger.info("Unauthorized user try to download the file !!!");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = { "/multiUplodCpfDoc" }, method = { RequestMethod.POST })
	public ResponseEntity<String> multiUpload(@RequestParam CommonsMultipartFile[] files,
			@RequestParam(name = "reqId") String reqId,
			@RequestParam(name = "claimSubmittedBy") String claimSubmittedEmpID,
			@RequestParam(name = "claimAppliedFor", required = false) String claimAppliedFor, HttpSession session) {
		String fileList = "";
		ResponseEntity<String> result = null;
		try {
			UserModel uModel = getUserModel();
			if (uModel != null) {

				String folderPath = null;
				if (!uModel.getEmpNum().equals("")) {
					// folderPath = "/prodshare/cpf_out/"+uModel.getEmpNum().trim()+"_OTHERS";//For Production server
					folderPath = "/fapshare/cpf_out/" + uModel.getEmpNum().trim() + "_OTHERS";// For Dev server
				}
				Path pathLoc = Paths.get(folderPath);
				if (!Files.exists(pathLoc))
					Files.createDirectories(pathLoc);
				
				//pathLoc.toFile().delete();
				Boolean deleteStatus = userService.deleteEmpOtherDoc(uModel, claimSubmittedEmpID, reqId);
				if (deleteStatus) {
					for (CommonsMultipartFile file : files) {
						String filename = file.getOriginalFilename();

						logger.info("File Upload location ::: " + pathLoc + "/" + filename);
						logger.info("claimAppliedFor :::: " + claimAppliedFor);
						Boolean saveStatus = userService.saveEmpOtherDoc(uModel, claimSubmittedEmpID, reqId, claimAppliedFor, pathLoc + "/" + filename);
						if (saveStatus) {
							byte barr[] = file.getBytes();
							BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(pathLoc + "/" + filename));
							bout.write(barr);
							bout.flush();
							bout.close();
						}
						fileList = fileList + filename + ", ";
					}
				}
			} /*
				 * else{ return "redirect:/login"; }
				 */
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		// return "redirect:/claim/actClaimReq?reqId="+reqId+"&uploadfiles="+fileList+"
		// files successfully uploaded";
		result = new ResponseEntity<String>(fileList, HttpStatus.OK);
		return result;
	}

	// Not in used due to version incompatible
	@RequestMapping(value = { "/getMaxPermAmount" })
	public String getMaxPermAmount(@RequestParam(name = "sancType") String sancType,
			@RequestParam(name = "empId") String empId) {
		UserModel uModel = getUserModel();
		String maxPermAmount = null;
		if (uModel != null) {
			maxPermAmount = userService.getMaxPermAmount(empId, sancType);
		} else {
			return "redirect:/login";
		}
		return maxPermAmount;
	}

}