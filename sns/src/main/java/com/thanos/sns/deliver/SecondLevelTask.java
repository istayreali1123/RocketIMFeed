package com.thanos.sns.deliver;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangjialong on 7/19/18.
 */
@Component
public class SecondLevelTask {

    public static boolean running = true;

    static {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.submit(()->{
            while(running) {
                try {
                    List<DeliverTask> taskList = DeliverQueue.getMessages(100);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}


