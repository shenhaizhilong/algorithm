package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *377. Combination Sum IV
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 *
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 *
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 *
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/9 14:43
 */
public class CombinationSumIV {


    public int combinationSum4(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        int[] res = new int[1];
        backTracking(lists, new ArrayList<>(),nums,0, target, res);
        return res[0];
    }



    private void backTracking(List<List<Integer>> lists, List<Integer> tempList,int[] nums, int start, int target, int[] res)
    {
        if(target < 0)
        {
            return;
        } else if (target == 0)
        {
            res[0]++;
        }else {
            for (int i = start; i < nums.length ; i++) {
                // the Array was sorted, so we don't need to consider the other elements which is bigger than target
                if (nums[i] > target) {
                    break;
                }
                tempList.add(nums[i]);
                backTracking(lists, tempList,nums, 0 , target - nums[i], res);  // we can reuse candidates[i], so it's not i + 1
                tempList.remove(tempList.size() -1);
            }
        }
    }

    public int combinationSum4Recursive(int[] nums, int target) {
        if(target == 0)return 1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if(target >= nums[i])
            {
                res += combinationSum4Recursive(nums, target - nums[i]);
            }

        }
        return res;
    }

    /**
     *other people's solution
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     *Think about the recurrence relation first. How does the # of combinations of the target related to the # of combinations of numbers that are smaller than the target?
     *
     * So we know that target is the sum of numbers in the array. Imagine we only need one more number to reach target, this number can be any one in the array, right? So the # of combinations of target, comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
     *
     * In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3). As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
     *
     * Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.
     *
     * EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4DP(int[] nums, int target) {

      int[] dp = new int[target + 1];
      dp[0] = 1; //base case
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i >= nums[j])
                {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];

    }

    public static void main(String[] args) {
        CombinationSumIV combinationSumIV = new CombinationSumIV();

//        System.out.println(combinationSumIV.combinationSum4(new int[]{1,2,3}, 4));
//        System.out.println(combinationSumIV.combinationSum4Recursive(new int[]{1,2,3}, 4));
      //  System.out.println(combinationSumIV.combinationSum4DP(new int[]{1,2}, 4));
        System.out.println(combinationSumIV.combinationSum4DP(new int[]{3,2}, 5));

//        System.out.println(combinationSumIV.combinationSum4(new int[]{1,2}, 10));
//        System.out.println(combinationSumIV.combinationSum4Recursive(new int[]{1,2}, 10));
//        System.out.println(combinationSumIV.combinationSum4DP(new int[]{1,2}, 10));
    }
}
