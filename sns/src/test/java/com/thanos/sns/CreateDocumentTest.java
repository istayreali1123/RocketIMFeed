package com.thanos.sns;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.DBGateStartup;
import com.thanos.sns.feed.FeedPublish;
import com.thanos.sns.relation.FollowRelation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 6/13/18.
 */
public class CreateDocumentTest {

    @Before
    public void init() {
        EleasticSearchClient.start();
        DBGateStartup.load();
    }

    public void testCreateDocument() {
        List<FeedMapper.Resource> mediaLink = new ArrayList();
        /*mediaLink.add("http://www.baidu.com/1.jpg");
        mediaLink.add("http://www.baidu.com/1.jpg");
        FeedMapper feedInfo = new FeedMapper(1, 2, "Hello",  mediaLink);
        EleasticSearchClient.createDocument(feedInfo, "2");*/
    }

    @Test
    public void testFeedPublish() {
        FeedMapper feedInfo = new FeedMapper();
        feedInfo.setAuthorId(105001676);
        feedInfo.setCommentNum(0);
        feedInfo.setLikeNum(0);
        feedInfo.setText("日日日");
        List<FeedMapper.Resource> mediaLink  = new ArrayList();
        mediaLink.add(new FeedMapper.Resource(
                "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2553700874,2589964990&fm=173&app=25&f=JPEG?w=640&h=427&s=A1D252875C33BAC4629912BD03001005",
                "image",""));
        mediaLink.add(new FeedMapper.Resource(
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2937575346,3672832990&fm=173&app=25&f=JPEG?w=640&h=640&s=B783F507570B66FC799904EF0300802A",
                "image",
                ""));
        feedInfo.setMediaLink(mediaLink);
        FeedPublish obj = new FeedPublish();
        obj.feedPublish(feedInfo);
        /*List<String> mediaLink = new ArrayList();
        mediaLink.add("http://www.baidu.com/1.jpg");
        mediaLink.add("http://www.baidu.com/1.jpg");
        FeedMapper feedInfo = new FeedMapper(2, "Hello",  mediaLink);
        DBGateStartup.load();
        FeedPublish obj = new FeedPublish();
        obj.feedPublish(feedInfo);*/
    }

    //@Test
    public void testAddFollow() {
        FollowRelation obj = new FollowRelation();
        obj.userFollow(105001676, 105003488);
    }
}
