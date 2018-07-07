package com.thanos.common.user;

import com.thanos.common.annotations.GenerateCache;
import com.thanos.common.cache.BaseCache;
import com.thanos.common.cache.ServiceCache;
import com.thanos.common.pojo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangjialong on 6/26/18.
 */
public class UserInfo {

    public static ServiceCache cache;

    static {
        cache = new ServiceCache() {

            @Override
            @GenerateCache(dataSource="com.thanos.dbgate.endpoint.UserAccountEndPoint",
                    refreshMethod="queryUserByIds")
            public void refreshData() {

            }
        };
    }

    public static long getUserIdByBduss(String bduss) {
        return 105001787;
    }


    public static List<UserMapper> getUserInfo(List<String> userIds) {
       return cache.getData(userIds, UserMapper.class);
    }
}
