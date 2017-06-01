package com.ssm.framework.sys;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroSessionListener implements SessionListener {
    
    DefaultSecurityManager securityManager;

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Override
    public void onExpiration(Session arg0) {
        System.out.println("=========onExpiration===========sessionId:"+arg0.getId());
        logger.info("=========onExpiration===========sessionId:"+arg0.getId());
        clearCached(arg0.getId());
    }

    @Override
    public void onStart(Session arg0) {
        logger.info("======onStart====sessionId:" + arg0.getId());
        CacheManager cacheManager = securityManager.getCacheManager();
        Session session = securityManager.getSessionManager().getSession(new DefaultSessionKey(arg0.getId()));
        if(null == session){
            cacheManager.getCache("demo.session.cache.name").clear();
        }
    }

    @Override
    public void onStop(Session arg0) {
        System.out.println("================clearCached====sessionId:"+ arg0.getId());
        logger.info("======clearCached====sessionId:" + arg0.getId());
        clearCached(arg0.getId());
    }
    
    private void clearCached(Serializable sessionId)
    {
        CacheManager cacheManager = securityManager.getCacheManager();
        clearAuthenticationCache(cacheManager, sessionId);
    }
    
    private void clearAuthenticationCache(CacheManager cacheManager,
            Serializable sessionId)
    {
        Collection<Realm> realms = securityManager.getRealms();
        for (Realm realm : realms)
        {
            if (realm instanceof AuthorizingRealm)
            {
                cacheManager.getCache(((AuthorizingRealm) realm).getAuthorizationCacheName())
                        .clear();
                cacheManager.getCache(((AuthenticatingRealm) realm).getAuthenticationCacheName())
                        .remove(sessionId);
            }
            else if (realm instanceof AuthenticatingRealm)
                cacheManager.getCache(((AuthenticatingRealm) realm).getAuthenticationCacheName())
                        .remove(sessionId);
        }
    }
    
    public void setSecurityManager(DefaultSecurityManager securityManager)
    {
        this.securityManager = securityManager;
    }
}
