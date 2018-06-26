package com.thanos.api.service.sns;

import com.thanos.common.pojo.FeedMapper;

/**
 * Created by wangjialong on 6/25/18.
 */
public interface UserFeed {

    void feedPublish(String bduss, FeedMapper feed);
}
