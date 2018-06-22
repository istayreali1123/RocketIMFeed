package com.thanos.dbgate;

import com.thanos.common.pojo.UserMapper;
import com.thanos.dbgate.endpoint.UserAccountEndPoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 6/11/18.
 */
public class UserLoginTest {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testQueryUser() {
        //AccountDAO accountDAO = (AccountDAO) context.getBean("acountDao");
        UserMapper data = UserAccountEndPoint.userLonginByPhone("105002987", "8807201139love1");
        ;
    }
}
