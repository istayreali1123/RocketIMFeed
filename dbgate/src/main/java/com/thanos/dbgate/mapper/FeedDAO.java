package com.thanos.dbgate.mapper;

import com.thanos.common.pojo.FeedMapper;
import com.thanos.common.pojo.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wangjialong on 6/14/18.
 */
@Repository("feedDaoMapper")
public interface FeedDAO {

    int addFeed(@Param("sns") FeedMapper user) throws Exception;

    FeedMapper queryFeed(@Param("feedId") long feedId) throws Exception;
}
