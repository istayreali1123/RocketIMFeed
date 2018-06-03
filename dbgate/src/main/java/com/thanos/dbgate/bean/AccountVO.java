package com.thanos.dbgate.bean;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by istayreali on 2018/6/2.
 */
public class AccountVO implements Serializable {
    private String uuid;
    private String userName;
    private String password;
    private String emailAddress;
    private String nickName;
    private Date lastUpdateTime;
    private String avatarURL;
    private String phoneNnumber;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPhoneNnumber() {
        return phoneNnumber;
    }

    public void setPhoneNnumber(String phoneNnumber) {
        this.phoneNnumber = phoneNnumber;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
