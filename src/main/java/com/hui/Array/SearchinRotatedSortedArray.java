package com.hui.Array;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 10:03
 */
public class SearchinRotatedSortedArray {


    /**
     *
     * 33. Search in Rotated Sorted Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     *
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     *
     * You may assume no duplicate exists in the array.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * Example 1:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     *
     * @param nums
     * @param target
     * @return
     */
    public  int search(int[] nums, int target) {

        if(nums == null || nums.length == 0)return -1;

        int len = nums.length;
        // find the index of smallest value.
        int low = 0;
        int high =  len -1;
        while (low < high)
        {
            int m = (low + high)>>>1;
            if(nums[m] > nums[high]){
                low = m + 1;
            }else {
                high = m;
            }
        }

        if(target > nums[len -1]){
            return binarySearchRange(nums,0,low -1, target);
        }else return binarySearchRange(nums,low, len -1, target);


    }

    private int binarySearchRange(int[] nums, int start, int end, int target)
    {
        while (start <= end)
        {
            int m = (start + end)>>>1;
            if(nums[m] < target)
            {
                start = m + 1;
            }else if(nums[m] > target)
            {
                end = m -1;
            }else{
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

       // System.out.println(search(new int[]{4,5,6,0,1,2},0));
        SearchinRotatedSortedArray searchinRotatedSortedArray = new SearchinRotatedSortedArray();
//
//        System.out.println(searchinRotatedSortedArray.search(new int[]{4,5,6,0,1,2},0));
//        System.out.println(searchinRotatedSortedArray.search(new int[]{4,5,6,0,1,2},3));
        System.out.println(searchinRotatedSortedArray.binarySearchRange(new int[]{1,3},0,1,3));
        System.out.println(searchinRotatedSortedArray.search(new int[]{1,3},3));
    }
}
