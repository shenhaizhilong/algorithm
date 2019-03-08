package com.hui.BackTracking;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 * 46. Permutations
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * @author: shenhaizhilong
 * @date: 2018/9/8 10:17
 */
public class Permutations {


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        backTrack(lists,new ArrayList<>(), nums, set);

        return lists;
    }

    private void backTrack(List<List<Integer>> lists, List<Integer> tempList, int[] nums, Set<Integer> set)
    {
        if(tempList.size() == nums.length)
        {
            lists.add(new ArrayList<>(tempList));
        }else {
            for (int i = 0; i < nums.length; i++) {
                if(set.contains(nums[i]))continue;  //already visited nums[i]
                set.add(nums[i]);
                tempList.add(nums[i]);
                backTrack(lists,tempList,nums, set);
                set.remove(tempList.get(tempList.size() -1));
                tempList.remove(tempList.size() -1);
            }
        }
    }



    public static void main(String[] args) {

        Permutations permutations = new Permutations();
       List<List<Integer>> lists =  permutations.permute(new int[]{1,2,3,4});
        for(List<Integer> list: lists)
        {
            System.out.println(list);
        }




    }
}
