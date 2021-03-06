/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhang Dezhou
 * @Description leetcode:面试题 02.01. 移除重复节点
 * @date 20-2-16 下午10:06
 */
public class RemoveDuplicateNodes {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 执行用时 :8 ms, 在所有 Java 提交中击败了89.47%的用户
     * 内存消耗 :49.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> existValueSet = new HashSet<>();
        ListNode p = head;
        ListNode preNode = null;
        while (p != null) {
            if (existValueSet.contains(p.val)) {
                preNode.next = p.next;
            } else {
                existValueSet.add(p.val);
                preNode = p;
            }
            p = p.next;
        }
        return head;
    }

    /**
     * 执行用时 :371 ms, 在所有 Java 提交中击败了5.26%的用户
     * 内存消耗 :48 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes1(ListNode head) {
        ListNode p = head;
        ListNode q;
        while (p != null && p.next != null) {
            q = p;
            while (q != null && q.next != null) {
                if (q.next.val == p.val) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
        return head;
    }
}

