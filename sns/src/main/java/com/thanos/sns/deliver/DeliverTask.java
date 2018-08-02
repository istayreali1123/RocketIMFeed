package com.thanos.sns.deliver;

import java.io.Serializable;

/**
 * Created by wangjialong on 7/18/18.
 */
public class DeliverTask implements Comparable<DeliverTask>, Serializable {

    public long fromUid;

    public long toUid;

    public long createTime = System.currentTimeMillis();

    public long feedId;

    public DeliverTask() {
    }

    public long getFeedId() {
        return feedId;
    }

    public long priorityScore = 0;

    public long getFromUid() {
        return fromUid;
    }

    public long getToUid() {
        return toUid;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setFromUid(long fromUid) {
        this.fromUid = fromUid;
    }

    public void setToUid(long toUid) {
        this.toUid = toUid;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setPriorityScore(long priorityScore) {
        this.priorityScore = priorityScore;
    }

    public DeliverTask(long fromUid, long toUid, long feedId) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.feedId = feedId;
    }

    public long getPriorityScore() {

        return priorityScore;
    }

    public DeliverTask(long fromUid, long toUid) {

        this.fromUid = fromUid;
        this.toUid = toUid;
    }

    public int compareTo(DeliverTask o) {
        return this.priorityScore < o.getPriorityScore()?-1:1;
    }
}
