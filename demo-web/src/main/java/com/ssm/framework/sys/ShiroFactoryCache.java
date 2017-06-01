package com.ssm.framework.sys;

import org.apache.shiro.cache.Cache;

public abstract class ShiroFactoryCache<K, V> {

    public abstract Cache<K, V> getShiroCache(String cacheType);
   
}
