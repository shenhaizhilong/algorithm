package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *39. Combination Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * @author: shenhaizhilong
 * @date: 2018/9/9 12:59
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        backTracking(lists, new ArrayList<>(),candidates,0, target);
        return lists;
    }

    private void backTracking(List<List<Integer>> lists, List<Integer> tempList,int[] candidates, int start, int target)
    {
         if(target < 0)
         {
             return;
         } else if (target == 0)
        {
            lists.add(new ArrayList<>(tempList));
        }else {
            for (int i = start; i < candidates.length ; i++) {
                int curr = candidates[i];

                // the Array was sorted, so we don't need to consider the other elements which is bigger than target
                if (curr > target) {
                    break;
                }
                tempList.add(curr);
                backTracking(lists, tempList,candidates, i , target - curr);  // we can reuse candidates[i], so it's not i + 1
                tempList.remove(tempList.size() -1);
            }
        }
    }


    public static void main(String[] args) {

        CombinationSum  combinations = new CombinationSum();

        List<List<Integer>> lists =  combinations.combinationSum(new int[]{1,2,3},4);
        for(List<Integer> list : lists)
        {
            System.out.println(list);
        }
    }
}
