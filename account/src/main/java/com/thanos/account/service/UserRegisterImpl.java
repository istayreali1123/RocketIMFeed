package com.thanos.account.service;

import com.thanos.account.intereface.Register;
import com.thanos.common.pojo.UserInfo;
import com.thanos.common.exception.UserRegisterException;

/**
 * Created by wangjialong on 5/31/18.
 */
public class UserRegisterImpl implements Register {

    public void addUser(UserInfo userInfo) throws UserRegisterException {
        System.out.println("ok");

    }
}
