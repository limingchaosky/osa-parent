package com.goldencis.osa;

/**
 * Created by limingchao on 2018/10/11.
 */

import com.goldencis.osa.common.utils.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void redisConfigTest() {
        redisUtil.set("test", "li");
        Assert.assertEquals("li", redisUtil.get("test"));
    }

    @Test
    public void hGetSetTest() {
        redisUtil.hset("HKey", "InnerKey", "v1");
        Assert.assertEquals("v1", redisUtil.hget("HKey", "InnerKey"));
    }

    @Test
    public void expireTest() {
        redisUtil.expire("aaa", 20);
        redisUtil.set("bbb", "222", 20);
    }
}