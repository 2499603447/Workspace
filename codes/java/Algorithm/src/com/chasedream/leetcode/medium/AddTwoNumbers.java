/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import java.util.ArrayList;

/**
 * @author Zhang Dezhou
 * @Description Leetcode 2.两数相加
 * @date 2020/2/3 22:48
 */
public class AddTwoNumbers {
    // Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        /*ListNode a = new ListNode(2);
        a.next = new ListNode(4);
        a.next.next = new ListNode(3);
        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(4);*/
        ListNode a = new ListNode(1);
        a.next = new ListNode(9);
        ListNode b = new ListNode(9);
        b.next = new ListNode(9);

        ListNode res = atn.addTwoNumbers2(a, b);
    }

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了41.18%的用户
     * 内存消耗 :44.4 MB, 在所有 Java 提交中击败了48.44%的用户
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 求和后的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode p = l1;
        ListNode q = l2;
        int jump = 0;
        int temp = 0;

        // 首先求和到相同位数
        while (p != null) {
            if (q == null) {
                break;
            }
            temp = p.val + q.val + jump;
            list.add(temp % 10);
            jump = temp / 10;
            p = p.next;
            q = q.next;
        }

        // 计算剩余的位数
        while (p != null) {
            temp = p.val + jump;
            list.add(temp % 10);
            jump = temp / 10;
            p = p.next;
        }

        while (q != null) {
            temp = q.val + jump;
            list.add(temp % 10);
            jump = temp / 10;
            q = q.next;
        }

        // 是否有余数
        if (jump > 0) {
            list.add(jump);
        }

        // 结果存入链表
        ListNode res = new ListNode(0);
        ListNode tempRes = res;
        for (int i = 0; i < list.size(); i++) {
            tempRes.val = list.get(i);
            if (i == list.size() - 1) {
                break;
            }
            tempRes.next = new ListNode(0);
            tempRes = tempRes.next;
        }
        return res;
    }

    /**
     * 执行用时 :2 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗 :43.7 MB, 在所有 Java 提交中击败了74.47%的用户
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 求和后的链表
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode tempRes = res;

        ListNode p = l1;
        ListNode q = l2;
        int jump = 0;
        int temp = 0;

        // 首先求和到相同位数
        while (p != null) {
            if (q == null) {
                break;
            }
            temp = p.val + q.val + jump;
            tempRes.val = temp % 10;
            jump = temp / 10;
            if (p.next != null || q.next != null || jump > 0) {
                tempRes.next = new ListNode(0);
            }
            p = p.next;
            q = q.next;
            tempRes = tempRes.next;
        }

        // 计算剩余的位数
        while (p != null) {
            temp = p.val + jump;
            tempRes.val = temp % 10;
            jump = temp / 10;
            if (p.next != null || jump > 0) {
                tempRes.next = new ListNode(0);
            }
            p = p.next;
            tempRes = tempRes.next;
        }

        while (q != null) {
            temp = q.val + jump;
            tempRes.val = temp % 10;
            jump = temp / 10;
            if (q.next != null || jump > 0) {
                tempRes.next = new ListNode(0);
            }
            q = q.next;
            tempRes = tempRes.next;
        }

        // 是否有余数
        if (jump > 0) {
            tempRes.val = jump;
        }

        return res;
    }
}
