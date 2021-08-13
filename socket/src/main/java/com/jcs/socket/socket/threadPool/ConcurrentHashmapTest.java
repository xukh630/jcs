package com.jcs.socket.socket.threadPool;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapTest {

    static volatile ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    static volatile HashMap<String, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
        System.out.println(map.get("a"));
    }

    public static synchronized void test(){
        Integer integer = map.get("a");
        if (integer == null){
            map.put("a",1);
        } else {
            map.put("a",integer + 1);
        }
    }
}
