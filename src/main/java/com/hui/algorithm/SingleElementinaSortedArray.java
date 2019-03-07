package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/14 0:41
 */
public class SingleElementinaSortedArray {


    /**
     *
     * 540. Single Element in a Sorted Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.
     *
     * Example 1:
     * Input: [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * Example 2:
     * Input: [3,3,7,7,10,11,11]
     * Output: 10
     * Note: Your solution should run in O(log n) time and O(1) space.
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1)return nums[0];
        int low = 0 ;
        int  high = nums.length -1;
        int middle;
        while (low < high)
        {
            // single element can't be a the odd index, if it's odd index, we decrease the middle.so the middle is even.
            middle = (low + high) >>>1;
            if((middle & 0x01) == 1)middle--;
            if(nums[middle] != nums[middle +1]) high = middle;  //if nums[middle] == nums[middle +1], so the single element must bi not on the right.
            else low = middle + 2;  // if nums[middle] == nums[middle +1], middle is even, so the single element must not be on the left.

        }

        return nums[low];

    }


    public int singleNonDuplicate2(int[] nums) {
        int i = 0;
        for(;i<nums.length-1;i++) {
            if(nums[i] != nums[i+1]) return nums[i];
            i++;
        }
        return nums[i];
    }
    public static void main(String[] args) {

        SingleElementinaSortedArray singleElementinaSortedArray = new SingleElementinaSortedArray();
        //System.out.println(singleElementinaSortedArray.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
        System.out.println(singleElementinaSortedArray.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }
}
