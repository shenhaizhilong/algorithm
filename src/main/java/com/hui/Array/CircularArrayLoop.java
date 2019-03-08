package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/9 10:35
 */
public class CircularArrayLoop {


    /**
     *457. Circular Array Loop
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.
     *
     * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
     *
     * Example 2: Given the array [-1, 2], there is no loop.
     *
     * Note: The given array is guaranteed to contain no element "0".
     *
     * Can you do it in O(n) time complexity and O(1) space complexity?
     *
     * @param nums
     * @return
     */
    int len;
    public boolean circularArrayLoop(int[] nums) {
        if(nums == null || nums.length <= 1)return false;
        len = nums.length;
        for (int i = 0; i < len; i++) {
            if(nums[i] == 0)continue;

            int slow = i;
            int fast = getIndex(nums, i);
            int signI = Integer.signum(nums[i]);
            // moving forward or backwards - either both negative or positive.
            while (Integer.signum(nums[fast])*signI > 0 && Integer.signum(nums[getIndex(nums,fast)])*signI > 0)
            {


            }
        }
        return false;
    }

    private int getIndex(int[] nums, int i)
    {
        return (i + nums[i]%len + len) %len;
    }
    public static void main(String[] args) {

    }
}
