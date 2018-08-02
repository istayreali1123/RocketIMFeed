package com.thanos.sns.deliver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.exception.ElasticSearchException;
import com.thanos.common.messageBroker.Deliver;
import com.thanos.common.pojo.FeedMapper;
import com.thanos.common.pojo.RelationMapper;
import com.thanos.dbgate.endpoint.RelationEndPoint;
import org.elasticsearch.search.SearchHit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.thanos.common.constant.EsInex.RELATION_INDEX;
import static com.thanos.common.constant.EsInex.RELATION_TYPE;

/**
 * Created by wangjialong on 7/15/18.
 */
public class FirstLevelDeliver implements Deliver {


    public void execute(Object message) {
        try {
            String strMessage = (String) message;
            ObjectMapper mapper = new ObjectMapper();
            FeedMapper feedInfo = mapper.readValue(strMessage, new TypeReference<FeedMapper>(){});
            Long authorId = feedInfo.getAuthorId();
            Long feedId = feedInfo.getFeedId();
            makeDeliverTask(authorId, feedId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Love is love");
    }

    public void makeDeliverTask(Long userId, Long feedId) {
        //1. 首先需要知道有多少推送量

        //2. 从elastic search 拉取指定userid 的粉丝数据， 根据scrollId 做分页操作
        String scrollId = null;
        List<DeliverTask> taskList = new ArrayList();
        while (true) {
            try {
                taskList.clear();
                Term term = new Term(userId);
                Query query = new Query(term);
                ObjectMapper mapper = new ObjectMapper();
                String testV = mapper.writeValueAsString(query);
                EleasticSearchClient.SearchResult searchHits = EleasticSearchClient
                        .searchDocument(RELATION_INDEX, RELATION_TYPE, 5,  scrollId,
                                "", mapper.writeValueAsString(query), "createTime");
                if( searchHits.getSearchHit().length == 0 ) {
                    break;
                }
                for (SearchHit searchHit : searchHits.getSearchHit()) {
                    String stringData = searchHit.getSourceAsString();
                    RelationMapper relation = mapper.readValue(stringData, RelationMapper.class);
                    //3. 根据 from to 打成消息投递信戳，入投递业务线程池, 原则上这里只生成在线或者活跃用户的feed 推送任务
                    // check user activity status
                    DeliverTask task = new DeliverTask(relation.getFromUid(), relation.getToUid(), feedId);
                    taskList.add(task);
                }
                DeliverQueue.putMessage(taskList);
                scrollId = searchHits.getScrollId();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }


        //RelationEndPoint.getFansList(userId, 1000);

    }

    public static class BigQuery implements Serializable {
        public Query query;

        public BigQuery(Query query) {
            this.query = query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Query getQuery() {

            return query;
        }
    }

    public static class Query implements Serializable {
        public Term term;

        public Term getTerm() {
            return term;
        }

        public Query(Term term) {
            this.term = term;
        }

        public void setTerm(Term term) {

            this.term = term;
        }
    }

    public static class Term implements Serializable {
        public long toUid;

        public Term(long toUid) {
            this.toUid = toUid;
        }

        public long getToUid() {
            return toUid;
        }

        public void setToUid(long toUid) {

            this.toUid = toUid;
        }
    }


}
