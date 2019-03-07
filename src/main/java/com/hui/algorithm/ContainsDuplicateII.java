package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *https://leetcode.com/problems/contains-duplicate-ii/description/
 * 219. Contains Duplicate II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * @author: shenhaizhilong
 * @date: 2018/8/22 11:42
 */
public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null )return false;
        int end = k >= nums.length ? nums.length -1: k;
        int start;
        while (end < nums.length)
        {
            start = end - k;
            start = start < 0 ? 0: start;
            while (start < end)
            {
                if(nums[start] == nums[end])return true;
                start++;
            }
            end++;
        }
        return false;
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i]))
            {
                map.put(nums[i], i);
            }else {
                if(i - map.get(nums[i]) <= k)return true;
                map.put(nums[i], i);
            }
        }

        return false;

    }
    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        int[] b = {1,0, 1, 1};
        int[] c = {1,2,3,1,2,3};
        int[] d = {99,99};
        int[] e = {2,2};

//        System.out.println(containsNearbyDuplicate(a,3));
//        System.out.println(containsNearbyDuplicate(b,1));
//        System.out.println(containsNearbyDuplicate(c, 2));
        System.out.println(containsNearbyDuplicate(d,2));
        System.out.println(containsNearbyDuplicate(e,3));
    }
}
