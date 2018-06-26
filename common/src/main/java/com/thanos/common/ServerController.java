package com.thanos.common;

import com.thanos.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by wangjialong on 6/25/18.
 */
public class ServerController implements Runnable {

    @Autowired
    public SpringContextUtil springContextUtil;

    private boolean running = false;
    private Thread thread;

    public ServerController() {
        thread = new Thread(this);
    }

    public void start() {
        running = true;
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
