package com.thanos.sns.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.constant.FeedTopic;
import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.messageBroker.KafkaManager;
import com.thanos.common.messageBroker.MessageEntity;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.endpoint.FeedEndPoint;
import com.thanos.sns.deliver.FirstLevelDeliver;
import com.thanos.sns.service.intereface.Feed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/13/18.
 */
public class FeedPublish implements Feed {

    @Autowired
    public KafkaManager kManager;

    public void feedPublish(FeedMapper feedInfo) {
        //1. add sns to storage
        FeedEndPoint.addFeed(feedInfo);

        //2. add sns to elastic search
        EleasticSearchClient.createDocumentDefault(feedInfo, String.valueOf(feedInfo.feedId));

        //3. pub event to async mq
        try {
            ObjectMapper mapper = new ObjectMapper();
            String messageEntity = mapper.writeValueAsString(feedInfo);
            MessageEntity message = new MessageEntity(messageEntity, "com.thanos.sns.deliver.FirstLevelDeliver");
            kManager.sendMessage(FeedTopic.FEED_PUBLISH_TOPIC, String.valueOf(feedInfo.authorId), message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
