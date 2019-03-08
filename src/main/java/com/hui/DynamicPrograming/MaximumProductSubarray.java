package com.hui.DynamicPrograming;

/**
 *
 *
 * 152. Maximum Product Subarray
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * https://leetcode.com/problems/maximum-product-subarray/discuss/118535/C%2B%2B-DP-Solution-using-2-Arrays/174661
 *https://leetcode.com/problems/maximum-product-subarray/discuss/48261/Share-my-DP-code-that-got-AC/48395
 * @author: shenhaizhilong
 * @date: 2018/9/11 17:51
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if(nums.length == 1)return nums[0];
        //dpMax means maximum product that can be achieved ending with i
        int[] dpMax = new int[nums.length];
        dpMax[0] = nums[0];
        //dpMin means minimum product that can be achieved ending with i
        int[] dpMin = new int[nums.length];
        dpMin[0] = nums[0];

        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(dpMax[i -1]*nums[i], dpMin[i -1]*nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMax[i -1]*nums[i], dpMin[i -1]*nums[i]));
            max = Math.max(dpMax[i], max);
        }
        return max;

    }

    public int maxProduct2(int[] nums) {
        if(nums.length == 1)return nums[0];

        int dpMax = nums[0];
        int dpMin = nums[0];

        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int k = dpMax*nums[i];
            int m = dpMin*nums[i];
            //    max/min product for the current number is either the current number itself
            //     or the max/min by the previous number times the current one
            dpMax = Math.max(nums[i], Math.max(k, m));
            dpMin = Math.min(nums[i], Math.min(k, m));

            max = Math.max(dpMax, max);
        }
        return max;

    }

    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        System.out.println(maximumProductSubarray.maxProduct2(new int[]{2,3,-2,4}));
        System.out.println(maximumProductSubarray.maxProduct2(new int[]{-2,0,-1}));
        System.out.println(maximumProductSubarray.maxProduct2(new int[]{-1,2,-2,-3,5,6}));
    }
}
