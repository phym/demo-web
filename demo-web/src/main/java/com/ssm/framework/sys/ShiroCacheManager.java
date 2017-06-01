package com.ssm.framework.sys;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

public class ShiroCacheManager<K, V> implements CacheManager {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);
    
    private String cacheType = "ehCache";
    
    private EhCacheCacheManager ehCacheManager;
    
    private RedisCacheManager redisCacheManager;
    
    private CacheProvider<K, V> cacheProvider;

    public EhCacheCacheManager getEhCacheManager() {
        return this.ehCacheManager;
    }

    public void setEhCacheManager(EhCacheCacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }
    

    public RedisCacheManager getRedisCacheManager() {
        return this.redisCacheManager;
    }

    public void setRedisCacheManager(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    public CacheProvider<K, V> getCacheProvider() {
        return this.cacheProvider;
    }

    public void setCacheProvider(CacheProvider<K, V> cacheProvider) {
        this.cacheProvider = cacheProvider;
    }
    
    public String getCacheType() {
        return this.cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cache<K, V> getCache(String name) throws CacheException {
        logger.info("============ShiroCacheManager================");
        Cache<K, V> retCache = null;
        org.springframework.cache.Cache cache = null;
        if("redisCache".equals(getCacheType())){
            cache = getRedisCacheManager().getCache(name);
            getCacheProvider().setCache(cache);
            retCache = getCacheProvider().getShiroCache(getCacheType());
        }else{
            cache = getEhCacheManager().getCache(name);
            getCacheProvider().setCache(cache);
            retCache = getCacheProvider().getShiroCache(getCacheType());
        }
        return retCache;
    }
}
