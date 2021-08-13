package com.jcs.socket.socket.leetcode;

import java.util.*;

public class Lrutest<K, V> {

    private Map<K, Node<K, V>> hashMap;

    private LinkedList<Node<K, V>> linkedList;

    private int defaultSize;

    private int size;

    public Lrutest() {
        this(4);
    }

    public Lrutest(int defaultSize) {

        if (defaultSize < 0) {
            this.size = 4;
        } else {
            this.size = defaultSize;
        }
        this.defaultSize = defaultSize;
        linkedList = new LinkedList<>();
        hashMap = new HashMap(size);
    }

    public Integer put(K key){
        Node<K, V> insertNode = hashMap.get(key);



        return null;
    }

    public Integer get(Integer key) {
        Node<K, V> insertNode = hashMap.get(key);
        if (Objects.isNull(insertNode)) {
            return null;
        }

        linkedList.remove(insertNode);
        linkedList.addFirst(insertNode);
        return null;
    }

    private static class Node<K, V> {
        private Integer key;

        private Integer value;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
