package com.hui.Combination;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 17:07
 *
 *
 */
public class PartitiontoKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int totalSum = 0;
        for(int i: nums) totalSum += i; // sum[A]
        int N = nums.length;  // total length
        if(totalSum  % k != 0)return false;
        int target = totalSum/k;
        Arrays.sort(nums);
        if (nums[nums.length - 1] > target) {
            return false;
        }
        // same with 416. Partition Equal Subset Sum, but every elements just used once.
        boolean[] used = new boolean[N];
        while (k > 0 && combinationSum(nums, N -1, target, used)){
            k--;
        }
        return k == 0;
    }


    public boolean combinationSum(int[] candidates,int idx, int target, boolean[] used) {
        if(target == 0)return true;
        if(idx < 0)return false;
        if(target < candidates[0])return false; // no more small elements than target.
        for (int i = idx; i >=0 ; i--) {
            if ( used[i] || candidates[i] > target)  //used this element or this element is bigger than target just continue.
                continue;
            used[i] = true;
            if(combinationSum(candidates, i-1,  target - candidates[i], used)){
                return true;
            }
            used[i] = false;
        }
        return false;

    }

    public static void main(String[] args) {

        PartitiontoKEqualSumSubsets partitiontoKEqualSumSubsets = new PartitiontoKEqualSumSubsets();
        System.out.println(partitiontoKEqualSumSubsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
