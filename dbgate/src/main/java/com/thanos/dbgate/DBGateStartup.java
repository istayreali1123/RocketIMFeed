package com.thanos.dbgate;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBGateStartup {
    public static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext(
                "classpath:spring-dubbo.xml");
        context.start();
        //ctx.start();
    }

    public static void main(String[] args) {
        DBGateController controller = (DBGateController) context.getBean("accountController");
        controller.start();
    }
}
