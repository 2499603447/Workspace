/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/9 16:03
 */
public class GetDecimalValue {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        RemoveElements re = new RemoveElements();
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        GetDecimalValue gtn = new GetDecimalValue();
        gtn.getDecimalValue(head);
    }

    /**
     * 思路：
     * 每次将当前res*2后+当前节点val
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :34.3 MB, 在所有 Java 提交中击败了49.52%的用户
     *
     * @param head 链表头节点
     * @return 二进制转十进制的值.
     */
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }

        return res;
    }
}
