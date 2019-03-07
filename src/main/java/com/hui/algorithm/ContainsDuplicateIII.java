package com.hui.algorithm;

/**
 *
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 * 220. Contains Duplicate III
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * @author: shenhaizhilong
 * @date: 2018/8/27 13:27
 */
public class ContainsDuplicateIII {
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 )return false;
         k = k > nums.length ? nums.length:k;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i -1; j >=0 ; j--) {
                if(i - j > k)break;
                if(i - j <= k  && Math.abs((long)nums[i] - nums[j]) <= t)return true;
            }
        }
        return false;
    }


    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if(nums==null || nums.length<2 || t<0 || k<1) return false;
        int slow = 0, fast = 1;
        while(slow < nums.length - 1 )
        {
           if(slow != fast && Math.abs((long)nums[slow] - nums[fast]) <= t)return true;
           if(fast - slow == k || fast == nums.length -1)
           {
               slow++;
               if(t != 0)fast = slow +1;
           }else fast++;

        }
        return false;

    }

    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        int[] b = {1,0, 1, 1};
        int[] c = {1,2,3,1,2,3};
        int[] d = {99,99};
        int[] e = {2,2};

        System.out.println(containsNearbyAlmostDuplicate(a,3, 0));
        System.out.println(containsNearbyAlmostDuplicate(b,1, 2));
        System.out.println(containsNearbyAlmostDuplicate(c, 2, 2));
        System.out.println(containsNearbyAlmostDuplicate(d,2, 1));
        System.out.println(containsNearbyAlmostDuplicate(e,3, 1));

        int[] f = {10,15, 18, 24};
        System.out.println(containsNearbyAlmostDuplicate(f,3, 3));
        int[] g = {-1,2147483647};
        System.out.println(containsNearbyAlmostDuplicate( g,1,2147483647));
    }
}
