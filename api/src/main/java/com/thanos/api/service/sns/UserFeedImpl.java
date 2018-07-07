package com.thanos.api.service.sns;

import com.alibaba.dubbo.config.annotation.Reference;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.common.user.UserInfo;
import com.thanos.sns.feed.FeedPull;
import com.thanos.sns.service.intereface.Feed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangjialong on 6/25/18.
 */
@Service("UserFeedImpl")
public class UserFeedImpl implements UserFeed {

    @Reference(group="pub")
    public Feed feedServiceHandler;

    @Reference(group="pull")
    public Feed feedPullServiceHandler;

    public void feedPublish(String bduss, FeedMapper feed) {
        //1. 反解bduss, 获取用户user id
        long userId = UserInfo.getUserIdByBduss(bduss);

        //2. 检查feed内容， 做内容过滤

        feed.setAuthorId(userId);
        Date createTime = new Date();
        feed.setCreateTime(createTime.getTime());

        //3. add feed to storage
        feedServiceHandler.feedPublish(feed);
    }

    public List<FeedMapper> pullFeedList(String bduss, String scrollId, int size, StringBuilder lastId) {

        long userId = UserInfo.getUserIdByBduss(bduss);
        userId = 105001676;
        //1. get feed info list from user mail box
        List<FeedMapper> feedInMail = getFeedListByMailBox(userId, scrollId, size);

        long lastFeedId;

        if (feedInMail.isEmpty()) {
            lastFeedId = 0;
        } else {
            lastFeedId = feedInMail.get(feedInMail.size() - 1).feedId;
        }


        //2. check if the data can satisfy the count size
        int countRemain = size - feedInMail.size();
        if (countRemain <= 0) {
            lastId.append(lastFeedId);
            return feedInMail;
        }


        //3. get the remaining feed stream by pull mode
        FeedPull.FeedList pullData = feedPullServiceHandler.feedPull(userId, scrollId,  size,  lastFeedId);
        List<FeedMapper> feedByPull = pullData.getFeedList();
        String new_scroll = pullData.getScrollId();
        lastId.append(new_scroll);

        //4. merge the two feed list to to one
        Stream<FeedMapper> feedStreamInMail = feedInMail.stream();
        Stream<FeedMapper> feedStreamByPull = feedByPull.stream();

        Stream<FeedMapper> feedStream = Stream.concat(feedStreamInMail, feedStreamByPull);

        List<FeedMapper> feedList = feedStream.collect(Collectors.toList());

        return feedList;
    }

    public List<FeedMapper> getFeedListByMailBox(long userId, String scrollId, int size) {
        return new ArrayList<FeedMapper>();
    }

    public String getLastFeedId(List<FeedMapper> feedList) {
        return "";
    }
}
