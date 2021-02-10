package com.chasedream.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	static long startTime;
	static long endTime;
	static int steps = 0;

	public static void main(String[] args) {
		int[] in = { 1, 2, 3, 4, 5, 6, 150, 151, 200, 250, 301, 498 };
		int[] result = twoSumMethod1(in, 301);
		endTime = System.currentTimeMillis();
		System.out.println(result[0] + " " + result[1]);
		System.out.println("use time:" + (endTime - startTime));
		System.out.println("steps:" + steps);
	}

	/**
	 * 思维方式要进行转变
	 *
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	 *
	 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
	 *
	 * 示例:
	 *
	 * 给定 nums = [2, 7, 11, 15], target = 9
	 *
	 * 因为 nums[0] + nums[1] = 2 + 7 = 9
	 * 所以返回 [0, 1]
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/two-sum
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		int[] result = new int[2];

		for ( int i = 0 ; i < nums.length; i++) {
			int temp = target - nums[i];
			if (map.containsKey(temp)) {
				result[0] = map.get(temp);
				result[1] = i;
				return result;
			}
			map.put(nums[i], i);
		}

		return result;
	}
	/**
	 * ����Ϊ������������飬�������������ӶȽϸ�
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSumMethod1(int[] nums, int target) {
		Arrays.sort(nums);
		startTime = System.currentTimeMillis();
		int length = nums.length;
		int[] result = new int[2];
		int start = 0;
		int end = 0;
		for (int i = 0; i < length; i++) {
			if ((nums[i] + nums[length / 2 - 1]) < target) {
				start = length / 2 - 1;
				end = length;
			} else {
				start = i + 1;
				end = length / 2 - 1;
			}
			for (int j = start; j < end; j++) {
				steps++;
				if ((nums[i] + nums[j]) == target) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}

		return result;
	}

	/**
	 * ����Ϊ������������飬��ϣ�����
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSumMethod2(int[] nums, int target) {
		Arrays.sort(nums);
		startTime = System.currentTimeMillis();
		int length = nums.length;
		int[] result = new int[2];
		return result;
	}

	/**
	 * ����Ϊ������������飬indexOf���������±�
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSumMethod3(int[] nums, int target) {
		int length = nums.length;
		int[] result = new int[2];

		for (int i : nums) {
			int k = indexOf(nums, target - nums[i]);
			if (k != i && k != -1) {
				result[0] = i;
				result[1] = k;
			}
		}

		return result;
	}

	public static int indexOf(int[] nums, int m) {
		for (int i = 0; i < nums.length; i++)
			if (m == nums[i])
				return i;

		return -1;
	}

	/**
	 * ����Ϊ���������
	 * 
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		int start = 0;
		int end = numbers.length - 1;
		while (start < end) {
			if (numbers[start] + numbers[end] == target) {
				result[0] = start + 1;
				result[1] = end + 1;
				return result;
			} else {
				if (numbers[start] + numbers[end] < target) {
					start++;
				} else {
					end--;
				}
			}
		}

		return result;
	}
}
