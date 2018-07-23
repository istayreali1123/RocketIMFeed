package com.thanos.sns.deliver;

import com.thanos.common.pojo.RelationMapper;
import com.thanos.common.utils.RandomUtils;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangjialong on 7/18/18.
 */
public class DeliverQueue {

    static Queue<DeliverTask> taskPriorityQueue = new PriorityQueue();

    private static final ReadWriteLock lockQueue = new ReentrantReadWriteLock();

    private static final Condition writeCondition = lockQueue.writeLock().newCondition();

    private static final SpinLock spinLock = new SpinLock();

    private static final AtomicLong msgCount = new AtomicLong();

    private static Sync sync = new Sync();


    // 需要支持按优先级推送， 比如优先推送活跃用户

    public static void putMessage(List<DeliverTask> tasks) {
        try {
            lockQueue.writeLock().lockInterruptibly();
            int vaildMsgCount = 0;
            for (DeliverTask task: tasks) {
                // 计算推送优先级分数
                int score = RandomUtils.getRandomInt(9999);
                task.setPriorityScore(Long.valueOf(score));
                if (taskPriorityQueue.offer(task)) {
                    vaildMsgCount++;
                }
            }
            msgCount.addAndGet(vaildMsgCount);
        } catch (InterruptedException e) {
             e.printStackTrace();
        } finally {
            writeCondition.signalAll();
            lockQueue.writeLock().unlock();
        }

        //integerPriorityQueue.add(task);
    }
    
    public static List<DeliverTask> getMessages(int batchSize) {
        List<DeliverTask> taskList = new ArrayList();
        try {
            waitNotEmpty();
            lockQueue.readLock().lockInterruptibly();
            for (int i = 0; i < batchSize; ++i) {
                DeliverTask task = poll();
                if (task !=null ) {
                    taskList.add(task);
                    msgCount.decrementAndGet();
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lockQueue.readLock().unlock();
        }
        return taskList;
    }

    public static DeliverTask poll() {
        DeliverTask task;
        try {
            spinLock.lock();
            task = taskPriorityQueue.poll();
        } finally {
            spinLock.unlock();
        }
        return task;
    }

    public static void waitNotEmpty() {
        while (true) {
            try {
                if (taskPriorityQueue.isEmpty()) {
                    try {
                        lockQueue.writeLock().lockInterruptibly();
                        writeCondition.await();
                    } finally {
                         lockQueue.writeLock().unlock();
                    }
                } else {
                    break;
                }
            } catch (InterruptedException e) {

            } finally {
            }
        }
    }

    protected static class SpinLock {
        protected Sync sync = new Sync();

        protected Thread currentOwner;

        public void setCurrentOwner(Thread currentOwner) {
            this.currentOwner = currentOwner;
        }

        protected void lock() {
            int c = sync.getOriginState();

            while(true) {
                if (sync.tryAcquire(c,1)) {
                    break;
                }
            }
            setCurrentOwner(Thread.currentThread());
        }

        public Thread getCurrentOwner() {
            return currentOwner;
        }

        protected void unlock() {
            if (!Objects.equals(Thread.currentThread(), getCurrentOwner())) {
                throw new IllegalMonitorStateException();
            }
            sync.tryRelease(1);
            setCurrentOwner(null);
        }
    }

    static class Sync extends AbstractQueuedSynchronizer {

        protected boolean tryAcquire(int originState, int acquires) {
            return compareAndSetState(originState, originState + acquires);
        }

        protected boolean tryRelease(int acquires) {
            int c = getState();
            return compareAndSetState(c, c - acquires);
        }

        protected int getOriginState() {
            return getState();
        }

    }
}
