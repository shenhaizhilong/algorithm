package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 18:48
 */
public class LargestNumberAtLeastTwiceofOthers {

    /**
     *747. Largest Number At Least Twice of Others
     * DescriptionHintsSubmissionsDiscussSolution
     * In a given integer array nums, there is always exactly one largest element.
     *
     * Find whether the largest element in the array is at least twice as much as every other number in the array.
     *
     * If it is, return the index of the largest element, otherwise return -1.
     *
     * Example 1:
     *
     * Input: nums = [3, 6, 1, 0]
     * Output: 1
     * Explanation: 6 is the largest integer, and for every other number in the array x,
     * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
     *
     *
     * Example 2:
     *
     * Input: nums = [1, 2, 3, 4]
     * Output: -1
     * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
     *
     *
     * Note:
     *
     * nums will have a length in the range [1, 50].
     * Every nums[i] will be an integer in the range [0, 99].
     *
     * @param nums
     * @return
     */
    public static int dominantIndex(int[] nums) {
        if(nums == null || nums.length == 0 )return -1;
        if(nums.length == 1)return 0;
        int[] hash = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            hash[nums[i]] = i + 1;
        }

        int res = -1;
        int firstLarge = Integer.MAX_VALUE;
        int secondLarge = Integer.MAX_VALUE;
        for (int i = hash.length -1; i >=0 ; i--) {
            if(hash[i] != 0)
            {
                if(firstLarge == Integer.MAX_VALUE)
                {
                    firstLarge = i;
                    res = hash[i] -1;
                }else {
                    secondLarge = i;
                    break;
                }
            }
        }

       if(firstLarge/2 >= secondLarge)
       {
           return res;
       }
       return -1;
    }

    public static int dominantIndex2(int[] nums)
    {
        int indexOfMax = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[indexOfMax])
            {
                indexOfMax = i;
            }
        }

        int k = nums[indexOfMax]/2;
        for (int i = 0; i < nums.length; i++) {
            if(i != indexOfMax && k< nums[i])
                return -1;
        }
        return indexOfMax;
    }

    public static void main(String[] args) {
        System.out.println(dominantIndex2(new int[]{1,2,3,4}));
        System.out.println(dominantIndex2(new int[]{3,6,1,0}));
    }

}
