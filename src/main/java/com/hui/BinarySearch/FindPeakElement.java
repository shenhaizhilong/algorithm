package com.hui.BinarySearch;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 21:20
 */
public class FindPeakElement {


    /**
     *
     * 162. Find Peak Element
     * DescriptionHintsSubmissionsDiscussSolution
     * A peak element is an element that is greater than its neighbors.
     *
     * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
     *
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     *
     * You may imagine that nums[-1] = nums[n] = -∞.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index number 2.
     * Example 2:
     *
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 1 or 5
     * Explanation: Your function can return either index number 1 where the peak element is 2,
     *              or index number 5 where the peak element is 6.
     * Note:
     *
     * Your solution should be in logarithmic complexity.
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i+1])return i;
        }
        return nums.length -1;
    }

    public static int findPeakElement2(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }
    public static int find(int[] data, int l, int r) {

        while (l < r)
        {
            int m = (l + r)>>1;
            if(data[m] > data[m +1])
            {
                r = m;
            }else {
                l = m +1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        int[]  nums = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement2(nums));
    }

}
