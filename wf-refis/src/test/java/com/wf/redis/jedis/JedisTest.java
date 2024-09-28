package com.wf.redis.jedis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/22 16:35
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    public void setup(){
        jedis = new Jedis("192.168.127.108",6379);
        jedis.auth("000000");
        jedis.select(0);
    }

    @Test
    public void testSet(){
        jedis.set("hello", "wangfeng");
        String value = jedis.get("hello");
        System.out.println(value);
    }
}
