package com.ssm.framework.cache.factory;

/**
 * 缓存工厂，创建ICache对象
 * 
 * CacheFactory.
 *
 * @author zax
 */
public interface CacheFactory {
    /**
     * 
     * 创建缓存对象
     *
     * @return ICache
     *
     * @author zax
     */
    public ICache buildCache();
    
    /**
     * 
     * 停止cache
     *
     *
     * @author zax
     */
    public void stop();
}
