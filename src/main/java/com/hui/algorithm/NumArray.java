package com.hui.algorithm;

/**
 *
 *
 * 303. Range Sum Query - Immutable
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/29 22:39
 */
public class NumArray {

    int[] PreSum;
    public NumArray(int[] nums) {
        preSum(nums);
    }

    public int sumRange(int i, int j) {
        return PreSum[j +1] - PreSum[i];
    }

    // compute prefix sum
    private void preSum(int[] nums)
    {
        PreSum = new int[nums.length +1];
        PreSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            PreSum[i +1] = PreSum[i] + nums[i];
        }
    }

    public static void main(String[] args) {
        int[]  nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0,0));
        System.out.println(numArray.sumRange(0,1));
        System.out.println(numArray.sumRange(0,2));
        System.out.println(numArray.sumRange(0,3));
        System.out.println(numArray.sumRange(0,4));
        System.out.println(numArray.sumRange(0,5));
        System.out.println(numArray.sumRange(2,5));
    }
}
