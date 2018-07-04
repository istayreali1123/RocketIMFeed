package com.thanos.sns.service.intereface;

import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.exception.FeedPublishException;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.sns.feed.FeedPull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/25/18.
 */
public interface Feed {

    default void feedPublish(FeedMapper feedInfo) throws FeedPublishException {

    }

    default FeedPull.FeedList feedPull(long userId, String scrollId,
                                       int size, long gt) throws ElasticSearchException.ElasticSearchQueryException {
        return new FeedPull.FeedList();
    }
}
