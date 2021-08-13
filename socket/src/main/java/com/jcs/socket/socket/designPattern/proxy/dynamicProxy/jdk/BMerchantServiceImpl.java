package com.jcs.socket.socket.designPattern.proxy.dynamicProxy.jdk;

public class BMerchantServiceImpl implements BSellService {
    @Override
    public void sale(String name) {
        System.out.println(name + "购买了B商品");
    }
}
