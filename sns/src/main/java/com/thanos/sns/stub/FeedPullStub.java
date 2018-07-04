package com.thanos.sns.stub;

import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.sns.feed.FeedPull;
import com.thanos.sns.service.intereface.Feed;

import java.util.List;

/**
 * Created by wangjialong on 7/2/18.
 */
public class FeedPullStub implements Feed {

    public Feed feedEntity;

    public FeedPullStub(Feed feedEntity) {
        this.feedEntity = feedEntity;
    }

    public FeedPull.FeedList feedPull(long userId, String scrollId,
                                     int size, long gt) throws ElasticSearchException.ElasticSearchQueryException {
        try {
            System.out.println("fuck !!!!!!");
            return feedEntity.feedPull(userId, scrollId, size, gt);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ElasticSearchException();
        }
    }
}
