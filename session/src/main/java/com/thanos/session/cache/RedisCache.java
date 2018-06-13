package com.thanos.session.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import java.util.logging.Logger;

public class RedisCache implements DataCache {

    //定义静态接口类作为实例成员变量，静态变量，同一个类的所有实例共享同一块内存区，配置类用单例即可
    private static DataCache dataCache;

    private Log log = LogFactory.getLog(RedisCache.class);

    public RedisCache() {
        initialize();
    }

    public byte[] set(String key, byte[] value) {
        return dataCache.set(key, value);
    }

    public Long setnx(String key, byte[] value) {
        return dataCache.setnx(key, value);
    }

    public Long expire(String key) {
        return dataCache.expire(key);
    }

    public byte[] get(String key) {
        return dataCache.get(key);
    }

    public long remove(String key) {
        return dataCache.remove(key);
    }

    private void initialize() {
        if (null != dataCache) {
            return;
        }
        //加载配置文件
        Properties properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties rel = null;
        //TODO
        return rel;
    }

}
