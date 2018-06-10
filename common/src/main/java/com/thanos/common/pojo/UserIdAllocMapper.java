package com.thanos.common.pojo;

import java.io.Serializable;

/**
 * Created by wangjialong on 6/10/18.
 */
public class UserIdAllocMapper implements Serializable {

    public long id;

    public String osid;

    public long createTime = System.currentTimeMillis();

    public void setId(long id) {
        this.id = id;
    }

    public UserIdAllocMapper() {
    }

    public void setOsid(String osid) {
        this.osid = osid;
    }

    public long getId() {
        return id;
    }

    public String getOsid() {
        return osid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
