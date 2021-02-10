package com.chasedream.leetcode.easy;

import static java.lang.System.out;

/**
 * 53.最大子序列的和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{-3, -2};
        out.println(maxSubArray2(arr));
    }

    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];
        for (int i = 0; i < len; i++) {
            int temp = 0;
            for (int j = i; j < len; j++) {
                temp = temp + nums[j];
                if (temp > maxSum) {
                    maxSum = temp;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubArray2(int[] nums) {
        /*if (nums == NULL || numsSize <= 0) {
            return 0;
        }

        int dp[numsSize];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < numsSize; ++i) {
            dp[i] = (dp[i - 1] >= 0 ? dp[i - 1] + nums[i] : nums[i]);
            maxSum = max(maxSum, dp[i]);
        }
        return maxSum;*/
        int len = nums.length;
        int maxSum = nums[0];
        int thisSum = 0;
        for (int i = 0; i < len; i++) {
            if (thisSum + nums[i] > nums[i]) {
                thisSum += nums[i];
            } else {
                thisSum = nums[i];
            }

            if (thisSum > maxSum){
                maxSum = thisSum;
            }
        }
        return maxSum;
    }
}
