package com.thanos.common.messageBroker;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.security.JaasUtils;

import java.util.*;

/**
 * Created by wangjialong on 7/9/18.
 */
public class KafkaProducer {

    public static Producer<String, Object> producer;

    private Map<String, Integer> topicPartition = new HashMap();

    public static List<Object>  messageQueue = new LinkedList();
    public String topic;
    public static ZkUtils zkUtils;

    public static Properties properties;

    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "com.thanos.common.messageBroker.FeedPartitioner");

        producer = new org.apache.kafka.clients.producer.KafkaProducer<String, Object>(properties);
        zkUtils = ZkUtils.apply("localhost:2181", 30000, 30000, JaasUtils.isZkSecurityEnabled());

    }

    public Producer<String, Object> getProducer() {
        return producer;
    }

    public static boolean isTopicExist(String topic) {
        return AdminUtils.topicExists(zkUtils, topic);
    }

    public static void createTopic(String topic, int partition, int replica) {
        if (!isTopicExist(topic)) {
            AdminUtils.createTopic(zkUtils, topic, partition, replica, new Properties(), RackAwareMode.Enforced$.MODULE$);
        } else {

        }
    }

    public static void pubMessage(String topic, String key, String message) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord(topic, key, message);
        producer.send(producerRecord);
    }
}
