package com.thanos.account.service;

import com.thanos.account.intereface.Register;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;

/**
 * Created by wangjialong on 5/31/18.
 */
public class AddUserImpl implements Register {

    public void addUser(UserMapper userInfo) throws UserRegisterException {
        System.out.println("ok");

    }
}
