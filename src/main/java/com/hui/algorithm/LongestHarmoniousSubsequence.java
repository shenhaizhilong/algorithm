package com.hui.algorithm;

import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/2 21:08
 */
public class LongestHarmoniousSubsequence {

    /**
     *
     * 594. Longest Harmonious Subsequence
     * DescriptionHintsSubmissionsDiscussSolution
     * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
     *
     * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
     *
     * Example 1:
     * Input: [1,3,2,2,5,2,3,7]
     * Output: 5
     * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
     * Note: The length of the input array will not exceed 20,000.
     *
     * forget to use containsKey method
     * @param nums
     * @return
     */
    public static int findLHS(int[] nums) {
        if(nums == null || nums.length <2)return 0;
        int ans = 0;
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for(int i:nums){
            map.put(i, map.getOrDefault(i,0) + 1);
        }
        for(int k:map.keySet())
        {
            int v1 = map.get(k);
            int v2 = map.getOrDefault(k + 1, 0);
            if(v2 != 0)
            {
                ans = Math.max(v1 + v2, ans);
            }
        }

        return ans;
    }


    /**
     * 官网解
     * @param nums
     * @return
     */
    public static int findLHS2(int[] nums) {
        HashMap < Integer, Integer > map = new HashMap < > ();
        int res = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key: map.keySet()) {
            if (map.containsKey(key + 1))
                res = Math.max(res, map.get(key) + map.get(key + 1));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findLHS(new int[]{1,3,2,2,5,2,3,7}));
        System.out.println(findLHS(new int[]{1,1,1,1}));
        System.out.println(findLHS(new int[]{-1,0,-1,0,-1,0,-1}));
    }
}
