package com.hui.algorithm;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 *https://leetcode.com/problems/intersection-of-two-arrays/description/
 * 349. Intersection of Two Arrays
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 * @author: shenhaizhilong
 * @date: 2018/8/18 20:08
 */
public class IntersectionofTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null)return null;
        if(nums1.length == 0)return nums1;
        if(nums2.length == 0)return nums2;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i]))set2.add(nums2[i]);
        }

        int[] res = new int[set2.size()];
        int i = 0;
        for (Integer item :
                set2) {
            res[i] = item.intValue();
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
       int[] nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }
}
