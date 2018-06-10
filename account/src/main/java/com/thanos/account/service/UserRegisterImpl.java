package com.thanos.account.service;

import com.thanos.account.intereface.Register;
import com.thanos.common.exception.UserIdAllocException;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserIdAllocMapper;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.endpoint.UserAccountEndPoint;

/**
 * Created by wangjialong on 5/31/18.
 */
public class UserRegisterImpl implements Register {

    public void addUser(UserMapper userInfo) throws UserRegisterException {
        UserAccountEndPoint.registerAccount(userInfo);
    }

    public long userIdAlloc(String osid) throws UserIdAllocException {
        //1. build id alloc object
        UserIdAllocMapper uuidMapper = new UserIdAllocMapper(osid);
        return UserAccountEndPoint.userUUidgenerate(uuidMapper);
    }
}
