package com.hui.algorithm;

/**
 * https://leetcode.com/problems/search-insert-position/description/
 * Search Insert Position
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 * @author: shenhaizhilong
 * @date: 2018/8/15 8:26
 */
public class BinarySearch {
    public static int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length -1;
        int middle = 0;
        while (start < end)
        {
            middle = (start + end)>>1;
            if(nums[middle] == target)return middle;
            if(nums[middle] < target)
            {
                start = middle + 1;
            }else {
                end = middle -1;
            }

        }

        //target 在右侧
        if(nums[start] < target)
        {
            return start +1;
        }
        return start;

    }


    /**
     *
     * https://leetcode.com/problems/binary-search/description/
     *
     * 704. Binary Search
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
     *
     *
     * Example 1:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     *
     * Example 2:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     *
     *
     * Note:
     *
     * You may assume that all elements in nums are unique.
     * n will be in the range [1, 10000].
     * The value of each element in nums will be in the range [-9999, 9999].
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int index = -1;
        int start = 0;
        int end = nums.length -1;
        while (start <= end)
        {
            int middle = (start + end)>>>1;
            if(nums[middle] < target)
            {
                start = middle +1;
            }else if(nums[middle] > target)
            {
                end = middle -1;
            }else {
                index = middle;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
//        int[] a = {1,3,5,6};
//        int[] b = {5,2,7,0,1,3,6};
//        for (int i :
//                b) {
//            System.out.println(searchInsert(a,i));
//        }

        int[] nums = {-1};
        int target = 9;
        System.out.println(search(nums,target));
        System.out.println(search(nums,-1));
    }
}
