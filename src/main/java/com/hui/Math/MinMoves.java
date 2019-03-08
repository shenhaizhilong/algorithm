package com.hui.Math;

/**
 *
 *https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/
 *453. Minimum Moves to Equal Array Elements
 *
 *Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 *
 * Example:
 *
 * Input:
 * [1,2,3]
 *
 * Output:
 * 3
 *
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * it's a mathmatic issue, assume the final value is Target, and the minimal value of the given array is MIN, SUM is the sum of all value of the given array, then we can know, the following equation :
 * (Target - MIN) * (n - 1) + sum = Target * n , then Target = SUM - MIN * n + MIN, so the minimal moves should be: Target - MIN = SUM - MIN * n
 *
 * Target - min = c = Sum - min*n;
 *
 * @author: shenhaizhilong
 * @date: 2018/8/18 14:29
 */
public class MinMoves {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(min > nums[i])min = nums[i];
            sum += nums[i];
        }
        return sum - min*nums.length;
    }

}
