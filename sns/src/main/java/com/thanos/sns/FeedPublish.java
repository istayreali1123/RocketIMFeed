package com.thanos.sns;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.endpoint.FeedEndPoint;

/**
 * Created by wangjialong on 6/13/18.
 */
public class FeedPublish {

    public void pubFeedToElasticSearch(FeedMapper feedInfo) {
        //1. add sns to storage
        FeedEndPoint.addFeed(feedInfo);

        //2. add sns to elastic search
        EleasticSearchClient.createDocument(feedInfo, String.valueOf(feedInfo.authorId));

        //3. pub event to async mq
    }
}
