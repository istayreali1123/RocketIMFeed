package com.thanos.common.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by wangjialong on 7/27/18.
 */
@Service
@Aspect
public class RedisOpsAspect {

    public static final int NUM_RETRIES = 3;

    public Object executeWithRetry(ProceedingJoinPoint pjp, Object[] args) {
        int tries = 0;
        boolean success = false;
        Object result = null;
        do {
            ++tries;
            try {
                result = pjp.proceed(args);
                success = true;
            }catch (JedisException exception) {
                if (tries == NUM_RETRIES) {
                    throw exception;
                }
            } catch (Throwable e) {
            }
        } while (!success && tries < NUM_RETRIES);
        return result;
    }
}
