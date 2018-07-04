package com.thanos.sns.feed;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.endpoint.FeedEndPoint;
import com.thanos.sns.service.intereface.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/13/18.
 */
public class FeedPublish implements Feed {


    public void feedPublish(FeedMapper feedInfo) {
        //1. add sns to storage
        FeedEndPoint.addFeed(feedInfo);

        //2. add sns to elastic search
        EleasticSearchClient.createDocument(feedInfo, String.valueOf(feedInfo.feedId));

        //3. pub event to async mq
    }
}
