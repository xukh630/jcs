package com.jcs.socket.socket.designPattern.proxy.dynamicProxy.jdk;

public class Test {

    public static void main(String[] args) {
        // A商品卖家
        ASellService A = new AMerchantServiceImplA();
        // B商品卖家
        BSellService B = new BMerchantServiceImpl();

        // 购买者
        String customerName = "张三";
        // 代购公司
        DCompany dCompany = new DCompany();
        dCompany.setObjFactory(A);
        ASellService AProxyInstance = (ASellService) dCompany.getProxyInstance();

        System.out.println("业务员"+AProxyInstance.getClass()+"接待"+customerName);
        AProxyInstance.sale(customerName);

        System.out.println("--------------------------------------------------------------");

        dCompany.setObjFactory(B);
        BSellService BProxyInstance = (BSellService) dCompany.getProxyInstance();
        System.out.println("业务员"+BProxyInstance.getClass()+"接待"+customerName);
        BProxyInstance.sale(customerName);
    }
}
