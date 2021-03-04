/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        LengthOfLongestSubstring lstd = new LengthOfLongestSubstring();
        String s = "pww";
        System.out.println(lstd.lengthOfLongestSubstring(s));
    }

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 入口参数检查
        if (TextUtils.isEmpty(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length == 1) {
            return 1;
        }

        int start = 0;
        int end = 0;
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < length) {
            if (map.containsKey(chars[end])) {
                start = Math.max(map.get(chars[end]), start);
            }
            map.put(chars[end], end + 1);

            result = Math.max(end - start + 1, result);
            end++;
        }

        return result;
    }

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        // 入口参数检查
        if (TextUtils.isEmpty(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length == 1) {
            return 1;
        }

        int maxL = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = start; j < i; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    maxL = Math.max(i - start, maxL);
                    start = j + 1;
                    break;
                }
            }
        }
        return Math.max(s.length() - start, maxL);
    }

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        // 入口参数检查
        if (TextUtils.isEmpty(s)) {
            return 0;
        }

        int length = s.length(), res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < length; end++) {
            char endChar = s.charAt(end);
            if (map.containsKey(endChar)) {
                start = Math.max(map.get(endChar), start);
            }
            map.put(s.charAt(end), end + 1);
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
