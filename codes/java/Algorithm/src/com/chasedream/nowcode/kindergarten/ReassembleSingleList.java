/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.nowcode.kindergarten;

import java.util.Scanner;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/6/14 16:00
 */
public class ReassembleSingleList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }

        ListNode head = new ListNode(arr[0]);
        ListNode p = head;
        for (int i = 1; i < len; i++) {
            p.next = new ListNode(arr[i]);
            p = p.next;
        }

        ListNode out = reAssemble(head, len);
        while (out != null) {
            System.out.print(out.val + " ");
            out = out.next;
        }
    }

    private static ListNode reAssemble(ListNode head, int len) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }

        ListNode left = head;
        ListNode right = slow;
        ListNode res = new ListNode(0);
        ListNode p = res;
        for (int i = 0; i < len / 2; i++) {
            p.next = new ListNode(left.val);
            left = left.next;
            p = p.next;
            p.next = new ListNode(right.val);
            right = right.next;

            p = p.next;
        }

        if (right != null) {
            p.next = new ListNode(right.val);
        }

        return res.next;
    }

}
