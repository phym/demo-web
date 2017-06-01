package com.ssm.framework.sys;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.ssm.framework.utils.ConstantDef;

public class MyCredentialsMatcher extends HashedCredentialsMatcher{
    
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(MyCredentialsMatcher.class);
    
    /**
     * 登录次数
     */
    private Cache passwordRetryCache;
    /**
     * 
     * 获取缓存中的登录次数
     * The Constructors Method.
     *
     * @param cacheManagerName 缓存的名称
     *
     * @author zax
     */
    public MyCredentialsMatcher(CacheManager cacheManager){
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String userName = token.getPrincipal();
        Object obj = token.getPrincipal();
        SystemCustomerPrincipal s = (SystemCustomerPrincipal) obj;
        String userName = s.getUser().getLoginName();
        AtomicInteger retryCount = passwordRetryCache.get(userName,AtomicInteger.class);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userName,retryCount);
        }
        if (retryCount.incrementAndGet() > ConstantDef.INT_VALUE.INT_FIVE) {
            logger.error("===error: login gt five==========");
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches){
            passwordRetryCache.evict(userName);
        }
        return matches;
    }
}
