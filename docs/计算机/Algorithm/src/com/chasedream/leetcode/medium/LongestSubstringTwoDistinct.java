package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * leetcode 159. 至多包含两个不同字符的最长子串
 */
public class LongestSubstringTwoDistinct {
    public static void main(String[] args) {
        LongestSubstringTwoDistinct lsd = new LongestSubstringTwoDistinct();
        String in = "aacbbbb";
        Out.println(lsd.lengthOfLongestSubstringTwoDistinct1(in));
    }

    /**
     * 思路：
     * 哈希和双指针
     * 使用Hashmap，当hashmap的大小为2时是一个转折点，此时通过变换指针的指向继续循环直到字符串结束
     * 时间复杂度o(n)
     * 形成新的输出字符串，直至遍历结束。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct1(String s) {
        if (s.length() <= 2) {
            return s.length();
        }
        // 首先定义两个指针
       return 0;
    }

    /**
     * 借助哈希和队列实现
     * 遇到map大小为2时，根据辅助队列将map的大小变为1后再次放入。
     * 缺点：思路有点绕，队列的出入有耗时开销。
     * 时间复杂度：O（n）* O（队列平均大小时间开销）
     *
     * @param s 输入字符
     * @return 最长的仅仅包含两个不同字符的字串大小
     */
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        // 用于保存char-int<字符-字符的数量>键值对
        Map<Character, Integer> map = new HashMap<>();
        // 用于存放当前字符集
        Queue<Character> queue = new LinkedList<>();
        int size = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            // 若包含当前字符，则将其数量+1
            if (map.containsKey(ch)) {
                int count = map.get(ch);
                map.put(ch, count + 1);
                queue.offer(ch);
                continue;
            }

            // map不包含当前字符的情况，
            // 分两种：
            // map大小小于2直接放入
            // map大小等于2，则根据queue的数据将map的大小变为1后将当前字符放入
            // 当map的大小小于2时直接放入
            if (map.size() < 2) {
                map.put(ch, 1);
                queue.offer(ch);
            } else {
                size = Math.max(size, queue.size());
                // 根据queue将map的大小从2变为1
                while (map.size() >= 2) {
                    char temp = queue.poll();
                    int count = map.get(temp);
                    if (count == 1) {
                        map.remove(temp);
                    } else {
                        map.put(temp, count - 1);
                    }
                }

                // 放入当前字符
                map.put(ch, 1);
                queue.offer(ch);
            }
        }

        return Math.max(size, queue.size());
    }
}
