package com.hui.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 216. Combination Sum III
 * DescriptionHintsSubmissionsDiscussSolution
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * @author: shenhaizhilong
 * @date: 2018/9/9 14:04
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int target) {

        List<List<Integer>> lists = new ArrayList<>();
        backTracking(lists, new ArrayList<>(),1, target, k);
        return lists;
    }


    private void backTracking(List<List<Integer>> lists, List<Integer> tempList, int start, int target, int k)
    {
        if(k < 0)
        {
            return;
        } else if (k == 0 && target == 0)
        {
            lists.add(new ArrayList<>(tempList));
        }else {
            for (int i = start; i <=9 + k -1  ; i++) {
                //  so we don't need to consider the other elements which is bigger than target
                if (i > target) {
                    break;
                }
                tempList.add(i);
                backTracking(lists, tempList, i +1 , target - i, k -1);  //
                tempList.remove(tempList.size() -1);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumIII combinationSumIII = new CombinationSumIII();
        List<List<Integer>> lists = combinationSumIII.combinationSum3(3,9);
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }
    }
}
