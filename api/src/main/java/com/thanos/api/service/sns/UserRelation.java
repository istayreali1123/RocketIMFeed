package com.thanos.api.service.sns;

import com.alibaba.dubbo.config.annotation.Reference;
import com.thanos.common.user.UserInfo;
import com.thanos.sns.service.intereface.Relation;
import org.springframework.stereotype.Service;

/**
 * Created by wangjialong on 6/26/18.
 */
@Service
public class UserRelation {

    @Reference
    public Relation relationHandler;

    public void addFollow(String bduss, long toUserId) {
        long userId = UserInfo.getUserIdByBduss(bduss);

        relationHandler.userFollow(userId, toUserId);
    }
}
