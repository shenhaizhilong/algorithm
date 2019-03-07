package com.hui.algorithm;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/5 8:45
 */
public class FindDuplicates {


    /**
     *
     * 442. Find All Duplicates in an Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     *
     * Find all the elements that appear twice in this array.
     *
     * Could you do it without extra space and in O(n) runtime?
     *
     * Example:
     * Input:
     * [4,3,2,7,8,2,3,1]
     *
     * Output:
     * [2,3]
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {

        List<Integer> res = new ArrayList<>();
        BitSet bitSet = new BitSet(32);
        for(int i: nums)
        {
            if(bitSet.get(i) == true)
            {
                res.add(i);
            }
            bitSet.set(i);
        }
        return res;
    }

    /**
     *
     *   // when we find a number i, flip the number at position i-1 to negative.
     *     // if the number at position i-1 is already negative, i is the number that occurs twice.
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i: nums)
        {
            int index = Math.abs(i) -1;
            if(nums[index] < 0)
            {
                res.add(Math.abs(i));
            }else {
                nums[index] = -nums[index];
            }
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(findDuplicates2(new int[]{4,3,2,3,2,1}));
    }
}
