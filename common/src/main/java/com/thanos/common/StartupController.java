package com.thanos.common;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjialong on 6/25/18.
 */
public class StartupController {

    public static ClassPathXmlApplicationContext context;

    public String confName;

    private boolean running = false;
    private Thread thread;

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public void start() {
        context = new ClassPathXmlApplicationContext(confName);

        context.start();

        ServerController controller = (ServerController) context.getBean("serverController");

        controller.start();
    }
}
