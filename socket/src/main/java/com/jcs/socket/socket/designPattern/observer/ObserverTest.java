package com.jcs.socket.socket.designPattern.observer;

public class ObserverTest {

    public static void main(String[] args) {
        // 创建一个观察目标
        JavaStackObservable javaStackObservable = new JavaStackObservable();

        // 添加观察者
        javaStackObservable.addObserver(new ReaderObserver("小明"));
        javaStackObservable.addObserver(new ReaderObserver("小张"));
        javaStackObservable.addObserver(new ReaderObserver("小爱"));

        // 发表文章
        javaStackObservable.publish("什么是观察者模式？");
    }

}
