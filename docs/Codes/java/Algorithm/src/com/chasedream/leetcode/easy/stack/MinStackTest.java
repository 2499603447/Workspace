/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import com.chasedream.utils.Out;

import java.util.Arrays;

/**
 * @author Zhang Dezhou
 * @Description leetcode:155. 最小栈
 * @date 2020/2/10 21:49
 */

public class MinStackTest {
    public static void main(String[] args) {
        MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int val = minStack.getMin();   // 返回 -3.
        minStack.pop();
        val = minStack.top();      // 返回 0.
        val = minStack.getMin();   // 返回 -2.
        Out.println("");
    }
}

/**
 * 执行用时 :7 ms, 在所有 Java 提交中击败了86.39%的用户
 * 内存消耗 :47.8 MB, 在所有 Java 提交中击败了8.78%的用户
 */
class MinStack {
    private static final int DEFAULT_SIZE = 1024; // 初始值的设定决定了扩容的次数，从而影响算法的最终执行时间
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int[] mData;
    private int[] mMinVal;
    private int size;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        mData = new int[DEFAULT_SIZE];
        mMinVal = new int[DEFAULT_SIZE];
    }

    public void push(int x) {
        if (isFull()) {
            ensureCapacity();
        }

        if (size == 0) {
            mMinVal[size] = x;
        } else {
            mMinVal[size] = Math.min(x, mMinVal[size - 1]);
        }

        mData[size++] = x;
    }

    public void pop() {
        size--;
    }

    public int top() {
        return mData[size - 1];
    }

    public int getMin() {
        return mMinVal[size - 1];
    }

    private boolean isFull() {
        return size == mData.length;
    }

    private void ensureCapacity() {
        int oldCapacity = mData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        mData = Arrays.copyOf(mData, newCapacity);
        mMinVal = Arrays.copyOf(mMinVal, newCapacity);
    }
}

/**
 * 执行用时 :7 ms, 在所有 Java 提交中击败了86.39%的用户
 * 内存消耗 :53 MB, 在所有 Java 提交中击败了6.30%的用户
 */
class MinStack1 {
    private Node head;

    /**
     * initialize your data structure here.
     */
    public MinStack1() {

    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;

        int min;

        Node next;

        private Node(int val, int min) {
            this.val = val;
            this.min = min;
            this.next = null;
        }

        private Node(int val, int min, Node node) {
            this.val = val;
            this.min = min;
            this.next = node;
        }
    }
}

