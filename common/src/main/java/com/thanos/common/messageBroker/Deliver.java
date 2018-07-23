package com.thanos.common.messageBroker;

/**
 * Created by wangjialong on 7/16/18.
 */
public interface Deliver {

    public void execute(Object message);
}
