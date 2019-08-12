package com.jcs.consumer.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池添加task时的执行过程：
 * 1. 如果池中线程数小于核心线程数，直接创建一个线程入池并执行此task
 * 2. 如果池中线程数大于或等于核心线程数，则判断队列中task数量：
 * <1>如果队列中task数量未达到队列容量，则task直接进队列（不创建新线程，因为线程是很重的资源，能不新建就不新建，例外情况是
 * 如果核心线程数是0，则新建一个线程）
 * <2>如果队列中task数量达到队列最大容量，且池中线程数量未达到最大线程数，则创建一个线程入池，并用此线程执行
 * 此task（注意，是直接执行本次要添加的task，而不是从队列里拿task）
 * <3>如果队列中task数量达到队列最大容量，且池中线程数量达到最大线程数，则根据指定的RejectPolicy来决定：
 * CallerRunsPolicy: 用提交task的线程来执行此task，不扔RejectedExecutionException
 * AbortPolicy: 直接丢弃此task，扔RejectedExecutionException; 此策略为未指定RejectedExecutionHandler时的默认策略
 * DiscardPolicy: 直接丢弃此task，不扔RejectedExecutionException
 * DiscardOldestPolicy: 丢弃队列中最老的task，并将此task放入队尾，不扔RejectedExecutionException
 * 线程池构造函数：
 * int corePoolSize,                  核心线程数
 * int maximumPoolSize,               最大线程数
 * long keepAliveTime,                线程闲置时保持存活的时间数量
 * TimeUnit unit,                     线程闲置时保持存活的时间单位
 * BlockingQueue<Runnable> workQueue, 线程任务队列
 * ThreadFactory threadFactory,       线程池用于创建线程的工厂类
 * RejectedExecutionHandler handler   线程池满且队列满时的任务提交策略
 *
 */
public class ThreadPools {
    private static final AtomicInteger unPooledThreadNumber = new AtomicInteger(1);

    private static final Logger log = LoggerFactory.getLogger(ThreadPools.class);
    private ExecutorService syncBigDataPool;

    private ThreadPools() {
        syncBigDataPool = new ThreadPoolExecutor(4,
                16,
                15,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(1024),
                new NamedThreadFactory("receiveMsgPool"));
    }

    public void traceIdThreadPool(Runnable task) {
        try {
            syncBigDataPool.submit(task);
        } catch (RejectedExecutionException e) {
            log.error("", e);

        }
    }

    public static ThreadPools getInstance() {
        return ThreadPoolManagerHolder.instance;
    }

    public void shutdown() {
        syncBigDataPool.shutdown();
    }

    private static class ThreadPoolManagerHolder {
        public static ThreadPools instance = new ThreadPools();
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public NamedThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-" + name + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
