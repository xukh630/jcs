package com.jcs.socket.socket.threadPool;


import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
                    executor = new ThreadPoolExecutor(4,
                            16,
                            60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(1024));
                }
            }
        }
        return executor;
    }
}
