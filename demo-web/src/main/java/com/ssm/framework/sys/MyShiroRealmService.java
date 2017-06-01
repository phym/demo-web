package com.ssm.framework.sys;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.framework.model.user.User;
import com.ssm.framework.service.role.RoleService;
import com.ssm.framework.service.user.UserService;

/**
 * 
 * Real实现
 * 
 * MyShiroRealmService.
 *
 * @author zax
 */
public class MyShiroRealmService extends AuthorizingRealm {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(MyShiroRealmService.class);

    /**
     * 用户Service
     */
    @Autowired
    private UserService userService;

    /**
     * 角色Service
     */
    @Autowired
    private RoleService roleService;

    // public MyShiroRealmService() {
    // super.setAuthenticationCachingEnabled(true);
    // }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        SystemCustomerPrincipalToken systemCustomerPrincipalToken = (SystemCustomerPrincipalToken) authenticationToken;
        SystemCustomerPrincipal systemCustomerPrincipal = systemCustomerPrincipalToken.getSystemCustomerPrincipal();
        User user = systemCustomerPrincipal.getUser();
        if (null != user) {

            return new SimpleAuthenticationInfo(user, user.getLoginPwd(),
                ByteSource.Util.bytes(user.getLoginName().getBytes()), getName());
        } else if (null != null) {// 可做其他对象判断
            return null;
        } else {
            logger.error("==========no user info===========");
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }
    
    protected Object getAvailablePrincipal(PrincipalCollection principals) {
        Object primary = null;
        if (!CollectionUtils.isEmpty(principals)) {
            Collection<?> thisPrincipals = principals.fromRealm(getName());
            if (!CollectionUtils.isEmpty(thisPrincipals)) {
                primary = thisPrincipals.iterator().next();
            } else {
                //no principals attributed to this particular realm.  Fall back to the 'master' primary:
                primary = principals.getPrimaryPrincipal();
            }
        }

        return primary;
    }
}
