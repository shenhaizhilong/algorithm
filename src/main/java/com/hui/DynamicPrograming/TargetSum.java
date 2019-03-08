package com.hui.DynamicPrograming;

import com.hui.Array.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/22 14:39
 *
 *494. Target Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 */
public class TargetSum {


    public int findTargetSumWays(int[] nums, int S) {
        int[] res = new int[1];
        List<List<Integer>> lists = new ArrayList<>();

        dfs(nums, 0, S, res, lists, new ArrayList<>());
        Matrix.print(lists);
        return res[0];
    }

    private void dfs(int[] nums, int idx, int target, int[] res, List<List<Integer>> lists, List<Integer> list)
    {
        if( target == 0 && list.size() == nums.length)
        {
            res[0]++;
            lists.add(new ArrayList<>(list));
            return;
        }
        if(idx >= nums.length)return;
        list.add(nums[idx]);
        dfs(nums, idx +1, target - nums[idx], res, lists, list);
        list.remove(list.size() -1);
        list.add(-nums[idx]);
        dfs(nums, idx +1,target + nums[idx], res, lists, list);
        list.remove(list.size() -1);

    }


    public int findTargetSumWays2(int[] nums, int S) {
        if(nums == null || nums.length < 1)return 0;
        int[][] cache = new int[nums.length][2001];
        for(int[] row:cache)
        {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

       // Matrix.print(cache);

        return dfs(nums, 0,0, S, cache);
    }

    private int dfs(int[] nums, int idx,int sum, int target, int[][] cache )
    {
        if(idx == nums.length && target == sum)return 1;
        if(idx >= nums.length)return 0;

        if(cache[idx][sum +1000] >= 0)
        {
            return cache[idx][sum +1000];
        }

        int left =   dfs(nums, idx +1,  sum + nums[idx], target, cache );
        int right =   dfs(nums, idx +1, sum - nums[idx], target, cache );
        cache[idx][sum + 1000] = left + right;
        return left + right;

    }


    public int findTargetSumWays3(int[] nums, int S) {
        if(nums == null || nums.length < 1)return 0;
        int N = nums.length;
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;  // if nums[0] == 0, then it's 2

        for (int i = 1; i < N; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if(dp[i -1][sum + 1000] > 0)
                {
                    dp[i][sum + nums[i] + 1000] += dp[i -1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i -1][sum + 1000];
                }
            }
        }

        return S > 1000 ? 0: dp[nums.length -1][S + 1000];
    }

    public int findTargetSumWays4(int[] nums, int S) {
        if(nums == null || nums.length < 1)return 0;
        int N = nums.length;
        int[] dpPrev = new int[2001];
        dpPrev[nums[0] + 1000] = 1;
        dpPrev[-nums[0] + 1000] += 1;  // if nums[0] == 0, then it's 2

        for (int i = 1; i < N; i++) {
            int[] curr = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if(dpPrev[sum + 1000] > 0)
                {
                    curr[sum + nums[i] + 1000] += dpPrev[sum + 1000];
                    curr[sum - nums[i] + 1000] += dpPrev[sum + 1000];
                }
            }
            dpPrev = curr;
        }

        return S > 1000 ? 0: dpPrev[S + 1000];
    }


    /**
     *
     *
     * https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C++-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
     * The original problem statement is equivalent to:
     * Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
     *
     * Let P be the positive subset and N be the negative subset
     * For example:
     * Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
     * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
     *
     * Then let's see how this can be converted to a subset sum problem:
     *
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * So the original problem has been converted to a subset sum problem as follows:
     * Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     *
     * Note that the above formula has proved that target + sum(nums) must be even
     *
     * @param nums
     * @param S
     * @return
     */

    public int findTargetSumWays5(int[] nums, int S) {
        if(nums == null || nums.length < 1)return 0;
        int sum = 0;
        for(int i:nums)sum += i;
        if(sum < S || ((sum + S) & 1) == 1)return 0;
        return NumberOfSubsetSum(nums, (sum + S) >>> 1);
    }

    /**
     *
     * 有N 个非负元素的数组，组合和为 S 的数量，每个元素最多用一次
     *
     * @param nums
     * @param s
     * @return
     */
    public int NumberOfSubsetSum(int[] nums, int s)
    {
        int[] dp = new int[s +1];
        dp[0] = 1;
        for(int n: nums)
        {
            for (int i = s; i >= n; i--)
            {
                dp[i] += dp[i -n];
            }
        }
        return dp[s];
    }



    public static void main(String[] args) {

        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays2(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays2(new int[]{2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53}, 1000));
        System.out.println("****************");

        System.out.println(targetSum.findTargetSumWays3(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays3(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays3(new int[]{2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53}, 1000));
        System.out.println("****************");
        System.out.println(targetSum.findTargetSumWays4(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays4(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays4(new int[]{2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53}, 1000));

        System.out.println("****************");
        System.out.println(targetSum.findTargetSumWays5(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays5(new int[]{1,1,1,1,1},3));
        System.out.println(targetSum.findTargetSumWays5(new int[]{2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53}, 1000));

    }

}
