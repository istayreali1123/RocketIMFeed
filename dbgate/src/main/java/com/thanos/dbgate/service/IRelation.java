package com.thanos.dbgate.service;

/**
 * Created by wangjialong on 6/20/18.
 */
public interface IRelation {

    void addFollow(long fromUserId, long toUserId);
}
