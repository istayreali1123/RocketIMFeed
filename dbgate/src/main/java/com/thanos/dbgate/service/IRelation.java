package com.thanos.dbgate.service;

import com.thanos.common.pojo.RelationMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/20/18.
 */
public interface IRelation {

    void addFollow(long fromUserId, long toUserId);

    default List<Long> getUserFansListByPage(long userId, int count) {
        return new ArrayList();
    }

    default List<Long> getUserFansListByPageAndId(long userId, long lastId, int count) {
        return new ArrayList();
    }

    RelationMapper getRelationMapper(long fromUserId, long toUserId);
}
