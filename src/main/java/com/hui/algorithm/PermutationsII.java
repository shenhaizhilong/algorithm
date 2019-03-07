package com.hui.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 *
 * @author: shenhaizhilong
 * @date: 2018/9/8 11:28
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backTracking(lists,new ArrayList<>(), nums,visited);
        return lists;
    }

    private void backTracking(List<List<Integer>> lists,List<Integer> tempList, int[] nums, boolean[] visited)
    {
        if(tempList.size() == nums.length)
        {
            lists.add(new ArrayList<>(tempList));
        }else {

            for (int i = 0; i < nums.length; i++) {

                if(visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i -1])continue;  // skip used and skip duplicates
                visited[i] = true;
                tempList.add(nums[i]);
                backTracking(lists,tempList,nums,visited);
                tempList.remove(tempList.size() -1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {

        PermutationsII permutations = new PermutationsII();
        List<List<Integer>> lists =  permutations.permuteUnique(new int[]{1,1,2});
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }
    }
}
