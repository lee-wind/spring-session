package com.wind.springsession.util;

import com.wind.springsession.pojo.CSRF;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CSRFUtil {

    public static final String SUFFIX = "_csrf";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public CSRF create(String key, Object value){

        redisTemplate.opsForValue().set(key, value);

        return new CSRF(key, value);
    }

    public String get(String key){

        return String.valueOf(redisTemplate.opsForValue().get(key));
    }

    public void delete(String key) {

        redisTemplate.delete(key);
    }

}
