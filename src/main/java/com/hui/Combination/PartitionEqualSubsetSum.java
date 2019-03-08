package com.hui.Combination;

import java.util.Arrays;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/7 16:01
 *
 *416. Partition Equal Subset Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 *
 * _________________________
 *
 * subset A, subset B
 *  SumA + SumB = totalSum
 *  SumA = SumB, then 2*SumA = totalSum;
 *  so total Sum is even.
 *
 *  totalSum - nums[last] < nums[last], return false.
 *
 *  since nums[0] + nums[1] + ... nums[last -1] < nums[last]
 *
 */
public class PartitionEqualSubsetSum {


    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int totalSum = 0;
        for(int i: nums) totalSum += i; // sum[A]
        int N = nums.length;  // total length
        if((totalSum & 1) == 1)return false;
        int target = totalSum/2;
        Arrays.sort(nums);
        if(totalSum - nums[N -1] < nums[N -1])return false;
        return dfs(nums, 0, target);
        // or use.
       // return totalSum - target == target && combinationSum(nums, 0, target);
    }

    public boolean combinationSum(int[] candidates,int idx, int target) {
        if(target == 0)return true;
        if(idx >= candidates.length)return false;
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1])   // chose previous element can't find the target, so the current  element also fail
                continue;
            if(candidates[i] > target)return false;
            if(combinationSum(candidates, i+1,  target - candidates[i])){
                return true;
            }
        }
        return false;

    }


    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int totalSum = 0;
        int max = 0;
        for(int i: nums)
        {
            totalSum += i; // sum[A]
            max = Math.max(max, i);
        }
        if((totalSum & 1) == 1)return false;
        int target = totalSum/2;

        if(totalSum - max < max)return false;

        return isSubsetSum(nums, target);
        // or use.
        // return combinationSum(nums, 0, target);
    }


    /**
     *
     * whether sub set of nums can combination s, when nums[i] >= 0
     *
     * @param nums
     * @param s
     * @return
     */
    public boolean isSubsetSum(int[] nums, int s)
    {
        boolean[] dp = new boolean[s +1];
        dp[0] = true;
        for(int n: nums)
        {
            for (int i = s; i >= n; i--)
            {
                dp[i] = dp[i] || dp[i -n];
            }
        }
        //Matrix.print(dp);
        return dp[s];
    }

    public boolean dfs(int[] candidates, int idx, int target)
    {
        if(idx >= candidates.length)return false;
        if(target == candidates[idx])return true;
        if(target < candidates[idx])return false;
        return dfs(candidates, idx + 1, target - candidates[idx]) || dfs(candidates, idx + 1, target);
    }



    public static void main(String[] args) {

        PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
//        System.out.println(partitionEqualSubsetSum.canPartition(new int[]{1,5,11,5}));
//        System.out.println(partitionEqualSubsetSum.canPartition(new int[]{1,2,3,5}));
//
//        System.out.println(partitionEqualSubsetSum.canPartition2(new int[]{1,5,11,5}));
//        System.out.println(partitionEqualSubsetSum.canPartition2(new int[]{1,2,3,5}));

       // System.out.println(partitionEqualSubsetSum.canPartition2(new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100}));
        System.out.println(partitionEqualSubsetSum.canPartition2(new int[]{66,90,7,6,32,16,2,78,69,88,85,26,3,9,58,65,30,96,11,31,99,49,63,83,79,97,20,64,81,80,25,69,9,75,23,70,26,71,25,54,1,40,41,82,32,10,26,33,50,71,5,91,59,96,9,15,46,70,26,32,49,35,80,21,34,95,51,66,17,71,28,88,46,21,31,71,42,2,98,96,40,65,92,43,68,14,98,38,13,77,14,13,60,79,52,46,9,13,25,8}));
    }

}
