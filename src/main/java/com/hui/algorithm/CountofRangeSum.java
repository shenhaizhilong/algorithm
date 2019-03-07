package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/6 22:11
 *
 *327. Count of Range Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *
 * Example:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2,
 * Output: 3
 * Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 *
 *
 */
public class CountofRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0)return 0;
        int[] prefixSum = new int[nums.length +1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i +1] = prefixSum[i] + nums[i];
        }

        int ans = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            for (int j = 0; j < i; j++) {
                int range = prefixSum[i] - prefixSum[j];
                if(range >= lower && range <= upper)
                {
                    ans++;
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {

        CountofRangeSum countofRangeSum = new CountofRangeSum();
        System.out.println(countofRangeSum.countRangeSum(new int[]{-2,5,-1},-2,2));
    }

}
