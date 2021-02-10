/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description leetcode:237. 删除链表中的节点
 * @date 2020/2/9 15:43
 */
public class DeleteNode {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：将当前节点替换成下一个节点
     * 将当前节点的值替换成下一个节点的值
     * 将当前节点的next成员替换成下一个节点的next成员
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了5.05%的用户
     *
     * @param node 待删除的节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
