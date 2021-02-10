/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/15 21:43
 */
public class NumDistinctIslands {
    public static void main(String[] args) {
        NumDistinctIslands ndl = new NumDistinctIslands();
/*int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};*/

        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {
                {0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0}};
        Out.println(ndl.numDistinctIslands1(grid));
    }


    /**
     * 方法一：通过网格散列
     * 算法：
     * <p>
     * 在开始的时候，我们需要找到每个岛屿，我们可以使用直接的深度优先搜索。最困难的部分是判断两个岛屿是否相同。
     * 若两个岛是相同的那么这个岛可以通过平移变换成另一个岛，那么让我们把每个岛的左上角当作是 (0, 0)，例如，如果一个岛是由 [(2, 3), (2, 4), (3, 4)] 构成，当固定左上角时，我们可以把这个形状看作是 [(0, 0), (0, 1), (1, 1)]。
     * 从这里，我们只需要检查唯一的形状（所以 [(0, 0), (0, 1)] 与 [(0, 1), (1, 0)] 相同）。我们可以直接使用集合，但是我们也可以对每个列表进行排序，并将这些排序的列表放入我们的集合结构 shapes 中。
     * 复杂度分析
     * <p>
     * 时间复杂度：O(R*C)O(R∗C)。其中 RR 是给定网格中的行数，CC 是列数。我们遍历每个网格一次。
     * 空间复杂度：O(R*C)O(R∗C)，seen 用来保存跟踪访问过的网格和形状。
     *
     * @param grid
     * @return
     */
    private Set<List<Integer>> set = new HashSet<>();
    private int baseI = 0;
    private int baseJ = 0;

    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                baseI = i;
                baseJ = j;
                traversal(grid, list, i, j);
                set.add(list);
            }
        }

        return set.size();
    }

    private int traversal(int[][] grid, List<Integer> list, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        list.add(r - baseI);
        list.add(c - baseJ);
        grid[r][c] = 0;
        return 1 + traversal(grid, list, r - 1, c) + traversal(grid, list, r + 1, c)
                + traversal(grid, list, r, c - 1) + traversal(grid, list, r, c + 1);
    }

    /**
     * 方法二：通过路径散列
     * 算法：
     * 当我们从某个岛的左上角开始深度优先搜索时，如果形状相同，则深度优先搜索所采用的路径将相同。我们可以通过记录我们所形成的路径来利用这一点，记录我们在进入和退出函数时的状态。代码的其余部分与方法 1 相同。
     * <p>
     * 复杂度分析
     * 时空复杂度： O(R*C)O(R∗C)。分析与方法 1 相同。
     *
     * @param grid
     * @return
     */
    public int numDistinctIslands1(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        Set<String> path = new HashSet<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                StringBuilder builder = new StringBuilder();
                traversal(grid, builder, i, j);
                path.add(builder.toString());
            }
        }

        return path.size();
    }

    private void traversal(int[][] grid, StringBuilder builder, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return;
        }
        grid[r][c] = 0;
        // 伪路径,不管是否可走
        /*traversal(grid, builder.append("u"), r - 1, c);
        traversal(grid, builder.append("d"), r + 1, c);
        traversal(grid, builder.append("l"), r, c - 1);
        traversal(grid, builder.append("r"), r, c + 1);*/

        // 真路径,判断是否可走
        if (r - 1 >= 0 && grid[r - 1][c] == 1) {
            traversal(grid, builder.append("u"), r - 1, c);
        }
        if (r + 1 < m && grid[r + 1][c] == 1) {
            traversal(grid, builder.append("d"), r + 1, c);
        }
        if (c - 1 >= 0 && grid[r][c - 1] == 1) {
            traversal(grid, builder.append("l"), r, c - 1);
        }
        if (c + 1 < n && grid[r][c + 1] == 1) {
            traversal(grid, builder.append("r"), r, c + 1);
        }
    }
}
