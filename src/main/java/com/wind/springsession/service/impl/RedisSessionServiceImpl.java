package com.wind.springsession.service.impl;

import com.wind.springsession.service.RedisSessionService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisSessionServiceImpl implements RedisSessionService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(String name, String wsSessionId) {

        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(name);
        boundValueOperations.set(wsSessionId, 24 * 3600, TimeUnit.SECONDS);
    }

    @Override
    public boolean delete(String name) {

        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection)
                    throws DataAccessException {

                byte[] key = redisTemplate.getStringSerializer().serialize(name);
                return redisConnection.del(key) > 0;
            }
        });
    }

    @Override
    public String get(String name) {

        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(name);
        return (String) boundValueOperations.get();
    }
}
