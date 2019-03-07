package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/8 8:22
 */
public class ContiguousArray {


    /**
     *525. Contiguous Array
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     *
     * Example 1:
     * Input: [0,1]
     * Output: 2
     * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
     * Example 2:
     * Input: [0,1,0]
     * Output: 2
     * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
     * Note: The length of the given binary array will not exceed 50,000.
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length == 0)return 0;

        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, -1);
        int preSum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1: nums[i];
            if(preSumMap.containsKey(preSum))
            {
                max = Math.max(max, i - preSumMap.get(preSum));
            }else {
                preSumMap.put(preSum, i);
            }
        }

        return max;
    }

    public int findMaxLength2(int[] nums) {
        if(nums == null || nums.length == 0)return 0;

        int length = nums.length;
        int[] preSumMap = new  int[2*length +1]; //[-n,n]
        int preSum = 0;
        int max = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum += nums[i -1] == 0 ? -1: 1;
            int idx = preSum + length;
            if(preSumMap[idx] == 0 && preSum !=0)  // don't contains key idx
            {
                preSumMap[idx] = i;
            }else {
                max = Math.max(max, i - preSumMap[idx]);
            }

        }

        return max;
    }
    public static void main(String[] args) {

        int[] arr = {1,1,0,0};
        ContiguousArray contiguousArray = new ContiguousArray();
        System.out.println(contiguousArray.findMaxLength(arr));
        System.out.println(contiguousArray.findMaxLength2(arr));
    }
}
