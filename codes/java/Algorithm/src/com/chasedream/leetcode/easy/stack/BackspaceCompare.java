/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode: 844. 比较含退格的字符串
 * @date 2020/2/13 22:40
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        BackspaceCompare bc = new BackspaceCompare();
        String s = "y#fo##f";
        String t = "y#f#o##f";
        bc.backspaceCompare(s, t);
    }

    /**
     * 执行用时 :1 ms, 在所有 Java 提交中击败了98.36%的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了5.34%的用户
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        char[] s = new char[S.length()];
        char[] t = new char[T.length()];
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        int lenS = removeSpecifyCharacter(charS, s);
        int lenT = removeSpecifyCharacter(charT, t);

        return lenS == lenT &&
                Arrays.equals(Arrays.copyOf(s, lenS), Arrays.copyOf(t, lenT));

    }

    private int removeSpecifyCharacter(char[] in, char[] out) {
        int len = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == '#') {
                if (len > 0) {
                    len--;
                }
                continue;
            }
            out[len++] = in[i];
        }
        return len;
    }

    /**
     * 栈实现
     * 执行用时 :2 ms, 在所有 Java 提交中击败了79.16%的用户
     * 内存消耗 :40.8 MB, 在所有 Java 提交中击败了5.34%的用户
     *
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {
        char[] charS = S.toCharArray();
        char[] charT = T.toCharArray();
        Stack<Character> stackS = new Stack<>();
        removeSpecifyCharacter1(charS, stackS);
        Stack<Character> stackT = new Stack<>();
        removeSpecifyCharacter1(charT, stackT);

        return stackS.size() == stackT.size() && isStackEqual(stackS, stackT);
    }

    private void removeSpecifyCharacter1(char[] in, Stack<Character> out) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == '#') {
                if (!out.isEmpty()) {
                    out.pop();
                }
                continue;
            }
            out.push(in[i]);
        }
    }

    private boolean isStackEqual(Stack<Character> s1, Stack<Character> s2) {
        while (!s1.isEmpty()) {
            if (s1.pop() != s2.pop()) {
                return false;
            }
        }

        return true;
    }
}
