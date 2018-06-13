package com.thanos.feed;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.endpoint.FeedEndPoint;

/**
 * Created by wangjialong on 6/13/18.
 */
public class feedPublish {

    public void pubFeedToElasticSearch(FeedMapper feedInfo) {
        //1. add feed to storage
        FeedEndPoint.addFeed(feedInfo);

        //2. add feed to elastic search
        EleasticSearchClient.createDocument(feedInfo);
        
        //3. pub event to async mq
    }
}
