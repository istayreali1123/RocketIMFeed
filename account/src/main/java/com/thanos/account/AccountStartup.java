package com.thanos.account;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 5/31/18.
 */
public class AccountStartup {

    public static ClassPathXmlApplicationContext context;

    static {
            context = new ClassPathXmlApplicationContext(
                    "classpath:spring-dubbo.xml");
            context.start();
            //ctx.start();
        }

   public static void main(String[] args) {
        AccountController controller = (AccountController) context.getBean("accountController");
        controller.start();
   }
}
