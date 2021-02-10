/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import java.util.HashSet;

/**
 * @author Zhang Dezhou
 * @Description leetcode:160. 相交链表
 * @date 2020/2/8 15:46
 */
public class GetIntersectionNode {
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
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(1);
        GetIntersectionNode gin = new GetIntersectionNode();
        gin.getIntersectionNode(nodeA, nodeB);
    }

    /**
     * 暴力
     * 执行用时 :581 ms, 在所有 Java 提交中击败了5.05%的用户
     * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了15.61%的用户
     *
     * @param headA 链表A头节点
     * @param headB 链表B头节点
     * @return 相交的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB;

        while (pA != null) {
            pB = headB;
            while (pB != null) {
                // 若存在相交节点，则返回该节点
                if (pA == pB) {
                    return pA;
                }
                pB = pB.next;
            }
            pA = pA.next;
        }

        return null;
    }

    /**
     * 哈希
     * 执行用时 :11 ms, 在所有 Java 提交中击败了12.95%的用户
     * 内存消耗 :43.1 MB, 在所有 Java 提交中击败了14.84%的用户
     *
     * @param headA 链表A头节点
     * @param headB 链表B头节点
     * @return 相交的节点
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        HashSet<ListNode> nodeSet = new HashSet<>();

        while (pA != null) {
            nodeSet.add(pA);
            pA = pA.next;
        }

        while (pB != null) {
            // 若set中存在相同的节点，则返回该节点
            if (!nodeSet.add(pB)) {
                return pB;
            }
            pB = pB.next;
        }

        return null;
    }

    /**
     * 双指针
     * 双指针最重要的就是消除两个链表的长度差，
     * 如果两个链表相交，则在相交的节点开始之后的所有节点都是公用同一个内存地址，他们的hashcode都是一样的
     * 因此需要从将两个链表的指针都移动到距离末尾相同的距离
     * <p>
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :43.2 MB, 在所有 Java 提交中击败了14.02%的用户
     *
     * @param headA 链表A头节点
     * @param headB 链表B头节点
     * @return 相交的节点
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }
}
