package com.thanos.account.service;

import com.thanos.account.intereface.Login;
import com.thanos.common.exception.UserLoginException;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.endpoint.UserAccountEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangjialong on 6/9/18.
 */
public class UserLoginImpl implements Login {

    public static Logger logger = LoggerFactory.getLogger(UserLoginImpl.class);

    public UserMapper userLonginByPhone(String phone, String password) {
        UserMapper userInfo;
        try {
            logger.info("dubbo requests me.....");
            userInfo =  UserAccountEndPoint.userLonginByPhone(phone, password);
        } catch (Exception e) {
            logger.error("unexpected exception:", e);
            return null;
        }
        if (userInfo == null) {
            throw new UserLoginException.LoginAuthException();
        }
        return userInfo;
    }
}
