package com.hui.TwoPointer;

import java.util.Arrays;

/**
 *https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/8/sorting-and-searching/52/
 * 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * @author: shenhaizhilong
 * @date: 2018/8/14 9:36
 */
public class MergeTwoSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tempArray = Arrays.copyOf(nums1,m);
            int leftIndex = 0;
            int rightIndex = 0;
            int position = 0;

            while (leftIndex < m && rightIndex < n)
            {
                if(tempArray[leftIndex] < nums2[rightIndex])
                {
                    nums1[position++] = tempArray[leftIndex++];
                }else {
                    nums1[position++] = nums2[rightIndex++];
                }
            }

            while (leftIndex < m)
            {
                nums1[position++] = tempArray[leftIndex++];
            }

            while (rightIndex < n)
            {
                nums1[position++] = nums2[rightIndex++];
            }

    }

    public static void main(String[] args) {
       int[]  nums1 = {1,2,3,0,0,0};
       int[] nums2 = {2,5,6};
       merge(nums1,3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

}
