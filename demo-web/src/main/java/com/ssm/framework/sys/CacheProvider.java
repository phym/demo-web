package com.ssm.framework.sys;

import org.apache.shiro.cache.Cache;

public class CacheProvider<K, V> extends ShiroFactoryCache<K, V> {
    
    private org.springframework.cache.Cache cache;

    @Override
    public Cache<K, V> getShiroCache(String cacheType) {
            if(cacheType.equals("redisCache")){
                return new RedisCacheShiro<K, V>((getCache()));
            }
           return new EhCacheShiro<K, V>(getCache());
    }

    public org.springframework.cache.Cache getCache() {
        return this.cache;
    }

    public void setCache(org.springframework.cache.Cache cache) {
        this.cache = cache;
    }
}
