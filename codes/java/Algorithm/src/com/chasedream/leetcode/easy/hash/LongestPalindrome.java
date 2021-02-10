/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/23 15:31
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        //String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        String s = "a";

        lp.longestPalindrome(s);
    }

    public int longestPalindrome(String s) {
        int[] bucket = new int['z' - 'A' + 1];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'A']++;
        }

        int res = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] % 2 == 0) {
                res += bucket[i];
            } else if (bucket[i] > 0 && bucket[i] % 2 == 1) {
                res += bucket[i] - 1;
            }
        }

        return res != s.length() ? res + 1 : res;
    }
}
