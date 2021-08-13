package com.jcs.socket.socket.spring;

public class SpringMvc {

    public static void main(String[] args) {
        ThreadLocal<Object> local = new ThreadLocal<>();

        local.set("111");
        local.set("123");


        System.out.println(local.get());
    }

}
