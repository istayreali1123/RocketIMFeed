package com.thanos.sns.feed;

import com.thanos.common.constant.FeedTopic;
import com.thanos.common.ServerController;
import com.thanos.common.messageBroker.KafkaManager;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 7/15/18.
 */
public class FeedDeliverTest {

    @Test
    public void testData() {
        ApplicationContext context = new ClassPathXmlApplicationContext("sns-spring.xml");

        ServerController controller = (ServerController) context.getBean("serverController");

        KafkaManager kafkaManager = (KafkaManager) context.getBean("mqManager");

        String topic = FeedTopic.FEED_PUBLISH_TOPIC;
        kafkaManager.createTopic(topic, 5, 1);
        kafkaManager.subscribe(topic);
        kafkaManager.start();
    }
}
