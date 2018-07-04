package com.thanos.dbgate;

import com.thanos.common.utils.SpringContextUtil;
import com.thanos.dbgate.service.IAccount;
import com.thanos.dbgate.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by istayreali on 2018/6/3.
 */
@Component("dbgateController")
public class DBGateController implements Runnable {

    @Autowired
    public SpringContextUtil springContextUtil;

    /*@Autowired(required = false)
    private IAccount accountService;*/

    private boolean running = false;
    private Thread thread;

    public DBGateController() {
        //该thread被该controller持有
        thread = new Thread(this);
    }

    public void start() {
        /*try {
            accountService.registerAccount("");
        }catch (Exception exception) {
            System.out.print(exception.getMessage());
        }

//        running = true;
//        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) springContextUtil.getApplicationContext();
//        context.start();
//        thread.start();*/
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
