package com.hui.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 *https://leetcode.com/problems/contains-duplicate/description/
 * 217. Contains Duplicate
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * @author: shenhaizhilong
 * @date: 2018/6/15 13:59
 */
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {

        if(nums.length <2)return false;
        Set<Integer> set = new HashSet<Integer>();
        for (Integer i :
                nums) {
            if(set.contains(i))
            {
                return true;
            }
            set.add(i);
        }

        return false;
    }


    public static boolean containsDuplicate2(int[] nums) {


       return Arrays.stream(nums).distinct().count() < nums.length;
    }

    public static boolean containsDuplicate3(int[] nums) {

        if(nums.length <2)return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1])
                return true;
        }

        return false;
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        System.out.println(containsDuplicate(a));
        System.out.println(containsDuplicate2(a));
        System.out.println(containsDuplicate3(a));

    }
}
