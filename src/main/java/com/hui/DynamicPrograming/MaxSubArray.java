package com.hui.DynamicPrograming;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author: shenhaizhilong
 * @date: 2018/7/5 13:41
 */
public class MaxSubArray {
    public static long maxSubArray(int[] a)
    {
        int maxSum = 0;
        int thisSum = 0;
        int maxNumber = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if(a[i] > maxNumber)
            {
                maxNumber = a[i];
            }
            thisSum+=a[i];
            if(thisSum > maxSum)
            {
                maxSum = thisSum;
            }else if(thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum ==0? maxNumber : maxSum;
    }


    public static long maxSubArray2(int[] nums)
    {
        long[] dp = new long[nums.length]; // dp[i] means max sub sum to nums[i]
        dp[0] = nums[0];
        long max = nums[0];
        for (int i = 1; i < nums.length; i++) {

            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static long maxSubArray3(int[] nums)
    {
        long dpEndingI = nums[0]; // dp[i] means max sub sum to nums[i]

        long max = nums[0];
        for (int i = 1; i < nums.length; i++) {

            dpEndingI = Math.max(nums[i], nums[i] + dpEndingI);
            max = Math.max(dpEndingI, max);
        }
        return max;
    }

    public static void main(String[] args) {


        System.out.println(maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray3(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

}
