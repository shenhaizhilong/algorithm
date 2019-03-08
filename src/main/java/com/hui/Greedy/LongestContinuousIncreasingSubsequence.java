package com.hui.Greedy;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/31 20:53
 */
public class LongestContinuousIncreasingSubsequence {

    /**
     *
     * 674. Longest Continuous Increasing Subsequence
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
     *
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 3
     * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
     * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
     * Example 2:
     * Input: [2,2,2,2,2]
     * Output: 1
     * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
     * Note: Length of the array will not exceed 10,000.
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        if(nums == null || nums.length == 0)return 0;
        int count = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>nums[i-1])
            {
                count++;
            }else {
               max = count > max ? count: max;
               count = 1;
            }
        }
        max = count > max ? count: max;
        return max;
    }

    public static void main(String[] args) {

        System.out.println(findLengthOfLCIS(new int[]{1,3,5,7}));
        System.out.println(findLengthOfLCIS(new int[]{1,3,5,4,7}));
        System.out.println(findLengthOfLCIS(new int[]{2,2,2,2,2,2}));
    }
}
