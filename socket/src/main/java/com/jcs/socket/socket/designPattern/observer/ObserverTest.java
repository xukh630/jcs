package com.jcs.socket.socket.designPattern.observer;

public class ObserverTest {


    /**观察者模式主要解决什么问题 ?
     * 观察者模式主要解决一个对象有变动,可以通知到观察对象,做出相应的动作,保证易用,低耦合
     *
     * 四种角色
     * 主题（Subject）主题是一个接口，该接口规定了具体主题需要实现的方法，比如，添加、删除观察者以及通知观察者更新数据的方法。
     * 观察者（Observer）：观察者是一个接口，该接口规定了具体观察者用来更新数据的方法。
     * 具体主题（ConcreteSubject）：具体主题是实现主题接口类的一个实例，该实例包含有可以经常发生变化的数据。具体主题需使用一个集合，比如ArrayList，存放观察者的引用，以便数据变化时通知具体观察者。
     * 具体观察者（ConcreteObserver）：具体观察者是实现观察者接口类的一个实例。具体观察者包含有可以存放具体主题引用的主题接口变量，以便具体观察者让具体主题将自己的引用添加到具体主题的集合中，使自己成为它的观察者，或让这个具体主题将自己从具体主题的集合中删除，使自己不再是它的观察者。
     *
     * 使用场景
     * 当一个对象的数据更新时需要通知其他对象，但这个对象又不希望和被通知的那些对象形成紧耦合。
     * 当一个对象的数据更新时，这个对象需要让其他对象也各自更新自己的数据，但这个对象不知道具体有多少对象需要更新数据。
     *
     * @param args
     */
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
