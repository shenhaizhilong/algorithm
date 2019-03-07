package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/1 22:12
 */
public class MaximumProduct {
    public static int maximumProduct(int[] nums) {

      int firstMax =  findMax(nums,0, nums.length -1);
      int secondMax =  findMax(nums,1,nums.length -1);
      int thirdMax =   findMax(nums, 2, nums.length -1);
      int firstMin = findMin(nums, 0, nums.length -1);
      int secondMin = findMin(nums,1, nums.length -1);

//        System.out.println("fMax: " + firstMax);
//        System.out.println("sMax: " + secondMax);
//        System.out.println("tMax: " + thirdMax);
//        System.out.println("fMin: " + firstMin);
//        System.out.println("sMin: " + secondMin);
      int right = firstMax*secondMax*thirdMax;
      int left = firstMax*firstMin*secondMin;
      return right > left ? right : left;
    }
    public static void swap(int[] nums, int a, int b)
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static int findMax(int[] nums, int start, int end)
    {
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if(max < nums[i])
            {
                max = nums[i];
                swap(nums, start, i);

            }

        }
        return max;
    }

    public static int findMin(int[] nums, int start, int end)
    {
        int min = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if(min > nums[i])
            {
                min = nums[i];
                swap(nums, start, i);
            }

        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{1,2,3}));
        System.out.println(maximumProduct(new int[]{1,2,3,4}));
        System.out.println(maximumProduct(new int[]{-1,2,3,4}));
        System.out.println(maximumProduct(new int[]{0,800,90,200}));
        System.out.println(maximumProduct(new int[]{-100,-200,0,100,5,4}));
    }

}
