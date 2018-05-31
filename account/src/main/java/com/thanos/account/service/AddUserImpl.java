package com.thanos.account.service;

import com.thanos.account.intereface.Register;
import com.thanos.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 5/31/18.
 */
public class AddUserImpl implements Register {

    public BaseResponse.ResponseBody<List<String>> addUser(String userId) {

        BaseResponse.ResponseBody<List<String>> resp = new BaseResponse.ResponseBody();

        resp.setCode("0");
        resp.setMessage("add user successfully");

        resp.setData(new ArrayList<String>());
        return resp;
    }
}
