package com.thanos.sns.deliver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.messageBroker.Deliver;
import com.thanos.common.messageBroker.MessageEntity;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.common.redis.RedisUtils;

import java.io.IOException;

import static com.thanos.common.constant.FeedTopic.FEED_USER_MAIL_PREFIX;

/**
 * Created by wangjialong on 7/24/18.
 */
public class SecondLevelDeliver implements Deliver {

    private static ObjectMapper mapper = new ObjectMapper();

    public void execute(Object message) {
        try {
            DeliverTask task =  mapper.readValue((String)message, DeliverTask.class);

            Long clientId = task.getFromUid();
            Long feedId = task.getFeedId();
            String key = String.format(FEED_USER_MAIL_PREFIX, String.valueOf(clientId));
            RedisUtils.execute("zadd", key, Double.valueOf(feedId), String.valueOf(feedId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
