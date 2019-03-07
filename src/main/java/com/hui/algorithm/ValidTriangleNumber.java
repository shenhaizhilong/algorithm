package com.hui.algorithm;

import java.util.Arrays;

/**
 *611. Valid Triangle Number
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/8/24 10:55
 */
public class ValidTriangleNumber {


    //暴力解法
    public static int triangleNumber(int[] nums) {
        if(nums == null || nums.length <3)return 0;
        int count = 0;
        for (int i = 0; i < nums.length -2; i++) {
            for (int j = i+1; j < nums.length -1 ; j++) {
                for (int k = j +1; k < nums.length; k++) {
                    if(isTriangle(nums,i,j,k))
                    {
                        count++;
                       // System.out.println(nums[i] + ", " + nums[j] + ", " + nums[k]);
                    }

                }
            }

        }
        return count;
    }

    public static boolean isTriangle(int[] nums, int i, int j, int k)
    {
        int a = nums[i];
        int b = nums[j];
        int c = nums[k];
        if(a + b <= c)return false;
        if(a + c <= b)return false;
        if(b + c <= a)return false;

        return true;
    }
    public static int triangleNumber2(int[] nums) {

        if(nums == null || nums.length <3)return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length -2; i++) {
            int k = i +2;
            for (int j = i + 1; j < nums.length -1 && nums[i] != 0 ; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k])
                {
                    k++;
                }
                count += k - j -1;
            }

        }
        return count;
    }


    //先排序，从后往前
    public static int triangleNumber3(int[] nums) {

        if(nums == null || nums.length <3)return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length -1; i >=2 ; i--) {
            int k = 0;
            int j = i -1;
            while (k < j)
            {
                if(nums[k] != 0 && nums[k] + nums[j] > nums[i])
                {
                    count += j - k;
                    j--;
                }else {
                    k++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {

        int[] a = {2,2,2,2,2};
        System.out.println(triangleNumber3(a));
    }
}

