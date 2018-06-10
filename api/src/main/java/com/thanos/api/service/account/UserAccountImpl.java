package com.thanos.api.service.account;

import com.alibaba.dubbo.config.annotation.Reference;
import com.thanos.account.intereface.Login;
import com.thanos.account.intereface.Register;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.exception.PhoneNumberException;
import com.thanos.common.pojo.UserMapper;
import com.thanos.common.utils.MD5Util;
import org.springframework.stereotype.Service;

/*
 * Created by wangjialong on 6/3/18.
 */
@Service("userAccountHandler")
public class UserAccountImpl implements UserAccount {

    @Reference
    Register registerHandler;

    @Reference
    Login loginHandler;

    public UserMapper registerByPhoneNumber(String phoneNumber, String password, String nickname,
                                            String userIcon, String verifyCode) throws UserRegisterException {
        //step1: check if the phone number has been used
        verifyPhoneNumber(phoneNumber);

        //step3: do double md5 to password, password should be encrypted in client first
        password = MD5Util.md5(MD5Util.md5(password));

        //step2: build userInfo Instance
        UserMapper userInfo = new UserMapper();
        userInfo.setUserName(phoneNumber);
        userInfo.setPhoneNumber(phoneNumber);
        userInfo.setPassword(password);
        userInfo.setNickName(nickname);
        userInfo.setAvatarURL(userIcon);

        //step3. add user info into db gate
        registerHandler.addUser(userInfo);

        //step4. active user session status, genetate user bduss

        return userInfo;

    }

    public UserMapper userLonginByPhone(String phone, String password) {
        // step1: encrypt the passpword
        password = MD5Util.md5(MD5Util.md5(password));

        // step2: verify the passpord
        UserMapper userInfo =  loginHandler.userLonginByPhone(phone, password);

        //step3. activate the user session, generate the bduss

        return userInfo;
    }

    public void verifyPhoneNumber(String phoneNumber) throws PhoneNumberException {

    }
}
