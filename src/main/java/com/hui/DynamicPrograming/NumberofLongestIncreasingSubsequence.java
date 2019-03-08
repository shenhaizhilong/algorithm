package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/8 13:53
 */
public class NumberofLongestIncreasingSubsequence {


    /**
     *673. Number of Longest Increasing Subsequence
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array of integers, find the number of longest increasing subsequence.
     *
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 2
     * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
     * Example 2:
     * Input: [2,2,2,2,2]
     * Output: 5
     * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
     * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {

        if(nums == null) return 0;
        if(nums.length < 2)return nums.length;
        int len = nums.length;
        int[] dp = new int[len];  // dp[i] = length of longest sub sequence ending in nums[i];
        int[] count = new int[len]; // count[i] = number of longest sub sequence ending in nums[i];
        int maxLen = 1;
        for (int i = 0; i < len ; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                {
                    if(dp[j] >= dp[i])
                    {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[j] + 1 == dp[i])
                    {
                        count[i] += count[j];
                    }
                }

            }
            maxLen = Math.max(maxLen, dp[i]);   // find the max len

        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            if(maxLen == dp[i])
                res += count[i];
        }

        return res;
    }





    public static void main(String[] args) {

        int[] arr = {2,2,2,2,2};
        NumberofLongestIncreasingSubsequence numberofLongestIncreasingSubsequence = new NumberofLongestIncreasingSubsequence();
        System.out.println(numberofLongestIncreasingSubsequence.findNumberOfLIS(arr));
       System.out.println(numberofLongestIncreasingSubsequence.findNumberOfLIS(new int[]{1,3,5,4,7}));

    }
}
