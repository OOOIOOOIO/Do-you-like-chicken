package com.sh.chicken.global.util.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public <T> T getByClassType(String key, Class<T> valueType) {
        String str = (String)redisTemplate.opsForValue().get(key);
        if(str != null) {
            Object obj = parseStringToObject(str, valueType);
            return (T) obj;
        }
        return null;
    }

    public void putString(String key, Object value, Long expirationTime) {
        if(expirationTime != null){
            Object obj = parseObjectToString(value);
            redisTemplate.opsForValue().set(key, obj, expirationTime, TimeUnit.SECONDS);
        }else{
            Object obj = parseObjectToString(value);
            redisTemplate.opsForValue().set(key, obj);
        }
    }

    public Long putSet(String key, Object value, Long expirationTime) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        if(expirationTime != null){
            Object obj = parseObjectToString(value);
            return redisTemplate.opsForSet().add(key, obj, expirationTime, TimeUnit.SECONDS);

        }else{
            Object obj = parseObjectToString(value);
            return  redisTemplate.opsForSet().add(key, obj);
        }
    }

    public boolean deleteKey(String key){
        return redisTemplate.delete(key);
    }
    public Long removeSetValue(String key, Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();

        return set.remove(key, value);
    }

    public Long totalLike(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();

        return set.size(key);
    }

    public boolean isExists(String key){
        return redisTemplate.hasKey(key);
    }

    public void setExpireTime(String key, long expirationTime){
        redisTemplate.expire(key, expirationTime, TimeUnit.SECONDS);
    }

    public long getExpireTime(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    private Object parseObjectToString(Object data)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;
        try {
            result = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("===== parseObjectToString Error ====");
        }

        return result;
    }

    private <T> Object parseStringToObject(String data, Class<T> valueType)  {
        ObjectMapper objectMapper = new ObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(data, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("==== parseStringToObject Error ====");
        }

        return obj;
    }

}
