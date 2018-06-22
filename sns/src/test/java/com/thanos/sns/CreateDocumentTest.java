package com.thanos.sns;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.dbgate.DBGateStartup;
import com.thanos.sns.relation.Follow;
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
    }

    public void testCreateDocument() {
        List<String> mediaLink = new ArrayList();
        mediaLink.add("http://www.baidu.com/1.jpg");
        mediaLink.add("http://www.baidu.com/1.jpg");
        FeedMapper feedInfo = new FeedMapper(1, 2, "Hello",  mediaLink);
        EleasticSearchClient.createDocument(feedInfo, "2");
    }


    public void testFeedPublish() {
        List<String> mediaLink = new ArrayList();
        mediaLink.add("http://www.baidu.com/1.jpg");
        mediaLink.add("http://www.baidu.com/1.jpg");
        FeedMapper feedInfo = new FeedMapper(2, "Hello",  mediaLink);
        DBGateStartup.load();
        FeedPublish obj = new FeedPublish();
        obj.pubFeedToElasticSearch(feedInfo);
    }

    @Test
    public void testAddFollow() {
        Follow obj = new Follow();
        obj.userFollow(105001676, 105003488);
    }
}
