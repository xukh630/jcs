package com.jcs.socket.socket.designPattern.singleton;

import java.lang.reflect.Constructor;

public class Singleton {

    private Singleton() {
    }

    private static volatile Singleton singleton = null;

    private static Singleton getInstance(){
        if (null != singleton){
            synchronized (Singleton.class){
                if (null != singleton){
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
