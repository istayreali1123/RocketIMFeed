package com.thanos.common.pojo;

import com.thanos.common.annotations.ToMap;

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

    public FeedMapper(long feedId, long authorId, String text,  List<Resource> mediaLink) {
        this.feedId = feedId;
        this.authorId = authorId;
        this.text = text;
        this.mediaLink = mediaLink;
    }

    public FeedMapper(long authorId, String text,  List<Resource> mediaLink) {
        this.authorId = authorId;
        this.text = text;
        this.mediaLink = mediaLink;
    }

    public long authorId;

    public FeedMapper(long feedId, long authorId, String text, List<Resource> mediaLink,
                      int commentNum, int likeNum, long createTime) {
        this.feedId = feedId;
        this.authorId = authorId;
        this.text = text;
        this.mediaLink = mediaLink;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.createTime = createTime;
    }

    public String text;

    @ToMap
    public List<Resource> mediaLink = new ArrayList();

    public int commentNum = 0;

    public int likeNum = 0;

    public long createTime = new Date().getTime();

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

    public List<Resource> getMediaLink() {
        return mediaLink;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMediaLink(List<Resource> mediaLink) {
        this.mediaLink = mediaLink;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static class Resource implements Serializable {

        // 暂时不考虑多码率多资源， 需要在加一个append 字段
        public String resourceUrl;

        public String resourceType;

        public String thumbUrl;

        public Resource() {
        }

        public Resource(String resourceUrl, String resourceType, String thumbUrl) {
            this.resourceUrl = resourceUrl;
            this.resourceType = resourceType;
            this.thumbUrl = thumbUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getResourceUrl() {

            return resourceUrl;
        }

        public String getResourceType() {
            return resourceType;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }
    }
}
