package com.thanos.dbgate.service.impl;


import com.thanos.common.BaseResponse;
import com.thanos.dbgate.dao.impl.AccountDAOImpl;
import com.thanos.common.exception.UserRegisterException;
import com.thanos.common.pojo.UserInfo;
import com.thanos.dbgate.mapper.AccountDAO;
import com.thanos.dbgate.dto.UserDTO;
import com.thanos.dbgate.service.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("111");
        userDTO.setUserName("xxxx");
        userDTO.setPassword("123456");
        userDTO.setPhoneNnumber("11111111");
        userDTO.setNickName("555555");
        userDTO.setEmailAddress("1.com");
        userDTO.setLastUpdateTime(null);
        int result = accountDAO.addAccount(userDTO);
        return result;
    }

    public void userRegister(UserInfo userInfo) throws UserRegisterException {

    }

    public UserDTO userLonginByPhone(String phoneNumber, String password) {
       return accountDAO.queryUser(phoneNumber, password);
    }
}
