package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/18 14:53
 */
public class MinMoves2 {

    /**
     *
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
     * 462. Minimum Moves to Equal Array Elements II
     * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
     *
     * You may assume the array's length is at most 10,000.
     *
     * Example:
     *
     * Input:
     * [1,2,3]
     *
     * Output:
     * 2
     *
     * Explanation:
     * Only two moves are needed (remember each move increments or decrements one element):
     *
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     *
     */

    public int minMoves2(int[] nums) {
        if ( nums == null || nums.length <= 1) return 0;


        Arrays.sort(nums);

        int lMedian = (nums.length / 2) - 1;
        int rMedian = (nums.length /2);
        int lSum = 0;
        int rSum = 0;


        for (int i = 0; i < nums.length; i++){
            lSum += (Math.abs(nums[lMedian] - nums[i]));
            rSum += (Math.abs(nums[rMedian] - nums[i]));
        }

        if ((nums.length & 0x01) == 1) lSum = rSum;


        return Math.min(lSum, rSum);
    }
}
