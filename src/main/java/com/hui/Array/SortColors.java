package com.hui.Array;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 16:47
 */
public class SortColors {

    /**
     *75. Sort Colors
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     *
     * Example:
     *
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * Follow up:
     *
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
     * Could you come up with a one-pass algorithm using only constant space?
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {
        int[] counter = new int[3];
        for(int i: nums)
        {
            counter[i]++;
        }
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            while (counter[i] > 0)
            {
                nums[index++] = i;
                counter[i]--;
            }
        }

    }


    /**
     *
     * one pass
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int start = 0; int end = nums.length -1;
        int i = 0;
        while ( i <= end)
        {
            if(nums[i] == 0 && i != start )
            {
                swap(nums,i,start);
                start++;
                i--;
            }else if(nums[i] == 2 && i != end )
            {
                swap(nums,i,end);
                end--;
                i--;
            }
            i++;
        }


    }

    private static void swap(int[] array, int i, int j)
    {
        if(i != j)
        {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }


    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] colors = new int[]{2,0,2,1,1,0};
        sortColors.sortColors2(colors);
        System.out.println(Arrays.toString(colors));
    }
}
