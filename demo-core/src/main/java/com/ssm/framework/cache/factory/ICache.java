package com.ssm.framework.cache.factory;

import java.util.List;

public interface ICache {

    public Object get(Object key);
    
    public void put(Object key, Object value);
    
    public void put(Object key, Object value, int seconds);
    
    public void update(Object key, Object value);
    
    public List<Object> keys();
    
    public void evict(Object key);
    
    public void evict(List<Object> keys);
    
    public void clear();
    
    public void destroy();
    
    public Long size();
    
    public List<Object> values();
    
    public Boolean exists(Object key);
}
