package com.thanos.session.cache;

import com.thanos.session.constants.RedisConstants;
import com.thanos.session.constants.SessionConstants;
import com.thanos.session.utils.RedisCacheUtil;
import com.thanos.session.utils.RedisClusterCacheUtil;
import org.apache.catalina.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import javax.print.attribute.standard.NumberUp;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
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

    public Long expire(String key, int seconds) {
        return dataCache.expire(key, seconds);
    }

    public byte[] get(String key) {
        return dataCache.get(key);
    }

    public Long delete(String key) {
        return dataCache.delete(key);
    }

    private void initialize() {
        if (null != dataCache) {
            return;
        }
        //加载配置文件
        Properties properties = loadProperties();
        //判断配置文件具体参数
        //是否集群模式
        Boolean clusterEnabled = Boolean.valueOf(properties.getProperty(RedisConstants.CLUSTER_ENABLED, RedisConstants.DEFAULT_CLUSTER_ENABLED));
        //host and password
        String hosts = properties.getProperty(RedisConstants.HOST, Protocol.DEFAULT_HOST.concat(":").concat(String.valueOf(Protocol.DEFAULT_PORT)));
        String password = properties.getProperty(RedisConstants.PASSWORD);
        password = (null != password && password.length() > 0) ? password : null;
        //database and timeout
        int dataBase = Integer.parseInt(properties.getProperty(RedisConstants.DATABASE, String.valueOf(Protocol.DEFAULT_DATABASE)));
        int timeout = Integer.parseInt(properties.getProperty(RedisConstants.TIMEOUT, String.valueOf(Protocol.DEFAULT_TIMEOUT)));
        timeout = timeout < Protocol.DEFAULT_TIMEOUT ? Protocol.DEFAULT_TIMEOUT : timeout;
        //get jedis nodes
        Collection<? extends Serializable> redisNodes = getJedisNodes(hosts, clusterEnabled);

        if (clusterEnabled) {
            dataCache = new RedisClusterCacheUtil((Set<HostAndPort>)redisNodes, password, timeout, getPoolConfig(properties));
        }else {
            dataCache = new RedisCacheUtil(((List<String>)redisNodes).get(0),  Integer.parseInt(((List<String>)redisNodes).get(1)), password, dataBase, timeout, getPoolConfig(properties));
        }
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

//此类实现了Serilizable的接口，就可继承Serializable接口
    private Collection<? extends Serializable> getJedisNodes(String hosts, boolean clusterEnabled) {
        if (null != hosts && hosts.length() > 0) {
            //List与Set都为Collection，可以通过泛型直接返回
            //去掉所有空格
            hosts = hosts.replaceAll("\\s", "");
            List<String> node = null;
            Set<HostAndPort> nodes = null;
            //10.150.55.2:8080,10.150.55.2:8080
            String[] hostsList = hosts.split(",");
            for (String host : hostsList) {
                String[] hostAndPort = host.split(":");
                if (clusterEnabled) {
                    nodes = (null == nodes) ? new HashSet<HostAndPort>() : null;
                    nodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
                }else {
                    //非集群模式，只解析一个节点
                    int port = Integer.parseInt(hostAndPort[1]);
                    if (hostAndPort[0].length() > 0 && port > 0) {
                        node = (null == node) ? new ArrayList<String>() : node;
                        node.add(hostAndPort[0]);
                        node.add(hostAndPort[1]);
                        break;
                    }
                }
            }
            return clusterEnabled ? nodes : node;

        }
        return null;
    }

    /**
     * To get jedis pool configuration
     *
     * @param properties
     * @return
     */
    private JedisPoolConfig getPoolConfig(Properties properties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        int maxActive = Integer.parseInt(properties.getProperty(RedisConstants.MAX_ACTIVE, RedisConstants.DEFAULT_MAX_ACTIVE_VALUE));
        poolConfig.setMaxTotal(maxActive);
//
        boolean testOnBorrow = Boolean.parseBoolean(properties.getProperty(RedisConstants.TEST_ONBORROW, RedisConstants.DEFAULT_TEST_ONBORROW_VALUE));
        poolConfig.setTestOnBorrow(testOnBorrow);
//
        boolean testOnReturn = Boolean.parseBoolean(properties.getProperty(RedisConstants.TEST_ONRETURN, RedisConstants.DEFAULT_TEST_ONRETURN_VALUE));
        poolConfig.setTestOnReturn(testOnReturn);
//
        int maxIdle = Integer.parseInt(properties.getProperty(RedisConstants.MAX_ACTIVE, RedisConstants.DEFAULT_MAX_ACTIVE_VALUE));
        poolConfig.setMaxIdle(maxIdle);
//
        int minIdle = Integer.parseInt(properties.getProperty(RedisConstants.MIN_IDLE, RedisConstants.DEFAULT_MIN_IDLE_VALUE));
        poolConfig.setMinIdle(minIdle);
//
        boolean testWhileIdle = Boolean.parseBoolean(properties.getProperty(RedisConstants.TEST_WHILEIDLE, RedisConstants.DEFAULT_TEST_WHILEIDLE_VALUE));
        poolConfig.setTestWhileIdle(testWhileIdle);
//
        int testNumPerEviction = Integer.parseInt(properties.getProperty(RedisConstants.TEST_NUMPEREVICTION, RedisConstants.DEFAULT_TEST_NUMPEREVICTION_VALUE));
        poolConfig.setNumTestsPerEvictionRun(testNumPerEviction);
//
//        long timeBetweenEviction = Long.parseLong(properties.getProperty(RedisConstants.TIME_BETWEENEVICTION, RedisConstants.DEFAULT_TIME_BETWEENEVICTION_VALUE));
//        poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEviction);
        return poolConfig;
    }

}
