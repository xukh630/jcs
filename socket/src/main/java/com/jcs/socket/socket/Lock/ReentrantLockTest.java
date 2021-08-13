package com.jcs.socket.socket.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("thread1 加锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("thread1 解锁成功");

                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("thread2 加锁成功");
                }finally {
                    lock.unlock();
                    System.out.println("thread2 解锁成功");

                }
            }
        });
        thread1.setName("线程1");
        thread2.setName("线程2");

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }



}
