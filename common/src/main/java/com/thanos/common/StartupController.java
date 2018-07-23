package com.thanos.common;

import com.thanos.common.messageBroker.KafkaManager;
import com.thanos.common.constant.FeedTopic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 6/25/18.
 */
public class StartupController {

    public static ClassPathXmlApplicationContext context;

    public String confName;

    private boolean running = false;
    private Thread thread;

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public void start() {
        context = new ClassPathXmlApplicationContext(confName);

        context.start();

        KafkaManager kafkaManager = (KafkaManager) context.getBean("mqManager");

        String topic = FeedTopic.FEED_PUBLISH_TOPIC;
        kafkaManager.createTopic(topic, 5, 1);
        kafkaManager.subscribe(topic);
        kafkaManager.start();

        ServerController controller = (ServerController) context.getBean("serverController");

        controller.start();

    }
}
