package com.thanos.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thanos.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 5/31/18.
 */
@RestController
public class Index {

    @RequestMapping("/")
    public BaseResponse.ResponseBody<List<String>> index() {

        String echoStr = "Welcome to the world of thanos";

        BaseResponse.ResponseBody<List<String>> resp = new BaseResponse.ResponseBody();

        resp.setCode("");
        resp.setMessage(echoStr);
        resp.setData(new ArrayList<String>());
        return resp;

    }
}
