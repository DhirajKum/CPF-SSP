package com.techm.fci.cpf.service;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techm.fci.cpf.model.RegisteredUser;


@Service("cpfUserDetailsService")
public class CPFUserDetailsService implements UserDetailsService {

	  	@Autowired
	    private UserService userService;
	     
	    @Transactional(readOnly=true)
	    public UserDetails loadUserByUsername(String empNum) throws UsernameNotFoundException {
	    	RegisteredUser user = userService.findByEmpNum(empNum);
	        System.out.println("User : "+user);
	        if(user==null){
	            System.out.println("User not found");
	            throw new UsernameNotFoundException("Username not found");
	        }
	            return new org.springframework.security.core.userdetails.User(user.getEmpNum(), user.getPassword(), user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	    }
	 
	     
	    private List<GrantedAuthority> getGrantedAuthorities(RegisteredUser user){
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	         
	        /*for(UserProfile userProfile : user.getUserProfiles()){
	            System.out.println("UserProfile : "+userProfile);
	            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
	        }*/
	        
	        System.out.println("User : "+user.getUserRole());
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserRole()));
	        
	        System.out.print("authorities :"+authorities);
	        return authorities;
	    }

}