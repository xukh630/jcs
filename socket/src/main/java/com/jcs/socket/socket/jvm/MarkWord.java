package com.jcs.socket.socket.jvm;

import com.jcs.socket.socket.model.User;
import org.openjdk.jol.info.ClassLayout;

public class MarkWord {

    static User u =  new User();

    static User [] ur =  new User[10];


    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)                 #MarkWord
    //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)                 #MarkWord
    //      8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)        #klass pointer    JVM会默认使用选项 +UseCompressedOops 开启指针压缩
    //     12     4    java.lang.String User.username                             (object)
    //     16     4    java.lang.String User.password                             (object)
    //     20     4   java.lang.Integer User.age                                  2
    //     24     4   java.lang.Boolean User.sex                                  true
    //     28     4                     (loss due to the next object alignment)
    //Instance size: 32 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    //
    //[Lcom.jcs.socket.socket.model.User; object internals:
    // OFFSET  SIZE                               TYPE DESCRIPTION                               VALUE
    //      0     4                                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
    //      4     4                                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //      8     4                                    (object header)                           82 c1 00 f8 (10000010 11000001 00000000 11111000) (-134168190)
    //     12     4                                    (object header)                           0a 00 00 00 (00001010 00000000 00000000 00000000) (10)
    //     16    40   com.jcs.socket.socket.model.User User;.<elements>                          N/A
    //Instance size: 56 bytes
    //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total



    //|--------------------------------------------------------------------------------------------------------------|
    //|                                              Object Header (128 bits)                                        |
    //|--------------------------------------------------------------------------------------------------------------|
    //|                        Mark Word (64 bits)                                    |      Klass Word (64 bits)    |
    //|--------------------------------------------------------------------------------------------------------------|
    //|  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  无锁
    //|----------------------------------------------------------------------|--------|------------------------------|
    //|  thread:54 |         epoch:2      | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |  偏向锁     //epoch  偏向时间戳
    //|----------------------------------------------------------------------|--------|------------------------------|
    //|                     ptr_to_lock_record:62                            | lock:2 |     OOP to metadata object   |  轻量锁     //ptr_to_lock_record  指向栈中锁记录的指针。
    //|----------------------------------------------------------------------|--------|------------------------------|
    //|                     ptr_to_heavyweight_monitor:62                    | lock:2 |     OOP to metadata object   |  重量锁     //ptr_to_heavyweight_monitor:62  指向线程Monitor的指针
    //|----------------------------------------------------------------------|--------|------------------------------|
    //|                                                                      | lock:2 |     OOP to metadata object   |    GC
    //|--------------------------------------------------------------------------------------------------------------|



    // biased_lock     lock     状态
    //      0           01      无锁
    //      1           01      偏向锁
    //      0           00      轻量级锁
    //      0           10      重量级锁
    //      0           11      GC标记


    /*********************偏向锁**********************/

    public static void pianxiangsuo() throws InterruptedException {
        Thread thread = Thread.currentThread();

        thread.sleep(5000);

        thread.getId();

        User user = new User();

        synchronized (user){
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
            System.out.println(thread.getId());
        }

        //System.gc();
    }

    /****原始****/                 //0000101   分代年龄 0000    偏向锁 0  锁状态  01   偏向线程id  00000000 00000000 000000    延迟加载导致无偏向
    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
    //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //      8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
    //     12     4    java.lang.String User.username                             (object)
    //     16     4    java.lang.String User.password                             (object)
    //     20     4   java.lang.Integer User.age                                  2
    //     24     4   java.lang.Boolean User.sex                                  true
    //     28     4                     (loss due to the next object alignment)
    //Instance size: 32 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    /**** sleep 5000****/               //0000101   分代年龄 0000    偏向锁 1  锁状态  01   偏向线程id  00000000 00000000 000000   偏向锁 但没有偏向线程id
    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
    //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //      8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
    //     12     4    java.lang.String User.username                             (object)
    //     16     4    java.lang.String User.password                             (object)
    //     20     4   java.lang.Integer User.age                                  2
    //     24     4   java.lang.Boolean User.sex                                  true
    //     28     4                     (loss due to the next object alignment)
    //Instance size: 32 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    /**** 手动触发GC ****/              //0001101 00000000 00000000 000000   分代年龄 0001    偏向锁 1  锁状态  01  偏向线程id  00000000 00000000 000000
    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           0d 00 00 00 (00001101 00000000 00000000 00000000) (13)
    //      4     4                     (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
    //      8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
    //     12     4    java.lang.String User.username                             (object)
    //     16     4    java.lang.String User.password                             (object)
    //     20     4   java.lang.Integer User.age                                  2
    //     24     4   java.lang.Boolean User.sex                                  true
    //     28     4                     (loss due to the next object alignment)
    //Instance size: 32 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    /****对象加锁****/              //0000101 01010000 00000001 10111000  分代年龄 0000    偏向锁 1  锁状态  01  偏向线程id  01010000 00000001 10111000
    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           05 50 01 b8 (00000101 01010000 00000001 10111000) (-1207873531)
    //      4     4                     (object header)                           da 7f 00 00 (11011010 01111111 00000000 00000000) (32730)
    //      8     4                     (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
    //     12     4    java.lang.String User.username                             (object)
    //     16     4    java.lang.String User.password                             (object)
    //     20     4   java.lang.Integer User.age                                  2
    //     24     4   java.lang.Boolean User.sex                                  true
    //     28     4                     (loss due to the next object alignment)
    //Instance size: 32 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total


    /*********************轻量级锁**********************/  //-XX:-UseBiasedLocking  关闭偏向锁



    public static void qingliangjisuo() throws InterruptedException {
        final User a = new User();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
                try {
                    //thread1退出同步代码块，且没有死亡
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        thread1.start();

        //让thread1执行完同步代码块中方法。
        Thread.sleep(3000);
        thread2.start();
    }

    //com.jcs.socket.socket.model.User object internals:     //     锁状态  00    轻量级锁
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           38 59 a5 05 (00111000 01011001 10100101 00000101) (94722360)
    //      4     4                     (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
    //      8     4                     (object header)                           bf c2 00 f8 (10111111 11000010 00000000 11111000) (-134167873)
    //     12     4   java.lang.Integer User.age                                  2
    //     16     4   java.lang.Boolean User.sex                                  true
    //     20     4                     (loss due to the next object alignment)
    //Instance size: 24 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total


    /*********************重量级锁**********************/       // 锁状态  10

    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           ba 72 01 56 (10111010 01110010 00000001 01010110) (1442935482)
    //      4     4                     (object header)                           f6 7f 00 00 (11110110 01111111 00000000 00000000) (32758)
    //      8     4                     (object header)                           bf c2 00 f8 (10111111 11000010 00000000 11111000) (-134167873)
    //     12     4   java.lang.Integer User.age                                  2
    //     16     4   java.lang.Boolean User.sex                                  true
    //     20     4                     (loss due to the next object alignment)
    //Instance size: 24 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    //
    //thread2 locking
    //com.jcs.socket.socket.model.User object internals:
    // OFFSET  SIZE                TYPE DESCRIPTION                               VALUE
    //      0     4                     (object header)                           ba 72 01 56 (10111010 01110010 00000001 01010110) (1442935482)
    //      4     4                     (object header)                           f6 7f 00 00 (11110110 01111111 00000000 00000000) (32758)
    //      8     4                     (object header)                           bf c2 00 f8 (10111111 11000010 00000000 11111000) (-134167873)
    //     12     4   java.lang.Integer User.age                                  2
    //     16     4   java.lang.Boolean User.sex                                  true
    //     20     4                     (loss due to the next object alignment)
    //Instance size: 24 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    public static void zhongliangjisuo() throws InterruptedException {
        final User a = new User();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
                try {
                    //thread1退出同步代码块，且没有死亡
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        //thread1退出同步代码块，且没有死亡
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        pianxiangsuo();
    }
}


