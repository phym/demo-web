package com.ssm.framework.sys;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemCustomerPrincipalToken extends UsernamePasswordToken {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SystemCustomerPrincipalToken.class);
    
    private SystemCustomerPrincipal systemCustomerPrincipal;

    public SystemCustomerPrincipal getSystemCustomerPrincipal() {
        return this.systemCustomerPrincipal;
    }

    public void setSystemCustomerPrincipal(SystemCustomerPrincipal systemCustomerPrincipal) {
        this.systemCustomerPrincipal = systemCustomerPrincipal;
    }

    public SystemCustomerPrincipalToken(final SystemCustomerPrincipal userBean, final String password,
        final boolean rememberMe, final String host) {
        logger.info("=========init SystemCustomerPrincipalToken==============");
        this.systemCustomerPrincipal = userBean;
        super.setPassword(password.toCharArray());
        super.setRememberMe(rememberMe);
        super.setHost(host);
        if (this.systemCustomerPrincipal != null) {
            if (this.systemCustomerPrincipal.getUser() != null) {
                super.setUsername(userBean.getUser().getUserNo());
            }
        }
    }

    public SystemCustomerPrincipalToken(final SystemCustomerPrincipal userBean, final String password,
        final boolean rememberMe) {
        this(userBean, password, rememberMe, null);
    }

    @Override
    public Object getPrincipal() {
        return getSystemCustomerPrincipal();
    }
}
