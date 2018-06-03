package com.thanos.account.intereface;

import com.thanos.common.BaseResponse;

import java.util.List;

/**
 * Created by wangjialong on 5/31/18.
 */
public interface Register {

    BaseResponse.ResponseBody<List<String>> addUser(String userId);
}
