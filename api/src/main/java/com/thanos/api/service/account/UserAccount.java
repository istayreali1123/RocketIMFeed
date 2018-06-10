package com.thanos.api.service.account;

import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserMapper;

/**
 * Created by wangjialong on 6/3/18.
 */
public interface UserAccount {

    UserMapper registerByPhoneNumber(String phoneNumber, String password, String nickanme,
                                   String userIcon, String verifyCode) throws UserRegisterException;


    void verifyPhoneNumber(String phoneNumber) throws UserRegisterException.PhoneAlreadyUsedException;

    UserMapper userLonginByPhone(String phone, String password);

}
