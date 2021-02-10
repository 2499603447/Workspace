/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Zhang Dezhou
 * @Description leetcode：206. 反转链表
 * @date 2020/2/8 22:46
 */
public class ReverseList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ReverseList rl = new ReverseList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        rl.reverseList2(head);
    }

    /**
     * 迭代实现方式
     * 思路：将当前节点的next指向前一个节点
     * 首先保存下一个节点，避免next指向后丢失后面的节点
     * 头节点的next指向null
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.4 MB, 在所有 Java 提交中击败了11.25%的用户
     *
     * @param head 链表头节点
     * @return 返回逆序后的头节点
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curNode = head;
        ListNode nextNode = curNode.next;
        curNode.next = null;
        ListNode preNode = curNode;

        while (nextNode != null) {
            curNode = nextNode;
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
        }

        return curNode;
    }

    /**
     * 递归的实现方式
     * <p>
     * 思路：将当前节点的next指向前一个节点
     * 首先保存下一个节点，避免next指向后丢失后面的节点
     * 头节点的next指向null
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了18.18%的用户
     *
     * @param head 链表头节点
     * @return 返回逆序后的头节点
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        // 头节点的处理
        ListNode preNode;
        ListNode nextNode;
        ListNode curNode = head;
        nextNode = curNode.next;
        curNode.next = null;
        preNode = curNode;

        return reverseList(curNode, preNode, nextNode);
    }

    /**
     * 递归修改节点的指向
     *
     * @param curNode  当前节点
     * @param preNode  前一个节点
     * @param nextNode 后一个节点
     * @return 返回交换节点信息后的节点
     */
    private ListNode reverseList(ListNode curNode, ListNode preNode, ListNode nextNode) {
        if (nextNode != null) {
            curNode = nextNode;
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            return reverseList(curNode, preNode, nextNode);
        }

        return curNode;
    }

    /**
     * 最暴力的方法，通过数组存储后，反向遍历数组重新创建新的链表
     * <p>
     * 执行用时 :1 ms, 在所有 Java 提交中击败了7.80%的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了16.66%的用户
     *
     * @param head 链表头结点
     * @return 逆序后的头结点
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        // 将原先的list值按序存入list中
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(new ListNode(p.val));
            p = p.next;
        }

        // 对list进行逆序
        Collections.reverse(list);
        // 创建新的list，并返回其头节点
        ListNode res = new ListNode(list.get(0).val);
        ListNode resTemp = res;
        for (int i = 1; i < list.size(); i++) {
            resTemp.next = new ListNode(list.get(i).val);
            resTemp = resTemp.next;
        }

        return res;
    }
}
