package com.thanos.dbgate.dao.impl;

import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.mapper.AccountDAO;
import com.thanos.dbgate.dao.SimpleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("com.thanos.dbgate.mapper.AccountDAO")
public class AccountDAOImpl extends SimpleDAO implements AccountDAO {


    private AccountDAO accountDAO;

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired()
    public AccountDAOImpl(AccountDAO accountDAO) {
        this.setAccountDAO(accountDAO);
    }


    public int addAccount(UserMapper userMapper) throws Exception {
//        BaseSqlSession baseSqlSession = getSqlSession();
//        return baseSqlSession.getMapper(AccountDAO.class).addAccount(userMapper);
        return accountDAO.addAccount(userMapper);
    }

    public UserMapper queryUser(String phoneNumber, String password) {
        return null;
    }

}
