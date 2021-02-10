/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import java.util.Stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode:232. 用栈实现队列
 * @date 2020/2/11 21:39
 */
public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.peek();  // 返回 1
        queue.pop();   // 返回 1
        queue.empty(); // 返回 false
    }
}

/**
 * 思路：stack1, stack2
 * 先将stack1中的数据移到stack2，
 * 新数据放入stack1，
 * 再将stack2中的数据移到stack1中.
 * <p>
 * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 :40.9 MB, 在所有 Java 提交中击败了5.03%的用户
 */
class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of list.
     */
    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        stack2.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * Removes the element from in front of list and returns that element.
     */
    public int pop() {
        return stack1.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return stack1.peek();
    }

    /**
     * Returns whether the list is empty.
     */
    public boolean empty() {
        return stack1.isEmpty();
    }
}

/**
 * 思路：
 * put：
 * 直接放stack1，
 * pop：
 * stack2不为空时，直接stack2.pop
 * stack2为空时，先将stack1全部移动到stack2后，在stack2.pop
 * <p>
 * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 :40.9 MB, 在所有 Java 提交中击败了5.03%的用户
 */
class MyQueue1 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of list.
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Removes the element from in front of list and returns that element.
     */
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /**
     * Returns whether the list is empty.
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}