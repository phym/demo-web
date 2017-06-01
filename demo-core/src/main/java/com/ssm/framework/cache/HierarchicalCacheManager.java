package com.ssm.framework.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.framework.cache.factory.ICache;
import com.ssm.framework.cache.provider.EhCacheProvider;

/**
 * 多级缓存管理
 * HierarchicalCacheManager.
 *
 * @author zax
 */
public class HierarchicalCacheManager {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(HierarchicalCacheManager.class);
    
    /**
     * 几级缓存
     */
    private int level;
    
    private static EhCacheProvider ehCacheProvider;
    
    private String cacheType;
    

    private static ICache getCache(){
        return ehCacheProvider.buildCache();
    }
    
    public static Object get(Object key) {
        return getCache().get(key);
    }

    public static void put(Object key, Object value) {
        getCache().put(key, value);
        
    }

    public static void put(Object key, Object value, int seconds) {
        getCache().put(key, value, seconds);
    }

    public static void update(Object key, Object value) {
        getCache().update(key, value);
    }

    public static List<Object> keys() {
        return getCache().keys();
    }

    public static void evict(Object key) {
        getCache().evict(key);
    }

    public static void evict(List<Object> keys) {
        getCache().evict(keys);
    }

    public static void clear() {
        getCache().clear();
    }

    public static void destroy() {
        getCache().destroy();
    }

    public static Long size() {
        return getCache().size();
    }

    public static List<Object> values() {
        return getCache().values();
    }

    public static Boolean exists(Object key) {
        return getCache().exists(key);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCacheType() {
        return this.cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }
}
