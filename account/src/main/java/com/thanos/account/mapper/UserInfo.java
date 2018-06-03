package com.thanos.account.mapper;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangjialong on 6/3/18.
 */
public class UserInfo implements Serializable {

    public long userId;

    public String nickName ;

    public String userName;

    public String userIcon;

    public String phoneNumber;

    public String email;

    public String password;

    public long createTime = System.currentTimeMillis();

    public String getBudss() {
        return budss;
    }

    public char getIsOnline() {
        return isOnline;
    }

    public void setBudss(String budss) {
        this.budss = budss;

    }

    public void setIsOnline(char isOnline) {
        this.isOnline = isOnline;
    }

    public long updateTime = System.currentTimeMillis();

    public int deleted = 0;

    public String budss;

    public char isOnline = '0';

    public UserInfo(long userId, String nickName, String userName,
                    String phoneNumber, String email, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        createTime = System.currentTimeMillis();
        updateTime = System.currentTimeMillis();
        deleted = 0;
    }

    public UserInfo() {
    }

    public long getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleted(int deleted) {
        deleted = deleted;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

}
