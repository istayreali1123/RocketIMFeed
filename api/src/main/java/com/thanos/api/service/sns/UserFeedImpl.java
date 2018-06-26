package com.thanos.api.service.sns;

import com.alibaba.dubbo.config.annotation.Reference;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.common.user.UserPassport;
import com.thanos.sns.service.intereface.Feed;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by wangjialong on 6/25/18.
 */
@Service("UserFeedImpl")
public class UserFeedImpl implements UserFeed {

    @Reference
    public Feed feedPubHandler;

    public void feedPublish(String bduss, FeedMapper feed) {
        //1. 反解bduss, 获取用户user id
        long userId = UserPassport.getUserIdByBduss(bduss);

        //2. 检查feed内容， 做内容过滤

        feed.setAuthorId(userId);

        //3. add feed to storage
        feedPubHandler.feedPublish(feed);
    }
}
