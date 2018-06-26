package com.thanos.sns.relation;

import com.thanos.common.es.EleasticSearchClient;
import com.thanos.common.exception.UserRelationException;
import com.thanos.dbgate.endpoint.RelationEndPoint;
import com.thanos.sns.service.intereface.Relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjialong on 6/19/18.
 */
public class FollowRelation implements Relation {

    public final String INDEX = "follows";

    public final String TYPE = "follower";

    public void userFollow(long fromUserId, final long toUserId) throws UserRelationException.UserAlreadyFollowException {

        //1. store user relation in db
        try {
            RelationEndPoint.userFollow(fromUserId, toUserId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }


        final Map<String, Object> upsert = new HashMap();
        Map<String, Object> params = new HashMap(){
            {
                put("follower", toUserId);
            }
        };

        upsert.put("followers", new ArrayList<Long>() {
            {
                add(toUserId);
            }
        });

        Map<String, Object> options = new HashMap(){
            {
                put("upsert", upsert);
            }
        };


        //2. pub user relation into elastic search
        EleasticSearchClient.createOrUpdateDocumentByMap(INDEX, TYPE , "ctx._source.followers.add(params.follower)"
                , params, upsert, String.valueOf(fromUserId));
    }
}
