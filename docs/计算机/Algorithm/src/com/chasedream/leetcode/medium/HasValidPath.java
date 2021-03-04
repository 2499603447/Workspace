/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/22 11:08
 */
public class HasValidPath {
    public static void main(String[] args) {
        HasValidPath hp = new HasValidPath();
        int[][] grid = new int[][]{{4, 1}, {6, 1}};
        Out.println(hp.hasValidPath(grid));
    }

    private char[][] directions = new char[7][];

    /**
     * 执行用时 :5 ms, 在所有 Java 提交中击败了98.78%的用户
     * 内存消耗 :55.5 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param grid 道路路径数组
     * @return 是否能够从左上角走到右下角
     */
    public boolean hasValidPath(int[][] grid) {
        directions[1] = new char[]{'l', 'r'};
        directions[2] = new char[]{'u', 'd'};
        directions[3] = new char[]{'l', 'd'};
        directions[4] = new char[]{'r', 'd'};
        directions[5] = new char[]{'l', 'u'};
        directions[6] = new char[]{'u', 'r'};

        if (grid[0][0] == 5) {
            return false;
        }
        if (grid[0][0] == 4) {
            return isValid(grid, directions[4][0]) || isValid(grid, directions[4][1]);
        } else {
            return isValid(grid, directions[grid[0][0]][1]);
        }
    }

    private boolean isValid(int[][] grid, char direction) {
        int r = 0;
        int c = 0;
        while (r != grid.length - 1 || c != grid[0].length - 1) {
            if (direction == 'u') {
                r -= 1;
                if (r < 0 || directions[grid[r][c]][1] != 'd') {
                    return false;
                }
                direction = directions[grid[r][c]][0];

            } else if (direction == 'd') {
                r += 1;
                if (r >= grid.length || (directions[grid[r][c]][0] != 'u'
                        && directions[grid[r][c]][1] != 'u')) {
                    return false;
                }
                direction = directions[grid[r][c]][0] == 'u' ?
                        directions[grid[r][c]][1] : directions[grid[r][c]][0];

            } else if (direction == 'l') {
                c -= 1;
                if (c < 0 || (directions[grid[r][c]][0] != 'r'
                        && directions[grid[r][c]][1] != 'r')) {
                    return false;
                }
                direction = directions[grid[r][c]][0] == 'r' ?
                        directions[grid[r][c]][1] : directions[grid[r][c]][0];
            } else if (direction == 'r') {
                c += 1;
                if (c >= grid[0].length || directions[grid[r][c]][0] != 'l') {
                    return false;
                }

                direction = directions[grid[r][c]][1];
            }
        }
        return true;
    }
}
