package jigubigu.com.github.learning.service;

import jigubigu.com.github.learning.util.DistributedLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 18:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LockTestServiceTest {
    private static final String LOCK_NAME = "testLock";
    private Logger logger = LoggerFactory.getLogger(LockTestService.class);
    @Autowired
    private DistributedLockUtil lockUtil;
    @Value("${server.port}")
    private String serverPort;

    @Test
    public void lockTest() throws UnknownHostException {
        logger.info("开始尝试获取锁");
        boolean lockResult = false;
        try {
            String lockValue = InetAddress.getLocalHost().getHostAddress() + ":" + serverPort;
            lockResult = lockUtil.lock(LOCK_NAME, lockValue, 10);

            if(lockResult){
                logger.info(lockValue + "-获取分布式锁成功");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            if(lockResult){
                String lockValue = InetAddress.getLocalHost().getHostAddress() + ":" + serverPort;
                boolean releaseResult = lockUtil.unlock(LOCK_NAME, lockValue);

                if(releaseResult){
                    logger.info(lockValue + "- 释放分布式锁成功");
                }
            }
        }
    }
}