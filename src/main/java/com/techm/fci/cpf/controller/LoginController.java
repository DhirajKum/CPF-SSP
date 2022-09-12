package com.techm.fci.cpf.controller;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.techm.fci.cpf.dto.ForgotPassword;
import com.techm.fci.cpf.dto.HomeDto;
import com.techm.fci.cpf.dto.LoginForm;
import com.techm.fci.cpf.model.EmpMaster;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.UserModel;
import com.techm.fci.cpf.service.OTPService;
import com.techm.fci.cpf.service.UserService;
import com.techm.fci.cpf.util.AesUtil;

@Controller
public class LoginController {
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private HttpSession session;

	/*@Autowired
	private HttpGetHandler httpGetHandler;*/

	@Autowired
	private UserService userService;
	
	@Autowired
	private OTPService otpService;
	
	String otpValidate="";
	
	private UserModel getUserModel(){
		UserModel uModel = (UserModel)session.getAttribute("userModel");
		return uModel;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(name="operation", required=false) String operation,@RequestParam(name="uploadfile", required=false) String uploadFile, HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Home");
		mv.addObject("clickHome", true);
		mv.addObject("showNavBar",true);
		
		UserModel uModel = getUserModel();
		if(uModel!=null){
		HomeDto homeDto =  userService.getHomePageData(uModel.getEmpNum());
		mv.addObject("homePageData", homeDto);
		
		if(operation != null){
			if(operation.equals("claimSuccess")){
				mv.addObject("message","Claim id "+session.getAttribute("claimId")+" submitted successfully ...");
			}
		}
		if(uploadFile != null){
			mv.addObject("message",uploadFile);
		}
		}else{
			return new ModelAndView("login");
		}
		return mv;
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(ModelMap model, @RequestParam(name = "error", required = false) String error, HttpSession session) {
		
		session.removeAttribute("error");
		SecurityContextHolder.clearContext();
		model.addAttribute("loginForm", new LoginForm());
		Map<String, String> keySaltMap = AesUtil.getkeySaltMap();
		model.addAttribute("keystr", keySaltMap.get("key"));
		model.addAttribute("teststr", keySaltMap.get("salt"));
		if (error != null) {
			model.addAttribute("message", "Invalid user credential !!!");
		}
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
		model.addAttribute("loginForm", new LoginForm());
		return "redirect:/login";
	}

	@RequestMapping(value = "/userRegistration", method = RequestMethod.GET)
	public String userRegistration(@RequestParam(name="operation", required=false) String operation, ModelMap model, HttpSession session) {
		session.removeAttribute("error");
		
		/*model.addAttribute("title", "Registration");
		model.addAttribute("clickRegistration", true);
		model.addAttribute("showNavBar",true);*/
		
		RegisteredUser registeredUser = new RegisteredUser();
		model.addAttribute("regInfo", session.getAttribute("regInfo"));
		model.addAttribute("registeredUser", registeredUser);
		model.addAttribute("otpValidate",otpValidate);
		otpValidate="";
		if(operation != null){
			if(operation.equals("registrationfailed")){
				model.addAttribute("message","Registration Failed...!!! <br>Possible Reasons<br>a) OTP validation failed<br>b) The browser you're using turned off JavaScript.");
			}
		}
		return "registration";
	}

	@RequestMapping(value = "/getEmpDetailsByEmpNum", method = RequestMethod.GET)
	@ResponseBody
	public EmpMaster getEmpDetailsByEmpNum(@RequestParam String empNum, HttpSession session,ModelMap model) {
		EmpMaster empMas = new EmpMaster();
		empMas = userService.getEmpDetailsByEmpNum(empNum);
		if(empMas==null){
			session.setAttribute("empIdStatus", "Employee ID not valid !!!");
		}
		
		return empMas; 
		
	}

	// @RequestMapping(value = "/saveRegistrationData", method =
	// RequestMethod.POST)
	/*
	 * @PostMapping(value = "/saveRegistrationData") public @ResponseBody
	 * Boolean saveRegistrationData(@RequestBody String input, HttpSession
	 * session) { //return userService.getEmpDetailsByEmpNum(input);
	 * System.out.println("In side save registration method");
	 * 
	 * return false; }
	 */

	@RequestMapping(value = "/saveRegistrationData", method = RequestMethod.POST)
	public String saveRegistrationData(@ModelAttribute("registeredUser") RegisteredUser regUser, @RequestParam String js_enabled, HttpSession session) {
		RegisteredUser rUser;
		logger.info("::: In side save registration method :::");
		rUser = userService.checkEmpInRegisteredUserByEmpNum(regUser.getEmpNum());

		if (rUser == null && js_enabled.equals("1")) {
			if (userService.saveRegData(regUser)) {
				return "redirect:/login";
			} else {
				return "redirect:/userRegistration?operation=registrationfailed";
			}
		} else if(js_enabled.equals("0")){
			session.setAttribute("regInfo", "You don't have javascript enabled. Kindly enable it before going through the registration process.");
			return "redirect:/userRegistration";
		} else {
			logger.info("rUser :::: INFO" + rUser.toString());
			session.setAttribute("regInfo", "You Have Already Registered ...!!! Kindaly Go For Login...");
			return "redirect:/userRegistration";
		}
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, HttpSession session) {
		session.removeAttribute("error");
		model.addAttribute("title", "Access Denied");
		return "403";
	}

	/**
	 * @param otpNumber
	 * @return
	 */
	@RequestMapping(value = "/send-otp", method = RequestMethod.GET)
	public ResponseEntity<Boolean> sendOtp(@RequestParam(value = "mobile", required = true) String mobileNumber,@RequestParam(value = "empNum", required = true) String empNum,@RequestParam(value = "empName", required = true) String empName) {
		logger.info("going to send otp number ");
		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		RegisteredUser regUser = userService.findByEmpNum(empNum);
		if(regUser==null){
			RegisteredUser registerUser = userService.getRegUserDetailsbyMobile(mobileNumber);
			if(registerUser.getEmpPhone()==null){
				registerUser.setEmpPhone("0");
			}
			if(registerUser!=null && registerUser.getEmpPhone().equals(mobileNumber)){
				result = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CREATED);
			}else{
				Boolean smsSentStatus = otpService.sendOtpSms(mobileNumber, empNum, empName);
				if (!smsSentStatus) {
					result = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		
		}else{
			result = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.ACCEPTED);
		}
		session.setAttribute("mobile", mobileNumber);
		return result;
	}
	
	
	/**
	 * 
	 * @param otpNumber
	 * @return
	 */
	@RequestMapping(value = "/verify-otp", method = RequestMethod.GET)
	public ResponseEntity<Boolean> verifyOtp(@RequestParam(value ="otp", required = true) String otp) {
		logger.info("Going to check otp number... ");
		String mobileNumber;
		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
		if(session.getAttribute("mobile")!=null){
		mobileNumber = (String)session.getAttribute("mobile");
		Boolean verifyOtpStatus = otpService.verifyOtp(mobileNumber, otp);
		if(!verifyOtpStatus){
			result = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
			otpValidate="FAIL";
			logger.info("OTP verification failed... ");
		}else{
			otpValidate="SUCCESS";			
		}
		}else{
			result = new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
			logger.info("Your session has been expired !!!");
		}
		return result;
	}
	
	@RequestMapping(value = "/sendOTP", method = RequestMethod.GET)
	public String otpSend(ModelMap model, @RequestParam(value="mobileNo", required = false) String mobileNo, 
			@RequestParam(value="regMobileNo", required = false) String regMobileNo,
			@RequestParam(value="empNum", required = false) String empNum, 
			@RequestParam(value="empName", required = false) String empName,
			@RequestParam(value="empEmail", required = false) String empEmail,
			@RequestParam(value="uan", required = false) String uan, HttpSession session) {
		
		session.removeAttribute("error");
		// ModelAndView mv = new ModelAndView("masterpage");
		// model.addAttribute("registrationForm", new Registration());
		model.addAttribute("title", "OTP Verification");
		model.addAttribute("clickOTPVerification", true);
		if(mobileNo!=null && !mobileNo.equals("")){
			model.addAttribute("msg","OTP send successfully on your registered mobile no "+mobileNo);
		}
		/*RegisteredUser rUser = new RegisteredUser();
		rUser.setEmpPhone(regMobileNo);
		rUser.setEmpNum(empNum);
		rUser.setEmpName(empName);
		rUser.setEmpEmail(empEmail);
		rUser.setUan(uan);
		session.setAttribute("rUserData", rUser);*/
		
		session.setAttribute("empNum", empNum);
		session.setAttribute("empName", empName);
		session.setAttribute("empEmail", empEmail);
		session.setAttribute("uan", uan);
		session.setAttribute("regMobileNo", regMobileNo);
		
				
		return "mobileOtp";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(HttpSession session) throws IOException{
		session.removeAttribute("error");
		ForgotPassword changePassword = new ForgotPassword();
		ModelAndView model = new ModelAndView("masterpage");
		model.addObject("title", "Change Password");
		model.addObject("clickChangePass", true);
		model.addObject("changePassword", changePassword);
		//return "changePassword";
		return model;
	}

	@RequestMapping(value = "/changePasswordSubmit", method = RequestMethod.POST)
	public String changePasswordSubmit(ModelMap model, HttpSession session, @ModelAttribute("changePassword") ForgotPassword changePassword) {
		
		logger.info("Going to change old password :::: ");
		UserModel uModel = getUserModel();
		if(uModel!=null){
		Boolean smsSentStatus = userService.changePassword(changePassword.getNewPassword(), getUserModel().getEmpNum());
		if (!smsSentStatus) {
			return "redirect:/changePassword";
		}else{
			return "redirect:/home";
		}
		}else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model, HttpSession session) {
		session.removeAttribute("error");
		ForgotPassword forgotPassword = new ForgotPassword();
		model.addAttribute("otpValidate",otpValidate);
		model.addAttribute("forgotPassword", forgotPassword);
		otpValidate="";
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/forgotSendOtp", method = RequestMethod.GET)
	public ResponseEntity<String> forgotSendOtp(ModelMap model, HttpSession session, @RequestParam(value = "empNum", required = true) String empNum) {

		logger.info("::::: Going to send otp number for forgot password ::::: ");
		
		ResponseEntity<String> result=null;
		RegisteredUser regUser = userService.findByEmpNum(empNum);
		if(regUser!=null){
			Boolean smsSentStatus = otpService.sendOtpSms(regUser.getEmpPhone(), empNum, regUser.getEmpName());
			
			session.setAttribute("mobile",regUser.getEmpPhone());
			if (!smsSentStatus) {
				result = new ResponseEntity<String>("FALSE", HttpStatus.INTERNAL_SERVER_ERROR);
			}else{
				result = new ResponseEntity<String>(regUser.getEmpPhone(), HttpStatus.OK);
			}
		}else{
			result = new ResponseEntity<String>("FALSE", HttpStatus.NOT_FOUND);
			session.setAttribute("empIdStatus", "Employee ID not valid !!!");
		}
		return result;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, HttpSession session, @ModelAttribute("forgotPassword") ForgotPassword forgotPass) {
		logger.info("Going to set forgot password :::: ");
		Boolean smsSentStatus = userService.changePassword(forgotPass.getNewPassword(), forgotPass.getEmpNum());
		if (!smsSentStatus) {
			return "forgotPassword";
		}else{
			return "login";
		}
	}
}
