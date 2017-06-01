package com.ssm.framework.cache.bulid;

import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssm.framework.cache.factory.ICache;
import com.ssm.framework.cache.rediscache.RedisCache;

/**
 * redis实现
 * RedisBuild.
 *
 * @author zax
 */
public class RedisBuild {

    private RedisTemplate<Object, Object> redisTemplate;
    
    private JedisConnection jedisConnection;
    
    private JedisConnectionFactory jedisConnectionFactory;
    
    public RedisBuild(){
        jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();
        if(null == jedisConnection)
            jedisConnection =  (JedisConnection) jedisConnectionFactory.getConnection().getNativeConnection();
    }
    
    public ICache getCache(){
        return new RedisCache(jedisConnection);
    }
   
    public RedisTemplate<Object, Object> getRedisTemplate() {
        return this.redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
