package com.thanos.account.service;

import com.thanos.account.intereface.Login;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.endpoint.UserAccountEndPoint;

/**
 * Created by wangjialong on 6/9/18.
 */
public class UserLoginImpl implements Login {

    public UserMapper userLonginByPhone(String phone, String password){

        return UserAccountEndPoint.userLonginByPhone(phone, password);
    }
}
