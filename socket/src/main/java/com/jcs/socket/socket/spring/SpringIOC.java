package com.jcs.socket.socket.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIOC {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        context.getBean("");


    }
}
