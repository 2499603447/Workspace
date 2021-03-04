/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Dezhou
 * @Description todo: 回溯法解决
 * @date 2020/3/16 23:49
 */
public class ReadBinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num)
                    times.add(String.format("%d:%02d", h, m));
            }
        }
        return times;
    }

    public static void main(String[] args) {
        ReadBinaryWatch rw = new ReadBinaryWatch();
        rw.letterCasePermutation("a1b2");
    }

    List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        backTrace(new StringBuilder(S.toLowerCase()), 0);

        return res;
    }

    private void backTrace(StringBuilder builder, int index) {
        if (index >= builder.length()) {
            res.add(builder.toString());
            return;
        }
        char ch = builder.charAt(index);
        if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
            backTrace(builder, index + 1);
            builder.setCharAt(index, (char) (ch ^ 32));
            backTrace(builder, index + 1);
        } else {
            backTrace(builder, index + 1);
        }
    }
}
