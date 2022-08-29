package com.techm.fci.cpf.daoimpl;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techm.fci.cpf.dao.BaseDao;
import com.techm.fci.cpf.dao.UserDao;
import com.techm.fci.cpf.model.RegisteredUser;

 
@Repository("userDao")
public class UserDaoImpl extends BaseDao<Integer, RegisteredUser> implements UserDao {

	public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
    private SessionFactory sessionFactory;
	
    public RegisteredUser findById(int id) {
        return getByKey(id);
    }
 	
    @Override
    @Transactional
    public RegisteredUser findByEmpNum(String empNum) {
    	RegisteredUser registeredUser = null;
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	if(empNum!=null && !empNum.equals("anonymousUser")){
	    	Criteria crit = createEntityCriteria();
	        crit.add(Restrictions.eq("empNum", empNum));
	        registeredUser=(RegisteredUser) crit.uniqueResult();
    	}
        session.getTransaction().commit();
        return registeredUser;
    }

	@Override
	public RegisteredUser getRegUserDetailsbyMobile(String mobile) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		RegisteredUser regUser = new RegisteredUser();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S",Locale.ENGLISH);
		try{
			String query = "select uan, emp_num, emp_name, emp_phone, role_name, state, emp_email, created_date from cpf_registered_users "
					+ "where emp_phone=:mobile";
			
			Query hQuery = session.createSQLQuery(query).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if (mobile != null) {
				hQuery.setParameter("mobile", mobile);
			}
			List<Map<String, Object>> list = hQuery.list();

			for (Map<String, Object> map : list) {
				regUser.setUan(map.get("UAN").toString());
				regUser.setEmpNum(map.get("EMP_NUM").toString());
				regUser.setEmpName(map.get("EMP_NAME").toString());
				regUser.setEmpPhone(map.get("EMP_PHONE").toString());
				regUser.setUserRole(map.get("ROLE_NAME").toString());
				regUser.setState(map.get("STATE").toString());
				regUser.setEmpEmail(map.get("EMP_EMAIL").toString());
				regUser.setCreatedDate(format.parse(map.get("CREATED_DATE").toString()));
			}
			session.getTransaction().commit();
			
			}catch (RuntimeException re) {
				logger.info("Find by example failed :::", re);
				throw re;
			} catch (ParseException e) {
				
				e.printStackTrace();
			}		
		return regUser;	
	}
}