package com.thanos.dbgate.service.impl;

import com.thanos.common.config.relation.RelationValue;
import com.thanos.common.exception.UserRelationException;
import com.thanos.common.pojo.RelationMapper;
import com.thanos.dbgate.mapper.FollowDAO;
import com.thanos.dbgate.service.IRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * Created by wangjialong on 6/20/18.
 */
@Service(value = "RelationService")
public class RelationServiceImpl implements IRelation {

    @Autowired
    @Qualifier("FollowDAOMapper")
    private FollowDAO relationDao;

    @Transactional(value = "transactionManager", propagation=REQUIRED)
    public void addFollow(long fromUserId, long toUserId) {
        //1. check if a has followed b yet
        RelationMapper leftRelation = relationDao.queryByUserId(fromUserId, toUserId);
        if (leftRelation != null) {
            throw new UserRelationException.UserAlreadyFollowException();
        }

        //2. check if b has followed a yet
        RelationMapper rightRelation = relationDao.queryByUserId(toUserId, fromUserId);

        char direction = rightRelation==null ? RelationValue.SINGLE_LEFT_RELATION.getValue():
                RelationValue.DOUBLE_RELATION.getValue();
        //3. calculate the follow direction
        RelationMapper relationMapper = new RelationMapper(fromUserId, toUserId, direction);
        relationDao.addFollow(relationMapper);

        //4. update the relation from right to left
        if (rightRelation != null){
            rightRelation.direction = RelationValue.DOUBLE_RELATION.getValue();
            relationDao.updateDirection(fromUserId, toUserId, rightRelation.direction);
            relationDao.updateDirection(toUserId, fromUserId, rightRelation.direction);
        }
    }

    public RelationMapper getRelationMapper(long fromUserId, long toUserId) {
        return relationDao.queryByUserId(fromUserId, toUserId);
    }

    /*public List<Re> getUserFansListByPage(long userId, int count) {
        return relationDao.queryByUserId(userId, count);
    }

    public List<Long> getUserFansListByPageAndId(long userId,
                                                 long lastId,
                                                 int count) {

    }*/
}
