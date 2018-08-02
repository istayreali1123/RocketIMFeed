package com.thanos.common.constant;

public interface RedisConstants {


    final String PROPERTIES_FILE="redis-session-cache.properties";
    //redis properties
    final String HOST="redis.hosts";
    final String CLUSTER_ENABLED = "redis.cluster.enabled";
    //连接池最大连接数（使用负值表示没有限制）
    final String MAX_ACTIVE = "redis.max.active";

    final String TEST_ONBORROW = "redis.test.onBorrow";
    final String TEST_ONRETURN = "redis.test.onReturn";
    //连接池中的最大空闲连接
    final String MAX_IDLE = "redis.max.idle";
    //连接池中的最小空闲连接
    final String MIN_IDLE = "redis.min.idle";
    final String TEST_WHILEIDLE = "redis.test.whileIdle";
    final String TEST_NUMPEREVICTION = "redis.test.numPerEviction";
    //移除key的时间间隔
    final String TIME_BETWEENEVICTION = "redis.time.betweenEviction";

    final String PASSWORD = "redis.password";
    final String DATABASE = "redis.database";
    //客户端空闲N秒后断开连接，参数0表示不启用
    final String TIMEOUT = "redis.timeout";

    // redis property default values
    final String DEFAULT_MAX_ACTIVE_VALUE = "10";
    final String DEFAULT_TEST_ONBORROW_VALUE = "true";
    final String DEFAULT_TEST_ONRETURN_VALUE = "true";
    final String DEFAULT_MAX_IDLE_VALUE = "5";
    final String DEFAULT_MIN_IDLE_VALUE = "1";
    final String DEFAULT_TEST_WHILEIDLE_VALUE = "true";
    final String DEFAULT_TEST_NUMPEREVICTION_VALUE = "10";
    final String DEFAULT_TIME_BETWEENEVICTION_VALUE = "60000";
    final String DEFAULT_CLUSTER_ENABLED = "false";

    final String CONN_FAILED_RETRY_MSG = "Jedis connection failed, retrying...";


}
