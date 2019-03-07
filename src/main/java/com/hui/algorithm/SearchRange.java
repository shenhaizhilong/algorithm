package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 15:59
 */
public class SearchRange {


    /**
     *34. Find First and Last Position of Element in Sorted Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     *
     *https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        int leftMost = binarySearch(nums,target, true);
        if(leftMost == nums.length || nums[leftMost] != target)
            return res;
        int rightMost = binarySearch(nums,target, false);
        if(nums[rightMost] != target)rightMost--;
        res[0] = leftMost;
        res[1] = rightMost;
        return res;

    }

    private int binarySearch(int[] nums, int target, boolean isLeft)
    {
        int l = 0;
        int h = nums.length -1;
        while (l < h)
        {
            int m = (l + h)>>>1;
            if(nums[m] > target || (isLeft && target == nums[m]))
            {
                h = m;
            }else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {


        int[] nums = {1,2,5,9};
        SearchRange searchRange = new SearchRange();
        System.out.println(searchRange.binarySearch(nums,5,false));
    }
}
