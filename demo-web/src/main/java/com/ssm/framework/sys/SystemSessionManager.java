package com.ssm.framework.sys;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssm.framework.property.CustomizedPropertyPlaceholderConfigurer;


/**
 * 
 * 处理Session相关操作
 * SystemSessionManager.
 *
 * @author zax
 */
public class SystemSessionManager implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /** logger ***/
    private static final Logger logger = LoggerFactory.getLogger(SystemSessionManager.class);
    
    private static SystemSessionManager systemSessionManager = null;

    /**
     * session对象
     */
    private HttpSession session;
    
    /**
     * session key
     */
    private String sessionKey = "loginInfo";
    
    /**
     * 
     * The Constructors Method.
     *
     * @param session
     *
     * @author zax
     */
    private SystemSessionManager(HttpSession session){
        this.session = session;
    } 
    
    /**
     * 
     * 获取Session操作对象
     *
     * @param request HttpServletRequest
     * 
     * @return SystemSessionManager
     *
     * @author zax
     */
    public static SystemSessionManager getContextInstance(HttpServletRequest request) {
        if(null == request){
            request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        }
        if(systemSessionManager == null){
            if (request != null) {
                systemSessionManager = new SystemSessionManager(request.getSession());
            } else {
                systemSessionManager = new SystemSessionManager(null);
            }
        }
        return systemSessionManager;
    }
    
    /**
     * 
     * 获取Session操作对象
     *
     * @param request HttpServletRequest
     * 
     * @return SystemSessionManager
     *
     * @author zax
     */
    public static SystemSessionManager getContextInstance(){
       return getContextInstance(null);
    }
    
    /**
     * 销毁session
     */
    public void destoryContext() {
        this.clear();
        session.invalidate();
        session = null;
    }
    
    /**
     * 
     *
     * 清楚session对象信息
     *
     * @author zax
     */
    public void clear() {
        try {
            session.removeAttribute(sessionKey);
            Enumeration<?> attributes = session.getAttributeNames();
            while (attributes.hasMoreElements()) {
                Object element = attributes.nextElement();
                if (element != null && element instanceof String) {
                    String sessionName = (String) element;
                    session.removeAttribute(sessionName);
                    if (logger.isDebugEnabled()) {
                        logger.debug("clear session:{}", sessionName);
                    }
                }
            }
        } catch (IllegalStateException e) {
            logger.error("exception occurred  when clear session.", e);
        }
    }

    /**
     * 
     * 获取登录信息对象
     *
     * @param session HttpSession
     * 
     * @return SystemCustomerPrincipal
     *
     * @author zax
     */
    public SystemCustomerPrincipal getLoginInfo(){
        SystemCustomerPrincipal systemCustomerPrincipal = null;
        if(null != session){
            systemCustomerPrincipal = (SystemCustomerPrincipal) session.getAttribute(sessionKey);
            if(systemCustomerPrincipal == null){
                String key = CustomizedPropertyPlaceholderConfigurer.getContextProperty("loginInfo");
                setSessionKey(key);
                systemCustomerPrincipal = (SystemCustomerPrincipal) session.getAttribute(key);
            }
        }
        return systemCustomerPrincipal;
    }
    
    /**
     * 
     * 设置登录信息
     *
     * @param loginInfo 登录对象
     *
     * @author zax
     */
    public void setLoginInfo(SystemCustomerPrincipal loginInfo) {
        if(!isLogined()){
            session.setAttribute(sessionKey, loginInfo);
        }
    }
    
    /**
     * 
     * 是否登录
     *
     * @return boolean
     *
     * @author zax
     */
    public boolean isLogined() {
        boolean isLogined = false;
        if (this.getLoginInfo() != null) {
            isLogined = true;
        }
        return isLogined;
    }
    
    /**
     * 
     * 向session对象中添加信息
     *
     * @param key key
     * @param value value
     *
     * @author zax
     */
    public void put(String key, Object value) {
        this.session.setAttribute(key, value);
    }
    
    /**
     * 获取Session里面值
     * 
     * @param key Session的KEY
     * @return 对应的Value
     */
    public Object get(String key) {
        return this.session.getAttribute(key);
    }
    
    /**
     * 删除Session里面的值
     * 
     * @param key Session的Key
     */
    public void remove(String key) {
        this.session.removeAttribute(key);
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
