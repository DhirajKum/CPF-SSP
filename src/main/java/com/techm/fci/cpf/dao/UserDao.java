package com.techm.fci.cpf.dao;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import com.techm.fci.cpf.model.RegisteredUser;
 
public interface UserDao {
 
	RegisteredUser findById(int id);
     
	RegisteredUser findByEmpNum(String sso);
	
	RegisteredUser getRegUserDetailsbyMobile(String mobile);

}