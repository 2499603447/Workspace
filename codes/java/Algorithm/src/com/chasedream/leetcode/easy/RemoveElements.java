/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description leetcode：203. 移除链表元素
 * @date 2020/2/9 1:14
 */
public class RemoveElements {
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
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        re.removeElements2(head, 6);
    }

    /**
     * 执行用时 : 1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :39.6 MB, 在所有 Java 提交中击败了41.00%的用户
     *
     * @param head 链表头结点
     * @param val  待删除的值
     * @return 删除后的链表头节点
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode curNode = head;
        ListNode resHead = new ListNode(-1);
        ListNode preNode = resHead;
        while (curNode != null) {
            if (curNode.val == val) {
                preNode.next = curNode.next;
            } else {
                preNode.next = curNode;
                preNode = curNode;
            }
            curNode = curNode.next;
        }

        return resHead.next;
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :39.8 MB, 在所有 Java 提交中击败了30.23%的用户
     *
     * @param head 链表头结点
     * @param val  待删除的值
     * @return 删除后的链表头节点
     */
    public ListNode removeElements2(ListNode head, int val) {
        // 找到第一个值不为val的节点
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode curNode = head;
        ListNode preNode = curNode;
        while (curNode != null) {
            if (curNode.val == val) {
                preNode.next = curNode.next;
            } else {
                preNode = curNode;
            }
            curNode = curNode.next;
        }

        return head;
    }
}
