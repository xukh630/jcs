package com.jcs.socket.socket.designPattern.strategy;

public class Test {


    /**
     * 策略模式解决了什么问题?
     * 解决了把使用算法的决策和算法本身分隔开来,委派给不同的对象处理.解决了多重if判断问题.
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        EatContext context1 = new EatContext(new ChineseFood());
        EatContext context2 = new EatContext(new WestFood());
        context1.eat();
        context2.eat();
    }
}
