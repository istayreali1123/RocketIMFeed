package com.thanos.account.service;

import com.thanos.account.intereface.Register;
import com.thanos.account.mapper.UserInfo;
import com.thanos.common.exception.UserRegisterException;

/**
 * Created by wangjialong on 5/31/18.
 */
public class AddUserImpl implements Register {

    public void addUser(UserInfo userInfo) throws UserRegisterException {
        System.out.println("ok");

    }
}
