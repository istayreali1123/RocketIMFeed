package com.thanos.common.redis;

import com.thanos.common.constant.RedisConstants;
import redis.clients.jedis.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

import static com.thanos.common.constant.RedisConstants.CLUSTER_ENABLED;
import static com.thanos.common.constant.RedisConstants.DEFAULT_CLUSTER_ENABLED;

/**
 * Created by wangjialong on 7/26/18.
 */
public class RedisConnectionFactory {

    public static RedisConnectionFactory instance;

    public static JedisCommands jedisClient;

    //for cluster mode
    public static JedisPool jedisPool;

    public static Boolean clusterEnabled;

    private static final int MAX_REDIRECTIONS = 5;

    public void initialize() {
        if (null != instance) {
            return;
        }
        //加载配置文件
        Properties properties = loadProperties();
        //判断配置文件具体参数
        //是否集群模式
        clusterEnabled = Boolean.valueOf(properties.getProperty(CLUSTER_ENABLED, DEFAULT_CLUSTER_ENABLED));
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
            jedisClient = new JedisCluster((Set)redisNodes, timeout, Protocol.DEFAULT_TIMEOUT, MAX_REDIRECTIONS, password, getPoolConfig(properties));
        } else {
            jedisPool = new JedisPool(getPoolConfig(properties), ((List<String>) redisNodes).get(0),
                    Integer.parseInt(((List<String>) redisNodes).get(1)), timeout, password, dataBase);
        }
    }

    public static JedisCommands getConnection() {
        if (clusterEnabled) {
            return jedisClient;
        }
        return jedisPool.getResource();
    }


    private Properties loadProperties() {
        Properties rel = new Properties();
        try {
            String filePath = "common.properties";
            InputStream inputStream = null;
            try {
                inputStream =  RedisConnectionFactory.class.getClassLoader().getResourceAsStream(filePath);
                rel.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                inputStream.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
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
                } else {
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
