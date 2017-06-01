package com.ssm.framework.cache.provider;

import com.ssm.framework.cache.bulid.EhCacheBuild;
import com.ssm.framework.cache.cachelistener.EhCacheEventListener;
import com.ssm.framework.cache.factory.CacheFactory;
import com.ssm.framework.cache.factory.ICache;

import net.sf.ehcache.CacheManager;

/**
 * Ehcache创建者
 * EhcacheProvider.
 *
 * @author zax
 */
public class EhCacheProvider implements CacheFactory {
    
    private CacheManager cacheManager;
    
    private String cacheName;
    
    private EhCacheEventListener ehCacheEventListener;

    @Override
    public ICache buildCache() {
        return new EhCacheBuild(getCacheManager()).getEhCache(getCacheName(), getEhCacheEventListener());
    }
    
    @Override
    public void stop()
    {
        if (cacheManager != null)
        {
            cacheManager.shutdown();
            cacheManager = null;
        }
    }

    public String getCacheName() {
        return this.cacheName;
    }


    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }


    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public EhCacheEventListener getEhCacheEventListener() {
        return this.ehCacheEventListener;
    }

    public void setEhCacheEventListener(EhCacheEventListener ehCacheEventListener) {
        this.ehCacheEventListener = ehCacheEventListener;
    }
}
