package com.thanos.dbgate.itf;

import com.thanos.common.BaseResponse;

import java.util.Map;

/**
 * Created by istayreali on 2018/6/3.
 */
public interface IAccount {
    //注册账号信息
    BaseResponse.ResponseBody<String> registerAccount(String registerInfo);
}
