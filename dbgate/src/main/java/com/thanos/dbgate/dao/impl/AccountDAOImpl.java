package com.thanos.dbgate.dao.impl;

import com.thanos.dbgate.mapper.AccountDAO;
import com.thanos.dbgate.dao.BaseSqlSession;
import com.thanos.dbgate.dao.SimpleDAO;
import com.thanos.dbgate.dto.UserDTO;
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


    public int addAccount(UserDTO userDTO) throws Exception {
//        BaseSqlSession baseSqlSession = getSqlSession();
//        return baseSqlSession.getMapper(AccountDAO.class).addAccount(userDTO);
        return accountDAO.addAccount(userDTO);
    }

    public UserDTO queryUser(String phoneNumber, String password) {
        return null;
    }
}
