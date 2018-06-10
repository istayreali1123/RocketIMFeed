package com.thanos.dbgate.endpoint;

import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserIdAllocMapper;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangjialong on 6/9/18.
 */
@Component
public class UserAccountEndPoint {

    private static UserAccountEndPoint instance;

    public UserAccountEndPoint() {
        instance = this;
    }


    @Autowired
    IAccount acountEntity;

    public static UserMapper userLonginByPhone(String phone, String password) {
        return instance.acountEntity.userLonginByPhone(phone, password);

    }

    public static void registerAccount(UserMapper userInfo) throws UserRegisterException {
        instance.acountEntity.userRegister(userInfo);
    }

    public static long userUUidgenerate(UserIdAllocMapper uuidMapper) {
        return instance.acountEntity.allocUserId(uuidMapper);
    }

}
