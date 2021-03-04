/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/15 17:55
 */
public class NumIslands {
    public static void main(String[] args) {
        NumIslands ns = new NumIslands();
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        Out.println(ns.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                count++;
                reverse(grid, i, j);
            }
        }

        return count;
    }

    private int reverse(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return 0;
        }

        grid[r][c] = '0';
        return 1 + reverse(grid, r + 1, c) + reverse(grid, r - 1, c)
                + reverse(grid, r, c + 1) + reverse(grid, r, c - 1);

    }
}
