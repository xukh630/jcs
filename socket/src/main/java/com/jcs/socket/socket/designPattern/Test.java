package com.jcs.socket.socket.designPattern;

public class Test {


    public static void main(String[] args) {
        int i = 10;
        pass(i);

        System.out.println(i);
    }

    public static void pass(int j){
        j = 20;
        System.out.println(j);
    }
}
