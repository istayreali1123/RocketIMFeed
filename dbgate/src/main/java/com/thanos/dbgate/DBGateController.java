package com.thanos.dbgate;

import com.thanos.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import sun.awt.AppContext;

/**
 * Created by istayreali on 2018/6/3.
 */
@Component("dbgateController")
public class DBGateController implements Runnable {

    @Autowired
    public SpringContextUtil springContextUtil;

    private boolean running = false;
    private Thread thread;

    public DBGateController() {
        //该thread被该controller持有
        thread = new Thread(this);
    }

    public void start() {
        running = true;
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) springContextUtil.getApplicationContext();
        context.start();
        thread.start();
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException exception) {

            }
        }
    }
}
