package com.ssm.framework.cache.provider;

import com.ssm.framework.cache.bulid.RedisBuild;
import com.ssm.framework.cache.factory.CacheFactory;
import com.ssm.framework.cache.factory.ICache;

/**
 * 
 * redis创建者
 * RedisProvider.
 *
 * @author zax
 */
public class RedisCacheProvider implements CacheFactory{
    
    @Override
    public ICache buildCache() {
        return new RedisBuild().getCache();
    }

    @Deprecated
    @Override
    public void stop() {
        
    }
}
