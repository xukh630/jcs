package com.jcs.socket.socket.designPattern.proxy.staticProxy.interfaceImpl;

public class EatProxy implements Food {

    private Food food;

    public EatProxy(Food food){
        this.food=food;
    }

    @Override
    public void eatFood() {

        System.out.println("吃饭前准备");
        food.eatFood();
        System.out.println("吃饭后洗碗");
    }
}
