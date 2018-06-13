package com.thanos.dbgate.endpoint;

import com.thanos.common.exception.FeedPublishException;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.mapper.FeedDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangjialong on 6/14/18.
 */
@Component
public class FeedEndPoint {

    private static FeedEndPoint instance;

    private static FeedDAO feedDAO;

    public FeedEndPoint() {
        instance = this;
    }

    @Autowired
    public FeedDAO feedEntity;

    public static void addFeed(FeedMapper feedInfo) {
        try {
            instance.feedEntity.addFeed(feedInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FeedPublishException();
        }
    }

    public static FeedMapper queryFeed(long feedId) {
        try {
            return instance.feedEntity.queryFeed(feedId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FeedPublishException();
        }
    }
}
