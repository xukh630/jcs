package com.jcs.socket.socket.designPattern.singleton;

import java.lang.reflect.Constructor;

public class Singleton {

    private Singleton() {
    }

    private static volatile Singleton singleton = null;

    private static Singleton getInstance(){
        if (null != singleton){                     //没创建对象才去竞争锁,  减少锁竞争
            synchronized (Singleton.class){
                if (null != singleton){             //防止重复创建
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws Exception {
        Singleton singleton1 = Singleton.getInstance();
        Constructor<Singleton> declaredConstructor = Singleton.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        Singleton singleton2 = declaredConstructor.newInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);

    }
}
