package com.techm.fci.cpf.xss;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.esapi.ESAPI;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private static Pattern[] patterns = new Pattern[]{
    		Pattern.compile("mocha:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),                 					
    		Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),         			          			        					
    		Pattern.compile("<script(.*?)>",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),				         			
    		Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),								
    		Pattern.compile("alert(.*?)\\)", Pattern.CASE_INSENSITIVE),	
    		Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
    		Pattern.compile("href", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("expression\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
    		Pattern.compile("onkey.*\\)", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("onmouse.*\\)", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("onfocus.*\\)", Pattern.CASE_INSENSITIVE),
    		//Pattern.compile("(.*?)\\)", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("eval\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), 
    		Pattern.compile("document[.]+[a-z]+.*\\)", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("on(.*)click.*\\)", Pattern.CASE_INSENSITIVE), 
    		Pattern.compile("\\<(.*?)\\>",Pattern.CASE_INSENSITIVE),
    		Pattern.compile("<.*?>",Pattern.CASE_INSENSITIVE),
    		Pattern.compile("script", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("url(.*?)\\)", Pattern.CASE_INSENSITIVE),
    		Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE)
    };

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {
        if (value != null) {
            value = ESAPI.encoder().canonicalize(value);
            value = value.replaceAll("\0", "");
            // Remove all sections that match a pattern
            for (Pattern scriptPattern : patterns){
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }
}
