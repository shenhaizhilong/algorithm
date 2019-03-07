package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/19 9:51
 *
 *
 * 410. Split Array Largest Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 *
 */
public class SplitArrayLargestSum{

        public int splitArray(int[] nums, int m) {

            int N = nums.length;
            int[] preSum = new int[N];
            preSum[0] = nums[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i -1] + nums[i];
            }
            int[][] memo = new int[m +1][N +1];
            return find(nums, N -1,  m, preSum,memo);

        }

        private int find(int[] nums, int end,  int m,int[] presum, int[][] memo)
        {
            if(m == 1)return presum[end];
            if(memo[m][end] > 0)return memo[m][end];
            int sum = 0;
            int min = Integer.MAX_VALUE;
            // Array      =     [7, 2, 5,10, 8]
            // presumLeft =     [7, 9,14,24,32]
            //presumRight = [32,25,23,18,8]
            for (int i = end; i >= m -1 && presum[i] >= sum; i--) {
                sum += nums[i];
                min = Math.min(min, Math.max(sum, find(nums, i-1,  m -1,presum, memo)));
            }
            memo[m][end] = min;
            return min;

        }




    public static void main(String[] args) {

            SplitArrayLargestSum splitArrayLargestSum = new SplitArrayLargestSum();
        System.out.println(splitArrayLargestSum.splitArray(new int[]{7,2,5,10,8}, 2));
    }
}
