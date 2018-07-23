package com.thanos.common.exception;

/**
 * Created by wangjialong on 7/9/18.
 */
public class KafkaException extends RuntimeException {

    public static class TopicNoExistException extends KafkaException {

    }

}
