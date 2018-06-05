package com.thanos.dbgate.dao.impl;

import com.thanos.dbgate.dao.AccountDAO;
import com.thanos.dbgate.dao.BaseSqlSession;
import com.thanos.dbgate.dao.SimpleDAO;
import com.thanos.dbgate.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository("com.thanos.dbgate.dao.AccountDAO")
public class AccountDAOImpl extends SimpleDAO implements AccountDAO {

    public int addAccount(UserDTO userDTO) throws Exception {
        BaseSqlSession baseSqlSession = getSqlSession();
        return baseSqlSession.getMapper(AccountDAO.class).addAccount(userDTO);
    }
}
