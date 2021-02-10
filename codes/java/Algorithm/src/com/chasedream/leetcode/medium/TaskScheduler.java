package com.chasedream.leetcode.medium;

import java.util.Arrays;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 *
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TaskScheduler {
    public static void main(String[] args) {
        char[] chars = new char[]{'A','A','A','B','B','B'};
        System.out.println(leastInterval(chars, 2));
    }

    /**
     * 通过数学推算的方式计算得到最终结果
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        int totalTime = 0;
        int[] times = new int[26];
        for(char task : tasks) {
            times[task - 'A']++;
        }
        Arrays.sort(times);

        int maxTimes = times[25];
        int idle = (maxTimes - 1) * n;
        for (int i = 24; i >= 0 && times[i] > 0; i--) {
            idle -= Math.min(times[i], maxTimes - 1);
        }

        return idle > 0 ? idle + tasks.length : tasks.length;
    }

    /**
     * 通过队列的方式计算最终的结果
     * @param tasks
     * @param n
     * @return
     */
    public static int leastIntervalQueue(char[] tasks, int n) {
        int totalTime = 0;

        return totalTime;
    }
}
