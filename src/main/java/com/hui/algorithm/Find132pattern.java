package com.hui.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/14 12:28
 */
public class Find132pattern {


    /**
     *456. 132 Pattern
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
     *
     * Note: n will be less than 15,000.
     *
     * Example 1:
     * Input: [1, 2, 3, 4]
     *
     * Output: False
     *
     * Explanation: There is no 132 pattern in the sequence.
     * Example 2:
     * Input: [3, 1, 4, 2]
     *
     * Output: True
     *
     * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
     * Example 3:
     * Input: [-1, 3, 2, 0]
     *
     * Output: True
     *
     * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3)return false;
        for (int i = 0; i <= nums.length -3; i++) {
            for (int j = i +1; j <= nums.length -2; j++) {
                for (int k = j +1; k < nums.length; k++) {
                    if(nums[i] < nums[k] && nums[k] < nums[j])return true;
                }
            }
        }

        return false;
    }

    public boolean find132pattern2(int[] nums) {
        if(nums == null || nums.length < 3)return false;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length -2; i++) {
                min = Math.min(min, nums[i]);
                for (int k = i +1; k < nums.length; k++) {
                    if(min < nums[k] && nums[k] < nums[i])return true;
                }
            }


        return false;
    }


    /**
     *
     * https://leetcode.com/problems/132-pattern/discuss/94071/Single-pass-C++-O(n)-space-and-time-solution-(8-lines)-with-detailed-explanation.
     *
     * @param nums
     * @return
     */
    public boolean find132pattern3(int[] nums) {
        if(nums == null || nums.length < 3)return false;
        int S3 = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length -1; i >=0 ; i--) {
            if(nums[i] < S3)return true;
            while (!stack.isEmpty() && nums[i] > stack.peekFirst())
            {
                S3 = stack.peekFirst(); // the maximum candidate for s3 is always the recently popped number from the stack,
                // because if we encounter any entry smaller than the current candidate,
                // the function would already have returned.
                stack.pollFirst();
            }
            stack.addFirst(nums[i]);
        }

        return false;
    }


    public static void main(String[] args) {
        Find132pattern find132pattern = new Find132pattern();
//        System.out.println(find132pattern.find132pattern(new int[]{1,2,3,4}));
//        System.out.println(find132pattern.find132pattern(new int[]{3,1,4,2}));
//        System.out.println(find132pattern.find132pattern(new int[]{-1,3,2,0}));
//
//        System.out.println(find132pattern.find132pattern2(new int[]{1,2,3,4}));
//        System.out.println(find132pattern.find132pattern2(new int[]{3,1,4,2}));
//        System.out.println(find132pattern.find132pattern2(new int[]{-1,3,2,0}));

       // System.out.println(find132pattern.find132pattern3(new int[]{1,2,3,4}));
//        System.out.println(find132pattern.find132pattern3(new int[]{3,1,4,2}));
//        System.out.println(find132pattern.find132pattern3(new int[]{-1,3,2,0}));
        System.out.println(find132pattern.find132pattern3(new int[]{9, 11, 8, 9, 10, 7, 9}));
    }
}
