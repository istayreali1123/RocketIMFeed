package com.thanos.common.messageBroker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.pojo.FeedMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangjialong on 7/10/18.
 */
@Component("mqManager")
public class KafkaManager {

    public List<String> subscribeTopics = new ArrayList();

    public KafkaManager() {
    }

    org.apache.kafka.clients.consumer.KafkaConsumer consumer = KafkaConsumer.getConsumer();

    public void addTopic(String topic) {
        this.subscribeTopics.add(topic);
    }

    public void subscribe(String ... topic) {
        consumer.subscribe(Arrays.asList(topic));
    }

    public void start() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Executors.newCachedThreadPool();
        executorService.submit(()->{
            while(true) {
                try {
                    Object message = KafkaConsumer.take();
                    if (message !=null ) {
                        ObjectMapper mapper = new ObjectMapper();
                        MessageEntity data = mapper.readValue((String)message, new TypeReference<MessageEntity>(){});
                        this.dispatch(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        executorService.submit(()->{
            KafkaConsumer.startConsume();
        });
    }

    public void dispatch(MessageEntity message) {
        try {
            String className = message.getExecute();
            Deliver executor = (Deliver) Class.forName(className).newInstance();
            executor.execute(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sendMessage(String topic, String key, MessageEntity message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String strMessage = mapper.writeValueAsString(message);
            KafkaProducer.pubMessage(topic, key, strMessage);
        } catch (Exception e) {

        }

    }

    public void createTopic(String topic,  int partition, int replica) {
        KafkaProducer.createTopic(topic, partition, replica);
    }
}
