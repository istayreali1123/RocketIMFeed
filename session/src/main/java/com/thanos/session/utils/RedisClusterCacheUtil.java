package com.thanos.session.utils;

import com.thanos.session.cache.DataCache;
import com.thanos.session.constants.RedisConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisClusterMaxRedirectionsException;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Set;

public class RedisClusterCacheUtil implements DataCache {
    //jedis cluster
    private JedisCluster jedisCluster;

    private static final int NUM_RETRIES = 30;

    private static final int MAX_REDIRECTIONS = 5;

    private final Log log = LogFactory.getLog(RedisClusterCacheUtil.class);

    public RedisClusterCacheUtil(Set<HostAndPort> hostAndPorts, String password, int timeout, JedisPoolConfig config) {
        jedisCluster = new JedisCluster(hostAndPorts, timeout, Protocol.DEFAULT_TIMEOUT, MAX_REDIRECTIONS, password, config);
    }

    public byte[] set(String key, byte[] value) {
        int tries = 0;
        boolean success = false;
        String retStr = null;
        do {
            ++tries;
            try {
                retStr = jedisCluster.set(key.getBytes(), value);
                success = true;
            }catch (JedisClusterMaxRedirectionsException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                //需要等待重试
                waitForFailover();
            }catch (JedisConnectionException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }
        }while (!success && tries < NUM_RETRIES);
        return (null != retStr) ? retStr.getBytes() : null;
    }

    public Long setnx(String key, byte[] value) {
        int tries = 0;
        boolean success = false;
        Long rel = null;
        do {
            ++tries;
            try {
                rel = jedisCluster.setnx(key.getBytes(), value);
                success = true;
            }catch (JedisClusterMaxRedirectionsException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }catch (JedisConnectionException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
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
                rel = jedisCluster.expire(key, seconds);
                success = true;
            }catch (JedisClusterMaxRedirectionsException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }catch (JedisConnectionException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }
        }while (!success && tries < NUM_RETRIES);

        return rel;
    }

    public byte[] get(String key) {
        int tries = 0;
        boolean success = false;
        String rel = null;
        do {
            ++tries;
            try {
                rel = jedisCluster.get(key);
                success = true;
            }catch (JedisClusterMaxRedirectionsException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }catch (JedisConnectionException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }
        }while (!success && tries < NUM_RETRIES);

        return (null != rel) ? rel.getBytes() : null;
    }

    public Long delete(String key) {
        int tries = 0;
        boolean success = false;
        Long rel = null;
        do {
            ++tries;
            try {
                rel = jedisCluster.del(key);
                success = true;
            }catch (JedisClusterMaxRedirectionsException exception) {
                log.error(RedisConstants.CONN_FAILED_RETRY_MSG + tries);
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }catch (JedisConnectionException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
                waitForFailover();
            }
        }while (!success && tries < NUM_RETRIES);

        return rel;
    }


    //重试方法
    private void waitForFailover() {
        try {
            Thread.sleep(4000);
        }catch (Exception exception) {
            Thread.currentThread().interrupt();
        }
    }

}
