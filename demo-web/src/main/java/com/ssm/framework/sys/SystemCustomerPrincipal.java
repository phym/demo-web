package com.ssm.framework.sys;

import com.ssm.framework.model.user.User;

/**
 * 
 * 用户信息主体类
 * 
 * SystemCoustemPrincipal.
 *
 * @author zax
 */
public class SystemCustomerPrincipal  extends SystemCustomerExtend{

    /** serialVersionUID */
    private static final long serialVersionUID = -5415673956957290992L;
    
    /**
     * 账户对象
     */
    private User user;

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
