package com.thanos.dbgate.service.impl;


import com.thanos.common.BaseResponse;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserIdAllocMapper;
import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.mapper.AccountDAO;
import com.thanos.dbgate.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by istayreali on 2018/6/3.
 */
@Service(value = "accountService")
public class AccountServiceImpl implements IAccount {

   private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

   @Autowired
   @Qualifier("acountDaoMapper")
   private AccountDAO accountDAO;

   public int registerAccount(String registerInfo) throws Exception {
        BaseResponse.ResponseBody<String> resp = new BaseResponse.ResponseBody();
        UserMapper userMapper = new UserMapper();
        userMapper.setUuid("111");
        userMapper.setUserName("xxxx");
        userMapper.setPassword("123456");
        userMapper.setPhoneNumber("11111111");
        userMapper.setNickName("555555");
        userMapper.setEmailAddress("1.com");
        userMapper.setLastUpdateTime(0);
        int result = accountDAO.addAccount(userMapper);
        return result;
    }

    public void userRegister(UserMapper userInfo) throws UserRegisterException {
       try{
           accountDAO.addAccount(userInfo);
       } catch (Exception e) {
           logger.warning("add user exception " + e.getCause());
           throw new UserRegisterException();
       }
    }

    public UserMapper userLonginByPhone(String phoneNumber, String password) {
       UserMapper userInfo =  accountDAO.queryUser(phoneNumber, password);
       return userInfo;
    }


    public long allocUserId(UserIdAllocMapper uuidMapper)
            throws UserRegisterException.UserIdAllocException {
       accountDAO.uuidAlloc(uuidMapper);
       return uuidMapper.getId();
    }

    public UserIdAllocMapper queryUserIdByPhone(String phone) {
       return accountDAO.queryUidByPhone(phone);
    }

    public List<UserMapper> queryUserInfoByIds(List<String> userIds) {
       return accountDAO.getUserByIds(userIds);
    }


}
