package com.jcs.socket.socket.leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int defaultSize;

    public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder, int defaultSize) {
        super(initialCapacity, loadFactor, accessOrder);
        this.defaultSize = defaultSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > defaultSize;
    }


    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(16, 0.75f, true, 4);

        //LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer,Integer>(16,0.75f,true);

        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.get(1);
        cache.put(5,5);
        cache.get(4);

        Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println(next.getKey());
        }
    }

}
