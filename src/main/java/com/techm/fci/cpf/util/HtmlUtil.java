package com.techm.fci.cpf.util;

import org.springframework.web.util.HtmlUtils;


public class HtmlUtil {
	
	public static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }
}
