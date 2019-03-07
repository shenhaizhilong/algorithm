package com.hui.algorithm;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/23/
 *
 *旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 * @author: shenhaizhilong
 * @date: 2018/6/30 15:41
 */
public class RotateArray {

    //时间复杂度O(n), n is the length of nums，空间复杂度O(n)
    public int[] rotateRemain(int[] nums, int k)
    {
        k = k % nums.length;
        int[] results = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            results[(i + k)%nums.length] = nums[i];
        }
        return results;
    }
    // 时间复杂度O(k*n), n is the length of nums，空间复杂度O(1)
    public int[] rotate(int[] nums, int k) {

        k = k % nums.length;
        while (k-->0)
        {
            rotateOneStep(nums);
        }

        return nums;

    }

    // first reverse n - k elements, then reverse last k elements, then reverse all elements.
    public int[] rotateReverse(int[] nums, int k)
    {

        k = k % nums.length;  // 出去循环项 ,周期为nums.length
        if(k<=0)return nums;

        int n = nums.length - k;
        reverse(nums, 0, n -1);
        reverse(nums, n , nums.length -1);
        reverse(nums, 0, nums.length -1);
        return nums;

    }

    public void rotateOneStep(int[] nums)
    {
        for (int i = nums.length -1; i >0; i--) {
            swap(nums, i, i -1);
        }
    }
    public void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start, int end)
    {
        if(start <0 || start >=nums.length || end < 0 || end >=nums.length) return;
        while (start < end)
        {
            swap(nums, start, end);
            start++;
            end--;
        }
    }



    public static void main(String[] args) {

        int[] a ;
        int[] b ;
        int[] c ;
        RotateArray rotateArray = new RotateArray();
        for (int i = 0; i < 10; i++) {

            a = rotateArray.rotate(new int[]{1,2,3}, i);
            System.out.println("Method 1: k = " + i +": " + Arrays.toString(a));

            b = rotateArray.rotateRemain(new int[]{1,2,3}, i);
            System.out.println("Method 2: k = " + i +": " + Arrays.toString(b));
            c = rotateArray.rotateReverse(new int[]{1,2,3},i);
            System.out.println("Method 3: k = " + i +": " + Arrays.toString(c));
            System.out.println("***************************");
        }









    }
}
