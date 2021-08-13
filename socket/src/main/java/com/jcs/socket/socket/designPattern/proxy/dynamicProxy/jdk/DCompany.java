package com.jcs.socket.socket.designPattern.proxy.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DCompany implements InvocationHandler {

    private Object objFactory;

    public Object getObjFactory(){
        return objFactory;
    }

    public void setObjFactory(Object objFactory){
        this.objFactory = objFactory;
    }

    // 使用JDK代理类获取代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(objFactory.getClass().getClassLoader(), objFactory.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 进行业务增强
        System.out.println("JDK动态代理对业务进行了增强处理");
        // 通过反射调用方法本身
        Object invoke = method.invoke(objFactory, args);
        System.out.println("JDK动态代理对业务进行了增强处理结束");
        return invoke;
    }
}
