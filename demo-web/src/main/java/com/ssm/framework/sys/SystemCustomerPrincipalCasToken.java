package com.ssm.framework.sys;

import org.apache.shiro.cas.CasToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemCustomerPrincipalCasToken extends CasToken{

    public SystemCustomerPrincipalCasToken(String ticket) {
        super(ticket);
    }
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SystemCustomerPrincipalCasToken.class);
}
