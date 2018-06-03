package com.thanos.api.controller;

import com.thanos.common.utils.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 6/3/18.
 */
public class AbstractController {

    public static ApplicationContext context;

    public AbstractController() {
        context = (ClassPathXmlApplicationContext) SpringContextUtil.getApplicationContext();
    }
}
