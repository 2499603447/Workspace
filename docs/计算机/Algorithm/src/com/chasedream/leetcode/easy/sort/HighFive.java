/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.sort;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 20-2-17 下午9:57
 */
public class HighFive {
    public static void main(String[] args) {
        HighFive hf = new HighFive();
        int[][] in = {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        hf.highFive(in);
    }

    public int[][] highFive(int[][] items) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] arr : items) {
            List<Integer> list;
            if (!map.containsKey(arr[0])) {
                list = new ArrayList<>();
            } else {
                list = map.get(arr[0]);
            }
            list.add(arr[1]);
            map.put(arr[0], list);
        }

        int[][] res = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> scoreList = entry.getValue();
            Collections.sort(scoreList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            res[index][0] = entry.getKey();
            int size = scoreList.size();
            for (int i = 0; i < size && i < 5; i++) {
                res[index][1] += scoreList.get(i);
            }
            res[index][1] = res[index][1] / (Math.min(size, 5));
            index++;
        }

        return res;
    }

    public int[][] highFive1(int[][] items) {
        Arrays.sort(items, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int[][] res = new int[items[items.length - 1][0]][2];
        for (int i = 0; i < items.length; i++) {
            if (i == 0 || items[i][0] != items[i - 1][0]) {
                res[items[i][0] - 1][0] = items[i][0];
                for (int j = i; j < i + 5; j++) {
                    res[items[i][0] - 1][1] += items[j][1];
                }

                res[items[i][0] - 1][1] /= 5;
                i += 4;
            }
        }

        return res;
    }
}
