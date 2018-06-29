package com.thanos.api.controller;

import com.thanos.api.service.sns.UserRelation;
import com.thanos.common.BaseResponse;
import com.thanos.common.exception.FeedPublishException;
import com.thanos.common.exception.UserRelationException;
import com.thanos.common.pojo.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangjialong on 6/26/18.
 */
@RestController
public class RelationController extends AbstractController {

    @Autowired
    public UserRelation userRelationHandler;

    @RequestMapping("/user/follow")
    public BaseResponse.ResponseBody<RespObj> feedPublish(@RequestParam(value = "bduss") String bduss,
                                                          @RequestParam(value = "toUserId") long userId) {
        BaseResponse.ResponseBody<RespObj> resp = new BaseResponse.ResponseBody();
        RespObj data = new RespObj();
        try {
            userRelationHandler.addFollow(bduss, userId);
        } catch (UserRelationException e) {
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

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
