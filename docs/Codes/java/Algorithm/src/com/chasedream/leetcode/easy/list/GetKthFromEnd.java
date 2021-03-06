/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.list;

/**
 * @author Zhang Dezhou
 * @Description leetcode:面试题22. 链表中倒数第k个节点
 * @date 20-2-16 下午10:49
 */
public class GetKthFromEnd {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        GetKthFromEnd gkfe = new GetKthFromEnd();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        gkfe.getKthFromEnd(head, 2);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
