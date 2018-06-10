package com.thanos.dbgate.service;

import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;

/**
 * Created by istayreali on 2018/6/3.
 */
public interface IAccount {
    //注册账号信息
    int registerAccount(String registerInfo) throws Exception;

    void userRegister(UserMapper userInfo) throws UserRegisterException;

    UserMapper userLonginByPhone(String phone, String password);

}
