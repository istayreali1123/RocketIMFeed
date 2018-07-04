package com.thanos.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.pojo.FeedMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjialong on 7/3/18.
 */
public class FeedMapperTest {

    public ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testFeedMapper() throws Exception {

        FeedMapper feed = new FeedMapper();
        feed.setAuthorId(1);
        feed.setText("Hi");
        feed.setLikeNum(0);
        feed.setCommentNum(0);
        List<FeedMapper.Resource> mediaLink = new ArrayList();
        mediaLink.add(new FeedMapper.Resource("test1", "image", "test2"));
        feed.setMediaLink(mediaLink);
        String json = mapper.writeValueAsString(feed);
        FeedMapper out = mapper.readValue(json, FeedMapper.class);
        String json2 = mapper.writeValueAsString(mediaLink);
        List<FeedMapper.Resource> out2 = (List<FeedMapper.Resource>) mapper.readValue(json2,
                new TypeReference<List<FeedMapper.Resource>>(){});
    }
}
