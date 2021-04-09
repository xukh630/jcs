package com.jcs.socket.socket.designPattern.singleton;

public class StaticSingleton  {

    private static class Singleton{
        private String id;

        private static final StaticSingleton instance = new StaticSingleton();

    }
    public StaticSingleton() {
    }

    public static final StaticSingleton getInstance(){
        return Singleton.instance;
    }

    public static void main(String[] args) {
        StaticSingleton instance = StaticSingleton.getInstance();


        StaticSingleton instance1 = StaticSingleton.getInstance();


        System.out.println(instance.toString());
        System.out.println(instance1.toString());
    }

}
