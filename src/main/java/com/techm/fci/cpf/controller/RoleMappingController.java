package com.techm.fci.cpf.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.techm.fci.cpf.dto.DropdownDto;
import com.techm.fci.cpf.dto.RevokeRoleStatusDto;
import com.techm.fci.cpf.dto.RoleMappingBean;
import com.techm.fci.cpf.model.RoleMappingRequest;
import com.techm.fci.cpf.model.UserModel;
import com.techm.fci.cpf.service.RoleMappingService;

@Controller
@RequestMapping(value = "/role")
public class RoleMappingController {

	public static final Logger logger = LoggerFactory.getLogger(RoleMappingController.class);

	@Autowired
	private HttpSession session;

	@Autowired
	private RoleMappingService roleMappingService;

	private UserModel getUserModel() {
		UserModel uModel = (UserModel) session.getAttribute("userModel");
		return uModel;
	}

	/*
	 * @RequestMapping(value = "/roleMapping", method = RequestMethod.GET)
	 * public String roleMapping(ModelMap model, HttpSession session) {
	 * session.removeAttribute("error"); model.addAttribute("title",
	 * "RoleMapping"); model.addAttribute("clickRoleMapping", true);
	 * model.addAttribute("showNavBar",true);
	 * 
	 * RoleMappingBean roleMappingBean = new RoleMappingBean();
	 * model.addAttribute("regInfo", session.getAttribute("regInfo"));
	 * model.addAttribute("registeredUser", roleMappingBean);
	 * 
	 * return "rolemapping"; }
	 */

	@RequestMapping(value = { "/roleMapping" })
	public ModelAndView roleMapping() {

		ModelAndView mv = new ModelAndView("masterpage");
		session.removeAttribute("error");
		mv.addObject("title", "RoleMapping");
		mv.addObject("clickRoleMapping", true);
		mv.addObject("showNavBar", true);

		RoleMappingRequest roleMappingRequest = new RoleMappingRequest();
		mv.addObject("regInfo", session.getAttribute("regInfo"));
		mv.addObject("roleMapping", roleMappingRequest);
		return mv;
	}

	@RequestMapping(value = "/getEmpRoleDetailsByEmpNum", method = RequestMethod.GET)
	@ResponseBody
	public RoleMappingBean getRoleEmpDetailsByEmpNum(@ModelAttribute("roleMapping") RoleMappingBean rolemap,
			HttpSession session) {
		UserModel uModel = getUserModel();
		if (uModel != null) {
			rolemap = roleMappingService.getRoleMappingBeanDetails(rolemap.getEmpNum());
			if (rolemap.getEmpName() == null) {
				rolemap.setEmpName("noname");
			}
		}
		return rolemap;
	}

	@RequestMapping(value = "/getAllParentLocation", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getAllParentLocationList() {
		return roleMappingService.getAllParentLocationList();
	}

	@RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
	@ResponseBody
	public List<DropdownDto> getUnitLocationList() {
		return roleMappingService.getAllLocationList();
	}

	@RequestMapping(value = { "/saveRoleMappingRequest" }, method = { RequestMethod.POST })
	public String saveRoleMappingRequestData(@ModelAttribute("roleMapping") RoleMappingRequest roleMappingRequest) {
		logger.info("::::: In side save role mapping request method :::::");
		UserModel uModel = getUserModel();
		if (uModel != null) {
			RoleMappingRequest roleMappingReq = new RoleMappingRequest();
			roleMappingReq = roleMappingService.saveRoleMappingReq(roleMappingRequest, roleMappingRequest.getEMPID());

			if (roleMappingReq != null) {
				if (roleMappingReq.getINFO5().equalsIgnoreCase("duplicate")) {
					return "redirect:/role/roleMappingSuccess?operation=duplicate";
				}
				return "redirect:/role/roleMappingSuccess?operation=roleSuccess";
			} else {
				return "redirect:/role/roleMappingSuccess?operation=failed";
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = { "/raiseRoleMappingReq" })
	public ModelAndView raiseRoleMappingReq(@RequestParam(name = "operation", required = false) String operation) {
		// DateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy",
		// Locale.ENGLISH);
		// CpfClaimRequest cpfClaimReq = new CpfClaimRequest();
		// ActClaimDto cpfClaimReq = new ActClaimDto();
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "Raise Role Mapping Request");
		mv.addObject("clickRoleMapping", true);
		mv.addObject("showNavBar", true);

		UserModel uModel = getUserModel();
		if (uModel == null) {
			mv.addObject("message", "Session Expired, Kindly Go For Login !!!");
		}

		// mv.addObject("claimData",cpfClaimReq);

		if (operation != null) {
			if (operation.equals("failed")) {
				mv.addObject("message", "Something Wrong Contact Admin ...!");
			}
			if (operation.equals("roleSuccess")) {
				mv.addObject("message", "Role Mapping Saved SuccessFully ...!");
			}
		}
		return mv;
	}

	@RequestMapping(value = "/roleMappingSuccess", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(name = "operation", required = false) String operation, HttpSession session)
			throws IOException {

		ModelAndView mv = new ModelAndView("masterpage");
		session.removeAttribute("error");
		mv.addObject("title", "RoleMapping");
		mv.addObject("clickRoleMapping", true);
		mv.addObject("showNavBar", true);

		RoleMappingRequest roleMappingRequest = new RoleMappingRequest();
		mv.addObject("regInfo", session.getAttribute("regInfo"));
		mv.addObject("roleMapping", roleMappingRequest);

		if (operation != null) {
			if (operation.equals("roleSuccess")) {
				mv.addObject("message", "Role Assigned submited successfully ...");
			} else if (operation.equals("duplicate")) {
				mv.addObject("message", "Given Role Already Assigned ...");
			} else {
				mv.addObject("message", "Role Assigned Failed ...");
			}
		}
		return mv;
	}

	@RequestMapping(value = { "/viewRevokeRole" })
	public ModelAndView viewRoleAssignedUser(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("masterpage");
		mv.addObject("title", "All User Roles");
		mv.addObject("clickRevokeRole", true);
		mv.addObject("showNavBar", true);
		UserModel uModel = getUserModel();
		mv.addObject("userModel", uModel);

		if (operation != null) {
			if (operation.equals("updateSuccessfully")) {
				mv.addObject("message", "Role Revoked successfully !!!");
			}
			if (operation.equals("updateFail")) {
				mv.addObject("message", "Role Revoked failed !!!");
			}
		}
		return mv;
	}

	@RequestMapping(value = "/getAsignedRoleUser")
	public @ResponseBody List<RevokeRoleStatusDto> getAllAssignedRoleUser(HttpSession session) {

		List<RevokeRoleStatusDto> roleStatus = new ArrayList<RevokeRoleStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			roleStatus = roleMappingService.getAllAssignedRoleUser();
		}
		return roleStatus;
	}

	@RequestMapping(value = "/")
	public @ResponseBody List<RevokeRoleStatusDto> userRoleRevoke(HttpSession session) {

		List<RevokeRoleStatusDto> roleStatus = new ArrayList<RevokeRoleStatusDto>();
		UserModel uModel = getUserModel();
		if (uModel != null) {
			// roleStatus = roleMappingService.getAllAssignedRoleUser();
		}
		return roleStatus;
	}

	@RequestMapping(value = { "/revokeRole" })
	public String revokeRoleReq(@RequestParam(name = "reqId", required = true) String reqId) {
		boolean result = false;
		if (reqId != null) {
			result = roleMappingService.revokeSelectedUserRole(reqId);
		}
		if (result)
			return "redirect:/role/viewRevokeRole?operation=updateSuccessfully";
		else
			return "redirect:/role/viewRevokeRole?operation=updateFail";
	}

	/*
	 * @RequestMapping(value = "/getEmpDetailsByEmpNum", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public EmpMaster getEmpDetailsByEmpNum(@RequestParam String
	 * empNum, HttpSession session) { return
	 * userService.getEmpDetailsByEmpNum(empNum); }
	 */

	/*
	 * private List<Menu> getMenuForRole(String roleString) { List<Menu> menus =
	 * new ArrayList<Menu>(); // String roleString = StringUtils.join(roles,
	 * ','); roleString = roleString.replaceAll("ROLE_", ""); String restUrl =
	 * env.getProperty("mciservice.base.url") + "getUserMenu?roles=" +
	 * roleString; System.out.println("URL = " + restUrl);
	 * 
	 * //String menuDetails = httpGetHandler.getRequest(restUrl); String
	 * menuDetails =null; JSONArray arr = new JSONArray(menuDetails); for (int i
	 * = 0; i < arr.length(); i++) { JSONObject obj = arr.getJSONObject(i);
	 * String label = obj.getString("label"); String text =
	 * obj.getString("text"); String url = obj.getString("url"); Menu menu = new
	 * Menu(label, text, url); JSONArray subArr = obj.getJSONArray("subMenus");
	 * List<Menu> subMenus = new ArrayList<Menu>(); for (int j = 0; j <
	 * subArr.length(); j++) { JSONObject subObj = subArr.getJSONObject(j);
	 * String subLabel = subObj.getString("label"); String subText =
	 * subObj.getString("text"); String subUrl = subObj.getString("url"); Menu
	 * subMenu = new Menu(subLabel, subText, subUrl); subMenus.add(subMenu); }
	 * menu.setSubMenus(subMenus); menus.add(menu); }
	 * 
	 * return menus; }
	 */

}
