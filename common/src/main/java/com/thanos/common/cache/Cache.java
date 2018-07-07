package com.thanos.common.cache;

/**
 * Created by wangjialong on 7/7/18.
 */
public interface Cache {

    public void set(String key,  Object value, long expireTime);

    public Object get(String key);


}
