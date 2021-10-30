package com.jcs.socket.socket.threadPool;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Objects;
import java.util.concurrent.*;

public class ThreadPoolUtils {

    private static volatile ThreadPoolExecutor executor = null;

    /**
     * IO密集型   CPU密集型
     * @return
     */
    public static ThreadPoolExecutor getinstance(){
        if (Objects.isNull(executor)){
            synchronized (ThreadPoolUtils.class){
                if (Objects.isNull(executor)){
                    executor = new ThreadPoolExecutor(1,
                            16,
                            60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(20),
                            new MyThreadFactory(),
                            new MyRejectedExecutionHandler());
                }
            }
        }
        return executor;
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static ThreadLocal tl = new InheritableThreadLocal();

    public static void main(String[] args) {



        System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));

        executorService.execute(()->{
            System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
        });

        tl.set(1);

        executorService.execute(()->{
            System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
        });

        System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
    }
}
