package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 23:34
 */
public class SingleNumberIII {


    /**
     *
     * 260. Single Number III
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
     *
     * Example:
     *
     * Input:  [1,2,1,3,2,5]
     * Output: [3,5]
     * Note:
     *
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
     *
     *
     * https://leetcode.com/problems/single-number-iii/discuss/68901/Sharing-explanation-of-the-solution/171943
     *
     * If you were stuck by this problem, it's easy to find a solution in the discussion. However, usually, the solution lacks some explanations.
     *
     * I'm sharing my understanding here:
     *
     * The two numbers that appear only once must differ at some bit, this is how we can distinguish between them. Otherwise, they will be one of the duplicate numbers.
     *
     * One important point is that by XORing all the numbers, we actually get the XOR of the two target numbers (because XORing two duplicate numbers always results in 0). Consider the XOR result of the two target numbers; if some bit of the XOR result is 1, it means that the two target numbers differ at that location.
     *
     * Let’s say the at the ith bit, the two desired numbers differ from each other. which means one number has bit i equaling: 0, the other number has bit i equaling 1.
     *
     * Thus, all the numbers can be partitioned into two groups according to their bits at location i.
     * the first group consists of all numbers whose bits at i is 0.
     * the second group consists of all numbers whose bits at i is 1.
     *
     * Notice that, if a duplicate number has bit i as 0, then, two copies of it will belong to the first group. Similarly, if a duplicate number has bit i as 1, then, two copies of it will belong to the second group.
     *
     * by XoRing all numbers in the first group, we can get the first number.
     * by XoRing all numbers in the second group, we can get the second number.
     *
     *
     *
     *  /**
     *      * Returns an {@code int} value with at most a single one-bit, in the
     *      * position of the lowest-order ("rightmost") one-bit in the specified
     *      * {@code int} value.  Returns zero if the specified value has no
     *      * one-bits in its two's complement binary representation, that is, if it
     *      * is equal to zero.
     *      *
     *      * @param i the value whose lowest one bit is to be computed
     *      * @return an {@code int} value with a single one-bit, in the position
     *      *     of the lowest-order one-bit in the specified value, or zero if
     *      *     the specified value is itself equal to zero.
     *      * @since 1.5
     *
     *
     *         public static int lowestOneBit(int i) {
     *         // HD, Section 2-1
     *           return i & -i;
     *        }
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {

        int[] res = new int[2];
        int a = 0;
        for(int i: nums)
        {
            a = a^i;
        }

        // find the lowest one bit
        a = a& (-a);
        for (int i:nums)
        {
            if((i & a) > 0)
            {
                res[0] ^= i;
            }else {
                res[1] ^= i;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,1,3,2,5};

        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}
