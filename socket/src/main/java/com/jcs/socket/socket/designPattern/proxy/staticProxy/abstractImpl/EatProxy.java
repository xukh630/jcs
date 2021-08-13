package com.jcs.socket.socket.designPattern.proxy.staticProxy.abstractImpl;

public class EatProxy extends Food {

    private ChineseFood chineseFood;

    public EatProxy() {
        this.chineseFood = new ChineseFood();
    }

    @Override
    public void eat() {
        System.out.println("吃饭前准备");
        chineseFood = new ChineseFood();
        chineseFood.eat();
        System.out.println("吃饭后洗碗");
    }
}
