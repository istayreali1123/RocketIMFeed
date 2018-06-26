package com.thanos.sns.service.intereface;

import com.thanos.common.exception.UserRelationException;

/**
 * Created by wangjialong on 6/26/18.
 */
public interface Relation {

    void userFollow(long fromUserId, long toUserId) throws UserRelationException.UserAlreadyFollowException;
}
