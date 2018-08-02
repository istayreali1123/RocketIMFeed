package com.thanos.common.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisClusterMaxRedirectionsException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class RedisUtils {

    private JedisPool jedisPool;

    private static int NUM_RETRIES = 3;

    private JedisCluster jedisCluster;

    private static final int MAX_REDIRECTIONS = 5;

    private Log log = LogFactory.getLog(RedisUtils.class);

    private static RedisConnectionFactory connectionFactory;

    private static Class<?> classType;

    static {
        // initialize the connection factory
        connectionFactory = new RedisConnectionFactory();
        connectionFactory.initialize();
        classType = JedisCommands.class;
    }

    public static Object execute(String command, String key, Object... args) {
        int tries = 0;
        boolean success = false;
        Object response = null;
        do {
            ++tries;
            try {
                JedisCommands jedis = RedisConnectionFactory.getConnection();
                switch(command) {
                    case "set":
                        response = classType.getMethod(command, String.class, String.class).invoke(jedis,key, args[0]);
                        break;
                    case "get":
                        response = classType.getMethod(command, String.class).invoke(jedis,key);
                        break;
                    case "zadd":
                        response = classType.getMethod(command, String.class, double.class, String.class)
                                .invoke(jedis, key, args[0], args[1]);
                        break;
                    case "zrevrangeByScore":
                        response = classType.getMethod(command, String.class, double.class,
                                double.class, int.class, int.class)
                                .invoke(jedis, key, args[0], args[1], args[2], args[3]);
                        break;
                    case "zrevrangeByScoreWithScores":
                        response =  classType.getMethod(command, String.class, double.class,
                                double.class, int.class, int.class)
                                .invoke(jedis, key, args[0], args[1], args[2], args[3]);
                        LinkedHashSet<Tuple> resp = (LinkedHashSet<Tuple>) response;
                        Map<String, Double> zset = new HashMap();
                        for (Tuple data: resp) {
                            String element = data.getElement();
                            Double score = data.getScore();
                            zset.put(element, score);
                        }
                        return zset;
                }

                success = true;
                ((Jedis) jedis).close();
            }catch (JedisException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        } while (!success && tries < NUM_RETRIES);
        return response;
    }

}
