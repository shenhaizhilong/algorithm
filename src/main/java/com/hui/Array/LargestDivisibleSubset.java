package com.hui.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/6 17:24
 *
 * 368. Largest Divisible Subset
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 *
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 *
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums == null)return new ArrayList<>();
        int N = nums.length;
        Arrays.sort(nums);
        int[] count = new int[N];  // 到nums[i]为止子数组内元素都满足 nums[i] % nums[j] == 0， j >=0 && j < i 的最大长度
        int[] preIndex = new int[N]; // if nums[i] % nums[j] == 0， j >=0 && j < i; then preindex[i] = j
        int index = -1; // 子数组内元素都可以整除的最后一个元素的所引
        int max = 0;
        for (int i = 0; i < N; i++) {
            count[i] = 1;
            preIndex[i] = -1;
            for (int j = i -1; j >= 0 ; j--) {
                if(nums[i] % nums[j] == 0 && count[j] + 1 > count[i])
                {
                    count[i] = count[j] + 1;
                    preIndex[i] = j;
                }
            }
            if(count[i] > max)
            {
                max = count[i];
                index = i;
            }

        }

        List<Integer> res = new ArrayList<>();
        while (index != -1)
        {
            res.add(nums[index]);
            index = preIndex[index];
        }

        return res;
    }

    public static void main(String[] args) {

        LargestDivisibleSubset divisibleSubset = new LargestDivisibleSubset();
        System.out.println(divisibleSubset.largestDivisibleSubset( new int[]{1,2,3,4,5,6,8}));
    }
}
