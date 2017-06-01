package com.ssm.framework.sys;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 只是为了使用Shiro的自定义Filter
 * 
 * ShiroAuthenticationFilter.
 *
 * @author zax
 */
public class ShiroAuthenticationFilter extends PermissionsAuthorizationFilter {
    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(ShiroAuthenticationFilter.class);
    
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
        throws IOException {
        logger.info("==============ShiroAuthenticationFilter begin====================");
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;
        boolean isPermitted = true;
        if ((perms != null) && (perms.length > 0)) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else if (!subject.isPermittedAll(perms)) {
                isPermitted = false;
            }
        }
        logger.info("==============ShiroAuthenticationFilter end====================");
        return isPermitted;
    }
}
