package com.techm.fci.cpf.util;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

    final static String  CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    /**
     * Checks whether a String value is empty or null
     * @param value
     */
    public static boolean isNull(String value) {
        if (value == null || value.equals("") || value.trim().equals("") || value.equals("null")){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks whether a String value is empty or null
     * @param value
     */
    public static boolean isNotNull(String value) {
        return !isNull(value);
    }

    /**
     *
     * @param object1
     * @param object2
     * @return
     */
    public static boolean equals(Object object1,Object object2) {
        if(object1 == null || object2 == null){
            return Boolean.FALSE;
        }
        return object1.equals(object2);
    }

    /**
     * Removes Last Comma If Present
     */
    public static String removeLastComma(String value){
        if(StringUtils.isNotNull(value)){
            int index = value.lastIndexOf(",");
            value = value.substring(0, index);
        }
        return value;
    }
    
    /**
     * Get only numbers out of string
     */
    public static String getOnlyNumbers(String input){
    	//Guard Clause
    	if(StringUtils.isNull(input)) return input;
    	return input.replaceAll("\\D+",""); 
    }

    /**
     * This method splits any comma, space and arrow separated string.
     * @param str
     * @return array of string
     */
    public static String[] split(String str) {
        final String REGEX = "\\s*(\\s|=>|,)\\s*";
        final Pattern p = Pattern.compile(REGEX);
        return p.split(str);
    }

    /**
     * Split sting with commas
     * @param str
     * @return
     */
    public static String[] splitWithComma(String str) {
        final String REGEX = ",\\s*";
        final Pattern p = Pattern.compile(REGEX);
        return p.split(str);
    }

    public static String cleanEmailString(String email) {
        if(isNotNull(email)){
            email = email.replaceAll("[;\\s\\,]+",",");
        }

        return email;
    }

    public static boolean isValidEmailAddress(String aEmailAddress){
        //Regex to restrict leading, trailing, or consecutive dots in emails
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(aEmailAddress);
        return matcher.matches();
    }
    /**
     * This method generates random string
     * @return String
     */
    public static String generateRandomString(int RANDOM_STRING_LENGTH){
        String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    /**
     * This method generates random numbers
     * @return int
     */
    private static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    
    public static void main(String[] args) {
		String input = "abc123rrr456+";
		System.out.println(getOnlyNumbers(input));
	}

}