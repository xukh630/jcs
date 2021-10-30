package com.jcs.socket.socket.threadPool;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        return new Thread();
    }
}
