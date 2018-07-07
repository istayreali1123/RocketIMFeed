package com.thanos.common.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by istayreali on 2018/6/2.
 */
public class UserMapper extends AbstractMapper implements Serializable {

    private String uuid;

    private String userName;

    private String password;

    public int deleted = 0;

    public String budss;

    public char isOnline = '0';

    private String emailAddress;

    private String nickName;

    private long lastUpdateTime;

    private String avatarURL;

    private String phoneNumber;

    public long createTime = System.currentTimeMillis();

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setBudss(String budss) {
        this.budss = budss;
    }

    public void setIsOnline(char isOnline) {
        this.isOnline = isOnline;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDeleted() {

        return deleted;
    }

    public String getBudss() {
        return budss;
    }

    public char getIsOnline() {
        return isOnline;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

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

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPhoneNnumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getIndentityId() {
        return uuid;
    }
}
