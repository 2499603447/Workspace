package com.chasedream.leetcode.hard;

import com.chasedream.utils.Out;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {
    List<List<String>> res = new ArrayList<>();
    boolean isFind = false; // 表示是否已经找到一组结果

    public List<List<String>> solveNQueens(int n) {
        int[] a = new int[n + 1]; // a[i]的值表示第i行a[i]列放置皇后
        solveQueens(a, 1, n);

        return res;
    }

    /**
     * 放置皇后迭代主题
     * @param a 放置皇后的数组
     * @param k 当前放置的是第几个皇后
     * @param n 总共需要放置的皇后数目
     */
    public void solveQueens(int[] a, int k, int n) {
        if (k > n) {
            // isFind = true;
            // 根据数组a生成题目想要的结果
            List<String> temp = new ArrayList<>(); // 存储当前结果，每行为一个String字符串
            for (int i = 1; i <= n; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == a[i] - 1) {
                        line.append("Q");
                    } else {
                        line.append(".");
                    }
                }
                temp.add(line.toString());
            }
            res.add(temp);
        } else {
            for (int i = 1; i <= n; i++) {
                a[k] = i;
                if (!isConflict(a, k)) {
                    solveQueens(a, k + 1, n);
                }

                /*if(isFind) { // 若找到了一组结果，则直接返回结果，不在继续递归
                    break;
                }*/
            }
        }
    }

    /**
     * 冲突检查
     * @param a 放置皇后的数组
     * @param k 当前放置第几个皇后
     * @return 是否和前面的有冲突
     */
    private boolean isConflict(int[] a, int k) {
        for (int i = 1; i < k; i++) {
            if (a[i] == a[k] || Math.abs(a[i] - a[k]) == k - i) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        List<List<String>> out = solveNQueens.solveNQueens(8);
        Out.println(out.size());
    }
}
