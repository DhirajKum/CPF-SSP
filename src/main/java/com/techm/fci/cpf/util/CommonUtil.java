package com.techm.fci.cpf.util;
/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CommonUtil {
	private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	/*public AuditLogDto getAuditLog(HttpServletRequest request, HttpSession session){
		AuditLogDto auditLogDto = new AuditLogDto();
		String role= (String)session.getAttribute("userrole");
		String userName = (String)session.getAttribute("userName");
		auditLogDto.setUserName(userName);
		auditLogDto.setUserRole(role);
		auditLogDto.setAccessIp(request.getRemoteAddr());
		//auditLogDto.setAccessIp(session.getAttribute("localIp").toString());
		auditLogDto.setHost(request.getRemoteHost());
		return auditLogDto;
	}*/
	
	/**
	 * trying to find the client's ip address
	 * In a proxy environment the IP might be hidden in header X-Real-IP or X-Forwarded-For header
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getRemoteHost(HttpServletRequest request){
		String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-Real-IP");
            if (remoteAddr == null || "".equals(remoteAddr)) {
            	remoteAddr = request.getHeader("X-Forwarded-For");
            	if (remoteAddr == null || "".equals(remoteAddr)) {
            		remoteAddr = request.getRemoteAddr();
            	}
            }
        }
        log.debug("Client IP Address = " + remoteAddr);
        return remoteAddr;
	}
}
