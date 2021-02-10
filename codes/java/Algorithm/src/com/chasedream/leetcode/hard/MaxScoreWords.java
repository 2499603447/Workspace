package com.chasedream.leetcode.hard;

import java.util.*;

public class MaxScoreWords {
    public static int maxScoreWord(List<String> words, Map<Character, Integer> lettersMap, int[] score) {
        if (words.size() == 1) {
            Map<Character, Integer> temp = new HashMap<>(lettersMap);
            int tempMax = find(words.get(0), temp, score);
            return tempMax;
        }

        int res = 0;
        List<String> wordsMapTemp = new ArrayList<>(words);
        for (String str : words) {
            int temp = 0;
            Map<Character, Integer> lettersMapTemp = new HashMap<>(lettersMap);

            wordsMapTemp.remove(str);
            temp += find(str, lettersMapTemp, score);
            temp += maxScoreWord(wordsMapTemp, lettersMapTemp, score);
            if (temp > res) {
                res = temp;
            }
        }

        return res;
    }

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> lettersMap = new HashMap<>();
        List<String> wordsMap = new ArrayList<>();
        for (char c : letters) {
            if (lettersMap.containsKey(c)) {
                int count = lettersMap.get(c);
                lettersMap.put(c, count + 1);
            } else {
                lettersMap.put(c, 1);
            }
        }

        for (String str : words) {
            char[] chars = str.toCharArray();
            boolean isAdd = true;
            for (char ch : chars) {
                if (!lettersMap.containsKey(ch)) {
                    isAdd = false;
                }
            }
            if (isAdd) {
                wordsMap.add(str);
            }
        }

        return maxScoreWord(wordsMap, lettersMap, score);
    }

    private static int find(String word, Map<Character, Integer> map, int[] score) {
        Map<Character, Integer> temp = new HashMap<>();
        int tempMax = 0;
        char[] chars = word.toCharArray();
        boolean isCan = false;
        for (char ch : chars) {
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                isCan = false;
                break;
            }
            if (temp.containsKey(ch)) {
                int count = temp.get(ch);
                if (count + 1 > map.get(ch)) {
                    isCan = false;
                    break;
                }
                temp.put(ch, ++count);
            } else {
                temp.put(ch, 1);
            }
            isCan = true;
        }

        if (isCan) {

            for (Map.Entry entry : temp.entrySet()) {
                char ch = (char) entry.getKey();
                int count = (int) entry.getValue();
                int letterCount = map.get(ch);
                map.put(ch, letterCount - count);
                tempMax += score[ch - 'a'] * count;
            }
        }

        return tempMax;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"dog", "cat", "dad", "good"};
        char[] letters = new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score = new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        /*     String[] words = new String[]{"xxxz","ax","bx","cx"};
        char[] letters = new char[]{'z','a','b','c','x','x','x'};
        int[] score = new int[]{4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10};
   *//*String[] words = new String[]{"leetcode"};
        char[] letters = new char[]{'l','e','t','c','o','d'};
        int[] score = new int[]{0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0};
   */     /*String[] words = new String[]{"ac", "abd", "db", "ba", "dddd", "bca"};
        char[] letters = new char[]{'a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'd', 'd', 'd', 'd'};
        int[] score = new int[]{6, 4, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    */    /*String[] words = new String[]{"get","set"};
        char[] letters = new char[]{'g','s','e','t'};
        int[] score = new int[]{0,0,0,0,1,0,2,0,0,0,0,0,0,0,0,0,0,0,3,1,0,0,0,0,0,0};
      */ /* String[] words = new String[]{"baa","bba","ccb","ac"};
        char[] letters = new char[]{'a','b','b','b','b','c'};
        int[] score = new int[]{2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
*/        /*String[] words = new String[]{"baaa", "aacc", "ccbc", "da", "dbbc"};
        char[] letters = new char[]{'a', 'a', 'a', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd'};
        int[] score = new int[]{9, 4, 10, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
       */
        System.out.println(maxScoreWords(words, letters, score));
    }
}
