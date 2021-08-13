package com.jcs.socket.socket.jvm.lock;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LockTest {

    public static void main(String[] args) {

    }

    public void sss(){
        //list
        ArrayList<Object> arrayList = Lists.newArrayList();
        ListIterator<Object> iterator =
                arrayList.listIterator();

        LinkedList<Object> linkedList = Lists.newLinkedList();
        CopyOnWriteArrayList<Object> copyOnWriteArrayList = Lists.newCopyOnWriteArrayList();

        //Map
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("1",1);
        LinkedHashMap<Object, Object> linkedHashMap = Maps.newLinkedHashMap();
        TreeMap<Comparable, Object> treeMap = Maps.newTreeMap();

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1","1");
    }

}
