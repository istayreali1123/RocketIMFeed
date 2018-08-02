package com.thanos.common.utils;

import com.thanos.common.redis.RedisUtils;
import org.elasticsearch.common.collect.Tuple;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangjialong on 7/27/18.
 */
public class TestRedis {

    @Test
    public void testSet() {
        String key = "key1";
        String value = "姜臻颖";
        RedisUtils.execute("set", key, value);

        String getValue = (String) RedisUtils.execute("get", key);

        assert getValue.equals(value);
    }

    @Test
    public void testZadd() {
        String key = "key2";
        String ele = "1000";
        int score = 1002;
        RedisUtils.execute("zadd", key, score, ele);
    }

    @Test
    public void testZrevrangeWithScore() {
        String key = "key2";
        Map<String, Double> list = (Map<String, Double>) RedisUtils.execute("zrevrangeByScoreWithScores", key, Double.valueOf(1003), Double.valueOf(1), 0, 10);
        ;
    }
}
