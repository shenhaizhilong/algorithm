package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 9:43
 */
public class FindMinimuminRotatedSortedArray {


    /**
     *
     * 153. Find Minimum in Rotated Sorted Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * You may assume no duplicate exists in the array.
     *
     * Example 1:
     *
     * Input: [3,4,5,1,2]
     * Output: 1
     * Example 2:
     *
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if(nums.length == 1)return nums[0];
        if(nums.length == 2)return Math.min(nums[0],nums[1]);
        if(nums[nums.length -1] > nums[0])return nums[0];
        int low = 0;
        int high = nums.length -1;
        int middle = 0;
        while (low <= high)
        {
            middle = (low + high)>>>1;
            if(nums[middle] > nums[low])
            {
                low = middle +1;
            }else if(nums[middle] < nums[high])
            {
                high = middle - 1;
            }

            if(nums[middle] > nums[middle +1])
            {
                middle = middle + 1;
                break;
            }

            if(nums[middle] < nums[middle -1])
            {
                break;
            }

        }

        return nums[middle];

    }

    public static void main(String[] args) {

        System.out.println(findMin(new int[]{3,4,5,0,1,2}));
        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[]{0,1,2,3,4}));
        System.out.println(findMin(new int[]{2,5,6,0,0,1,2}));

    }
}
