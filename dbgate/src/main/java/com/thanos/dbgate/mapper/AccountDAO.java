package com.thanos.dbgate.mapper;

import com.thanos.dbgate.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO {


    int addAccount(UserDTO userDTO) throws Exception;
}
