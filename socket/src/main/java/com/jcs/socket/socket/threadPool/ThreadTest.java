package com.jcs.socket.socket.threadPool;

public class ThreadTest {

    private static Object object = new Object();
    private static volatile Integer num = 0;

    public static class Thread1 extends Thread{
        @Override
        public void run() {
                try {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+"获取资源   线程状态"+thread.getState());

                    for (int i = 0; i < 100; i++) {
                        if (i % 10 == 0){
                            System.out.println(thread.getName()+"  i= "+ i);
                            thread.yield();
                        }
                        i++;
                    }

                    System.out.println("Thread1 完成");
                    System.out.println("Thread1 让出");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

    public static class Thread2 implements Runnable{

        @Override
        public void run() {
            synchronized (object) {
                try {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+"获取资源   线程状态"+thread.getState());
                    for (int i = 0; i < 100; i++) {
                        if (i % 5 == 0){
                            System.out.println(thread.getName()+"  i= "+ i);
                            thread.yield();
                        }
                        i++;
                    }
                    System.out.println("Thread2 完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i);
                if (i % 20 == 0) {
                    Thread.yield();
                }
            }
        };
        new Thread(runnable, "栈长").start();
        new Thread(runnable, "小蜜").start();
    }
}



