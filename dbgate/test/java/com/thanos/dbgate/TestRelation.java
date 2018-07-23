package com.thanos.dbgate;

import com.thanos.common.pojo.RelationMapper;
import com.thanos.dbgate.mapper.FollowDAO;
import com.thanos.dbgate.service.IRelation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wangjialong on 6/21/18.
 */
public class TestRelation {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public void testFollow() {
        IRelation handler = (IRelation) context.getBean("RelationService");
        long fromUserId = 105002955;
        long toUserId = 105001676;
        handler.addFollow(fromUserId, toUserId);

    }

    @Test
    public void testQueryFans() {
        FollowDAO dao = (FollowDAO) context.getBean("FollowDAOMapper");
        List<RelationMapper> fans =  dao.getUserFansListByPage(105001676, 1);
        ;
    }
}
