package com.hui.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/8 20:19
 */
public class SubsetsII {


    /**
     *90. Subsets II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: [1,2,2]
     * Output:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backTracking(lists,new ArrayList<>(), nums, 0, visited);
        return lists;
    }

    private void backTracking(List<List<Integer>> lists, List<Integer> list, int[] nums, int start, boolean[] visited)
    {
        lists.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if( i > 0 && nums[i] == nums[i-1] &&  !visited[i-1])continue; // skip duplicate
            list.add(nums[i]);
            visited[i] = true;
            backTracking(lists,list,nums, i + 1, visited);
            list.remove(list.size() -1);
            visited[i] = false;
        }
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {


        SubsetsII subSets = new SubsetsII();
        List<List<Integer>> lists = subSets.subsetsWithDup(new int[]{1,2,2});
        for(List<Integer> list : lists)
        {
            System.out.println(list);
        }
    }


}
