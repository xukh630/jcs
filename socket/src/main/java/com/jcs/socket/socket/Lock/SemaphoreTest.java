package com.jcs.socket.socket.Lock;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

//        Semaphore semaphore = new Semaphore(20,false);
//
//        System.out.println(semaphore.tryAcquire(21));
//        semaphore.acquire(1);
//        semaphore.tryAcquire();
//
//
//        Semaphore semaphore1 = new Semaphore(10);
//        semaphore1.tryAcquire();

        String s = "aaabacas";

        System.out.println(lengthOfLongestSubstringSet(s));
        System.out.println(lengthOfLongestSubstringHashMap(s));
    }

    public static int lengthOfLongestSubstringSet(String s) {
        int length = s.length();
        int ans = 0, i = 0, j = 0;
        HashSet set = new HashSet();
        while (i < length && j < length) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static int lengthOfLongestSubstringHashMap(String s) {
        int length = s.length();
        int ans = 0, i = 0, j = 0;
        HashMap<Object, Object> map = Maps.newHashMap();

        while (i < length && j < length) {
            if (map.containsKey(s.charAt(j))) {
                map.put(s.charAt(j), j++);
                ans = Math.max(ans, j - i);
            } else {
                map.put(s.charAt(j++),i++);
            }
        }
        return ans;
    }

}
