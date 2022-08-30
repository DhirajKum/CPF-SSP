package com.techm.fci.cpf.util;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.io.Serializable;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

@Component
public class CPFCommonPropertyFileReader implements Serializable, MessageSourceAware {
	
	private static final long serialVersionUID = -137284886212268916L;
	public static MessageSource messageSource;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		CPFCommonPropertyFileReader.messageSource = messageSource;
	}

	/**
	 * Fetches value corresponding to a key
	 * If nothing is found returns the key itself
	 * @param key
	 * @return value
	 */
	public static String getValue(String key) {
		if (!StringUtils.isNull(key)) {
			return messageSource.getMessage(key, null, Locale.ENGLISH);
		}
		return null;
	}

}
