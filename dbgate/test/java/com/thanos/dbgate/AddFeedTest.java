package com.thanos.dbgate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.endpoint.FeedEndPoint;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/14/18.
 */
public class AddFeedTest {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void  query() {
        FeedMapper feed = FeedEndPoint.queryFeed(1);
        System.out.println("hello");
    }
}
