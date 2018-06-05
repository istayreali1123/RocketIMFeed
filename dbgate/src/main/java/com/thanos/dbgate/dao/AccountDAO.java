package com.thanos.dbgate.dao;

import com.thanos.dbgate.dto.UserDTO;

public interface AccountDAO {
    int addAccount(UserDTO userDTO) throws Exception;
}
