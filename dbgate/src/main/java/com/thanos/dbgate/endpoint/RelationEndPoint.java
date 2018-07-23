package com.thanos.dbgate.endpoint;

import com.thanos.common.pojo.RelationMapper;
import com.thanos.dbgate.service.IRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangjialong on 6/21/18.
 */
@Component
public class RelationEndPoint {

    public static RelationEndPoint instance;

    public RelationEndPoint() {
        instance = this;
    }

    @Autowired
    IRelation relationEntity;

    public static void userFollow(long fromUserId, long toUserId) {
        instance.relationEntity.addFollow(fromUserId, toUserId);
    }

    public static List<Long> getFansList(long userId,  int count) {
        return instance.relationEntity.getUserFansListByPage(userId, count);
    }

    public static List<Long> getUserFansListByPageAndId(long userId, long lastId, int count) {
        return instance.relationEntity.getUserFansListByPageAndId(userId, lastId, count);
    }

    public static RelationMapper getRelationMapper(long fromUserId, long toUserId) {
        return instance.relationEntity.getRelationMapper(fromUserId, toUserId);
    }
}
