package com.wind.springsession.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //==============common==============

    public boolean expire(String key, long time){

        try{
            if(time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key){

        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void delete(String... keys){
        if( keys != null && keys.length > 0){
            if(keys.length == 1){
                redisTemplate.delete(keys[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    //==============get==============

    public Object get(String key){

        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value){

        try{
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time){

        try{
            if(time > 0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long increment(String key, long delta){

        if(delta < 0){
            throw new RuntimeException("delta must > 0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decrease(String key, long delta){

        if(delta < 0){
            throw new RuntimeException("delta must > 0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //===============Map==============
    public Object hashGet(String key, String item){

        return redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hashMapGet(String key){

        return redisTemplate.opsForHash().entries(key);
    }

    public boolean hashMapSet(String key, Map<String, Object> map){

        try{
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public boolean hashMapSet(String key, Map<String, Object> map, long time){

        try{
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hashSet(String key, String item, Object value){

        try{
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hashSet(String key, String item, Object value, long time){

        try{
            redisTemplate.opsForHash().put(key, item, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void hashDelete(String key, Object... item){

        redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hashHasKey(String key, String item){

        return redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hashIncrement(String key, String item, double delta){

        return redisTemplate.opsForHash().increment(key,item, delta);
    }

    public double hashDecrease(String key, String item, double delta){

        return redisTemplate.opsForHash().increment(key, item, -delta);
    }

    //=============set===============

    public Set<Object> setGet(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean setHasKey(String key, Object value){

        try{
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long setSet(String key, Object... values){

        try{
            return redisTemplate.opsForSet().add(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long setSet(String key, long time, Object... values){

        try{
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time > 0){
                expire(key, time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long setGetSize(String key){

        try{
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long setRemove(String key, Object... values){

        try{
            return redisTemplate.opsForSet().remove(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //=============list============

    public List<Object> listGet(String key, long start, long end){

        try{
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public long listGetSize(String key){

        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public  Object listGetByIndex(String key, long index){

        try{
            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean listSet(String key, Object value){

        try{
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean listSet(String key, Object value, long time){

        try{
            redisTemplate.opsForList().rightPush(key, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean listSet(String key, List<Object> value){

        try{
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean listSet(String key, List<Object> value, long time){

        try{
            redisTemplate.opsForList().rightPushAll(key, value);
            if(time > 0){
                expire(key, time);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean listUpdateByIndex(String key, Object value, long index){

        try{
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long listRemove(String key, long count, Object value){

        try{
            return redisTemplate.opsForList().remove(key, count, value);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
