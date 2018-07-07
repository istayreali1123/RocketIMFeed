package com.thanos.common.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangjialong on 7/7/18.
 */
public class HashMapWithLRU<K, V> extends LinkedHashMap<K, V> {

    protected int capacity;


    public HashMapWithLRU(int initialCapacity, float loadFactor, boolean accessOrder, int capacity) {
        super(initialCapacity, loadFactor, accessOrder);
        this.capacity = capacity;
    }

    public HashMapWithLRU() {
        super();
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        V value = eldest.getValue();
        CacheEntity value2 = (CacheEntity) value;

        long remainsTime = System.currentTimeMillis() / 1000 - value2.getExpireTime();
        if (this.size() >= capacity || remainsTime >=0 ){
            return true;
        }
        return false;
    }

}
