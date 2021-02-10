/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description leetcode：876. 链表的中间结点
 * @date 2020/2/9 15:52
 */
public class MiddleNode {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：快慢指针
     * 快指针每次向后移动两个位置
     * 慢指针每次向后移动一个位置
     * 当快指针访问到最后一个节点时，此时慢指针正好到达中间节点
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :34.4 MB, 在所有 Java 提交中击败了5.17%的用户
     *
     * @param head 链表头结点
     * @return 返回中间节点，若有两个，则返回第二个节点
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
