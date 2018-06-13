package com.thanos.common.pojo;

import org.springframework.context.annotation.Lazy;
import sun.jvm.hotspot.debugger.linux.sparc.LinuxSPARCThreadContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangjialong on 6/13/18.
 */
public class FeedMapper implements Serializable {

    public long feedId;

    public FeedMapper() {
    }

    public FeedMapper(long feedId, long authorId, String text,  List<String> mediaLink) {
        this.feedId = feedId;
        this.authorId = authorId;
        this.text = text;
        this.mediaLink = mediaLink;
    }

    public long authorId;

    public String text;

    public List<String> mediaLink = new ArrayList();

    public int commentNum = 0;

    public int likeNum = 0;

    public Date createTime = new Date();

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getFeedId() {
        return feedId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public List<String> getMediaLink() {
        return mediaLink;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMediaLink(List<String> mediaLink) {
        this.mediaLink = mediaLink;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
