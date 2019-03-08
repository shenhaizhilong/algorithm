package com.hui.DynamicPrograming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *https://leetcode.com/problems/degree-of-an-array/description/
 *
 * 697. Degree of an Array
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * @author: shenhaizhilong
 * @date: 2018/8/22 22:44
 */
public class DegreeOfAnArray {
    public static int findShortestSubArray(int[] nums) {
        Map<Integer,int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i]))
            {
                int[] value = {1,i,i};  // // the first element in array is degree, second is first index of this key, third is last index of this key
                map.put(nums[i], value);
            }else {
                int[] tempV = map.get(nums[i]);
                tempV[0] +=1;
                tempV[2] = i;
            }
        }

        int minLen = Integer.MAX_VALUE;
        int degree = Integer.MIN_VALUE;
        for (int[] t :
                map.values()) {
            int currentLen = t[2] - t[1] +1;
            if (t[0] > degree)
            {
                degree = t[0];
                minLen = currentLen;
            }else if(t[0] == degree)
            {
                minLen = Math.min(minLen, currentLen);
            }
        }

        return minLen;
    }


    /**
     * 速度快，缺点是占用太多的内存，尤其是nums.len 较小的时候
     * @param nums
     * @return
     */
    public static int findShortestSubArray2(int[] nums) {
        int[] freqs = new int[50000];
        int[] left = new int[50000];
        int[] right = new int[50000];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        int max = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int freq = ++freqs[nums[i]];
            if (left[nums[i]] == -1) left[nums[i]] = i;
            right[nums[i]] = i;
            if (freq > max) {
                max = freq;
                res = right[nums[i]] - left[nums[i]] + 1;
            }
            else if (freq == max) {
                res = Math.min(res, right[nums[i]] - left[nums[i]] + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 2, 3, 1};
        int[] nums2= {1,2,2,3,1,4,2};
        System.out.println(findShortestSubArray(nums));
        System.out.println(findShortestSubArray(nums2));
    }
}
