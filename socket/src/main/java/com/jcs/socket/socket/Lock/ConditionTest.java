package com.jcs.socket.socket.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private final static Lock lock = new ReentrantLock(true);

    private final  static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile  boolean noUse = true;

    public static void main(String[] args) throws InterruptedException {


        new Thread(()->{
            while (true){
                buildData();
            }
        }).start();
        new Thread(()->{
            while (true){
                useData();
            }
        }).start();

    }



    /**
     * 生产数据
     */
    private static void buildData(){
        try {
            lock.lock();    //synchronized key word  #moitor enter
            System.out.println("buildData加锁成功");
            while (noUse){
                condition.await();  // monitor.wait()
            }
            data++;
            System.out.println("P:" + data);
            Thread.sleep(1000);
            noUse = true;
            condition.signal();  // monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // synchronized  end  #moitor end
            System.out.println("buildData释放锁成功");

        }
    }


    /**
     * 消费数据
     */
    private static void useData(){
        try {
            lock.lock();    //synchronized key word  #moitor enter
            System.out.println("useData加锁成功");
            while (!noUse){
                condition.await();  // monitor.wait()
            }
            System.out.println("C:" + data);
            Thread.sleep(1000);
            noUse = false;
            condition.signal();  // monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // synchronized  end  #moitor end
            System.out.println("useData释放锁成功");

        }
    }


}

