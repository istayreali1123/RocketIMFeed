package com.thanos.account.intereface;

import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;

/**
 * Created by wangjialong on 5/31/18.
 */
public interface Register {

    long userIdAlloc(String osid) throws UserRegisterException.UserIdAllocException;

    void addUser(UserMapper userInfo) throws UserRegisterException;

    long queryUserIdByPhone(String phone);
}
