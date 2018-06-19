package com.thanos.session.utils;

import com.thanos.session.cache.DataCache;
import com.thanos.session.constants.RedisConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class RedisCacheUtil implements DataCache {

    private JedisPool jedisPool;

    private static int NUM_RETRIES = 3;

    private Log log = LogFactory.getLog(RedisCacheUtil.class);

    public RedisCacheUtil(String host, int port, String password, int database,int timeout, JedisPoolConfig jedisPoolConfig) {
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }

    //implement Datache interface method
    public byte[] set(String key, byte[] value) {
        int tries = 0;
        boolean success = false;
        String retStr = null;
        do {
            ++tries;
            try {
                Jedis jedis = jedisPool.getResource();
                retStr = jedis.set(key.getBytes(), value);
                //remeber to close stream
                jedis.close();
                success = true;
            }catch (JedisException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
            }
        }while (!success && tries < NUM_RETRIES);
        return (null != retStr) ? retStr.getBytes() : null;
    }

    //Long is class extends Number
    public Long setnx(String key, byte[] value) {
        int tries = 0;
        boolean success = false;
        Long rel = null;
        do {
            ++tries;
            try {
                Jedis jedis = jedisPool.getResource();
                rel = jedis.setnx(key.getBytes(), value);
                jedis.close();
                success = true;
            } catch (JedisException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw  exception;
                }
            }
        }while (!success && tries < NUM_RETRIES);

        return rel;
    }

    public Long expire(String key, int seconds) {
        int tries = 0;
        boolean success = false;
        Long rel = null;
        do {
            ++tries;
            try {
                Jedis jedis = jedisPool.getResource();
                rel = jedis.expire(key, seconds);
                jedis.close();
                success = true;
            } catch (JedisException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw  exception;
                }
            }
        }while (!success && tries < NUM_RETRIES);
        return rel;
    }

    public byte[] get(String key) {
        int tries = 0;
        boolean success = false;
        String retStr = null;
        do {
            ++tries;
            try {
                Jedis jedis = jedisPool.getResource();
                jedis.get(key);
                //remeber to close stream
                jedis.close();
                success = true;
            }catch (JedisException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
            }
        }while (!success && tries < NUM_RETRIES);
        return (null != retStr) ? retStr.getBytes() : null;
    }

    public Long delete(String key) {
        int tries = 0;
        boolean success = false;
        long rel = 0;
        do {
            ++tries;
            try {
                Jedis jedis = jedisPool.getResource();
                rel = jedis.del(key);
                //remeber to close stream
                jedis.close();
                success = true;
            }catch (JedisException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
            }
        }while (!success && tries < NUM_RETRIES);
        return rel;
    }
}
