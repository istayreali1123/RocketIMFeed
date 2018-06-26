package com.thanos.api.controller;

import com.thanos.api.service.sns.UserFeed;
import com.thanos.common.BaseResponse;
import com.thanos.common.exception.FeedPublishException;
import com.thanos.common.pojo.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangjialong on 6/25/18.
 */
@RestController
public class FeedController extends AbstractController {

    @Autowired
    public UserFeed userFeedHandler;


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
            resp.setCode("-1");
        } finally {
            resp.setData(data);
        }
        return resp;
    }
}
