package com.thanos.sns;

import com.thanos.sns.deliver.DeliverQueue;
import com.thanos.sns.deliver.DeliverTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjialong on 8/2/18.
 */
public class TestQueue {

    @Test
    public void testQueue() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<DeliverTask> taskList = new ArrayList();
        DeliverTask task = new DeliverTask(1, 2, 3);
        taskList.add(task);

        executorService.submit(()->{
            while (true) {
                DeliverQueue.getMessages(100);
            }

        });

        executorService.submit(()->{
            while (true) {
                DeliverQueue.getMessages(100);
            }

        });

        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }

        while(true) {
            try {
                DeliverQueue.putMessage(taskList);
                Thread.sleep(10000);
            } catch (Exception e) {

            }

        }

    }
}
