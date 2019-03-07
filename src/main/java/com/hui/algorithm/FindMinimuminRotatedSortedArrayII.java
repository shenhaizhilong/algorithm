package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/15 10:19
 */
public class FindMinimuminRotatedSortedArrayII {


    /**
     *
     * 154. Find Minimum in Rotated Sorted Array II
     * DescriptionHintsSubmissionsDiscussSolution
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * The array may contain duplicates.
     *
     * Example 1:
     *
     * Input: [1,3,5]
     * Output: 1
     * Example 2:
     *
     * Input: [2,2,2,0,1]
     * Output: 0
     * Note:
     *
     * This is a follow up problem to Find Minimum in Rotated Sorted Array.
     * Would allow duplicates affect the run-time complexity? How and why?
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if(nums.length == 1)return nums[0];
        if(nums.length == 2)return Math.min(nums[0],nums[1]);
        if(nums[nums.length -1] > nums[0])return nums[0];
        int low = 0;
        int high = nums.length -1;
        int middle = 0;
        while (low < high)
        {
            middle = (low + high) >>>1;
            if(nums[middle] > nums[high])
            {
                low = middle + 1;
            }else if(nums[middle] < nums[high])
            {
                high = middle;
            }else {  // nums[middle] == nums[high]
                if(nums[low] == nums[middle])
                {
                    low++;
                    high--;
                }else {
                    high = middle;
                }
            }


        }

        return nums[high];
    }

    public static void main(String[] args) {
        FindMinimuminRotatedSortedArrayII findMinimuminRotatedSortedArrayII = new FindMinimuminRotatedSortedArrayII();
        System.out.println(findMinimuminRotatedSortedArrayII.findMin(new int[]{10,1,10,10,10}));
    }
}
