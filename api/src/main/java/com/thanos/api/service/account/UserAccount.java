package com.thanos.api.service.account;

import com.thanos.account.mapper.UserInfo;
import com.thanos.common.exception.PhoneNumberException;
import com.thanos.common.exception.UserRegisterException;

/**
 * Created by wangjialong on 6/3/18.
 */
public interface UserAccount {

    UserInfo registerByPhoneNumber(String phoneNumber, String password, String nickanme,
                                   String userIcon, String verifyCode) throws UserRegisterException;


    void verifyPhoneNumber(String phoneNumber) throws PhoneNumberException;
}
