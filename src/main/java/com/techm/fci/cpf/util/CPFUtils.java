package com.techm.fci.cpf.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.DigestUtils;

public class CPFUtils {
	
	/**
	 * Epoch Date
	 */
	private static Date epochDate = getEpochDate();
	
	/**
	 * Get Epoch Date
	 */
	public static Date getEpochDate(){
		Calendar epochCalendar = Calendar.getInstance();
		epochCalendar.set(Calendar.YEAR, 1970);
		epochCalendar.set(Calendar.MONTH, 0);
		epochCalendar.set(Calendar.DAY_OF_MONTH, 1);
		epochCalendar.set(Calendar.HOUR_OF_DAY, 0);
		epochCalendar.set(Calendar.MINUTE, 0);
		epochCalendar.set(Calendar.SECOND,0);
		epochCalendar.set(Calendar.MILLISECOND,0);
		return epochCalendar.getTime();
	}
	
	/**
	 * Convert comma separated ids to long array list
	 * @param commaseparatedIds
	 */
	public static List<Long> convertToLongArrayList(String commaSeparatedIds) {
		final String[] strIds = StringUtils.splitWithComma(commaSeparatedIds);
		final List<Long> ids = new ArrayList<Long>();
		for (String cId : strIds) {
			try{
				Long id = new Long(cId);
				ids.add(id);
			}catch(NumberFormatException nfe){
				//do nothing
			}
		}
		return ids;
	}
	
	/**
	 * Convert comma separated ids to long array list
	 * @param commaseparatedIds
	 */
	public static String convertToMySqlValue(Set<String> values) {
		StringBuffer mysqlValueBuffer = new StringBuffer("");
		for (String value : values) {
			mysqlValueBuffer.append("'" + value + "'" + ",");
		}
		String mysqlValue = mysqlValueBuffer.toString();
		mysqlValue = StringUtils.removeLastComma(mysqlValue);
		return mysqlValue;
	}
	
	/**
	 * Get Roles of the user
	 *//*
	public static Iterable<Roles> getRoles(Iterable<UserRoles> userRoles){
		Set<Roles> roles = new HashSet<Roles>();
		for(UserRoles userRole : userRoles){
			roles.add(userRole.getRoles());
		}
		return roles;
	}*/
	
	 /**
     * This method do the md5 hashing
     * @param input
     * @return md5 input
     */
    public static String doMd5Hashing(String input){
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }
	
	/**
	 * Get CPFId - It will always be unique
	 */
	public static String generateCPFId(){
        UUID uuid = UUID.randomUUID();
        String uuidVal = uuid.toString();
        Long timeDiff = System.currentTimeMillis() - epochDate.getTime();
        String finalProsperxId =  timeDiff + uuidVal;
		return doMd5Hashing(finalProsperxId);
	}
	
	/**
	 * generate otp
	 */
	public static int generateOtp(){
        return (int)(Math.random()*900000)+100000;
	}
	
	/**
	 * Get Normalized Email
	 * @param email
	 */
	public static String getNormalizedEmail(String email){
		//Guard Clause
		if(StringUtils.isNull(email)) return email;
		String replacement = Matcher.quoteReplacement("");
		String searchString = Pattern.quote(".");
		if(email.contains("@gmail.com")){
			return email.replaceAll(searchString, replacement);
		}
		return email;
	}
	
	
	public static void main(String[] args){
		System.out.println(getNormalizedEmail("dhiraj.4767@gmail.com"));
		System.out.println(generateOtp());
	}
	

}
