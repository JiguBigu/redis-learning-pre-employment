package jigubigu.com.github.learning.service.impl;

import com.alibaba.fastjson.JSON;
import jigubigu.com.github.learning.entity.Announcement;
import jigubigu.com.github.learning.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 16:53
 * Redis 缓存数据 Demo
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 通知的key
     */
    private static final String ANNO_KEY = "announcement:";
    /**
     * 缓存中代表所有通知的key
     */
    private static final String ANNO_LIST_KEY = "announcementList";


    /**
     * 获取对应通知信息
     * @param id 通知id
     * @return 通知json字符串
     */
    @Override
    public String getAnnouncement(long id) {
        String res = null;
        String key = ANNO_KEY + id;

        if(redisTemplate.hasKey(key)){
            res = (String) redisTemplate.opsForValue().get(key);
        } else {
            //从数据库中获取数据
            //Announcement announcement = AnnouncementMapper.getOne(id);
            Announcement announcement = new Announcement();
            redisTemplate.opsForValue().set(key, announcement, 60 * 10, TimeUnit.SECONDS);
            res = JSON.toJSONString(announcement);
        }
        return res;
    }



    /**
     * 获取所有通知
     */
    @Override
    public String getAllAnnouncement() {
        String res = null;
        boolean contains = redisTemplate.hasKey(ANNO_LIST_KEY);

        if(contains){
            res = (String) redisTemplate.opsForValue().get(ANNO_LIST_KEY);
        } else {
            List<Announcement> list = new ArrayList<>();
            //从数据库中获取数据···
            //······
            res = (String) JSON.toJSON(list);

            //缓存 20分钟
            long time = 60 * 20;
            //若为空值，则缓存 1至 6分钟
            if(res == null){
                time = 60 + (long)(Math.random() * 300);
            }
            redisTemplate.opsForValue().set(ANNO_LIST_KEY, res, time, TimeUnit.SECONDS);
        }
        return res;
    }

    //之后的操作同理，但记得要更新缓存
}
