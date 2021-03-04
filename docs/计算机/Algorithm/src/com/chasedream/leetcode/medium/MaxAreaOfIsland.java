/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/15 15:06
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        MaxAreaOfIsland mi = new MaxAreaOfIsland();
        /*int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};*/

        int[][] grid = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
        Out.println(mi.maxAreaOfIsland1(grid));
    }

    /**
     * 迭代求解
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                int count = 0;
                grid[i][j] = 0;
                queue.add(new Point(i, j));
                // 迭代
                while (!queue.isEmpty()) {
                    Point point = queue.poll();
                    count++;
                    enqueue(grid, queue, point.x - 1, point.y);
                    enqueue(grid, queue, point.x + 1, point.y);
                    enqueue(grid, queue, point.x, point.y - 1);
                    enqueue(grid, queue, point.x, point.y + 1);
                }
                max = Math.max(count, max);
            }
        }

        return max;
    }

    private void enqueue(int[][] grid, Queue<Point> queue, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        queue.add(new Point(x, y));
    }

    /**
     * 递归求解
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                // 递归
                max = Math.max(max, reverse(grid, i, j));
            }
        }

        return max;
    }

    private int reverse(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;

        return 1 + reverse(grid, r + 1, c) + reverse(grid, r - 1, c)
                + reverse(grid, r, c - 1) + reverse(grid, r, c + 1);
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
