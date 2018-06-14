package com.thanos.session.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCacheUtil {

    private JedisPool jedisPool;

    private static int NUM_RETRIES = 3;

    private Log log = LogFactory.getLog(RedisCacheUtil.class);

//    public RedisCacheUtil(String host, int port, String password, int database,int timeout, JedisPoolConfig jedisPoolConfig) {
//        jedisPool = new JedisPool(jedisPoolConfig, host,)
//    }
}
