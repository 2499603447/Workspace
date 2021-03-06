/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/25 23:01
 */
public class SurfaceArea {
    public static void main(String[] args) {
        SurfaceArea sa = new SurfaceArea();
        int[][] arr = {{1, 0}, {0, 2}};
        sa.surfaceArea(arr);
    }

    public int surfaceArea(int[][] grid) {
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0 && j < grid[i - 1].length) {
                    // 考虑上面
                    area -= Math.min(grid[i - 1][j], grid[i][j]) * 2;
                }

                if (j > 0) {
                    area -= Math.min(grid[i][j - 1], grid[i][j]) * 2;
                }

                area += grid[i][j] == 0 ? 0 : 4 * grid[i][j] + 2;
            }
        }
        return area;
    }
}
