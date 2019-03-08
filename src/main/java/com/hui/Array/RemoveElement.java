package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/14 22:35
 * remove element where a[i] == value, return the Array length
 * use slow and fast pointer
 */
public class RemoveElement {
    public static int removeElement(int[] a, int value)
    {
        int slow = 0;
        for (int fast = 0; fast < a.length; fast++) {
            if(a[fast] !=value)
            {
                a[slow] = a[fast];
                slow++;
            }

        }
        return slow;
    }

    public static int removeElement2(int[] nums, int val)
    {
        if(nums.length == 0)return 0;
        if(nums.length ==1 && nums[0] ==val)return 0;
        int lastIndex = nums.length -1;
        for (int i = 0; i <= lastIndex; i++) {
            if( nums[i] == val)
            {
                nums[i] = nums[lastIndex];
                lastIndex--;
                i--;
            }
        }

        return lastIndex +1;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,2,2,4,4};
        System.out.println(removeElement(a, 2));
        System.out.println(Arrays.toString(a));
        System.out.println(removeElement(a,2));
    }
}
