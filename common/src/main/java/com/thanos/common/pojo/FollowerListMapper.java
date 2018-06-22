package com.thanos.common.pojo;

import java.util.List;

/**
 * Created by wangjialong on 6/21/18.
 */
public class FollowerListMapper {
    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> followers;

    public List<Long> getFollowers() {
        return followers;
    }
}
