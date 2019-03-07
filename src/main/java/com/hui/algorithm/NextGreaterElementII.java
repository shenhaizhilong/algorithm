package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 16:20
 */
public class NextGreaterElementII {

    /**
     * https://leetcode.com/problems/next-greater-element-ii/description/
     *503. Next Greater Element II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
     *
     * Example 1:
     * Input: [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number;
     * The second 1's next greater number needs to search circularly, which is also 2.
     * Note: The length of given array won't exceed 10000.
     *
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElements(int[] nums2) {
        int[] nums1 = new int[nums2.length];
        List<int[]> list = new ArrayList<>(nums2.length);
        HashMap<int[], Integer> map = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            boolean find = false;
            int[] v =  new int[]{nums2[i],i};
            list.add(v);
            for (int j = i + 1; j <nums2.length ; j++) {
                if(nums2[j]> nums2[i])
                {
                    map.put(v, nums2[j]);
                    find = true;
                    break;
                }
            }
            if(!find)
            {
                for (int j = 0; j <i ; j++) {
                    if(nums2[j]> nums2[i])
                    {
                        map.put(v, nums2[j]);
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(list.get(i), -1);
        }

        return nums1;
    }


    public static int[] nextGreaterElements2(int[] nums) {
        if (nums == null) {
            return new int[0];
        }

        int[] array = new int[nums.length * 2];
        int[] nextGreater = new int[nums.length * 2];
        int[] ret = new int[nums.length];
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.fill(nextGreater, Integer.MIN_VALUE);
        for (int i = 0; i < nums.length; i++) {
            array[i] = nums[i];
            array[i + nums.length] = nums[i];
        }

        for (int i = 0; i < array.length; i++) {
            while (!deque.isEmpty() && array[i] > array[deque.peekFirst()]) {
                nextGreater[deque.pollFirst()] = array[i];
            }
            deque.offerFirst(i);
        }

        for (int i = 0; i < ret.length; i++) {
                ret[i] =  nextGreater[i] == Integer.MIN_VALUE ? -1 : nextGreater[i];
        }
        return ret;
    }

    public static void main(String[] args) {

        int[] nums1 = {100,1,11,1,120,111,123,1,-1,-100};
        int[] nums3 = {4,1,2};
        nums1 = nextGreaterElements(nums1);
        System.out.println(Arrays.toString(nums1));
        nums3 = nextGreaterElements(nums3);
        System.out.println(Arrays.toString(nums3));

    }
}
