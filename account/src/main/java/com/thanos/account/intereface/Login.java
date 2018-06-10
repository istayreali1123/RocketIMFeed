package com.thanos.account.intereface;

import com.thanos.common.pojo.UserInfo;

/**
 * Created by wangjialong on 6/9/18.
 */
public interface Login {

    public UserInfo userLonginByPhone(String phone, String password);
}
