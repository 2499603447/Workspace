package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

/**
 * 5257. 统计封闭岛屿的数目
 * 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
 * <p>
 * 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
 * <p>
 * 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
 * <p>
 * 请返回封闭岛屿的数目。
 */
public class ClosedIsland {
    public static void main(String[] args) {
        ClosedIsland cl = new ClosedIsland();
        //int[][] grid = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
        int[][] grid = {{0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 1, 1, 0}};
        Out.println(cl.closedIsland(grid));
    }

    private boolean isFind;

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                isFind = true;
                reverse(grid, i, j);
                count += isFind ? 1 : 0;
            }
        }

        return count;
    }

    private int reverse(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 1) {
            return 0;
        }

        isFind &= !(r == 0 || r == m - 1 || c == 0 || c == n - 1);
        grid[r][c] = 1;
        return 1 + reverse(grid, r + 1, c) + reverse(grid, r - 1, c)
                + reverse(grid, r, c + 1) + reverse(grid, r, c - 1);

    }
}
