package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/15 14:47
 *
 *
 * 18. 4Sum
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 *
 */
public class Sum4 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length < 4)return lists;
        Arrays.sort(nums);
        dfs(nums, 0, lists, new ArrayList<>(), target);
        return lists;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> lists ,List<Integer> list, int target)
    {

        if(list.size() == 4 )
        {
            if(target == 0)
                lists.add(new ArrayList<>(list));
            return;
        }
        if(idx >= nums.length)return;
        int sign = Integer.signum(target);
        if(sign >= 0 && target < nums[idx])return;
        if(sign < 0 && nums[idx] >= 0)return;
        Set<Integer> used = new HashSet<>();
        int size = 4 - list.size();
        if(size*nums[idx] > target)return;
        if(size*nums[nums.length -1] < target)return;
        for (int i = idx; i < nums.length; i++) {
            if(used.contains(nums[i]))continue;
            if(sign >= 0 && nums[i] > target)break;
            list.add(nums[i]);
            used.add(nums[i]);
            dfs(nums, i +1, lists, list, target - nums[i]);
            list.remove(list.size() -1);
        }
    }



    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums == null || nums.length < 4)return lists;
        Arrays.sort(nums);
        dfs2(nums, 0,nums.length -1,4, lists, new ArrayList<>(), target);
        return lists;
    }

    private void dfs2(int[] nums, int start, int end, int k,  List<List<Integer>> lists ,List<Integer> list, int target)
    {
        // 0 sum
        if(k == 0 || end < start)return;

        // 1 sum
        if(k == 1)
        {
            for (int i = start; i <= end; i++) {
                if(nums[i] == target)
                {
                    list.add(nums[i]);
                    lists.add(new ArrayList<>(list));
                    list.remove(list.size() -1);
                    break;
                }
            }
            return;
        }

        // 2 sum
        if(k == 2)
        {
           Set<Integer> set = new HashSet<>();
           Set<Integer> used = new HashSet<>();
           set.add(nums[start]);
            for (int i = start +1; i <= end; i++) {
                if(set.contains(target - nums[i]) && !used.contains(nums[i]))
                {
                    used.add(nums[i]);
                    used.add(target - nums[i]);
                    list.add(nums[i]);
                    list.add(target - nums[i]);
                    lists.add(new ArrayList<>(list));
                    list.remove(list.size() -1);
                    list.remove(list.size() -1);
                }
                set.add(nums[i]);
            }
            return;
        }

        // remove some unused case.
        if(k*nums[start] > target || k*nums[end] < target)return;

        // k > 2 , chose k elements from nums[start,end]
        for (int i = start; i <= end - k +1; i++) {
            // remove duplicate
            if(i > start && nums[i] == nums[i -1])continue;
            if(k*nums[i] <= target)
            {
                list.add(nums[i]);
                dfs2(nums, i +1,end, k -1, lists, list, target - nums[i]);
                list.remove(list.size() -1);
            }
        }
    }


    public static void print(List<List<Integer>> lists)
    {
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }
    public static void main(String[] args) {
        Sum4 sum4 = new Sum4();
        List<List<Integer>> lists = new ArrayList<>();
       lists =  sum4.fourSum(new int[]{1,0,-1,0,-2,2}, 0);

       print(lists);

        lists = sum4.fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);
        print(lists);

        System.out.println("********************");
        lists = sum4.fourSum2(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);
        print(lists);

//        int[] nums = {-497,-480,-477,-470,-452,-448,-440,-412,-390,-381,-372,-372,-369,-366,-355,-346,-340,-337,-322,-321,-311,-296,-258,-249,-248,-232,-215,-199,-174,-154,-128,-122,-122,-117,-115,-113,-110,-89,-86,-84,-78,-71,-69,-53,-49,-35,-25,-21,-7,3,7,21,25,30,47,52,81,84,87,91,96,157,161,167,178,184,210,217,228,236,274,277,283,286,290,301,302,341,352,354,361,367,384,390,412,421,458,468,483,484,486,487,490,491};
//        lists = sum4.fourSum2(nums, 8377);
//        print(lists);
    }

}
