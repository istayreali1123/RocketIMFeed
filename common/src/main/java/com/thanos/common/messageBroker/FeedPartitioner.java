package com.thanos.common.messageBroker;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Created by wangjialong on 7/15/18.
 */
public class FeedPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer partitionNum = cluster.partitionCountForTopic(topic);
        try {
            long indexOfKey = Long.parseLong((String) key);
            return (int) Math.abs(indexOfKey % partitionNum);
        } catch (Exception e) {
            return Math.abs(key.hashCode() % partitionNum);
        }
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
