/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou
 * @Description leetcode 21 合并两个有序链表
 * @date 2020/2/7 23:25
 */
public class MergeTwoLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);
        ListNode result = mergeTwoLists1(l1, l2);
        Out.print(result.val);
        ListNode p = result;
        while (p.next != null) {
            Out.print("->" + p.next.val);
            p = p.next;
        }
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了87.47%的用户
     * 内存消耗 :38.7 MB, 在所有 Java 提交中击败了38.36%的用户
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode res = new ListNode(0);
        ListNode tempRes = res;
        while (p1 != null || p2 != null) {
            // p1 跑到 l1的末尾
            if (p1 == null) {
                tempRes.val = p2.val;
                if (p2.next != null) {
                    tempRes.next = new ListNode(0);
                    tempRes = tempRes.next;
                }

                p2 = p2.next;
                continue;
            }

            // p2 跑到 l2的末尾
            if (p2 == null) {
                tempRes.val = p1.val;
                if (p1.next != null) {
                    tempRes.next = new ListNode(0);
                    tempRes = tempRes.next;
                }

                p1 = p1.next;
                continue;
            }

            // p1, p2 都在 l1, l2 中间
            if (p1.val < p2.val) {
                tempRes.val = p1.val;
                p1 = p1.next;
            } else {
                tempRes.val = p2.val;
                p2 = p2.next;
            }
            tempRes.next = new ListNode(0);
            tempRes = tempRes.next;
        }

        return res;
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了87.47%的用户
     * 内存消耗 :40.9 MB, 在所有 Java 提交中击败了5.01%的用户
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode tempNode;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                if (p1.next != null && p1.next.val <= p2.val) {
                    p1 = p1.next;
                    continue;
                }
                tempNode = p1.next;
                p1.next = p2;
                p1 = tempNode;
            } else {
                if (p2.next != null && p2.next.val < p1.val) {
                    p2 = p2.next;
                    continue;
                }
                tempNode = p2.next;
                p2.next = p1;
                p2 = tempNode;
            }
        }

        return l1.val <= l2.val ? l1 : l2;
    }

    /**
     * 递归解法
     * 执行用时 :1 ms, 在所有 Java 提交中击败了87.47%的用户
     * 内存消耗 :41 MB, 在所有 Java 提交中击败了5.01%的用户
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
