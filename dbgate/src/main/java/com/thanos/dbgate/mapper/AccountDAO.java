package com.thanos.dbgate.mapper;

import com.thanos.common.pojo.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//mybatis接口mapper需要加@Repository注解，方可直接在Service层直接自动装配注入。
@Repository("acountDaoMapper")
public interface AccountDAO {

    int addAccount(@Param("user") UserMapper user) throws Exception;

    UserMapper queryUser(@Param("phoneNumber")String phoneNumber, @Param("password") String password);
}
