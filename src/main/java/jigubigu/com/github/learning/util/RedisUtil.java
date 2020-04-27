package jigubigu.com.github.learning.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 12:01
 * Redis 使用 Demo
 */

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean sadd(String key, Object[] value){
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
