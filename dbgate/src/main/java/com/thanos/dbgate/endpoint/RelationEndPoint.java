package com.thanos.dbgate.endpoint;

import com.thanos.dbgate.service.IRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
