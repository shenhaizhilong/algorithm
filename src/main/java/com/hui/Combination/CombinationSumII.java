package com.hui.Combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 40. Combination Sum II
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * @author: shenhaizhilong
 * @date: 2018/9/9 13:43
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        backTracking(lists, new ArrayList<>(),candidates,0, target);
        return lists;
    }

    private void backTracking(List<List<Integer>> lists, List<Integer> tempList, int[] candidates, int start, int target)
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
                if(i > start && candidates[i] == candidates[i-1])continue;

                // the Array was sorted, so we don't need to consider the other elements which is bigger than target
                if (curr > target) {
                    break;
                }
                tempList.add(curr);
                backTracking(lists, tempList,candidates, i +1 , target - curr);  // we can't reuse candidates[i],  i + 1
                tempList.remove(tempList.size() -1);
            }
        }
    }

    public static void main(String[] args) {

        CombinationSumII  combinationSumII = new CombinationSumII();

        List<List<Integer>> lists =  combinationSumII.combinationSum2(new int[]{10,1,2,7,6,1,5},8);
        for(List<Integer> list : lists)
        {
            System.out.println(list);
        }

        List<List<Integer>> lists2 =  combinationSumII.combinationSum2(new int[]{2,5,2,1,2},5);
        for(List<Integer> list : lists2)
        {
            System.out.println(list);
        }
    }
}
