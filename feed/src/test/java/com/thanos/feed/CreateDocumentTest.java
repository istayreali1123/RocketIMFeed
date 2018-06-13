package com.thanos.feed;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.pojo.FeedMapper;
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

    @Test
    public void testCreateDocument() {
        List<String> mediaLink = new ArrayList();
        mediaLink.add("http://www.baidu.com/1.jpg");
        mediaLink.add("http://www.baidu.com/1.jpg");
        FeedMapper feedInfo = new FeedMapper(1, 2, "Hello",  mediaLink);
        EleasticSearchClient.createDocument(feedInfo);
    }
}
