package com.ssm.framework.cache.ehcache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssm.framework.cache.cachelistener.EhCacheEventListener;
import com.ssm.framework.cache.factory.ICache;
import com.ssm.framework.exception.CustomerEhCacheException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;

/**
 * EhCache
 * 
 * EhCache.
 *
 * @author zax
 */
public class EhCache implements ICache{
    
    private Cache cache;
    
    private EhCacheEventListener ehCacheEventListener;

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(EhCache.class);
    
    public EhCache(Cache cache, EhCacheEventListener ehCacheEventListener){
        Assert.notNull(cache, "Ehcache must not be null");
        Status status = cache.getStatus();
        Assert.isTrue(Status.STATUS_ALIVE.equals(status),
                "An 'alive' Ehcache is required - current cache is " + status.toString());
        this.cache = cache;
        this.ehCacheEventListener = ehCacheEventListener;
        this.cache.getCacheEventNotificationService().registerListener(this.ehCacheEventListener);
    }

    @Override
    public Object get(Object key) {
        try {
            if (null != key) {
                Element element = cache.get(key);
                if (null != element)
                    return element.getObjectValue();
            }
        } catch (CustomerEhCacheException e) {
            logger.error("ehcache get key is empty errorInfo:" + e.getMessage());
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        try {
            Element element = new Element(key, value);
            cache.put(element);
        } catch (CustomerEhCacheException e) {
            logger.error("ehcache put error errorInfo:" + e.getMessage());
        }
    }

    @Override
    public void put(Object key, Object value, int seconds) {
        try {
            Element element = new Element(key, value);
            element.setTimeToLive(seconds);
            cache.put(element);
        } catch (Exception e) {
            logger.error("ehcache put value by seconds error errorInfo:" + e.getMessage());
        }
    }

    @Override
    public void update(Object key, Object value) {
        put(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> keys() {
        return cache.getKeys();
    }

    @Override
    public void evict(Object key) {
        try
        {
            cache.remove(key);
        }catch(CustomerEhCacheException e){
            logger.error("ehcache evict error errorInfo:" + e.getMessage());
        }
    }

    @Override
    public void evict(List<Object> keys) {
        cache.removeAll(keys);
    }

    @Override
    public void clear() {
        cache.removeAll();
    }

    @Override
    public void destroy() {
        cache.getCacheManager().removeCache(cache.getName());
    }

    @Override
    public Long size() {
        return Long.valueOf(cache.getKeys().size());
    }

    @Override
    public List<Object> values() {
        throw new CacheException("Operation not supported!!!");
    }

    @Override
    public Boolean exists(Object key) {
        return cache.isKeyInCache(key);
    }
}
