package com.hui.Greedy;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/21 18:53
 */
public class MaximumGap {


    /**
     *164. Maximum Gap
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     *
     * Return 0 if the array contains less than 2 elements.
     *
     * Example 1:
     *
     * Input: [3,6,9,1]
     * Output: 3
     * Explanation: The sorted form of the array is [1,3,6,9], either
     *              (3,6) or (6,9) has the maximum difference 3.
     * Example 2:
     *
     * Input: [10]
     * Output: 0
     * Explanation: The array contains less than 2 elements, therefore return 0.
     * Note:
     *
     * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
     * Try to solve it in linear time/space.
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length <=1)return 0;
        Arrays.sort(nums);
        int maxGap = 0;
        int gap = 0;
        for (int i = 1; i < nums.length; i++) {
           gap = nums[i] - nums[i-1];
            if(gap > maxGap)
                maxGap = gap;
        }
        return maxGap;
    }
}
