package com.hui.SlidingWindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/15 20:43
 */
public class SlidingWindowMaximum {


    /**
     *
     * 239. Sliding Window Maximum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
     *
     * Follow up:
     * Could you solve it in linear time?
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <1)return nums;
        int len = nums.length;
        int[] results = new int[nums.length -k + 1];
        int idx = 0;
        int position = 0; // results' index
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k -1; i++) {
            maxHeap.offer(nums[i]);
        }
        while (idx <= len -k)
        {
            int start = idx;
            int end = start + k - 1;
            if(end <= len -1)
            {
                maxHeap.offer(nums[end]);
                results[position++] = maxHeap.peek();
                maxHeap.remove(nums[start]);
            }
            idx++;

        }
        return results;

    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1)return nums;
        int start = 0;
        int end = k - 1;
        int[] results = new int[nums.length -k + 1];  // return results.
        int maxVal = Integer.MIN_VALUE;  // the max val in the window k
        int maxIndex = -1;  // the max val's index in the window.
        while (end < nums.length)
        {
            // maxIndex out of the window.
            if(maxIndex < start)
            {
                // find the max val in the window.
                maxIndex = start;
                maxVal = nums[start];
                for (int i = start + 1; i <= end; i++) {
                    if(nums[i] > maxVal)
                    {
                        maxVal = nums[i];
                        maxIndex = i;
                    }
                }
            }

            results[start++] = maxVal;
            end++;
            if(end == nums.length)break;
            // incoming index is the maxIndex and incoming value is the max value in this window.
           if(nums[end] > maxVal)
           {
               maxIndex = end;
               maxVal = nums[end];
           }
        }

        return results;
    }



    public static void main(String[] args) {

        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] results = slidingWindowMaximum.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }
}
