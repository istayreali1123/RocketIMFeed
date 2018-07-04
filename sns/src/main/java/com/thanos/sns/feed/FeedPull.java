package com.thanos.sns.feed;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.sns.service.intereface.Feed;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjialong on 6/28/18.
 */
public class FeedPull implements Feed {

    ObjectMapper mapper = new ObjectMapper();

    public FeedList feedPull(long userId, String scrollId, int size, long gt)
            throws ElasticSearchException.ElasticSearchQueryException {
        List<FeedMapper> feedList = new ArrayList();
        System.out.println("got request......");
        Query query = buildQuery(userId, gt);
        try {
            EleasticSearchClient.SearchResult searchHits = EleasticSearchClient.searchDocument("thanos", "feed",
                    size, scrollId,"authorId", mapper.writeValueAsString(buildQuery(userId, gt)));
            for (SearchHit searchHit: searchHits.getSearchHit()) {
                String stringData = searchHit.getSourceAsString();
                feedList.add(mapper.readValue(stringData, FeedMapper.class));
            }
            String scroll = searchHits.getScrollId();
            return new FeedList(feedList, scroll);
        }catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Query buildQuery(long userId, long scrollId) {
        FollowQuery followQuery = new FollowQuery();
        followQuery.setId(String.valueOf(userId));

        TermsQuery termsQuery = new TermsQuery();
        termsQuery.setAuthorId(followQuery);

        Must must = new Must();
        must.setTerms(termsQuery);
        Bool bool = new Bool();
        Filter filter = new Filter();
        Gt gt = new Gt();
        gt.setGt(scrollId);
        Range range = new Range();
        range.setFeedId(gt);
        filter.setRange(range);
        bool.setMust(must);
        bool.setFilter(filter);

        Query query = new Query();
        query.setBool(bool);
        return query;
    }

    public class Query {
        Bool bool;

        public Bool getBool() {
            return bool;
        }

        public void setBool(Bool bool) {
            this.bool = bool;
        }
    }

    public class Bool {

        Must must;
        Filter filter;

        public void setMust(Must must) {
            this.must = must;
        }

        public Filter getFilter() {
            return filter;
        }

        public Must getMust() {

            return must;
        }

        public void setFilter(Filter filter) {
            this.filter = filter;
        }
    }

    public class Must {

        public TermsQuery terms;

        public void setTerms(TermsQuery terms) {
            this.terms = terms;
        }

        public TermsQuery getTerms() {

            return terms;
        }
    }

    public class Filter {
        public Range range;

        public Range getRange() {
            return range;
        }

        public void setRange(Range range) {
            this.range = range;
        }
    }

    public class Range {
        public Gt feedId;

        public Gt getFeedId() {
            return feedId;
        }

        public void setFeedId(Gt feedId) {
            this.feedId = feedId;
        }
    }

    public class Gt {

        long gt;

        public void setGt(long gt) {
            this.gt = gt;
        }

        public long getGt() {
            return gt;
        }
    }

    public static void main(String[] args) {
        try {
            EleasticSearchClient.start();
            FeedPull obj = new FeedPull();
            obj.feedPull(105001676, null, 1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    private class TermsQuery implements Serializable {

        FollowQuery authorId;

        public void setAuthorId(FollowQuery authorId) {
            this.authorId = authorId;
        }

        public FollowQuery getAuthorId() {
            return authorId;
        }
    }

    private class FollowQuery implements Serializable {
        String index="follows";
        String type = "follower";

        public String getType() {
            return type;
        }

        public String getId() {
            return authorId;
        }

        public String getPath() {
            return path;
        }

        public void setIndex(String index) {

            this.index = index;
        }

        public void setId(String id) {
            this.authorId = id;
        }

        public void setPath(String path) {
            this.path = path;
        }

        String authorId;

        public String getIndex() {
            return index;
        }

        protected void setType(String type) {
            this.type = type;
        }

        String path = "followers";
    }

    public static class FeedList implements Serializable {
        public FeedList() {
        }

        List<FeedMapper> feedList;

        public FeedList(List<FeedMapper> feedList, String scrollId) {
            this.feedList = feedList;
            this.scrollId = scrollId;
        }

        String scrollId;

        public List<FeedMapper> getFeedList() {
            return feedList;
        }

        public String getScrollId() {
            return scrollId;
        }
    }
}
