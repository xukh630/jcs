package com.jcs.socket.socket.threadPool;

public class ThreadWait {

    private static Object object = new Object();
    private static Object object1 = new Object();

    public static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+"获取资源   线程状态"+thread.getState());
                    thread.sleep(1000);
                    object.wait();
                    System.out.println("wait 完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                    thread.sleep(1000);
                    object.notifyAll();
                    System.out.println("notifyall 完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Thread3 extends Thread{
        @Override
        public void run() {
            synchronized (object1) {
                try {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+"获取资源   线程状态"+thread.getState());
                    thread.sleep(1000);
                    object1.wait();
                    System.out.println("wait 完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Thread4 implements Runnable{

        @Override
        public void run() {
            synchronized (object1) {
                try {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName()+"获取资源   线程状态"+thread.getState());
                    thread.sleep(1000);
                    System.out.println("notify 完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.setName("线程1");
        Thread thread2 = new Thread(new Thread2());
        thread2.setName("线程2");
//        Thread3 thread3 = new Thread3();
//        thread3.setName("线程3");
//        Thread thread4 = new Thread(new Thread4());
//        thread4.setName("线程4");

        System.out.println(thread1.getName()+thread1.getState()+"    "+thread2.getName()+thread2.getState());
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);


        thread2.join();
        System.out.println(thread1.getName()+thread1.getState()+"    "+thread2.getName()+thread2.getState());
        Thread.sleep(1000);
        System.out.println(thread1.getName()+thread1.getState()+"    "+thread2.getName()+thread2.getState());

        //thread1.stop();
        System.out.println(thread1.getName()+thread1.getState()+"    "+thread2.getName()+thread2.getState());
        Thread.sleep(1000);
        //thread2.interrupt();
        System.out.println(thread1.getName()+thread1.getState()+"    "+thread2.getName()+thread2.getState());

    }
}
