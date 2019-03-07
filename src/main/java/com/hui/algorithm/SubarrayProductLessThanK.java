package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 8:39
 */
public class SubarrayProductLessThanK {


    /**
     *
     * 713. Subarray Product Less Than K
     * DescriptionHintsSubmissionsDiscussSolution
     * Your are given an array of positive integers nums.
     *
     * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
     *
     * Example 1:
     * Input: nums = [10, 5, 2, 6], k = 100
     * Output: 8
     * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
     * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     * Note:
     *
     * 0 < nums.length <= 50000.
     * 0 < nums[i] < 1000.
     * 0 <= k < 10^6.
     * Approach #2: Sliding Window [Accepted]
     * Intuition
     *
     * For each right, call opt(right) the smallest left so that the product of the subarray nums[left] * nums[left + 1] * ... * nums[right] is less than k. opt is a monotone increasing function, so we can use a sliding window.
     *
     * Algorithm
     *
     * Our loop invariant is that left is the smallest value so that the product in the window prod = nums[left] * nums[left + 1] * ... * nums[right] is less than k.
     *
     * For every right, we update left and prod to maintain this invariant. Then, the number of intervals with subarray product less than k and with right-most coordinate right, is right - left + 1. We'll count all of these for each value of right.
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <=1)return 0;
       int count = 0;
       long product = 1;
       int slow = 0;
       int fast = 0;
       while (fast < nums.length)
       {
           product = product*nums[fast];

           while ( slow <= fast && product >= k)
           {
               product /= nums[slow];
               slow++;
           }

           count += (fast - slow +1);
           fast++;

       }
       return count;
    }


    public static void main(String[] args) {
        SubarrayProductLessThanK subarrayProductLessThanK = new SubarrayProductLessThanK();
        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
    }
}
