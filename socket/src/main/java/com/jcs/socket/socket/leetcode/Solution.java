package com.jcs.socket.socket.leetcode;

import com.alibaba.fastjson.JSON;

public class Solution {

    public static int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)){
            return -1;
        }
        return haystack.indexOf(needle);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] ints = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (num + nums[j] == target) {

                    ints[0] = i;
                    ints[1] = j;
                }
            }
        }
        return ints;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add  = 0;

        ListNode listNode = new ListNode(0);
        ListNode result = listNode;

        while (null != l1 || null != l2 || 0 != add){
            int addresult = add + (null == l1 ? 0 : l1.val) + (null == l2 ? 0:l2.val);
            add = addresult / 10;

            ListNode node = new ListNode(addresult%10);
            result.next = node;
            result = node;
            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
        }

        return listNode.next;
    }

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4,listNode3);
        ListNode listNode2 = new ListNode(2,listNode4);


        ListNode listNode24 = new ListNode(4);
        ListNode listNode26 = new ListNode(6,listNode24);
        ListNode listNode25 = new ListNode(5,listNode26);


        System.out.println(JSON.toJSONString(addTwoNumbers(listNode2, listNode25)));
    }
}
