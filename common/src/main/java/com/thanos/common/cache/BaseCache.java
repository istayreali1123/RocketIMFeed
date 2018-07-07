package com.thanos.common.cache;

import com.thanos.common.utils.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjialong on 7/4/18.
 */
@Component("baseCache")
public class BaseCache implements Runnable {

    //private Map<String, >
    public int size = 1024;

    public int collectKeyCount = 100;
    public int expireThreshold = 25;

    static Map<String,CacheEntity> container = new HashMapWithLRU(16, (float)0.75, true, 1024);

    public static void start() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new BaseCache(), -1, 20, TimeUnit.SECONDS);
    }

    public static synchronized void set(String key,  Object value, long expireTime) {
        container.put(key, new CacheEntity(value, System.currentTimeMillis() + expireTime));
        ;
    }

    public static synchronized Object get(String key) {
        if (container.containsKey(key)) {
            CacheEntity value = container.get(key);
            if (value.getExpireTime() <= System.currentTimeMillis() / 1000) {
                container.remove(key);
                return null;
            }
            return value.getValue();
        }
        return null;
    }

    public void run() {
        int deletedKeys = this.doCollect();
        while (deletedKeys >= expireThreshold) {
            this.doCollect();
        }
    }

    public synchronized int doCollect() {
        // random choose k keys;
        int deletedKeys = 0;
        Set<String> keySet = container.keySet();
        ArrayList<String> keyList = new ArrayList(keySet);
        for (int i = 0; i < collectKeyCount; i++ ) {
            int randomIndex = RandomUtils.getRandomInt(keyList.size());
            String key = keyList.get(randomIndex);
            if (get(key) == null ) {
                keyList.remove(randomIndex);
                deletedKeys++;
            }
        }
        return deletedKeys;
    }


}
