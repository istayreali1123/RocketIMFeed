package com.thanos.account.intereface;

import com.thanos.common.pojo.UserMapper;

/**
 * Created by wangjialong on 6/9/18.
 */
public interface Login {

    public UserMapper userLonginByPhone(String phone, String password);
}
