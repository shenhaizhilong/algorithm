package com.hui.algorithm;

/**
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/21 8:58
 */
public class ArrayPairSum {

    /**
     *561. Array Partition I
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
     *
     * Example 1:
     * Input: [1,4,3,2]
     *
     * Output: 4
     * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
     * Note:
     * n is a positive integer, which is in the range of [1, 10000].
     * All the integers in the array will be in the range of [-10000, 10000].
     *
     *
     *
     * counting sort
     * @param nums
     * @return
     */
    public static int arrayPairSum(int[] nums) {
        int[] buckets = new int[20001];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] + 10000;
            buckets[index] +=1;
        }

        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i] == 0)continue;
            while (buckets[i] != 0)
            {
                if((index & 0x01) ==0)
                {
                    sum += i - 10000;
                }
                index++;
                buckets[i]--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {1,4,2,3};
        System.out.println(arrayPairSum(a));
    }
}
