package com.jcs.socket.socket.designPattern.proxy.staticProxy.interfaceImpl;

public class Test {

    public static void main(String[] args) {

        ChineseFood food = new ChineseFood();
        EatProxy eatProxy = new EatProxy(food);
        eatProxy.eatFood();
    }
}
