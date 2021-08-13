package com.jcs.socket.socket.designPattern.template;


/*
 * 抽象类的应用: 利用抽象类特性,可提供方法的具体实现,子类必须实现抽象法法.
 *             实现通用功能模板类实现,定制化功能/方法子类实现.可以实现在不改变主流程的情况下实现特定的方法
 * 模板设计模式:
 * 银行业务
 */
public class BankQueuing {

    public static void main(String[] args) {
        fun(new Customer());
        fun(new Customer() {
            @Override
            public void business() {
                System.out.println("存钱");
            }

            @Override
            public boolean giveTea() {
                return true;
            }
        });
    }

    public static void fun(TemplateBank Test) {
        Test.implementation();
    }

}

abstract class TemplateBank {
    public TemplateBank() {

    }

    private void Take() {
        System.out.println("取号......");
    }

    private void YourTurn() {
        System.out.println("叫号......");
    }

    private void GiveTeaMoney() {
        System.out.println("给茶水费");
    }

    //业务
    //不确定项
    public abstract void business();

    public final void implementation() {
        Take();
        if (giveTea()){
            GiveTeaMoney();
        }
        YourTurn();
        business();
        System.out.println("-----------------");
    }

    public boolean giveTea(){
        return false;
    }


}


class Customer extends TemplateBank {
    public Customer() {

    }

    @Override
    public void business() {
        System.out.println("取钱");
    }

}
