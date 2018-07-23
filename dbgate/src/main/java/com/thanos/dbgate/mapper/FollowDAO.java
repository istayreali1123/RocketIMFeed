package com.thanos.dbgate.mapper;

import com.thanos.common.pojo.RelationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangjialong on 6/19/18.
 */
@Repository("FollowDAOMapper")
public interface FollowDAO {

    void addFollow(@Param("relation") RelationMapper relation);

    RelationMapper queryByUserId(long fromUserId, long toUserId);

    void updateDirection(@Param("fromUserId") long fromUserId,
                         @Param("toUserId") long toUserId,
                         @Param("direction") char direction);

    List<RelationMapper> getUserFansListByPage(@Param("userId") long userId, @Param("count") int count);

    List<RelationMapper> getUserFansListByPageAndId(@Param("userId") long userId,
                                                    @Param("lastId") long lastId,
                                                    @Param("count")int count);
}
