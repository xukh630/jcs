package com.jcs.socket.socket.designPattern.proxy.dynamicProxy.jdk;

public class AMerchantServiceImplA implements ASellService {
    @Override
    public void sale(String name) {
        System.out.println(name + "购买了A商品");
    }
}
