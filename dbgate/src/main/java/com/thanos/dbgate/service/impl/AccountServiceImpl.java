package com.thanos.dbgate.service;


import com.thanos.common.BaseResponse;
import com.thanos.dbgate.bean.AccountVO;
import com.thanos.dbgate.itf.IAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by istayreali on 2018/6/3.
 */
public class AccountServiceImpl implements IAccount {
   public BaseResponse.ResponseBody<String> registerAccount(String registerInfo) {
        BaseResponse.ResponseBody<String> resp = new BaseResponse.ResponseBody();
        //TODO 写入数据库逻辑
        resp.setCode("0");
        resp.setMessage("register user successfully");
        AccountVO accountVO = new AccountVO();
        resp.setData(accountVO.toString());
        return resp;
    }
}
