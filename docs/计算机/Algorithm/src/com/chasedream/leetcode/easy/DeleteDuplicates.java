/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description leetcode 83. 删除排序链表中的重复元素
 * @date 2020/2/7 23:25
 */
public class DeleteDuplicates {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(1);
        l2.next.next = new ListNode(2);
        l2.next.next.next = new ListNode(3);
        l2.next.next.next.next = new ListNode(3);
        DeleteDuplicates dd = new DeleteDuplicates();
        ListNode res = dd.deleteDuplicates(l2);
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了98.67%的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了97.93%的用户
     * @param head 头链表节点
     * @return 删除重复元素后的链表头节点
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode p = head;
        while(p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
                continue;
            }
            p = p.next;
        }

        return head;
    }
}
