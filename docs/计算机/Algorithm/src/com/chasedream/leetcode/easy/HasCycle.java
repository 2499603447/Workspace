/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhang Dezhou
 * @Description leetcode:141. 环形链表
 * @date 2020/2/8 13:42
 */
public class HasCycle {
    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        HasCycle hc = new HasCycle();
        hc.hasCycle1(node);
    }

    /**
     * 哈希表
     * 执行用时 :7 ms, 在所有 Java 提交中击败了9.88%的用户
     * 内存消耗 :37.9 MB, 在所有 Java 提交中击败了21.14%的用户
     *
     * @param head 链表头节点
     * @return <tt>true</tt> if the list contains cycle.
     */
    public boolean hasCycle(ListNode head) {
        ListNode p = head;
        Set<ListNode> indexSet = new HashSet<>();
        while (p != null) {
            if (!indexSet.add(p)) {
                return true;
            }
            p = p.next;
        }

        return false;
    }

    /**
     * 快慢指针：快指针每次移动2步，慢指针每次移动1步，当快慢指针相遇时，则链表存在环
     * 执行用时 : 0ms, 在所有 Java 提交中击败了100%的用户
     * 内存消耗 : 37.4MB, 在所有 Java 提交中击败了44.69%的用户
     *
     * @param head 链表头节点
     * @return <tt>true</tt> if the list contains cycle.
     */
    public boolean hasCycle1(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p = head;
        ListNode q = head.next;
        while (p != null) {
            // p, q相遇表示存在环
            if (p == q) {
                return true;
            }
            // p向后走一步
            p = p.next;

            // q会比p先访问到最后（单链表的情况）一个节点，当q或者q.next为null时，则不存在环
            if (q == null || q.next == null) {
                return false;
            } else {
                // 当q不为null且q.next不为null，q向后走两步
                q = q.next.next;
            }
        }

        return false;

        /*// 当p，q相等时退出
        while (p != q) {
            //  q会比p先访问到最后（单链表的情况）一个节点，当q或者q.next为null时，则不存在环
            if (q == null || q.next == null) {
                return false;
            }
            p = p.next; // p向后走一步
            q = q.next.next; // q向后走两步
        }

        return true;*/
    }
}
