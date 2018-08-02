package com.thanos.sns.deliver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.constant.FeedTopic;
import com.thanos.common.messageBroker.KafkaManager;
import com.thanos.common.messageBroker.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.thanos.sns.deliver.SecondLevelDeliver;

/**
 * Created by wangjialong on 7/19/18.
 */
@Component
public class SecondLevelTask implements ApplicationListener<ContextStartedEvent> {

    @Autowired
    public KafkaManager kManager;

    public static boolean running = true;

    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("-----test-------");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(()->{
            while(running) {
                try {
                    List<DeliverTask> taskList = DeliverQueue.getMessages(100);
                    ObjectMapper mapper = new ObjectMapper();
                    for (DeliverTask task: taskList) {
                        String messageEntity = mapper.writeValueAsString(task);
                        MessageEntity message = new MessageEntity(messageEntity, "com.thanos.sns.deliver.SecondLevelDeliver");
                        kManager.sendMessage(FeedTopic.FEED_DELIVERY_TOPIC, String.valueOf(task.getFromUid()), message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}


