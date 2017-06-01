package com.ssm.framework.cache.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.framework.cache.cachelistener.EhCacheEventListener;
import com.ssm.framework.cache.factory.ICache;
import com.ssm.framework.cache.provider.RedisCacheProvider;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 
 * 
 * EhCacheListener.
 *
 * @author zax
 */
public class EhCacheListener implements EhCacheEventListener {
    
    private RedisCacheProvider redisCacheProvider;
    
    private ICache getCache(){
        return getRedisCacheProvider().buildCache();
    }

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(EhCacheListener.class);

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
        logger.info("====notifyElementRemoved===");
        getCache().evict(element.getObjectKey());
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
        logger.info("====notifyElementPut=== key:"+element.getObjectKey());
        getCache().put(element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
        logger.info("====notifyElementUpdated=== key:"+element.getObjectKey());
        getCache().update(element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        logger.info("====notifyElementExpired===");
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        logger.info("====notifyElementEvicted=== key:"+element.getObjectKey());
        getCache().evict(element.getObjectKey());
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
        getCache().destroy();

    }

    @Override
    public void dispose() {
        getCache().destroy();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean notifyElementGet(Ehcache cache, Element element) throws CacheException {
        if(null == element.getObjectValue()){
            logger.info("============notifyElementGet=============");
            Object objVal = getCache().get(element.getObjectKey());
            logger.info("============Ehcache not val change for redis=============" + element.getObjectKey());
            Element newElement = new Element(element.getObjectKey(), objVal);
            cache.put(newElement);
            logger.info("============Ehcache put val=============key:{0}"+element.getObjectKey()+"==val:"+objVal);
        }
        return true;
    }

    public RedisCacheProvider getRedisCacheProvider() {
        return this.redisCacheProvider;
    }

    public void setRedisCacheProvider(RedisCacheProvider redisCacheProvider) {
        this.redisCacheProvider = redisCacheProvider;
    }
    
}
