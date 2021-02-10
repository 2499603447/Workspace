/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description leetcode：234. 回文链表
 * @date 2020/2/9 15:00
 */
public class IsPalindrome {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        IsPalindrome ip = new IsPalindrome();
        ListNode head = new ListNode(0);
        head.next = new ListNode(0);
        ip.isPalindrome(head);
    }

    /**
     * 思路：先遍历原链表，以此创建一个新的逆序链表
     * 将原链表和新逆序链表进行比较，一旦遇到不相同的值，则返回false
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了47.91%的用户
     * 内存消耗 :41.6 MB, 在所有 Java 提交中击败了34.05%的用户
     *
     * @param head 链表头节点
     * @return true if the list is palindrome list.
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode p = head;
        ListNode preNode = null;
        ListNode curNode = null;
        while (p != null) {
            curNode = new ListNode(p.val);
            curNode.next = preNode;
            preNode = curNode;
            p = p.next;
        }
        p = head;
        while (p != null) {
            if (p.val != curNode.val) {
                return false;
            }
            p = p.next;
            curNode = curNode.next;
        }

        return true;
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.65%的用户
     * 内存消耗 :40.6 MB, 在所有 Java 提交中击败了90.26%的用户
     *
     * @param head 链表头节点
     * @return true if the list is palindrome list.
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head, pre = null;
        //1、找到链表的中点，链表长度奇偶不影响
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2、将slow之后链表进行断开且反转，最后翻转完成之后pre指向反转链表的头节点
        ListNode p;
        while (slow != null) {
            p = slow.next;
            slow.next = pre;
            pre = slow;
            slow = p;
        }
        //3、前后链表进行比较，注意若为奇数链表，后半部分回比前部分多1一个节点，然而我们只比较相同长度的节点值，巧妙地避开这点判断
        while (head != null && pre != null) {
            if (head.val != pre.val) return false;
            head = head.next;
            pre = pre.next;
        }
        return true;
    }
}
