package com.thanos.api.controller;

import com.thanos.api.service.sns.UserFeed;
import com.thanos.api.service.sns.UserFeedImpl;
import com.thanos.common.BaseResponse;
import com.thanos.common.exception.FeedPublishException;
import com.thanos.common.pojo.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangjialong on 6/25/18.
 */
@RestController
public class FeedController extends AbstractController {

    @Autowired
    public UserFeedImpl userFeedHandler;


    @RequestMapping("/feed/publish")
    public BaseResponse.ResponseBody<AbstractController.RespObj> feedPublish(@RequestParam(value = "bduss") String bduss,
                                                                             @RequestBody(required=true)FeedMapper feed) {
        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        RespObj data = new FeedController.RespObj();
        try {
            userFeedHandler.feedPublish(bduss, feed);
        } catch (FeedPublishException.feedStorageException e) {
            e.printStackTrace();
            String code = e.getCode();
            resp.setCode(code);
        } catch (Exception e ) {
            e.printStackTrace();
            resp.setCode("-1");
        } finally {
            resp.setData(data);
        }
        return resp;
    }

    @RequestMapping("/feed/get")
    public BaseResponse.ResponseBody<FeedList> getFeedStream(@RequestParam(value="bduss") String bduss,
                                                                              @RequestParam(value="page") int page,
                                                                              @RequestParam(value="scrollId") String scrollId,
                                                                              @RequestParam(value="size") int size) {
        BaseResponse.ResponseBody<FeedList> resp = new BaseResponse.ResponseBody();
        scrollId = scrollId.equals("") ? null: scrollId;
        StringBuilder lastId = new StringBuilder("");
        List<FeedMapper> data = userFeedHandler.pullFeedList(bduss, scrollId, size, lastId);
        int count = data.size();
        char hasNext = count < size? '0':'1';
        FeedList feedList = new FeedList(data, lastId.toString(), hasNext);
        resp.setData(feedList);
        return resp;
    }

    public static class FeedList implements Serializable {

        public List<FeedMapper> list;

        public String scrollId;

        public FeedList(List<FeedMapper> list, String scrollId, char hasNext) {
            this.list = list;
            this.scrollId = scrollId;
            this.hasNext = hasNext;
        }

        public char hasNext;

        public List<FeedMapper> getList() {
            return list;
        }

        public String getScrollId() {
            return scrollId;
        }

        public char getHasNext() {
            return hasNext;
        }

        public void setList(List<FeedMapper> list) {

            this.list = list;
        }

        public void setScrollId(String scrollId) {
            this.scrollId = scrollId;
        }

        public void setHasNext(char hasNext) {
            this.hasNext = hasNext;
        }
    }
}
