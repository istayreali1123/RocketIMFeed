package com.thanos.common.cache;

/**
 * Created by wangjialong on 7/7/18.
 */
public class CacheEntity {

    Object value;
    long expireTime;

    public CacheEntity(Object value, long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
    }

    public Object getValue() {
        return value;
    }

    public long getExpireTime() {
        return expireTime;
    }
}
