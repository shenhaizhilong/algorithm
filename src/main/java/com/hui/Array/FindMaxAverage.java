package com.hui.Array;

/**
 *https://leetcode-cn.com/problems/maximum-average-subarray-i/description/
 * 子数组最大平均数 I
 *
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例 1:
 *
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 * 注意:
 *
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/7/5 16:40
 */
public class FindMaxAverage {

    public static double findMaxAverage(int[] nums, int k) {

        int maxSum = 0;

        for (int i = 0; i < k; i++) {
            maxSum +=nums[i];
        }

        int thisSum = maxSum;
        for (int i = 1; i <= nums.length-k; i++) {
            thisSum = thisSum + nums[i+k-1] - nums[i-1];
            if(thisSum > maxSum)
            {
                maxSum = thisSum;
            }
        }

        return maxSum*1.0d/k;

    }
}
