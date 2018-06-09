package com.thanos.dbgate.mapper;

import com.thanos.dbgate.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//mybatis接口mapper需要加@Repository注解，方可直接在Service层直接自动装配注入。
@Component
public interface AccountDAO {

    int addAccount(UserDTO userDTO) throws Exception;
}
