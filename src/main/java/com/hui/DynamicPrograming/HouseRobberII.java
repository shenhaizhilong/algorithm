package com.hui.DynamicPrograming;

/**
 *https://leetcode.com/problems/house-robber-ii/
 *
 * 213. House Robber II
 * DescriptionHintsSubmissionsDiscussSolution
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 *     dpi 代表打劫到第i家为止获得的最大收益
 *
 *
 *      递推表达式 dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
 * @author: shenhaizhilong
 * @date: 2018/8/24 18:01
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        if(nums.length == 1)return nums[0];
        if(nums.length == 2)return Math.max(nums[0], nums[1]);
        int max1 = robHelper(nums,0, nums.length -2);
        int max2 = robHelper(nums,1, nums.length -1);
        return Math.max(max1, max2);
    }

    private int robHelper(int[] nums, int start, int end)
    {
        if(end - start +1 ==  2)return Math.max(nums[start], nums[start +1]);
        ////用dpi_2 代表dp[i-2]
        int dp_2 = nums[start];

        //用dpi_1 代表dp[i-1]
        int dp_1 = Math.max(nums[start], nums[start +1]);
        int dpi =0;
        for (int i = start +2; i <= end ; i++) {
             dpi = Math.max(dp_1, dp_2 + nums[i]);
             dp_2 = dp_1;
             dp_1 = dpi;
        }

        return dpi;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        HouseRobberII houseRobberII = new HouseRobberII();
        System.out.println(houseRobberII.rob(nums));
       int[]  nums2 = new int[]{2,3,2};
        System.out.println(houseRobberII.rob(nums2));
    }
}
