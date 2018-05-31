package com.thanos.account;

import com.thanos.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by wangjialong on 5/31/18.
 */
@Component("accountController")
public class AccountController implements Runnable {

    @Autowired
    public SpringContextUtil springContextUtil;

    private boolean running = false;
    private Thread thread;

    public AccountController() {
        thread = new Thread(this);
    }

    public void start() {
        running = true;
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext )springContextUtil.getApplicationContext();
        context.start();
        thread.start();
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
