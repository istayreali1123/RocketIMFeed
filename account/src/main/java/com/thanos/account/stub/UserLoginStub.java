package com.thanos.account.stub;

import com.alibaba.dubbo.rpc.RpcException;
import com.thanos.account.intereface.Login;
import com.thanos.common.exception.UserLoginException;
import com.thanos.common.pojo.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wangjialong on 6/11/18.
 */
public class UserLoginStub implements Login {

    private final Login userLoginEntity;

    Logger logger = LoggerFactory.getLogger(UserLoginStub.class);

    public UserLoginStub(Login userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
    }

    public UserMapper userLonginByPhone(String phone, String password) {
        try {
            logger.info("rpc reqeust begins");
            return userLoginEntity.userLonginByPhone(phone, password);
        } catch (RpcException e) {
            // RPC 调用异常业务容错
            logger.error("rpc request login endpoint exception", e);
            throw new UserLoginException.LoginAuthException();
        }
    }
}
