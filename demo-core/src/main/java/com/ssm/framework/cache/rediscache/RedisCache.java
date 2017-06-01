package com.ssm.framework.cache.rediscache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.util.Assert;

import com.ssm.framework.cache.factory.ICache;
import com.ssm.framework.serializer.Serializer;
import com.ssm.framework.serializer.SerializerFactory;
import com.ssm.framework.serializer.SerializerType;

/**
 * 
 * Redis缓存实现
 * RedisCache.
 *
 * @author zax
 */
public class RedisCache implements ICache{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);
    
//    private RedisTemplate<Object, Object> redisTemplate;
    
    private static final Serializer K_SERIALIZER = SerializerFactory.getSerializer(SerializerType.JSON);
    
    private static final Serializer V_SERIALIZER = SerializerFactory.getSerializer(SerializerType.JAVA);
    
    /**
     * LUA脚本
     */
    private static final String DELETE_SCRIPT_IN_LUA = "local keys = redis.call('keys', '%s')"
            + "  for i,k in ipairs(keys) do"
            + "    local res = redis.call('del', k)" + "  end";
    
    private JedisConnection jedisConnection;
    
    public RedisCache(JedisConnection jedisConnection){
        this.jedisConnection  = jedisConnection;
    }
    
    private String serializeObject(Object object)
    {
        
        try {
            return V_SERIALIZER.serialize(object);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return null;
    }
    
    private Object deserializeObject(String str)
    {
        try {
            return V_SERIALIZER.deserialize(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return null;
    }
    
    private String serializeKey(Object key)
    {
        try {
            return K_SERIALIZER.serialize(key);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Object get(Object key) {
        Assert.notNull(key, "key must be not null");
        byte[] bytes = jedisConnection.get(serializeKey(key).getBytes());
        return deserializeObject(new String(bytes));
    }

    @Override
    public void put(Object key, Object value) {
        jedisConnection.set(serializeKey(key).getBytes(), serializeObject(value).getBytes());
    }

    @Override
    public void put(Object key, Object value, int seconds) {
        jedisConnection.setEx(serializeKey(key).getBytes(),seconds, serializeObject(value).getBytes());
    }

    @Override
    public void update(Object key, Object value) {
        put(key, value);
    }

    @Override
    public List<Object> keys() {
        List<Object> list = new ArrayList<Object>();
        Set<byte[]> set = jedisConnection.keys("*".getBytes());
        while(set.iterator().hasNext()){
            list.add(serializeObject(set.iterator().next()));
        }
        return list;
    }

    @Override
    public void evict(Object key) {
        jedisConnection.del(serializeKey(key).getBytes());
    }

    @Override
    public void evict(List<Object> keys) {
        for(Object key:keys){
            evict(key);
        }
    }

    @Override
    public void clear() {
        jedisConnection.eval(DELETE_SCRIPT_IN_LUA.getBytes(), null, keys().size(), "*".getBytes());
    }

    @Override
    public void destroy() {
        this.clear();
    }

    @Override
    public Long size() {
        return (long) keys().size();
    }

    @Override
    public List<Object> values() {
        Assert.isTrue(false,"no operation");
        return null;
    }

    @Override
    public Boolean exists(Object key) {
        if(null == key){
            return false;
        }
        return jedisConnection.exists(serializeKey(key).getBytes());
    }
}
