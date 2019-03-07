package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/15 0:34
 *
 *数组中重复的数字
 * NowCoder
 *
 * 题目描述
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 *
 * Input:
 * {2, 3, 1, 0, 2, 5}
 *
 * Output:
 * 2
 *
 * time:O(n)
 * space:O(1)
 *
 */
public class DuplicateNumberInArray {

    // put 0- n-1 elements to right  index i
    // {2, 3, 1, 0, 2, 5}
    // index 0:    {1,3,2,0,2,5}
    //             {3,1,2,0,2,5}
    //             {0,1,2,3,2,5};
    //index 1:     {0,1,2,3,2,5};
    // index 2:    {0,1,2,3,2,5};
    // index 3:    {0,1,2,3,2,5};
    // index 4:    {0,1,2,3,2,5};  // bingo 2 nums[4] == nums[nums[4]], already have nums[4]
    public boolean duplicate(int[] nums, int[] duplication) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i])
            {
                if(nums[i] == nums[nums[i]])
                {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    }
