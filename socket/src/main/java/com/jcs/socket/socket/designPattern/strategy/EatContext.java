package com.jcs.socket.socket.designPattern.strategy;

public class EatContext {

    private StrategyMethod strategyMethod;

    public EatContext(StrategyMethod strategyMethod){
        this.strategyMethod = strategyMethod;
    }

    public void eat(){
        strategyMethod.eat();
    }
}
