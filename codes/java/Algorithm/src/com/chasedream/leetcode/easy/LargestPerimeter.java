package com.chasedream.leetcode.easy;

import com.chasedream.utils.Out;

import java.util.Arrays;

public class LargestPerimeter {
    public static void main(String[] args) {
        LargestPerimeter lp = new LargestPerimeter();
        Out.println(lp.largestPerimeter(new int[]{3, 4, 15, 2, 9, 4}));
    }

    /**
     * 利用java.util自带的排序工具，然后依次从最后三个开始判定
     * @param A 待检查的数组
     * @return 最大周长
     */
    public int largestPerimeterWithUtils(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i)
            if (A[i] + A[i+1] > A[i+2])
                return A[i] + A[i+1] + A[i+2];
        return 0;
    }

    /**
     * 利用冒泡排序算法的特点，一趟下来，最后一个必定是最大值
     * 因此三趟便可以判断最大的那三个是否可以组成三角形，若能组成三角形，则直接返回；否则，继续冒泡
     * @param A 待检查的数组
     * @return 最大周长
     */
    public int largestPerimeter(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j <= len - 1 - i; j++) {
                if (A[j] < A[j - 1]) {
                    int temp = A[j];
                    A[j] = A[j - 1];
                    A[j - 1] = temp;
                }
            }

            if (i >= 2 && A[len - 1 - i] + A[len - i] > A[len - i + 1]) {
                return A[len - 1 - i] + A[len - i] + A[len - i + 1];
            }
        }

        if (A[0] + A[1] > A[2]) {
            return A[0] + A[1] + A[2];
        }

        return 0;
    }
}
