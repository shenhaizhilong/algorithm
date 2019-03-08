package com.hui.HashMap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/8/28 16:05
 */
public class NextGreaterElementI {

    /**
     *https://leetcode.com/problems/next-greater-element-i/description/
     *
     *496. Next Greater Element I
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
     *
     * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
     *
     * Example 1:
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * Output: [-1,3,-1]
     * Explanation:
     *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
     *     For number 1 in the first array, the next greater number for it in the second array is 3.
     *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
     * Example 2:
     * Input: nums1 = [2,4], nums2 = [1,2,3,4].
     * Output: [3,-1]
     * Explanation:
     *     For number 2 in the first array, the next greater number for it in the second array is 3.
     *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
     * Note:
     * All elements in nums1 and nums2 are unique.
     * The length of both nums1 and nums2 would not exceed 1000.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            boolean find = false;
            for (int j = i + 1; j <nums2.length ; j++) {
                if(nums2[j]> nums2[i])
                {
                    map.put(nums2[i], nums2[j]);
                    find = true;
                    break;
                }
            }
            if(!find){
                map.put(nums2[i], -1);
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }

        return nums1;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 4}, nums2 = {1,2,3,4};
        int[] nums3 = {4,1,2}, nums4 = {1,3,4,2};
        NextGreaterElementI nextGreaterElementI = new NextGreaterElementI();
        nums1 = nextGreaterElementI.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(nums1));
        nums3 = nextGreaterElementI.nextGreaterElement(nums3, nums4);
        System.out.println(Arrays.toString(nums3));

    }
}
