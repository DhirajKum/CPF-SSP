package com.techm.fci.cpf.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.techm.fci.cpf.dao.UserDao;
import com.techm.fci.cpf.model.RegisteredUser;
import com.techm.fci.cpf.model.UserModel;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	UserDao userDao;
	
	UserModel userModel=null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() throws HibernateException{
		if(session.getAttribute("userModel") == null){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			RegisteredUser rUser =userDao.findByEmpNum(authentication.getName());
			
			if(rUser!=null){
				userModel = new UserModel();
				userModel.setUan(rUser.getUan());
				userModel.setEmpNum(rUser.getEmpNum());
				userModel.setEmpName(rUser.getEmpName());
				userModel.setEmail(rUser.getEmpEmail());
				userModel.setEmpPhone(rUser.getEmpPhone());
				userModel.setRoleName(rUser.getUserRole());
				
				session.setAttribute("userModel", userModel);
				
				return userModel;
			}
		}
		return (UserModel) session.getAttribute("userModel");
	}
}
