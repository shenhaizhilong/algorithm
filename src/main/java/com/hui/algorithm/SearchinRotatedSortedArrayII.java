package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/6 11:56
 */
public class SearchinRotatedSortedArrayII {


    /**
     *
     * 81. Search in Rotated Sorted Array II
     * DescriptionHintsSubmissionsDiscussSolution
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
     *
     * You are given a target value to search. If found in the array return true, otherwise return false.
     *
     * Example 1:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     * Example 2:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 3
     * Output: false
     * Follow up:
     *
     * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
     * Would this affect the run-time complexity? How and why?
     *
     *
     *
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/28218/My-8ms-C++-solution-(o(logn)-on-average-o(n)-worst-case)
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0)return false;

        int len = nums.length;
        int low = 0;
        int high =  len -1;
        int m = -1;
        while (low <= high)
        {
             m = (low + high)>>>1;
            if(nums[m] == target)return true;

            if(nums[low] == nums[m] && nums[high] == nums[m]){
                low++;
                high--;
            }else if(nums[m] >= nums[low])
            {
                if((nums[low] <= target) && (nums[m] > target))
                {
                    high = m -1;
                }else {
                    low = m + 1;
                }
            }else {
                if((nums[m] < target) && (nums[high] >=target))
                {
                    low = m + 1;
                }else {
                    high = m -1;
                }
            }


        }
        return false;

    }



    public static void main(String[] args) {
        SearchinRotatedSortedArrayII searchinRotatedSortedArrayII = new SearchinRotatedSortedArrayII();
//        System.out.println(searchinRotatedSortedArrayII.search(new int[]{2,5,6,0,0,1,2},0));
//        System.out.println(searchinRotatedSortedArrayII.search(new int[]{2,5,6,0,0,1,2}, 3));
        System.out.println(searchinRotatedSortedArrayII.search(new int[]{1,1,3,1}, 3));
    }
}
