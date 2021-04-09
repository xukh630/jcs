package com.jcs.socket.socket.jvm;

import com.google.common.collect.Lists;

import java.util.List;

public class OOMTest {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }


    public static void main(String[] args) {
        //heapOom();

//        OOMTest test = new OOMTest();
//        test.stackLeak();

        constantPoolOverflow();
    }

    /**
     * -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/xukh/IdeaProjects/jcs/socket/src/main/resources/dump
     * *.hprof  内存镜像文件
     */
    public static void heapOom() {
        List<Object> list = Lists.newArrayList();
        while (true) {
            list.add(new OOMTest());
        }
    }

    /**
     * -Xss128k
     */
    public static void stackOom() {
        List<Object> list = Lists.newArrayList();
        while (true) {
            list.add(new OOMTest());
        }
    }

    /**
     * 虚拟机参数-XX:PermSize=10M -XX:MaxPermSize=10M
     */
    public static void constantPoolOverflow() {
        List<String> list = Lists.newArrayList();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
