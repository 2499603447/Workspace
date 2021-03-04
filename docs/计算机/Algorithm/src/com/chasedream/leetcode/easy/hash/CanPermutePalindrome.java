/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import com.chasedream.utils.Out;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/22 16:41
 */
public class CanPermutePalindrome {
    public static void main(String[] args) {
        CanPermutePalindrome cpp = new CanPermutePalindrome();
        Out.println(cpp.canPermutePalindrome("abc"));
    }

    Map<Character, Integer> map = new HashMap<>();

    public boolean canPermutePalindrome(String s) {
        int len = s.length();
        char[] chs = s.toCharArray();
        return len % 2 == 0 ? find(chs, true) == chs.length : find(chs, false) == 1;
    }

    /**
     * Solution:
     * 1.when the String length is even:
     * the number of each character must be even, such as ararbb: a->2,r->2,b->2
     * 2.when the String length is odd:
     * To meet palindrome requirements, only the character's number in the middle of the palindrome string must be odd.
     * such as araeeerbcbc: e->3
     *
     * @param chs
     * @param isEven
     * @return
     */
    private int find(char[] chs, boolean isEven) {
        int evenNumber = 0;
        int oddChNum = 0;
        for (char ch : chs) {
            int count = map.getOrDefault(ch, 0) + 1;
            map.put(ch, count);

            if (isEven) {
                evenNumber = count % 2 == 0 ? evenNumber + 2 : evenNumber;
            } else {
                oddChNum = count % 2 == 0 ? oddChNum - 1 : oddChNum + 1;
            }
        }

        return isEven ? evenNumber : oddChNum;
    }
}
