package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.*;

public class RepeatedDnaSequences {
    public static void main(String[] args) {
        RepeatedDnaSequences rds = new RepeatedDnaSequences();
        String input = "AAAAAAAAAAAAA";
        List<String> out = rds.findRepeatedDnaSequences2(input);
        for (String str : out) {
            Out.println(str);
        }
    }

    /**
     * 大用例会超时
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> out = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return out;
        }

        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            String remainString = s.substring(i + 1);
            if (remainString.contains(str) && !out.contains(str)) {
                out.add(str);
            }
        }

        return out;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> out = new ArrayList<>();
        if (s.length() <= 10) {
            return out;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            Integer count = map.get(str);
            if (count == null) {
                map.put(str, 1);
            } else if (count == 1) {
                out.add(str);
                map.put(str, 2);
            }
        }

        return out;
    }

    public List<String> findRepeatedDnaSequences3(String s) {
        List<String> out = new ArrayList<>();
        if (s.length() <= 10) {
            return out;
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            if (!set.add(str) && !out.contains(str)) {
                out.add(str);
            }
        }

        return out;

        /*Set<String> set = new HashSet<>();
        if (s.length() <= 10) {
            return new ArrayList<>();
        }

        Set<String> temp = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            if (!temp.add(str)) {
                set.add(str);
            }
        }

        return new ArrayList<>(set);*/
    }

    public List<String> findRepeatedDnaSequences4(String s) {
        int len = s.length();
        if (len == 0 || len < 10) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        int key = 0;
        char[] array = s.toCharArray();
        for (int i = 0; i < 10; i++) {
            key <<= 3;
            key |= (array[i] & 7);
        }
        set.add(key);
        for (int i = 10; i < len; i++) {
            key <<= 3;
            key |= (array[i] & 7);
            key &= 0x3fffffff;
            if (set.contains(key)) {
                res.add(s.substring(i - 9, i + 1));
            } else {
                set.add(key);
            }

        }
        return new ArrayList<>(res);
    }

    public List<String> findRepeatedDnaSequences6(String s) {
        List<String> list = new ArrayList<String>();
        int[] num = new int[1 << 20];
        int k = (1 << 18) - 1, key = 0;
        for (int i = 0; i < s.length(); i++) {
            key <<= 2;
            key += getValue(s.charAt(i));
            if (i >= 9) {
                if (num[key] == 1) {
                    num[key]++;
                    list.add(s.substring(i - 9, i + 1));
                }
                else num[key]++;
                key &= k;
            }
        }
        return list;
    }


    private int getValue(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                throw new IllegalArgumentException("Illegal character");
        }
    }
}
