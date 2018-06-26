package com.thanos.api.controller;

import com.thanos.common.BaseResponse;
import com.thanos.api.service.account.UserAccount;
import com.thanos.common.exception.UserLoginException;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.service.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by wangjialong on 6/3/18.
 */
@RestController
public class UserAccountController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    UserAccount userAccountHandler;

    @RequestMapping("/user/register")
    public BaseResponse.ResponseBody<RespObj> register(@RequestParam(value="phone") String phone,
                                                        @RequestParam(value="name", required=false) String name,
                                                        @RequestParam(value="icon", required=false) String icon,
                                                        @RequestParam(value="password") String password) {

        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        try {
            UserMapper userInfo = userAccountHandler.registerByPhoneNumber(phone, password, name, icon, "");
            RespObj data = wrapResponse(userInfo);
            resp.setData(data);
            resp.setCode("0");
        } catch (UserRegisterException e) {
            logger.error("exception:", e);
            String code = e.getCode();
            resp.setCode(code);
        } catch (Exception e) {
            logger.error("exception:", e);
            resp.setCode("-1");
        }
        return resp;
    }

    private RespObj wrapResponse(UserMapper userInfo) {
        RespObj data = new RespObj();
        data.budss = userInfo.getBudss();
        data.name = userInfo.getNickName();
        data.userName = userInfo.getUserName();
        data.userIcon = userInfo.getAvatarURL();
        data.isLogin = userInfo.getIsOnline();
        data.loginTime = System.currentTimeMillis();
        return data;
    }

    @RequestMapping("/user/login")
    public BaseResponse.ResponseBody<RespObj> login(@RequestParam(value = "phone") String phone,
                                                    @RequestParam(value = "password") String password) {
        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        try {
            UserMapper userInfo = userAccountHandler.userLonginByPhone(phone, password);
            RespObj data = wrapResponse(userInfo);
            resp.setData(data);
            resp.setCode("0");
        } catch (UserLoginException e) {
            String code = e.getCode();
            resp.setCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode("-1");
        }
        return resp;
    }


    public class RespObj implements Serializable {
        public String budss;
        public String name;
        public String userName;
        public char isLogin;
        public long loginTime;
        public String userIcon;
    }
}
