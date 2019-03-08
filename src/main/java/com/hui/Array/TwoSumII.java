package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/1 8:36
 */
public class TwoSumII {


    /**
     *
     * 167. Two Sum II - Input array is sorted
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     *
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
     *
     * Note:
     *
     * Your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     * Example:
     *
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i <= numbers.length -2; i++) {
            int index = binarySearch(numbers,i + 1,numbers.length -1, target - numbers[i]);
            if(index > 0)
            {
                res[0] = i + 1;
                res[1] = index;
                break;
            }
        }
        return res;

    }

    private static int binarySearch(int[] numbers, int start, int end, int target)
    {
        int res = -1;

        while (start <= end)
        {
            int middle = (start + end) >>>1;
            if(numbers[middle] > target)
            {
                end = middle -1;
            }else if(numbers[middle] < target)
            {
                start = middle + 1;
            }else {
                res = middle + 1;
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = {-1,0};
        //System.out.println(binarySearch(nums, 1, 3, 7));
        System.out.println(Arrays.toString(twoSum(nums, -1)) );
    }
}
