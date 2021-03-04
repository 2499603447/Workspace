/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import com.chasedream.utils.Out;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/1 19:26
 */
public class ShortestCompletingWord {
    public static void main(String[] args) {
        ShortestCompletingWord sce = new ShortestCompletingWord();
        Out.println(sce.shortestCompletingWord("licensePlate = \"1s3 PSt\"",
                new String[]{"step", "steps", "stripe", "stepple"}));
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int start = licensePlate.indexOf('"');
        licensePlate = licensePlate.substring(start + 1, licensePlate.length() - 1).toLowerCase();
        /*String lpCh = licensePlate.replaceAll("[^a-z^A-Z]", "").toLowerCase();*/
        char[] lpCh = new char[licensePlate.length()];
        int index = 0;
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                lpCh[index++] = c;
            }
        }
        lpCh = Arrays.copyOfRange(lpCh, 0, index);
        int resIndex = 0;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (contain(words[i].toLowerCase(), lpCh) && words[i].length() < len) {
                len = words[i].length();
                resIndex = i;
            }
        }

        return words[resIndex];
    }

    private boolean contain(String str, char[] lpCh) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (char ch : lpCh) {
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);
        }
        return true;
    }

    public String shortestCompletingWord1(String licensePlate, String[] words) {
        int[] license = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                license[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                license[c - 'A']++;
            }
        }
        String res = null;
        for (String word : words) {
            if (isContains(license, word)) {
                if (res == null || word.length() < res.length()) {
                    res = word;
                }
            }
        }

        return res;
    }

    private boolean isContains(int[] license, String word) {
        int[] ans = new int[26];
        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                ans[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                ans[c - 'A']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (ans[i] < license[i]) {
                return false;
            }
        }
        return true;
    }
}
