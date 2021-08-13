package com.jcs.socket.socket.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class LRU<K, V> {

   private Map<K,Node<K,V>> map;

   private LinkedList<Node<K,V>> linkedList;

   private int size;

   private int defaultSize = 16;

   public LRU(){
        this(16);
   }

   public LRU(int size){
       if (size <= 0){
           this.size = 16;
       }else {
           this.size = size;
       }

       this.map = new HashMap<>(this.size);
       this.linkedList = new LinkedList<>();
   }

   public synchronized void put(K key,V value){
       Node<K, V> insertNode = new Node<>(key, value);

       Node<K, V> node = map.get(key);

       if (Objects.nonNull(node)){
           linkedList.remove(node);
           linkedList.addFirst(insertNode);
           map.put(key,insertNode);
           return;
       }

       if (this.size == map.size()){
           linkedList.removeLast();
           map.remove(key);
           linkedList.addFirst(insertNode);
           map.put(key,insertNode);
           return;
       }

       map.put(key,insertNode);
       linkedList.addFirst(insertNode);
   }

   public synchronized V get(K key){
       Node<K, V> node = map.get(key);
       if (Objects.isNull(node)){
           return null;
       }

       linkedList.remove(node);
       linkedList.addFirst(node);
       return node.value;
   }

   public void delete(K key){
       Node<K, V> node = map.get(key);
       if (Objects.isNull(node)){
           linkedList.remove(node);
       }
   }







    private static class Node<K,V>{
        /**
         * 键
         */
        K key;

        /**
         * 值
         */
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" +
                    "key=" + key +
                    ", value=" + value +
                    ')';
        }
    }

}
