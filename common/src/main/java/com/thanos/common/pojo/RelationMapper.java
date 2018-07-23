package com.thanos.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangjialong on 6/19/18.
 */
public class RelationMapper implements Serializable {

    public long id;

    public long fromUid;

    public RelationMapper() {
    }

    public long toUid;

    public char direction;

    public RelationMapper(long fromUid, long toUid, char direction) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.direction = direction;
        this.createTime = new Date(System.currentTimeMillis()).toString();
    }

    public String createTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setFromUid(long fromUid) {
        this.fromUid = fromUid;
    }

    public long getId() {
        return id;
    }

    public long getFromUid() {
        return fromUid;
    }

    public long getToUid() {
        return toUid;
    }

    public char getDirection() {
        return direction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setToUid(long toUid) {
        this.toUid = toUid;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
