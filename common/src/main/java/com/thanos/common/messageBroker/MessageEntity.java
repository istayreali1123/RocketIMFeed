package com.thanos.common.messageBroker;

import com.thanos.common.pojo.FeedMapper;

import java.io.Serializable;

/**
 * Created by wangjialong on 7/15/18.
 */
public class MessageEntity implements Serializable {

    public String message;

    public String execute;

    public MessageEntity() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getExecute() {
        return execute;

    }

    public MessageEntity(String message, String execute) {
        this.message = message;
        this.execute = execute;
    }
    
    public String toString() {
        return message.toString();
    }
    
}
