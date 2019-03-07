package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 77. Combinations
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * @author: shenhaizhilong
 * @date: 2018/9/9 11:24
 */
public class Combinations {



    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> lists = new ArrayList<>();
        backTracking(lists, new ArrayList<>(),1, n, k);
        return lists;

    }

    private void backTracking(List<List<Integer>> lists, List<Integer> tempList, int start,int end,  int k)
    {
        if(0 == k)
        {
            lists.add(new ArrayList<>(tempList));
            return;
        }else {

            // just a little change it will save a lot of time.
            //
            //i <= end -k + 1, don't use i <= end
            for (int i = start; i <= end -k + 1 ; i++) {
                tempList.add(i);
                backTracking(lists, tempList, i +1, end, k-1);
                tempList.remove(tempList.size() -1);
            }
        }

    }

    public static void main(String[] args) {


        Combinations  combinations = new Combinations();

        List<List<Integer>> lists =  combinations.combine(4,2);
        for(List<Integer> list : lists)
        {
            System.out.println(list);
        }
    }
}
