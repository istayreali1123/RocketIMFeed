package com.thanos.account.intereface;

import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;

/**
 * Created by wangjialong on 5/31/18.
 */
public interface Register {

    void addUser(UserMapper userInfo) throws UserRegisterException;
}
