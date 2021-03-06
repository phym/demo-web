package com.ssm.framework.sys;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class EhCacheShiro<K, V> implements Cache<K, V>{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(EhCacheShiro.class);
    
    private org.springframework.cache.Cache cache;
    
    public EhCacheShiro(org.springframework.cache.Cache cache){
        this.cache = cache;
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) throws CacheException {
        logger.info("==========EhCache get key======= key:"+key);
        return (V) getCache().get(key).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        getCache().put(key, value);
        return  null;
    }

    @Override
    public V remove(K key) throws CacheException {
        getCache().evict(key);
        return null;
    }

    @Override
    public void clear() throws CacheException {
        getCache().clear();
    }

    @Override
    public int size() {
        Assert.isTrue(false, "not size method");
        return 0;
    }

    @Override
    public Set<K> keys() {
        Assert.isTrue(false, "not keys method");
        return null;
    }

    @Override
    public Collection<V> values() {
        Assert.isTrue(false, "not values method");
        return null;
    }

    public org.springframework.cache.Cache getCache() {
        return this.cache;
    }

    public void setCache(org.springframework.cache.Cache cache) {
        this.cache = cache;
    }
}
