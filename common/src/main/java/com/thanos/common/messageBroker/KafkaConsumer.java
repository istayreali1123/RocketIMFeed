package com.thanos.common.messageBroker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjialong on 7/10/18.
 */
public class KafkaConsumer {

    private static boolean running = true;

    public static org.apache.kafka.clients.consumer.KafkaConsumer<String, Object> feedConsumer;

    static LinkedBlockingDeque<Object> messageQueue = new LinkedBlockingDeque();

    public KafkaConsumer(){

    }

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "consermer-group1");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        feedConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer(properties);
    }

    public static org.apache.kafka.clients.consumer.KafkaConsumer<String, Object> getConsumer() {
        return feedConsumer;
    }


    public static void startConsume() {
        while(running) {
            try {
                ConsumerRecords<String, Object> consumerRecords = feedConsumer.poll(1000);
                for(ConsumerRecord record:consumerRecords) {
                    Object message = record.value();
                    messageQueue.push(message);
                }
            } catch(Exception e) {

            }

        }
    }

    public static Object take() {
        try {
            return messageQueue.poll(5, TimeUnit.SECONDS);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
