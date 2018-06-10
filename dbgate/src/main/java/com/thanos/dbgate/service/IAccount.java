package com.thanos.dbgate.service;

import com.thanos.common.BaseResponse;
import com.thanos.common.pojo.UserInfo;
import com.thanos.dbgate.dto.UserDTO;

import java.util.Map;

/**
 * Created by istayreali on 2018/6/3.
 */
public interface IAccount {
    //注册账号信息
    int registerAccount(String registerInfo) throws Exception;

    UserDTO userLonginByPhone(String phone, String password);

}
