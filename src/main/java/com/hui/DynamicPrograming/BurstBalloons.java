package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/17 0:364
 *
 *
 *
 * 312. Burst Balloons
 * DescriptionHintsSubmissionsDiscussSolution
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 *
 * https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
 *https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
 *
 */
public class BurstBalloons {

    public int maxCoins(int[] nums) {
        int N = nums.length;
        int[][] memo = new int[N][N];
        return burst(nums, 0, N -1, 1, 1, memo);

    }

    private int burst(int[] nums, int i, int j, int left, int right, int[][] cache)
    {
        if(i > j)return 0;
        if(cache[i][j] > 0)return cache[i][j];
        if(i == j)
        {
            cache[i][j] = left*nums[i]*right;
            return   cache[i][j];
        }

        int max = 0;
        for (int k = i; k <= j; k++) {
            int sum = left*right*nums[k] + burst(nums, i, k -1,left , nums[k], cache) + burst(nums, k+1, j,nums[k], right, cache);
            max = Math.max(max, sum);
        }
        cache[i][j] = max;
        return max;

    }


    public int maxCoins2(int[] nums) {
        int N = nums.length;
        int[][] memo = new int[N +2][N +2];
        int[] newNums = new int[N+2];
        newNums[0] = 1;
        newNums[N +1] = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i+1] = nums[i];
        }

        return burst2(newNums, 1, N, memo);
    }

    private int burst2(int[] nums, int start, int end, int[][] cache)
    {
        if(start > end )return 0;

        if(cache[start][end] > 0)return cache[start][end];
        if (start == end)
        {
            cache[start][end] = nums[start -1]*nums[start]*nums[end +1];
            return cache[start][end];
        }
        int max = 0;
        for (int k = start; k <= end; k++) {
            int sum = nums[start -1]*nums[k]*nums[end +1] + burst2(nums, start, k -1 , cache) + burst2(nums, k+1, end, cache);
            max = Math.max(max, sum);
        }
        cache[start][end] = max;
        return max;

    }

    public static void main(String[] args) {
        BurstBalloons burstBalloons = new BurstBalloons();
        System.out.println(burstBalloons.maxCoins(new int[]{3,1,5,8}));
        System.out.println(burstBalloons.maxCoins2(new int[]{3,1,5,8}));
    }
}
