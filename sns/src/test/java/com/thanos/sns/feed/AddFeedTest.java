package com.thanos.sns.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.DBGateStartup;
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
        DBGateStartup.load();
    }

    //@Test
    public void addFeed() {
        FeedMapper feedInfo = new FeedMapper();
        feedInfo.setAuthorId(105001676);
        feedInfo.setCommentNum(0);
        feedInfo.setLikeNum(0);
        feedInfo.setText("");
        List<FeedMapper.Resource> mediaLink  = new ArrayList();
        mediaLink.add(new FeedMapper.Resource(
                "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2553700874,2589964990&fm=173&app=25&f=JPEG?w=640&h=427&s=A1D252875C33BAC4629912BD03001005",
                "image",""));
        mediaLink.add(new FeedMapper.Resource(
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2937575346,3672832990&fm=173&app=25&f=JPEG?w=640&h=640&s=B783F507570B66FC799904EF0300802A",
                "image",
                ""));
        feedInfo.setMediaLink(mediaLink);
        FeedEndPoint.addFeed(feedInfo);
    }

    @Test
    public void  query() {
        FeedMapper feed = FeedEndPoint.queryFeed(24);
        System.out.println("hello");
    }

    public void testTmp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> testList = new ArrayList();
        testList.add("aaaaa");
        testList.add("bbbbb");
        String test = mapper.writeValueAsString(testList);
        System.out.println("test data json encode :" + test);
        List<String> test2 = mapper.readValue(test, ArrayList.class);
        System.out.println("test data json encode");
    }
}
