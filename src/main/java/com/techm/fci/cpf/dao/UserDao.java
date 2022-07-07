package com.techm.fci.cpf.dao;

import com.techm.fci.cpf.model.RegisteredUser;
 
public interface UserDao {
 
	RegisteredUser findById(int id);
     
	RegisteredUser findByEmpNum(String sso);
	
	RegisteredUser getRegUserDetailsbyMobile(String mobile);

}