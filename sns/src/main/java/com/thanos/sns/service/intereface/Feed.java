package com.thanos.sns.service.intereface;

import com.thanos.common.pojo.FeedMapper;

/**
 * Created by wangjialong on 6/25/18.
 */
public interface Feed {

    void feedPublish(FeedMapper feedInfo);
}
