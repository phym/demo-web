package com.ssm.framework.cache.bulid;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.framework.cache.cachelistener.EhCacheEventListener;
import com.ssm.framework.cache.ehcache.EhCache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.ConfigurationFactory;

/**
 * Ehcache实现
 * EhcacheBuild.
 *
 * @author zax
 */
public class EhCacheBuild{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(EhCacheBuild.class);

    /**
     * CacheManager
     */
    private CacheManager cacheManager;
    
    /** 
     *  EhCacheEventListener 自定义监听
     */
    private EhCacheEventListener ehCacheEventListener;
    
    /**
     * 缓存EhCache
     */
    private ConcurrentMap<String, EhCache> cacheMap = new ConcurrentHashMap<String, EhCache>();;
    
    public EhCacheBuild(CacheManager cacheManager){
        this.cacheManager = cacheManager;
        if (getCacheManager() == null) {
            setCacheManager(new CacheManager(ConfigurationFactory.parseConfiguration()));
        }
        initCache();
    }

    /**
     * 
     * 创建Cache
     *
     * @param cache EhCache对象
     * @param cacheName 缓存名称
     * @param cacheManagerName cacheManager名称
     *
     * @author zax
     */
    public void initCache() {
        Status status = getCacheManager().getStatus();
        if (!Status.STATUS_ALIVE.equals(status)) {
            throw new IllegalStateException(
                    "An 'alive' EhCache CacheManager is required - current cache is " + status.toString());
        }
        String[] names = getCacheManager().getCacheNames();
        String name = "";
        for (int i = 0; i < names.length; i++) {
            name = names[i];
            cacheMap.put(name, new EhCache(getCacheManager().getCache(name), getEhCacheEventListener()));
        }
    }
//    
    
    public EhCache getEhCache(String name, EhCacheEventListener ehCacheEventListener){
        Cache cache = cacheManager.getCache(name);
        if(null == cache){
            logger.warn("Could not find configuration [" + name
                + "]; using defaults.");
            cacheManager.addCache(name);
            cache = cacheManager.getCache(name);
            logger.debug("started EHCache region: " + name);
        }
        EhCache ehcache = new EhCache(cache, ehCacheEventListener);
        cacheMap.put(name, ehcache);
        return ehcache;
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
