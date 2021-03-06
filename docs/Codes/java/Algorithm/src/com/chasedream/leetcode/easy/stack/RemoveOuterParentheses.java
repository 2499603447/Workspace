/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import java.util.Stack;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/14 22:42
 */
public class RemoveOuterParentheses {

    /**
     * 思路：
     * 使用Stack
     * <p>
     * 执行用时 :11 ms, 在所有 Java 提交中击败了31.28%的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了5.04%的用户
     *
     * @param S
     * @return
     */
    public String removeOuterParentheses(String S) {
        char[] chs = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        int startIndex = 0; // used to record each primitive's head index.
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            // if stack is empty, it means that we need to start record current primitive's head index.
            if (stack.isEmpty()) {
                startIndex = i;
            }
            switch (chs[i]) {
                case '(':
                    stack.push(chs[i]); // if it comes up with '(' character , push it into stack directly.
                    break;
                case ')':
                    // if last character is '(', pop last element from stack.
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                    // check stack if it is empty.
                    if (stack.isEmpty()) {
                        // if stack is empty, it means that we reached each primitive's tail.
                        builder.append(S, startIndex + 1, i); // append current primitive to result string.
                    }
                    break;
                default:
            }
        }

        return builder.toString();
    }

    /**
     * 思路：用数组代替stack
     * 执行用时 :4 ms, 在所有 Java 提交中击败了69.53%的用户
     * 内存消耗 :42.1 MB, 在所有 Java 提交中击败了5.04%的用户
     *
     * @param S
     * @return
     */
    public String removeOuterParentheses1(String S) {
        char[] chs = S.toCharArray();
        int startIndex = 0; // used to record each primitive's head index.
        char[] arr = new char[S.length()];
        int currentPrimitiveLen = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if (currentPrimitiveLen == 0) {
                startIndex = i;
            }
            switch (chs[i]) {
                case '(':
                    arr[currentPrimitiveLen++] = '(';// if it comes up with '(' character , push it into stack directly.
                    break;
                case ')':
                    // if last character is '(', pop last element from stack.
                    if (arr[currentPrimitiveLen - 1] == '(') {
                        currentPrimitiveLen--;
                    }
                    // check stack if it is empty.
                    if (currentPrimitiveLen == 0) {
                        // if stack is empty, it means that we reached each primitive's tail.
                        builder.append(S, startIndex + 1, i); // append current primitive to result string.
                    }
                    break;
                default:
            }
        }

        return builder.toString();
    }

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了77.91%的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了5.04%的用户
     *
     * @param S
     * @return
     */
    public String removeOuterParentheses2(String S) {
        char[] chs = S.toCharArray();
        int currentPrimitiveLen = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            switch (chs[i]) {
                case '(':
                    if (currentPrimitiveLen > 0) {
                        builder.append(chs[i]);
                    }
                    currentPrimitiveLen++;
                    break;
                case ')':
                    if (currentPrimitiveLen > 1) {
                        builder.append(chs[i]);
                    }
                    currentPrimitiveLen--;
                    break;
                default:
            }
        }

        return builder.toString();
    }

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了77.91%的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了5.04%的用户
     *
     * @param S
     * @return
     */
    public String removeOuterParentheses3(String S) {
        char[] chs = S.toCharArray();
        int currentPrimitiveLen = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ')') {
                currentPrimitiveLen--;
            }
            if (currentPrimitiveLen >= 1) {
                builder.append(chs[i]);
            }
            if (chs[i] == '(') {
                currentPrimitiveLen++;
            }
        }

        return builder.toString();
    }
}
