package com.thanos.account.intereface;

import com.thanos.account.mapper.UserInfo;
import com.thanos.common.exception.UserRegisterException;

/**
 * Created by wangjialong on 5/31/18.
 */
public interface Register {

    void addUser(UserInfo userInfo) throws UserRegisterException;
}
