package com.thanos.session.cache;

import com.thanos.session.constants.RedisConstants;
import com.thanos.session.constants.SessionConstants;
import org.apache.catalina.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.print.attribute.standard.NumberUp;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
        try {
            String filePath = System.getProperty(SessionConstants.CATALINA_BASE).concat(File.separator).concat(SessionConstants.CONF).concat(File.separator).concat(RedisConstants.PROPERTIES_FILE);
            InputStream inputStream = null;
            try {
                inputStream = null != filePath && filePath.length() > 0 && (new File(filePath)).exists() ? new FileInputStream(filePath) : null;
                if (null == inputStream) {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    inputStream = classLoader.getResourceAsStream(RedisConstants.PROPERTIES_FILE);
                }
                rel.load(inputStream);
            }finally {
                inputStream.close();
            }
        }catch (Exception exception) {
            log.error("loadProperties exception : ", exception);
        }
        return rel;
    }

}
