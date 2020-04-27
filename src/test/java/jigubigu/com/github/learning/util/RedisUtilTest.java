package jigubigu.com.github.learning.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jigubigu.com.github.learning.entity.Announcement;
import jigubigu.com.github.learning.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 12:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger log = LoggerFactory.getLogger(RedisUtilTest.class);

    @Test
    public void set(){
        Student stu = new Student(1, "叽咕哔咕", 'M');
        redisTemplate.opsForValue().set("student:1001", stu);

        JSONObject b = (JSONObject)redisTemplate.opsForValue().get("student:1001");
        Student s = JSON.toJavaObject(b, Student.class);

        
        log.info(s.toString());
    }

    @Test
    public void get(){
        JSONObject jsonObject = (JSONObject) redisTemplate.opsForValue().get("student:1001");
        Student student = JSON.toJavaObject(jsonObject, Student.class);

        assertEquals(student.getName(), "叽咕哔咕");
        log.info(student.toString());
    }
}