package com.chasedream.leetcode.medium;

/**
 * 48.转图像
 * 主要限制条件：
 * 不允许开辟一个矩阵，就表明我们需要原地改变矩阵的值
 * 主要思想：
 * 将 n*n 矩阵想象成一个罗盘，每一圈都是可以无限转动的，那么我们想得到旋转
 */
public class Rotate {
    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate.rotate(matrix);
    }

    /**
     * 每次通过修改每条边上的值，temp = 上边 = 左边 = 下边 = 右边 = temp
     * 这种方法中每个for循环中一次修改每一个值，共循环次数=（n / 2）* (end - i) * 4
     *
     * @param matrix 待旋转的矩阵
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[] temp = new int[n];
        int end = n - 1;
        // 循环 n / 2 次
        for (int i = 0; i < n / 2; i++) {
            // 保存第i行的值
            System.arraycopy(matrix[i], i, temp, i, end - i + 1);

            // 每条边循环end - i次
            // 上边复制
            for (int j = i; j < end - i; j++) {
                matrix[i][j] = matrix[end - j][i];
            }

            // 左边复制
            for (int j = i; j <= end - i; j++) {
                matrix[j][i] = matrix[end - i][j];
            }

            // 下边复制
            for (int j = i; j <= end - i; j++) {
                matrix[end - i][j] = matrix[end - j][end - i];
            }

            // 右边复制
            for (int j = i; j <= end - i; j++) {
                matrix[j][end - i] = temp[j];
            }
        }
    }

    /**
     * 每次通过修改每条边上的值，temp = 上边 = 左边 = 下边 = 右边 = temp
     * 共循环次数=（n / 2）* (end - i)，
     * 循环次数缩短为原先的1/4，数据量大的时候时间开销会有明显提升。
     *
     * @param matrix 待旋转的矩阵
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        int end = n - 1;
        for (int i = 0; i < n / 2; i++) {
            // 将上述方法中的每条边分开复制合并到依次循环中
            for (int j = i; j < end - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[end - j][i];
                matrix[end - j][i] = matrix[end - i][end - j];
                matrix[end - i][end - j] = matrix[j][end - i];
                matrix[j][end - i] = temp;
            }
        }
    }
}
