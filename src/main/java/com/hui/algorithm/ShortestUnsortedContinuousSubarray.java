package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/1 11:09
 *
 * 581. Shortest Unsorted Continuous Subarray
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 1)return 0;
        int N = nums.length;
        int[] dpMax = new int[N]; // the maxValue from nums[0] to nums[i]
        int[] dpMin = new int[N];  // the min value from nums[N -1] to nums[i]
        dpMax[0] = nums[0];
        dpMin[N -1] = nums[N -1];
        for (int i = 1; i < N; i++) {
            dpMax[i] = dpMax[i -1] < nums[i] ? nums[i] : dpMax[ i -1];
            dpMin[N - 1 -i] = dpMin[N - i] > nums[N - 1 -i] ? nums[N - 1 -i]: dpMin[N  -i];
        }

        int left = 0;
        int right = N -1;
        while (left < right && dpMax[left] == dpMin[left])left++;
        while (right > left && dpMax[right] == dpMin[right])right--;
        if(left >= right)return 0;
        return right - left + 1;



    }

    public int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int N = nums.length;
        int dpMax = nums[0];  // the maxValue from nums[0] to nums[i]
        int dpMin = nums[N -1]; // the min value from nums[N -1] to nums[i]
        int left = 0;
        int right = -1;
        for (int i = 1; i < N ; i++) {
            dpMax = Math.max(dpMax, nums[i] );
            dpMin = Math.min(dpMin, nums[N - 1 - i]);

            // unsorted sub array
            if(nums[i] < dpMax)right = i;
            if(nums[N -1 - i] > dpMin) left = N - 1 - i;
        }
        return right - left + 1;
        
    }

    public static void main(String[] args) {

        ShortestUnsortedContinuousSubarray subarray = new ShortestUnsortedContinuousSubarray();
        System.out.println(subarray.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(subarray.findUnsortedSubarray(new int[]{2,6,4,8,10,9,6,15}));
        System.out.println(subarray.findUnsortedSubarray(new int[]{2,6,4,15}));
        System.out.println(subarray.findUnsortedSubarray(new int[]{2,6,15}));
        System.out.println("**************************");
        System.out.println(subarray.findUnsortedSubarray2(new int[]{2,6,4,8,10,9,15}));
        System.out.println(subarray.findUnsortedSubarray2(new int[]{2,6,4,8,10,9,6,15}));
        System.out.println(subarray.findUnsortedSubarray2(new int[]{2,6,4,15}));
        System.out.println(subarray.findUnsortedSubarray2(new int[]{2,6,15}));
    }
}
