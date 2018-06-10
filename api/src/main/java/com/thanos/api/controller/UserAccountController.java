package com.thanos.api.controller;

import com.thanos.common.BaseResponse;
import com.thanos.api.service.account.UserAccount;
import com.thanos.common.pojo.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by wangjialong on 6/3/18.
 */
@RestController
public class UserAccountController extends AbstractController {

    UserAccount userAccountHandler;

    @RequestMapping("/user/register")
    public BaseResponse.ResponseBody<RespObj> register(@RequestParam(value="phone") String phone,
                                                        @RequestParam(value="name", required=false) String name,
                                                        @RequestParam(value="icon", required=false) String icon,
                                                        @RequestParam(value="password") String password) {

        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        try {
            userAccountHandler = (UserAccount) context.getBean("userAccountHandler");
            UserMapper userInfo = userAccountHandler.registerByPhoneNumber(phone, password, name, icon, "");
            RespObj data = wrapResponse(userInfo);
            resp.setData(data);
            resp.setCode("0");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
        }
        return resp;
    }

    private RespObj wrapResponse(UserMapper userInfo) {
        RespObj data = new RespObj();
        data.budss = userInfo.getBudss();
        data.name = userInfo.getNickName();
        data.userName = userInfo.getUserName();
        data.isLogin = userInfo.getIsOnline();
        data.loginTime = System.currentTimeMillis();
        return data;
    }

    @RequestMapping("/user/login")
    public BaseResponse.ResponseBody<RespObj> login(@RequestParam(value = "phone") String phone,
                                                    @RequestParam(value = "passpwod") String password) {
        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        try {
            userAccountHandler = (UserAccount) context.getBean("userAccountHandler");
            UserMapper userInfo = userAccountHandler.userLonginByPhone(phone, password);
            RespObj data = wrapResponse(userInfo);
            resp.setData(data);
            resp.setCode("0");
        } catch (Exception e) {

        }
        return resp;
    }


    public class RespObj implements Serializable {
        public String budss;
        public String name;
        public String userName;
        public char isLogin;
        public long loginTime;
    }
}
