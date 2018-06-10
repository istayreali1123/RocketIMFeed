package com.thanos.dbgate.endpoint;

import com.thanos.common.pojo.UserInfo;
import com.thanos.dbgate.dto.UserDTO;
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

    public static UserDTO userLonginByPhone(String phone, String password) {
        return instance.acountEntity.userLonginByPhone(phone, password);

    }
}
