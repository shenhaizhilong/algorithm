package com.hui.DynamicPrograming;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/8 9:13
 */
public class LongestIncreasingSubsequence {


    /**
     *
     * 300. Longest Increasing Subsequence
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * Example:
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * Note:
     *
     * There may be more than one LIS combination, it is only necessary for you to return the length.
     * Your algorithm should run in O(n2) complexity.
     * Follow up: Could you improve it to O(n log n) time complexity?
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums == null )return 0;
        if(nums.length <2)return nums.length;


        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxlen = 1;
        for (int i = 1; i < nums.length ; i++) {
            int maxValRange = 0;
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                    maxValRange = Math.max(maxValRange, dp[j]);
            }
            dp[i] = ++maxValRange;
            maxlen = Math.max(maxlen, maxValRange);
        }
      //  System.out.println(Arrays.toString(dp));
        return maxlen;

    }

    /**
     *
     * https://leetcode.com/problems/longest-increasing-subsequence/solution/
     *https://en.wikipedia.org/wiki/Longest_increasing_subsequence#Efficient_algorithm
     *
     * @param nums
     * @return
     */

    public int lengthOfLIS2(int[] nums) {
        if(nums == null)return 0;
        int length = nums.length;
        if(length < 2)return length;
        //This dp array is meant to store the increasing sub-sequence formed by including the currently encountered element.
        // the dp array is storing increasing sub sequence
        int[] dp = new int[length];
        int maxLen = 0;
        for(int n: nums)
        {
            int idx = binarySearch(dp, maxLen, n);
            dp[idx] = n;
            if(idx == maxLen)
            {
                maxLen++;
            }
        }

        return maxLen;

    }

    private int binarySearch(int[] arr, int len, int target) {
        int start = 0, end = len - 1;
        while (start <= end) {
            int mid = (start + end)>>>1;
            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                return mid;  // target found
            }
        }
        return start;   // target not found, insertion position
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(arr));
        System.out.println(longestIncreasingSubsequence.lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(longestIncreasingSubsequence.lengthOfLIS2(new int[]{0,8,4,12,2}));
    }

}
