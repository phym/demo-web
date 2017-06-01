package com.ssm.framework.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * SystemManager.
 *
 * @author zax
 */
public class SystemManager {

    /**
     * 
     * 获取请求IP地址
     *
     * @param request HttpServletRequest对象
     * 
     * @return String
     *
     * @author zax
     */
    public static String getIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    }
    
    /**
     * 
     * 获取请求IP地址
     *
     * @return String
     *
     * @author zax
     */
    public static String getIP(){
        HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return getIP(request);
    }
}
