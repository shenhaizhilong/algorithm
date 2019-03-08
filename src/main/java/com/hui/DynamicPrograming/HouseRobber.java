package com.hui.DynamicPrograming;

/**
 *https://leetcode-cn.com/problems/house-robber/description/
 *
 * https://blog.csdn.net/kc171154/article/details/49667515
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * @author: shenhaizhilong
 * @date: 2018/8/14 16:33
 */
public class HouseRobber {

    // dp 代表打劫到第i家为止获得的最大收益

    /**
     * 递推表达式 dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
     *  转换为long 防止溢出
     * @param nums
     * @return
     */
    public static long rob(int[] nums)
    {
        if(nums == null || nums.length == 0)return 0L;
        if(nums.length == 1)return nums[0];
        if(nums.length == 2)return Math.max(nums[0], nums[1]);
        long dpi = 0L;

        //用dpi_2 代表dp[i-2]
        long dpi_2 = nums[0];

        //用dpi_1 代表dp[i-1]
        long dpi_1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {

            dpi = Math.max(dpi_1, dpi_2 + nums[i]);
            dpi_2 = dpi_1;
            dpi_1 = dpi;
        }
        return dpi;

    }

    public static void main(String[] args) {

        int[] a = {1,2,3,1};
        int[] b = {3,2,1,5};
        int[] c = {6,5,1,9};
        int[] d = {1};
        int[] e = {1,9};

        System.out.println(rob(a));
        System.out.println(rob(b));
        System.out.println(rob(c));
        System.out.println(rob(d));
        System.out.println(rob(e));
    }
}
