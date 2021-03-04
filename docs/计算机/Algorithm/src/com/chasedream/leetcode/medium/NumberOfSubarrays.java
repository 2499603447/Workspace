package com.chasedream.leetcode.medium;

import java.util.*;

/**
 * 1248.统计优美子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 总结：对比分析方法2和方法3发现，同样的算法时间复杂度，使用不同的数据结构会导致最终的算法执行时间大不相同
 * 方法2中奇数的存储通过使用ArrayList
 * 而方法3中是通过简单的数组来存储
 * 在循环体中，访问的速度上有着明显的不同
 *
 * 当把方法2中的ArrayList换成LinkedList时，会发现算法执行从原先的几毫秒飙升至5000ms左右，
 * 执行时间长了1000倍左右。
 */
public class NumberOfSubarrays {
    public static int numberOfSubarrays(int[] nums, int k) {
        int res = 0;
        int lens = nums.length;
        boolean isEnd = false;
        for (int i = 0; i < lens; i++) {
            int n = 0;
            isEnd = false;
            for (int j = i; j < lens; j++) {
                if (isEnd && nums[j] % 2 == 1) {
                    break;
                }
                if (nums[j] % 2 == 1) {
                    n++;
                }

                if (n == k) {
                    res++;
                    isEnd = true;
                }
            }
        }

        return res;
    }

    /**
     * 数据量较大时，会严重超时
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numberOfSubarrays2(int[] nums, int k) {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        int res = 0;
        int lens = nums.length;

        // 首先找到连续额K个奇数，将其依次放入队列
        list.add(-1);
        for (int i = 0; i < lens; i++) {
            if (nums[i] % 2 == 1) {
                list.add(i);
            }
        }
        list.add(lens);
        int oddLen = list.size();
        if (oddLen - 2 < k) {
            return res;
        }
        for (int i = 1; i + k < oddLen; i++) {
            int a = list.get(i) - list.get(i - 1);
            int b = list.get(i + k) - list.get(i + k - 1);
            res += a * b;
        }

        System.out.println(System.currentTimeMillis() - start);
        return res;
    }

    public static int numberOfSubarrays3(int[] nums, int k) {
        long start = System.currentTimeMillis();
        int res = 0;
        int lens = nums.length;
        int[] arr = new int[lens + 2];
        int oddNum = 0;
        // 首先找到连续额K个奇数，将其依次放入队列
        arr[0] = -1;
        for (int i = 0; i < lens; i++) {
            if (nums[i] % 2 == 1) {
                arr[++oddNum] = i;
            }
        }
        arr[oddNum + 1] = lens;
        for (int i = 1; i + k < oddNum + 2; i++) {
            int a = arr[i] - arr[i - 1];
            int b = arr[i + k] - arr[i + k - 1];
            res += a * b;
        }
        System.out.println(System.currentTimeMillis() - start);
        return res;
    }

    public static void main(String[] args) {
        int n = 50000;
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * n);
        }

        System.out.println(numberOfSubarrays2(arr, 1000));
        System.out.println(numberOfSubarrays3(arr, 1000));
    }
}
