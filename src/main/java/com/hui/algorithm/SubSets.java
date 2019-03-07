package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *78. Subsets
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *
 * time O(2^n)
 * @author: shenhaizhilong
 * @date: 2018/8/15 9:25
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backTracking(lists,new ArrayList<>(), nums,0);
        return lists;


    }

    private void backTracking(List<List<Integer>> lists, List<Integer> list, int[] nums, int start)
    {
        lists.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backTracking(lists,list,nums,i + 1);
            list.remove(list.size() -1);
        }

    }




    public static void main(String[] args) {


        SubSets subSets = new SubSets();
        List<List<Integer>> lists = subSets.subsets(new int[]{1,2,3});
        for(List<Integer> list : lists)
        {
            System.out.println(list);
        }
    }
}
