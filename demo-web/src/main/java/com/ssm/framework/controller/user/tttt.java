package com.ssm.framework.controller.user;

import org.springframework.cache.annotation.Cacheable;

import com.googlecode.ehcache.annotations.TriggersRemove;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class tttt {
    private static Cache menuCache=CacheManager.getInstance().getCache("menuCache");
    public static void testCache(){
        if(null == menuCache.get("testKey")){
            System.out.println("===============缓存中没有数据==============");
            Element element = new Element("testKey", "testValue");
            menuCache.put(element);
        }else{
            Element element = menuCache.get("testKey");
            System.out.println("key:"+element.getObjectKey().toString() + "=== value:"+element.getObjectValue().toString());
        }
    }
    
    @TriggersRemove(cacheName = { "menuCache" })
    public void testTrigger(){
        
    }
    
}
