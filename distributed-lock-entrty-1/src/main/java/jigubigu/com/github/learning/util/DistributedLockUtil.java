package jigubigu.com.github.learning.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 18:16
 */
@Component
public class DistributedLockUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean lock(String lockName, String lockValue, long expirationTime){
        boolean res = false;
        try {
            res = redisTemplate.opsForValue().setIfAbsent(lockName, lockValue);
            if(res){
                redisTemplate.expire(lockName, expirationTime, TimeUnit.SECONDS);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean unlock(String lockName, String lockValue){
        boolean res = false;
        try {
            if(lockValue.equals(redisTemplate.opsForValue().get(lockName))){
                return redisTemplate.delete(lockName);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
