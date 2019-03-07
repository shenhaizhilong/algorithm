package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/8 17:36
 */
public class IncreasingTripletSubsequence {


    /**
     *334. Increasing Triplet Subsequence
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
     *
     * Formally the function should:
     *
     * Return true if there exists i, j, k
     * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
     * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5]
     * Output: true
     * Example 2:
     *
     * Input: [5,4,3,2,1]
     * Output: false
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {


        /**
         *
         * in this problem we just need to determine whether the subsequence exists.
         * so after assigning the value to min and secondMin,
         * even though there might be a smaller value afterward and the variable min gets updated,
         * it does not affect the increasing subsequence overall as long as there is an integer that is larger than 'secondMin'
         *
         */
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for(int n: nums)
        {
            if(n <= first)   // first is the min value ending in n;
            {
                first = n;
            }else if(n <= second)   // only update second when there is a  x,  x > first and x <= second
            {
                second = n;
            }else return true;         //  return when x is bigger than first and second.
        }

        return false;
    }
}
