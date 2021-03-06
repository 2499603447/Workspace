/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Zhang Dezhou
 * @Description leetcode:394. 字符串解码
 * @date 2020/3/24 23:32
 */
public class DecodeString {
    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        String s = "3[a2[c]]";
        //String s = "2[abc]3[cd]ef";
        //String s = "100[leetcode]";
        //String s = "3[a]2[b4[FC]cb]";
        //String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";

        Out.println(ds.decodeString1(s));
    }

    /**
     * 辅助栈解法
     *
     * @param s encode string
     * @return decode string
     */
    public String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Deque<Integer> multiStack = new LinkedList<>();
        Deque<String> resStack = new LinkedList<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (ch == '[') {
                multiStack.push(multi);
                resStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder curBulder = new StringBuilder();
                int curMulti = multiStack.pop();
                while (curMulti-- > 0) {
                    curBulder.append(res);
                }

                res = new StringBuilder(resStack.pop() + curBulder);
            } else if (ch >= '0' && ch <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(ch));
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }

    /**
     * 深度遍历，递归查找最小集，向外扩展
     *
     * @param s encode string
     * @return decode string
     */
    public String decodeString(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch >= '0' && ch <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(ch));
            } else if (ch == '[') {
                String[] temp = dfs(s, index + 1);
                index = Integer.parseInt(temp[0]);
                while (multi > 0) {
                    res.append(temp[1]);
                    multi--;
                }
            } else if (ch == ']') {
                return new String[]{String.valueOf(index), res.toString()};
            } else {
                res.append(ch);
            }
            index++;
        }

        return new String[]{res.toString()};
    }

}
