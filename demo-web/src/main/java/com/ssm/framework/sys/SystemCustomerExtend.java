package com.ssm.framework.sys;

import java.io.Serializable;

/**
 * 用户扩展属性对象
 * 
 * SystemCustomerExtend.
 *
 * @author zax
 */
public class SystemCustomerExtend implements Serializable{
    /** serialVersionUID */
    private static final long serialVersionUID = 7788334332337967645L;
    
    private String IP;
    
    private boolean rememberMe = false;

    public boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }
}
