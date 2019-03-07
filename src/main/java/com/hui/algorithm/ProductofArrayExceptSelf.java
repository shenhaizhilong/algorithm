package com.hui.algorithm;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/3 20:38
 */
public class ProductofArrayExceptSelf {


    /**
     *
     * 238. Product of Array Except Self
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Note: Please solve it without division and in O(n).
     *
     * Follow up:
     * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] res = new int[nums.length];
        left[0] = 1;
        right[nums.length -1] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = nums[i  -1]*left[i -1];
        }

        for (int i = nums.length -2; i >=0 ; i--) {
            right[i] = right[i + 1]*nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = left[i]*right[i];
        }
       return res;
    }

  public int[]  productExceptSelf2(int nums[])
    {


        int prod[] = new int[nums.length];
        prod[0] = 1;
        for (int i = 1; i < nums.length ; i++) {
            prod[i] = prod[i -1]*nums[i-1];
        }
        for (int i = nums.length -1;i >0; i--) {
            prod[i] *= prod[0];
            prod[0] *= nums[i];
        }

        return prod;
    }


    public static void main(String[] args) {
        ProductofArrayExceptSelf productofArrayExceptSelf = new ProductofArrayExceptSelf();
        int[] nums = {1,2,3,4,5};
        System.out.println(Arrays.toString(productofArrayExceptSelf.productExceptSelf2(nums)));
    }
}
