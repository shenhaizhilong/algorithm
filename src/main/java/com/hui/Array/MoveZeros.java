package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/13 22:16
 */
public class MoveZeros {
    public static void moveZeros(int[] nums)
    {
        if(nums.length == 0)return;
        if(nums.length == 1)return;

        for (int i = 0; i < nums.length ; i++) {
            for (int j = i+1  ; j < nums.length; j++) {

                if(nums[i] == 0 && nums[j] !=0)
                {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
            }
        }
        
    }

    public static void moveZeros2(int[] nums)
    {
        if(nums.length == 0)return;
        if(nums.length == 1)return;

        int slow =0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] !=0)
            {
                nums[slow] = nums[fast];
                slow++;
            }

        }

        while (slow < nums.length)
        {
            nums[slow] = 0;
            slow++;
        }

    }


    public static void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {0,0,1,0,0,2,13,2,9};
        moveZeros2(a);
        System.out.println(Arrays.toString(a));
    }
}
