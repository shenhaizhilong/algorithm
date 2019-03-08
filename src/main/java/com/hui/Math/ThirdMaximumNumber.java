package com.hui.Math;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/24 9:38
 */
public class ThirdMaximumNumber {
    public static int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int firstMax = Integer.MIN_VALUE,secondMax = Integer.MIN_VALUE,thirdMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            if(nums[i]> firstMax && nums[i] > secondMax && nums[i]> thirdMax)
            {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            }else if(nums[i]< firstMax && nums[i]> secondMax && nums[i] > thirdMax)
            {
                thirdMax = secondMax;
                secondMax = nums[i];
            }else if(nums[i] < firstMax && nums[i] < secondMax && nums[i] > thirdMax)
            {
                thirdMax = nums[i];
            }
        }

        if(set.size() <3)return firstMax;
        return thirdMax;
    }


    /**
     *https://leetcode.com/problems/third-maximum-number/description/
     * 414. Third Maximum Number
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
     *
     * Example 1:
     * Input: [3, 2, 1]
     *
     * Output: 1
     *
     * Explanation: The third maximum is 1.
     * Example 2:
     * Input: [1, 2]
     *
     * Output: 2
     *
     * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
     * Example 3:
     * Input: [2, 2, 3, 1]
     *
     * Output: 1
     *
     * Explanation: Note that the third maximum here means the third maximum distinct number.
     * Both numbers with value 2 are both considered as second maximum.
     * @param nums
     * @return
     */
    public static int thirdMax2(int[] nums) {

        long firstMax = Long.MIN_VALUE,secondMax = Long.MIN_VALUE,thirdMax = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]> firstMax)
            {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            }else if(nums[i]> secondMax && nums[i] < firstMax)
            {
                thirdMax = secondMax;
                secondMax = nums[i];
            }else if( nums[i] > thirdMax && nums[i] < secondMax)
            {
                thirdMax = nums[i];
            }
        }
        return (int)(thirdMax == Long.MIN_VALUE ? firstMax: thirdMax);
    }


    public static void main(String[] args) {
//        int[] a = {3,2,1};
//        System.out.println(thirdMax(a));
//        System.out.println(thirdMax(new int[]{1,2}));
        System.out.println(thirdMax2(new int[]{1,1,3,4,5,9,10}));
    }
}
