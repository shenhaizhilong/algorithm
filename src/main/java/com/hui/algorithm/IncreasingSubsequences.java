package com.hui.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/13 21:58
 *
 *
 * 491. Increasing Subsequences
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .
 *
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 *
 *
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length <=1 )return lists;
        dfs(nums, 0, lists, new ArrayList<>(), new HashSet<>());
        return lists;
    }

    private void dfs(int[] nums, int startIdx, List<List<Integer>> lists, List<Integer> list, Set<List<Integer>> cache)
    {
        if(startIdx >= nums.length)return;
        for (int i = startIdx; i < nums.length; i++) {
            boolean isAdd = false;
            if(list.isEmpty() || nums[i] >= list.get(list.size() -1))
            {
                list.add(nums[i]);
                isAdd = true;
            }

            if(list.size() >1)
            {
                if(!cache.contains(list))
                {
                    List<Integer> temp = new ArrayList<>(list);
                    cache.add(temp);
                    lists.add(temp);
                }

            }
            dfs(nums, i +1, lists, list, cache);
            if(isAdd)
                list.remove(list.size() -1);

        }
    }



    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length <=1 )return lists;
        dfs2(nums, 0, lists, new ArrayList<>());
        return lists;
    }

    private void dfs2(int[] nums, int startIdx, List<List<Integer>> lists, List<Integer> list)
    {
        if(startIdx >= nums.length)return;
        int[] used = new int[201];  // it can be replaced with HashSet

        for (int i = startIdx; i < nums.length; i++) {
            if(used[nums[i] + 100] ==1)continue;
            if(list.isEmpty() || nums[i] >= list.get(list.size() -1))
            {
                list.add(nums[i]);
                // used只用于每一层递归，每次只添加一次，遇到重复的就自动跳过
                // 下一层会重新申请数组，所以并不会影响到下一层加新的重复的元素
                used[nums[i] + 100] = 1;
                if(list.size() >1)
                {
                        lists.add(new ArrayList<>(list));
                }
                dfs2(nums, i +1, lists, list);
                list.remove(list.size() -1);
            }
        }
    }


    public List<List<Integer>> findSubsequences3(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length <=1 )return lists;
        dfs3(nums, 0, lists, new ArrayList<>());
        return lists;
    }

    private void dfs3(int[] nums, int startIdx, List<List<Integer>> lists, List<Integer> list)
    {
        if(startIdx >= nums.length)return;
        Set<Integer> used = new HashSet<>();
        for (int i = startIdx; i < nums.length; i++) {
            if(used.contains(nums[i]))continue;
            if(list.isEmpty() || nums[i] >= list.get(list.size() -1))
            {
                list.add(nums[i]);
                // used只用于每一层递归，每次只添加一次，遇到重复的就自动跳过
                // 下一层会重新申请数组，所以并不会影响到下一层加新的重复的元素
                used.add(nums[i]);
                if(list.size() >1)
                {
                    lists.add(new ArrayList<>(list));
                }
                dfs3(nums, i +1, lists, list);
                list.remove(list.size() -1);
            }
        }
    }

    public static void test()
    {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(7);
        set.add(list);
        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(7);
        System.out.println(set.contains(list2));
        System.out.println(list.hashCode());
        System.out.println(list2.hashCode());
    }
    public static void main(String[] args) {

      //  test();
        IncreasingSubsequences increasingSubsequences = new IncreasingSubsequences();
        List<List<Integer>> lists = increasingSubsequences.findSubsequences2(new int[]{4,6,7,7});
                for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
        List<List<Integer>> lists2 = increasingSubsequences.findSubsequences(new int[]{84,-48,-33,-34,-52,72,75,-12,72,-45});
        System.out.println("*********************");

        for (int i = 0; i < lists2.size(); i++) {
            System.out.println(lists2.get(i));
        }
    }
}
