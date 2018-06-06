package com.thanos.dbgate;

import com.thanos.common.utils.SpringContextUtil;
import com.thanos.dbgate.config.ApplicationConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBGateStartup {

    @Autowired
    SpringContextUtil springContextUtil;

    public static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext(
                "classpath:spring-dubbo.xml", "classpath:mybatis-config.xml", "classpath:applicationContext.xml");
        context.start();
        //ctx.start();
    }

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.getEnvironment().setActiveProfiles("application"); // 先将激活的Profile设置为prod
//        ctx.register(ApplicationConfigurations.class); // 后置注册Bean配置类，不然为报Bean未定义的错误
//        ctx.refresh(); // 刷新容器

        DBGateController controller = (DBGateController) context.getBean("dbgateController");
        controller.start();
    }
}
